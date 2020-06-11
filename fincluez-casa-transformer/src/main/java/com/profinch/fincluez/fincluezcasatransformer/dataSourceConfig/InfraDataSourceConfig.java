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
@EntityScan({"com.profinch.fincluez.fincluezcasatransformer.entities.infraEntities",
        "com.profinch.fincluez.finclueztlibrary.entities.infraEntities"})
@EnableJpaRepositories(basePackages = {"com.profinch.fincluez.fincluezcasatransformer.repo.infraRepo",
        "com.profinch.fincluez.finclueztlibrary.repo.infraRepo"},
        entityManagerFactoryRef = "infraEntityManagerFactory",
        transactionManagerRef = "infraTransactionManager")
@PropertySource(
        value = "file:${PROPERTY_PATH}",
        factory = YamlPropertyLoaderFactory.class)
public class InfraDataSourceConfig {


    @Value("${fincluez.infra.datasource.url}")
    public String dataSourceUrl;

    @Value("${fincluez.infra.datasource.username}")
    public String dataSourceUsername;

    @Value("${fincluez.infra.datasource.password}")
    public String dataSourcePassword;

    @Value("${fincluez.infra.datasource.driver}")
    public String dataSourceDriver;

    @Bean(name = "infraDataSource")
    public DataSource infraDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceDriver);
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUsername);
        dataSource.setPassword(dataSourcePassword);
        return dataSource;
    }

    @Bean(name = "infraEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean infraEntityManagerFactory
            (EntityManagerFactoryBuilder builder) {

        Map<String, Object> properties = new HashMap<String, Object>();
        //properties.put("hibernate.hbm2ddl.auto", "update");
        //properties.put("hibernate.implicit_naming_strategy","org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");
        //properties.put("hibernate.physical_naming_strategy","org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        return builder
                .dataSource(infraDataSource())
                .properties(properties)
                .packages("com.profinch.fincluez.fincluezcasatransformer.entities.infraEntities",
                        "com.profinch.fincluez.finclueztlibrary.entities.infraEntities")
                .build();
    }

    //@Primary
    @Bean(name = "infraTransactionManager")
    public PlatformTransactionManager infraTransactionManager(
            final @Qualifier("infraEntityManagerFactory") LocalContainerEntityManagerFactoryBean infraEntityManagerFactory) {
        return new JpaTransactionManager(infraEntityManagerFactory.getObject());
    }
}