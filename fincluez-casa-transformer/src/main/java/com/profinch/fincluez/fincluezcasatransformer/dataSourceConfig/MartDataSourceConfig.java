package com.profinch.fincluez.fincluezcasatransformer.dataSourceConfig;

import com.profinch.fincluez.fincluezcasatransformer.config.YamlPropertyLoaderFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EntityScan({"com.profinch.fincluez.fincluezcasatransformer.entities.martEntities",
        "com.profinch.fincluez.finclueztlibrary.entities.martEntities"})
@EnableJpaRepositories(basePackages = {"com.profinch.fincluez.fincluezcasatransformer.repo.martRepo",
        "com.profinch.fincluez.finclueztlibrary.repo.martRepo"},
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
@PropertySource(
        value = "file:${PROPERTY_PATH}",
        factory = YamlPropertyLoaderFactory.class)
public class MartDataSourceConfig {


    @Value("${fincluez.mart.datasource.url}")
    public String dataSourceUrl;

    @Value("${fincluez.mart.datasource.username}")
    public String dataSourceUsername;

    @Value("${fincluez.mart.datasource.password}")
    public String dataSourcePassword;

    @Value("${fincluez.mart.datasource.driver}")
    public String dataSourceDriver;

    @Bean(name = "martDataSource")
    @Primary
    public DataSource martDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();


        dataSource.setDriverClassName(dataSourceDriver);
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUsername);
        dataSource.setPassword(dataSourcePassword);


        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory
            (EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<String, Object>();
        //properties.put("hibernate.hbm2ddl.auto", "update");
        //properties.put("hibernate.implicit_naming_strategy","org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");
        //properties.put("hibernate.physical_naming_strategy","org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");

        properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        return builder
                .dataSource(martDataSource())
                .properties(properties)
                .packages("com.profinch.fincluez.fincluezcasatransformer.entities.martEntities",
                        "com.profinch.fincluez.finclueztlibrary.entities.martEntities")
                .build();
    }

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(
            final @Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}