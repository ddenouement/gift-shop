package com.giftshop.repository;
import com.giftshop.repository.interfaces.IUserDao;
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

public class UserDao implements IUserDao {
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
    private String  findRoleIdOfUser;

    @Override
    public boolean isEmailUsed(String email) {
        return findUserByEmail(email) != null;
    }
    private Integer getRoleId(String roleName) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(
                "name_param", roleName);
        return template.queryForObject(findRoleIdByName, namedParameters,
                Integer.class);
    }

    private Role findRoleById( int roleId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(
                "id_param", roleId);
        String name =  template.queryForObject(findRoleNameById, namedParameters,
                String.class);
        return  new Role(roleId, name);
    }



    @Override
    public User findUserById(int u_id) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("user_id_param", u_id);
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
    public Role findUserRoleById(int uid) {
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
        person.setPatronym(resultSet.getString("patronymic"));
        person.setEmail(resultSet.getString("email"));
        person .setPassword(resultSet.getString("password"));
        person.setActivated(resultSet.getBoolean("is_activated"));
        person.setPhoneNumber(resultSet.getString("phone_number"));
        int roleId = resultSet.getInt("role_id");
        person.setRole(findRoleById(roleId));
        return person;
    }


}
