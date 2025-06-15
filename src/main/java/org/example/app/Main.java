package org.example.app;

import org.example.app.model.Customer;
import org.example.app.config.SpringJdbcConfig;
import org.example.app.dao.CustomerDao;
import org.example.app.dao.CustomerDaoImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);
        CustomerDao dao = new CustomerDaoImpl(context.getBean("jdbcTemplate", org.springframework.jdbc.core.JdbcTemplate.class));

        dao.add(new Customer(0, "Іван Іванов", "ivan@example.com", "+380995368344"));

        Customer c = dao.getById(1);
        System.out.println("Отримано по ID: " + c.getFullName());

        c.setEmail("newemail@example.com");
        dao.update(c);

        List<Customer> list = dao.getAll();
        for (Customer cust : list) {
            System.out.println(cust.getId() + ": " + cust.getFullName());
        }
    }
}
