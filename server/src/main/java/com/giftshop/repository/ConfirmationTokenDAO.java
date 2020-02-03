package com.giftshop.repository;

import com.giftshop.models.ConfirmationToken;
import com.giftshop.repository.interfaces.IConfirmationTokenDAO;
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
@PropertySource("classpath:sql/confirmationtoken_queries.properties")

public class ConfirmationTokenDAO implements IConfirmationTokenDAO{

    @Autowired
    private NamedParameterJdbcTemplate template;
    @Value("${get_token_by_id}")
    private String getTokenById;
    @Value("${get_token_by_user_id}")
    private String getTokenByUserId;
    @Value("${get_token_by_token}")
    private String getTokenByToken;
    @Value("${insert_new_token}")
    private String insertNewToken;
    @Value("${delete_token}")
    private String deleteToken;

    @Override
    public boolean exists(String token) {
        return getByToken(token) != null;
    }

    @Override
    public ConfirmationToken getById(Integer id) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_param", id);
        List<ConfirmationToken> tokens =
                template.query(getTokenById, param,
                        (resultSet, i) -> toConfirmationToken(resultSet));
        if (tokens.size() == 0) {
            return null;
        } else {
            return tokens.get(0);
        }
    }

    @Override
    public ConfirmationToken getByUserId(Integer userId) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_param", userId);
        List<ConfirmationToken> tokens =
                template.query(getTokenByUserId, param,
                        (resultSet, i) -> toConfirmationToken(resultSet));
        if (tokens.size() == 0) {
            return null;
        } else {
            return tokens.get(0);
        }
    }

    @Override
    public ConfirmationToken getByToken(String token) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("token_param", token);
        List<ConfirmationToken> tokens =
                template.query(getTokenByToken, param,
                        (resultSet, i) -> toConfirmationToken(resultSet));
        if (tokens.size() == 0) {
            return null;
        } else {
            return tokens.get(0);
        }
    }

    @Override
    public int createToken(ConfirmationToken ct) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("token", ct.getToken())
                .addValue("create_date", ct.getCreateDate())
                .addValue("user_id", ct.getUserId());
        return template.update(insertNewToken,param);
    }

    @Override
    public void deleteToken(int id) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_param", id);
        int status = template.update(deleteToken, param);
        if(status != 0){
            System.out.println("Confirmation token deleted for ID " + id);
        }else{
            System.out.println("No Confirmation token found with ID " + id);
        }
    }

    private ConfirmationToken toConfirmationToken(final ResultSet resultSet) throws SQLException {
        ConfirmationToken ct = new ConfirmationToken();
        ct.setTokenId(resultSet.getInt("uid"));
        ct.setToken(resultSet.getString("token"));
        ct.setCreateDate(resultSet.getTimestamp("create_date").toLocalDateTime());
        ct.setUserId(resultSet.getInt("user_id"));
        return ct;
    }
}
