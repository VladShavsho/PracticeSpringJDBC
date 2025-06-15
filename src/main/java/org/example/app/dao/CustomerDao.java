package org.example.app.dao;

import org.example.app.model.Customer;

import java.util.List;

public interface CustomerDao {
    void add(Customer customer);
    Customer getById(int id);
    void update(Customer customer);
    void delete(int id);
    List<Customer> getAll();
}
