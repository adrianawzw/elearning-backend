package com.elearning.platform_backend;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionTest implements CommandLineRunner {
    
    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("CONEXION EXITOSA CON SUPABASE");
            System.out.println("BASE DE DATOS: " + conn.getMetaData().getDatabaseProductName());

        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("ERROR DE CONEXION: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
