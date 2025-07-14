package com.example.libraryapi.config;

import com.sun.tools.jconsole.JConsoleContext;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    //criando uma config com banco de dados simples()nao recomendado para produção
   /* @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }*/
    //config padrao de banco recomendada(spring ja usa por padrao)
    @Bean DataSource hikariDatasource(){
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setJdbcUrl(url);
        config.setDriverClassName(driver);

        //maximo de conexoes liberadas
        config.setMaximumPoolSize(10);
        //tamanho inicial de conexoes
        config.setMinimumIdle(1);
        config.setPoolName("library-db-pool");
        config.setMaxLifetime(600000);//milisegundos(10min)
        config.setConnectionTimeout(100000);//tempo maximo para tentar conexao
        config.setConnectionTestQuery("select 1");//verificaçao rapida de conexao do banco

        return new HikariDataSource(config);
    }

}
