package com.giftshop.repository;
import com.giftshop.dto.UserRegistrationDTO;
import com.giftshop.repository.interfaces.IUserDAO;
import com.giftshop.models.Role;
import com.giftshop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@PropertySource("classpath:sql/user_queries.properties")

public class UserDAO implements IUserDAO {
    @Autowired
    private NamedParameterJdbcTemplate template;
    @Value("${find_role_id}")
    private String findRoleIdByName;
    @Value("${find_role_name}")
    private String findRoleNameById;
    @Value("${find_user_with_id}")
    private String findById;
    @Value("${find_user_with_email}")
    private String findUserByEmail;
    @Value("${find_user_role_id}")
    private String findRoleIdOfUser;
    @Value("${insert_new_user}")
    private String insertNewUser;
    @Value("${update_existing_user}")
    private String updateExistingUser;
    @Value("${delete_user_by_email}")
    private String deleteUserByEmail;

    @Override
    public boolean isEmailUsed(String email) {
        return findUserByEmail(email) != null;
    }
    public Integer getRoleId(String roleName) {

        SqlParameterSource namedParameters = new MapSqlParameterSource(
                "name_param", roleName);
        return template.queryForObject(findRoleIdByName, namedParameters,
                Integer.class);
    }

    private Role findRoleById( Integer roleId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(
                "id_param", roleId);
        String name =  template.queryForObject(findRoleNameById, namedParameters,
                String.class);
        return  new Role(roleId, name);
    }

    @Override
    public User findUserById(Integer u_id) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_param", u_id);
        List<User> foundUsers =
                template.query(findById, param,
                        (resultSet, i) -> toPerson(resultSet));
        if (foundUsers.size() == 0) {
            return null;
        } else {
            return foundUsers.get(0);
        }
    }

    @Override
    public Integer insertUser(User user) {
        if (isEmailUsed(user.getEmail())) return null;
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("firstname", user.getName())
                .addValue("surname", user.getSurname())
                .addValue("patronymic", user.getPatronymic())
                .addValue("birth_date",user.getBirthDate())
                .addValue("email",user.getEmail())
                .addValue("phone_number",user.getPhoneNumber())
                .addValue("password",user.getPassword())
                .addValue("role",getRoleId("USER"));
        return template.update(insertNewUser,param);
    }

    @Override
    public void updateUser(User user) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("uid",user.getUserId())
                .addValue("firstname", user.getName())
                .addValue("surname", user.getSurname())
                .addValue("patronymic", user.getPatronymic())
                .addValue("birth_date",user.getBirthDate())
                .addValue("email",user.getEmail())
                .addValue("phone_number",user.getPhoneNumber())
                .addValue("password",user.getPassword())
                .addValue("role", user.getRole())
                .addValue("is_activated", user.getIsActivated());
        int status = template.update(updateExistingUser, param);
        if(status != 0){
            System.out.println("User data updated for ID " + user.getUserId());
        }else{
            System.out.println("No User found with ID " + user.getUserId());
        }
    }

    @Override
    public User findUserByEmail(String email) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("email_param", email);
        List<User> foundUsers =
                template.query(findUserByEmail, param,
                        (resultSet, i) -> toPerson(resultSet));
        if (foundUsers.size() == 0) {
            return null;
        } else {
            return foundUsers.get(0);
        }

    }

    @Override
    public Role findUserRoleById(Integer uid) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_param", uid);
         int role_id =        template.queryForObject(findRoleIdOfUser, param, Integer.class);

        return findRoleById(role_id);

    }

    private User toPerson(final ResultSet resultSet) throws SQLException {
        User person = new User();
        person.setUserId(resultSet.getInt("uid"));
        person.setName(resultSet.getString("firstname"));
        person.setSurname(       resultSet.getString("surname"));
        person.setPatronymic(resultSet.getString("patronymic"));
        person.setEmail(resultSet.getString("email"));
        person .setPassword(resultSet.getString("password"));
        person.setIsActivated(resultSet.getBoolean("is_activated"));
        person.setPhoneNumber(resultSet.getString("phone_number"));
        person.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
        int roleId = resultSet.getInt("role_id");
        person.setRole(findRoleById(roleId));
        return person;
    }

    @Override
     public Integer deleteUser(String email){
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("email_param",email);
        int status = template.update(deleteUserByEmail, param);
        if(status != 0){
            System.out.println("User data deleted for  " + email);
            return 1;
        }else{
            System.out.println("No User found with  " + email);
            return -1;
        }
    }

}
