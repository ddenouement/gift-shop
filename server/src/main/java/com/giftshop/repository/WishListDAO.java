package com.giftshop.repository;

import com.giftshop.models.Product;
import com.giftshop.repository.interfaces.IWishListDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@PropertySource("classpath:sql/wishlist_queries.properties")
@PropertySource("classpath:sql/product_queries.properties")
public class WishListDAO implements IWishListDAO {

    @Autowired
    private NamedParameterJdbcTemplate template;
    @Value("${get_all_wished_products}")
    private String getAllWishedProducts;
    @Value("${add_product_to_users_wish_list}")
    private String addProductToUsersWishList;
    @Value("${remove_product_from_users_wish_list}")
    private String removeProductFromUsersWishList;
    @Value("${get_categories}")
    private String getCategories;


    public ArrayList<Integer> getCategories(Integer productId) {
        SqlParameterSource param = new MapSqlParameterSource(
                "id_param", productId);
        System.out.println(param);
        List<Integer> categoriesIds =
                template.query(getCategories, param,
                        (resultSet, i) -> resultSet.getInt("category_id"));
        return (ArrayList<Integer>) categoriesIds;
    }

    private Product toProduct(final ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setProductId(resultSet.getInt("uid"));
        product.setProductName(resultSet.getString("product_name"));
        product.setDescription(resultSet.getString("product_description"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setIsAvailable(resultSet.getBoolean("is_available"));
        product.setPhoto(resultSet.getString("photo"));
        ArrayList<Integer> categories = new ArrayList<>();

        categories.addAll(getCategories(product.getProductId()));
        product.setCategories(categories);

        return product;
    }

    @Override
    public ArrayList<Product> getAllWishedProducts(String userId) {
        SqlParameterSource param = new MapSqlParameterSource(
                "id_param", userId);
        List<Product> products =
                template.query(getAllWishedProducts, param,
                        (resultSet, i) -> toProduct(resultSet));
        return (ArrayList<Product>) products;
    }

    @Override
    public void addProductToUsersWishList(String productId, String userId) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("id_param1", productId);
        paramMap.addValue("id_param2", userId);
        template.update(addProductToUsersWishList, paramMap);
    }

    public boolean removeProductFromUsersWishList(String productId, String userId) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("id_param1", productId);
        paramMap.addValue("id_param2", userId);
        return 1 == template.update(removeProductFromUsersWishList, paramMap);
    }

}