package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword());
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        try {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{email, password}, (rs, rowNum) -> {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                return u;
            });
        } catch (Exception e) {
            return null;
        }
    }
}
