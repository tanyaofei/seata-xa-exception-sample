package com.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author tanyaofei
 * @since 2024/4/18
 **/
@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public int insert(User user) {
        return jdbc.update(conn -> {
            var stm = conn.prepareStatement("insert into user (id, username, password) values (?, ?, ?)");
            stm.setObject(1, user.getId());
            stm.setObject(2, user.getUsername());
            stm.setObject(3, user.getPassword());
            return stm;
        });
    }

    public int updateUserName(Long id, String username) {
        return jdbc.update(conn -> {
            var stm = conn.prepareStatement("update user set username = ? where id = ?");
            stm.setObject(1, username);
            stm.setObject(2, id);
            return stm;
        });
    }

    public int updatePassword(Long id, String password) {
        return jdbc.update(conn -> {
            var stm = conn.prepareStatement("update user set password = ? where id = ?");
            stm.setObject(1, password);
            stm.setObject(2, id);
            return stm;
        });
    }

    public User selectById(Long id) {
        return jdbc.query(conn -> {
            var stm = conn.prepareStatement("select * from user where id = ?");
            stm.setObject(1, id);
            return stm;
        }, rs -> {
            var user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        });
    }
}
