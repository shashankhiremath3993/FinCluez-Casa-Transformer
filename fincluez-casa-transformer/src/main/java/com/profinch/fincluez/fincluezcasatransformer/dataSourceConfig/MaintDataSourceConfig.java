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
@EntityScan({"com.profinch.fincluez.fincluezcasatransformer.entities.maintEntities",
        "com.profinch.fincluez.finclueztlibrary.entities.maintEntities"})
@EnableJpaRepositories(basePackages = {"com.profinch.fincluez.fincluezcasatransformer.repo.maintRepo",
        "com.profinch.fincluez.finclueztlibrary.repo.maintRepo"},
        entityManagerFactoryRef = "maintEntityManagerFactory",
        transactionManagerRef = "maintTransactionManager")
@PropertySource(
        value = "file:${PROPERTY_PATH}",
        factory = YamlPropertyLoaderFactory.class)
public class MaintDataSourceConfig {


    @Value("${fincluez.maint.datasource.url}")
    public String dataSourceUrl;

    @Value("${fincluez.maint.datasource.username}")
    public String dataSourceUsername;

    @Value("${fincluez.maint.datasource.password}")
    public String dataSourcePassword;

    @Value("${fincluez.maint.datasource.driver}")
    public String dataSourceDriver;

    @Bean(name = "maintDataSource")
    public DataSource maintDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();


        dataSource.setDriverClassName(dataSourceDriver);
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUsername);
        dataSource.setPassword(dataSourcePassword);


        return dataSource;
    }

    @Bean(name = "maintEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean maintEntityManagerFactory
            (EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<String, Object>();
        //properties.put("hibernate.hbm2ddl.auto", "update");
        //properties.put("hibernate.implicit_naming_strategy","org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");
        //properties.put("hibernate.physical_naming_strategy","org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");

        properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        return builder
                .dataSource(maintDataSource())
                .properties(properties)
                .packages("com.profinch.fincluez.fincluezcasatransformer.entities.maintEntities",
                        "com.profinch.fincluez.finclueztlibrary.entities.maintEntities")
                .build();
    }

    @Bean(name = "maintTransactionManager")
    public PlatformTransactionManager maintTransactionManager(
            final @Qualifier("maintEntityManagerFactory") LocalContainerEntityManagerFactoryBean maintEntityManagerFactory) {
        return new JpaTransactionManager(maintEntityManagerFactory.getObject());
    }
}