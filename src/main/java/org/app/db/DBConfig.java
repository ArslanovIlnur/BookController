package org.app.db;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;

@Configuration
public class DBConfig {
    @Bean
    public DataSource dataSource(@Value("${spring.datasource.url}")String url,
                                 @Value("${spring.datasource.username}")String username,
                                 @Value("${spring.datasource.password}")String password){
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public CommandLineRunner createBooksDB(DataSource dataSource){
        return args -> {
            try (InputStream inputStream = this.getClass().getResourceAsStream("/sql/books.sql")){
                String booksSql = new String(inputStream.readAllBytes());

                try (Connection connection = dataSource.getConnection();
                     Statement statement = connection.createStatement();){
                    statement.executeUpdate(booksSql);
                }
            }
        };
    }

    @Bean
    public CommandLineRunner createUsersDB(DataSource dataSource){
        return args -> {
            try (InputStream inputStream = this.getClass().getResourceAsStream("/sql/users.sql")){
                String usersSql = new String(inputStream.readAllBytes());

                try (Connection connection = dataSource.getConnection();
                     Statement statement = connection.createStatement();){
                    statement.executeUpdate(usersSql);
                }
            }
        };
    }

    @Bean
    public CommandLineRunner createReservationsDB(DataSource dataSource){
        return args -> {
            try (InputStream inputStream = this.getClass().getResourceAsStream("/sql/reservations.sql")){
                String usersSql = new String(inputStream.readAllBytes());

                try (Connection connection = dataSource.getConnection();
                     Statement statement = connection.createStatement();){
                    statement.executeUpdate(usersSql);
                }
            }
        };
    }
}
