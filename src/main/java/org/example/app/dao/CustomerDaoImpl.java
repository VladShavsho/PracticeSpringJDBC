package org.example.app.dao;


import org.example.app.mapper.CustomerRowMapper;
import org.example.app.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao{
    private final JdbcTemplate jdbcTemplate;

    public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Customer customer) {
        String sql = "INSERT INTO customer (full_name, email, social_security_number) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, customer.getFullName(), customer.getEmail(), customer.getSocialSecurityNumber());
    }

    @Override
    public Customer getById(int id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), id);
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customer SET full_name = ?, email = ?, social_security_number = ? WHERE id = ?";
        jdbcTemplate.update(sql, customer.getFullName(), customer.getEmail(), customer.getSocialSecurityNumber(), customer.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Customer> getAll() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }
}
