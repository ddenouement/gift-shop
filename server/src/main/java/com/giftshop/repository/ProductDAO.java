package com.giftshop.repository;

import com.giftshop.models.Product;
import com.giftshop.repository.interfaces.IProductDAO;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@PropertySource("classpath:sql/product_queries.properties")
public class ProductDAO implements IProductDAO {

    @Autowired
    private NamedParameterJdbcTemplate template;
    @Value("${get_by_id}")
    private String getById;
    @Value("${get_all}")
    private String getAll;
    @Value("${get_by_category}")
    private String getByCategory;
    @Value("${get_by_categories}")
    private String getByCategories;

    @Value("${insert_product}")
    private String insertProduct;
    @Value("${update_product}")
    private String updateProduct;

    @Value("${get_categories}")
    private String getCategories;

    @Override
    public Product getById(Integer productId) {
        SqlParameterSource param = new MapSqlParameterSource(
                "id_param", productId);
        List<Product> products =
                template.query(getById, param,
                        (resultSet, i) -> toProduct(resultSet));
        if (products.size() == 0) {
            return null;
        } else {
            return products.get(0);
        }
    }

    @Override
    public ArrayList<Product> getAll() {
        List<Product> products =
                template.query(getAll,
                        (resultSet, i) -> toProduct(resultSet));
        return (ArrayList<Product>) products;
    }

    @Override
    public ArrayList<Product> getByCategory(Integer categoryId) {
        SqlParameterSource param = new MapSqlParameterSource(
                "id_param", categoryId);
        List<Product> products =
                template.query(getByCategory, param,
                        (resultSet, i) -> toProduct(resultSet));
        return (ArrayList<Product>) products;
    }

    @Override
    public ArrayList<Product> getByCategories(ArrayList<Integer> categories) {
//        List<Integer> list = Arrays.asList(categories);

        SqlParameterSource param = new MapSqlParameterSource(
                "id_params", categories);
        List<Product> products =
                template.query(getByCategories, param,
                        (resultSet, i) -> toProduct(resultSet));
        products = products.stream().distinct().collect(Collectors.toList());
        return (ArrayList<Product>) products;
    }

    @Override
    public Integer insertProduct(Product product) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("product_name", product.getProductName())
                .addValue("product_description", product.getProductDescripton())
                .addValue("price", product.getProductPrice())
                .addValue("photo",product.getPhoto())
                .addValue("is_available",product.isAvailable());
        return template.update(insertProduct,param);
    }

    @Override
    public void updateProduct(Product product) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("uid",product.getProductId())
                .addValue("product_name", product.getProductName())
                .addValue("product_description", product.getProductDescripton())
                .addValue("price", product.getProductPrice())
                .addValue("photo",product.getPhoto())
                .addValue("is_available",product.isAvailable());
        int status = template.update(updateProduct, param);
        if(status != 0){
            System.out.println("Product data updated for ID " + product.getProductId());
        }else{
            System.out.println("No Product found with ID " + product.getProductId());
        }
    }

    public ArrayList<Integer> getCategories(Integer productId){
        SqlParameterSource param = new MapSqlParameterSource(
                "id_param", productId);
        List<Integer> categoriesIds =
                template.query(getCategories, param,
                        (resultSet, i) -> resultSet.getInt("category_id"));
        return (ArrayList<Integer>) categoriesIds;
    }

    private Product toProduct(final ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setProductId(resultSet.getInt("uid"));
        product.setProductName(resultSet.getString("product_name"));
        product.setProductDescripton(resultSet.getString("product_description"));
        product.setProductPrice(resultSet.getBigDecimal("price"));
        product.setAvailable(resultSet.getBoolean("is_available"));
        product.setPhoto(resultSet.getString("photo"));
        ArrayList<Integer> categories = new ArrayList<>();

        categories.addAll(getCategories(product.getProductId()));
        product.setCategories(categories);

        return product;
    }
}
