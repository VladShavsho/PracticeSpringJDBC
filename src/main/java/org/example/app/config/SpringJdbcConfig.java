package org.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;

@Configuration
public class SpringJdbcConfig {
    @Bean
    public DataSource dataSource() throws IOException {
        Properties props = new Properties();
        props.load(new ClassPathResource("db/jdbc.properties").getInputStream());

        String url = props.getProperty("dbDriver") + props.getProperty("dbName");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);

        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws IOException {
        return new JdbcTemplate(dataSource());
    }
}
