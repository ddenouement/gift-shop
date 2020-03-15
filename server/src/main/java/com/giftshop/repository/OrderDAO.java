package com.giftshop.repository;

import com.giftshop.dto.OrderDTO;
import com.giftshop.models.*;
import com.giftshop.repository.interfaces.IOrderDAO;
import com.giftshop.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@PropertySource("classpath:sql/order_queries.properties")
public class OrderDAO implements IOrderDAO {
    @Autowired
    private NamedParameterJdbcTemplate template;
    @Value("${get_order_by_id}")
    private String getById;
    @Value("${get_all_orders}")
    private String getAll;
    @Value("${insert_order}")
    private String insertOrder;
    @Value("${update_order}")
    private String updateOrder;
    @Value("${find_state_of_order}")
    String findStateOfOrder;
    @Value("${insert_order_item}")
    private String insertOrderItem;
    @Value("${find_order_items}")
    private String getOrderItems;

    @Autowired
    private IProductService productService;

    @Override
    public Order getById(Integer orderId) {
        SqlParameterSource param = new MapSqlParameterSource(
                "id_param", orderId);
        List<Order> orders =
                template.query(getById, param,
                        (resultSet, i) -> toOrder(resultSet));
        if (orders.size() == 0) {
            return null;
        } else {
            return orders.get(0);
        }
    }

    @Override
    public ArrayList<OrderDTO> getAll(){
        List<OrderDTO> orders =
                template.query(getAll, ((resultSet, i) -> toOrderDto(resultSet)));
        return  (ArrayList<OrderDTO>) orders;
    }

    private OrderDTO toOrderDto(ResultSet resultSet) throws SQLException{
        OrderDTO order = new OrderDTO();
        order.setOrderId(resultSet.getInt("uid"));
        order.setAddress(resultSet.getString("address"));
        order.setCashPayment(resultSet.getBoolean("cash_payment"));
        order.setPostDelivery(resultSet.getBoolean("post_delivery"));
        order.setOrderDate(resultSet.getTimestamp("order_date").toLocalDateTime());
        order.setTotalSum(resultSet.getBigDecimal("total_sum").toBigInteger());
        order.setUserId(resultSet.getInt("user_id"));
        int stateId = resultSet.getInt("state_id");
        order.setOrderStateId(stateId);
        return order;
    }

    @Override
    public Integer insertOrder(OrderDTO order){
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("address", order.getAddress())
                .addValue("cash_payment", order.getCashPayment())
                .addValue("post_delivery", order.getPostDelivery())
                .addValue("order_date", order.getOrderDate())
                .addValue("total_sum", order.getTotalSum())
                .addValue("user_id", order.getUserId())
                .addValue("state_id", order.getOrderState());
        template.update(insertOrder, param, holder, new String[]{"uid"});
        if (holder.getKeys() != null) {
            order.setOrderId(Objects.requireNonNull(holder.getKey()).intValue());
        }
        for (ProductIdQuantityPair entry : order.getOrderItems()){
            Product p =  productService.getById(entry.getProductId());
            if(p!=null)
              saveOrderItem(entry.getProductId(),entry.getQuantity(),order.getOrderId(), p.getPrice());
        }
        return order.getOrderId();
    }


    private void saveOrderItem(Integer product_id, Integer quantity, Integer orderId, Integer saved_price) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("quantity_param", quantity)
                .addValue("product_id_param", product_id)
                .addValue("order_id_param", orderId)
           .addValue("saved_price_param", saved_price);
        template.update(insertOrderItem, param);
    }

    private void saveOrderItem(OrderItem orderItem, Integer orderId) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("quantity_param", orderItem.getQuantity())
                .addValue("product_id_param", orderItem.getProduct().getProductId())
                .addValue("order_id_param", orderId)
                .addValue("saved_price_param", orderItem.getSavedPrice());
        template.update(insertOrderItem, param);
    }

    @Override
    public void updateOrder(OrderDTO order) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("uid", order.getOrderId())
                .addValue("address", order.getAddress())
                .addValue("cash_payment", order.getCashPayment())
                .addValue("post_delivery", order.getPostDelivery())
                .addValue("order_date", order.getOrderDate())
                .addValue("total_sum", order.getTotalSum())
                .addValue("user_id", order.getUserId())
                .addValue("state_id", order.getOrderState());
        int status = template.update(updateOrder, param);
        if(status != 0){
            System.out.println("order data updated");
        }else{
            System.out.println("order data update failed");
        }

    }

    @Override
    public Integer getSum(ProductIdQuantityPair[] order) {
        int result = 0;
        //gets current price instead of saved, because it must be used to check Cart, not order
        for( ProductIdQuantityPair pair: order){
            Product p = productService.getById(pair.getProductId());
            if(p.getPrice()!=null)
            result+=p.getPrice()*pair.getQuantity();
        }
        return result;
    }

    @Override
    public ArrayList<OrderItem> getOrderProductsById(Integer orderId) {
        return getOrderItems(orderId);
    }

    private OrderState findStateById(int stateId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id_param", stateId);
        String stateName = template.queryForObject(findStateOfOrder, namedParameters, String.class);
        return new OrderState(stateId, stateName);
    }

    private Order toOrder(final ResultSet resultSet) throws SQLException{
        Order order = new Order();
        order.setOrderId(resultSet.getInt("uid"));
        order.setAddress(resultSet.getString("address"));
        order.setCashPayment(resultSet.getBoolean("cash_payment"));
        order.setPostDelivery(resultSet.getBoolean("post_delivery"));
        order.setOrderDate(resultSet.getTimestamp("order_date").toLocalDateTime());
        order.setTotalSum(resultSet.getBigDecimal("total_sum").toBigInteger());
        order.setUser(resultSet.getInt("user_id"));
        int stateId = resultSet.getInt("state_id");
        order.setOrderState(findStateById(stateId));
  //      order.setOrderItems(getOrderItems(order.getOrderId()));
        return order;
    }

    private ArrayList<OrderItem> getOrderItems(Integer orderId) {

        ArrayList<OrderItem> mapRet= new ArrayList<>();
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("order_id_param", orderId);
        template.query(getOrderItems,param, (ResultSetExtractor<ArrayList>) rs -> {
            while(rs.next()){
                Product product = productService.getById(rs.getInt("product_id"));
                //product.setPrice(rs.getInt("saved_price"));
                mapRet.add(new OrderItem(product,rs.getInt("quantity"),rs.getInt("saved_price")));
            }
            return mapRet;
        });
        return mapRet;
    }


}
