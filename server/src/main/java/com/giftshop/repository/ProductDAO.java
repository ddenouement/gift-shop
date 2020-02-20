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

    @Value("${get_from_to}")
    private String getFromTo;
    @Value("${get_by_category_from_to}")
    private String getByCategoryFromTo;
    @Value("${get_by_categories_from_to}")
    private String getByCategoriesFromTo;

    @Value("${insert_product}")
    private String insertProduct;
    @Value("${update_product}")
    private String updateProduct;

    @Value("${get_categories_for_product}")
    private String getCategoriesForProduct;
    @Value("${add_category_for_product}")
    private String addCategoryForProduct;
    @Value("${delete_categories_for_product}")
    private String deleteCategoriesForProduct;

    @Value("${get_amount}")
    private String getAmount;

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
        SqlParameterSource param = new MapSqlParameterSource(
                "id_params", categories);
        List<Product> products =
                template.query(getByCategories, param,
                        (resultSet, i) -> toProduct(resultSet));
        products = products.stream().distinct().collect(Collectors.toList());
        return (ArrayList<Product>) products;
    }

    @Override
    public ArrayList<Product> getFromTo(Integer startRow, Integer endRow) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("from", startRow)
                .addValue("to", endRow);
        List<Product> products =
                template.query(getFromTo, param,
                                (resultSet, i) -> toProduct(resultSet));
        return (ArrayList<Product>) products;
    }

    @Override
    public ArrayList<Product> getByCategoryFromTo(Integer categoryId, Integer startRow, Integer endRow) {
        SqlParameterSource param = new MapSqlParameterSource(
                "id_param", categoryId)
                .addValue("from", startRow)
                .addValue("to", endRow);
        List<Product> products =
                template.query(getByCategory, param,
                        (resultSet, i) -> toProduct(resultSet));
        return (ArrayList<Product>) products;
    }

    @Override
    public ArrayList<Product> getByCategoriesFromTo(ArrayList<Integer> categories, Integer startRow, Integer endRow) {
        SqlParameterSource param = new MapSqlParameterSource(
                "id_params", categories)
                .addValue("from", startRow)
                .addValue("to", endRow);
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
                .addValue("product_description", product.getDescription())
                .addValue("price", product.getPrice())
                .addValue("photo",product.getPhoto())
                .addValue("is_available",product.getIsAvailable());
        return template.update(insertProduct,param);
    }

    @Override
    public void updateProduct(Product product) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("uid",product.getProductId())
                .addValue("product_name", product.getProductName())
                .addValue("product_description", product.getDescription())
                .addValue("price", product.getPrice())
                .addValue("photo",product.getPhoto())
                .addValue("is_available",product.getIsAvailable());
        int status = template.update(updateProduct, param);
        if(status != 0){
            System.out.println("Product data updated for ID " + product.getProductId());
            updateCategoriesForProduct(product.getProductId(), product.getCategories());
        }else{
            System.out.println("No Product found with ID " + product.getProductId());
        }
    }

    @Override
    public Integer getAmount() {
        List<Integer> data = template.query(getAmount,
                        (resultSet, i) -> resultSet.getInt("count"));
        if (data.size() == 0) {
            return null;
        } else {
            return data.get(0);
        }
    }

    private ArrayList<Integer> getCategoriesForProduct(Integer productId){
        SqlParameterSource param = new MapSqlParameterSource(
                "id_param", productId);
        List<Integer> categoriesIds =
                template.query(getCategoriesForProduct, param,
                        (resultSet, i) -> resultSet.getInt("category_id"));
        return (ArrayList<Integer>) categoriesIds;
    }

    private void updateCategoriesForProduct(Integer productId, ArrayList<Integer> categories){
        deleteCategoriesForProduct(productId);

        for (Integer category : categories) {
            addCategoryForProduct(productId, category);
        }
    }

    private void deleteCategoriesForProduct(Integer productId){
        SqlParameterSource param = new MapSqlParameterSource(
                "id_param", productId);
        int status = template.update(deleteCategoriesForProduct, param);
        if(status != 0){
            System.out.println("Categories deleted for product ID " + productId);
        }else{
            System.out.println("No Categories found with product ID " + productId);
        }
    }

    private void addCategoryForProduct(Integer productId, Integer categoryId) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("product_id", productId)
                .addValue("category_id", categoryId);
        int status = template.update(addCategoryForProduct, param);
        if(status != 0){
            System.out.println("Category " + categoryId + " added successfully for product ID " + productId);
        }else{
            System.out.println("Couldn't add category " + categoryId + "  for product ID " + productId);
        }
    }

    private Product toProduct(final ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setProductId(resultSet.getInt("uid"));
        product.setProductName(resultSet.getString("product_name"));
        product.setDescription(resultSet.getString("product_description"));
        product.setPrice(resultSet.getInt("price"));
        product.setIsAvailable(resultSet.getBoolean("is_available"));
        product.setPhoto(resultSet.getString("photo"));
        ArrayList<Integer> categories = new ArrayList<>();

        categories.addAll(getCategoriesForProduct(product.getProductId()));
        product.setCategories(categories);

        return product;
    }
}
