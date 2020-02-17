package com.giftshop.repository;

import com.giftshop.models.Category;
import com.giftshop.models.Product;
import com.giftshop.repository.interfaces.ICategoryDAO;
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
@PropertySource("classpath:sql/category_queries.properties")
public class CategoryDAO implements ICategoryDAO {

    @Autowired
    private NamedParameterJdbcTemplate template;
    @Value("${get_by_id}")
    private String getById;
    @Value("${get_all}")
    private String getAll;
    @Value("${insert_category}")
    private String insertCategory;
    @Value("${update_category}")
    private String updateCategory;
    @Value("${get_product}")
    private String getProduct;
    @Value("${delete_category}")
    private String deleteCategory;

    @Override
    public Category getById(Integer categoryId) {
        SqlParameterSource param = new MapSqlParameterSource("id_param", categoryId);
        List<Category> categories = template.query(getById, param, (resultSet, i) -> toCategory(resultSet));
        if (categories.size() == 0) {
            return null;
        } else {
            return categories.get(0);
        }
    }

    @Override
    public ArrayList<Category> getAllCategories() {
        List<Category> categories = template.query(getAll, (resultSet, i) -> toCategory(resultSet));
        return (ArrayList<Category>) categories;
    }

    @Override
    public Integer createCategory(Category category) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("category_name", category.getCategoryName());
        return template.update(insertCategory,param);
    }

    @Override
    public void updateCategory(Category category) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("category_id",category.getCategoryId())
                .addValue("category_name", category.getCategoryName());
        int status = template.update(updateCategory, param);
        if(status != 0){
            System.out.println("Category data updated for ID " + category.getCategoryId());
        }else{
            System.out.println("No Category found with ID " + category.getCategoryId());
        }

    }

    @Override
    public void deleteCategory(Category category) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("category_id", category.getCategoryId());
        List<Integer> productIds =
                template.query(getProduct, param,
                        (resultSet, i) -> resultSet.getInt("product_id"));
        if(productIds.size() > 0){
            System.out.println("Unable to delete category " + category.getCategoryName() + "\n Some Products use it:" + productIds.toString());
        }
        else{
            int status = template.update(deleteCategory, param);
            if(status != 0){
                System.out.println("Category id " + category.getCategoryId() + " deleted");
            }else{
                System.out.println("Category id " + category.getCategoryId() + " failed to delete");
            }
        }

    }

    private Category toCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setCategoryId(resultSet.getInt("category_id"));
        category.setCategoryName(resultSet.getString("category_name"));
        return category;
    }
}
