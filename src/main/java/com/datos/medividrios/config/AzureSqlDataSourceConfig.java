package com.datos.medividrios.config;

import com.microsoft.aad.msal4j.*;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

@Configuration
public class AzureSqlDataSourceConfig {

    @Bean
    public DataSource dataSource() throws MalformedURLException, ExecutionException, InterruptedException {
        PublicClientApplication app = PublicClientApplication.builder("your-client-id")
                .authority("https://login.microsoftonline.com/your-tenant-id")
                .build();

        IAuthenticationResult result = app.acquireToken(
                UserNamePasswordParameters.builder(
                        Collections.singleton("https://database.windows.net/.default"),
                        "your-email@yourdomain.com",
                        "your-password".toCharArray()
                ).build()
        ).get();

        String accessToken = result.accessToken();

        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:sqlserver://mediserver.database.windows.net:1433;databaseName=dbmedividrios;encrypt=true;trustServerCertificate=false;");
        ds.setUsername("");
        ds.setPassword("");
        ds.addDataSourceProperty("accessToken", accessToken);
        ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        return ds;
    }
}

