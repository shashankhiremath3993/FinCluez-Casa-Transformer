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
@EntityScan({"com.profinch.fincluez.fincluezcasatransformer.entities.stagingEntities",
        "com.profinch.fincluez.finclueztlibrary.entities.stagingEntities"})
@EnableJpaRepositories(basePackages = {"com.profinch.fincluez.fincluezcasatransformer.repo.stagingRepo",
        "com.profinch.fincluez.finclueztlibrary.repo.stagingRepo"},
        entityManagerFactoryRef = "stagingEntityManagerFactory",
        transactionManagerRef = "stagingTransactionManager")
@PropertySource(
        value = "file:${PROPERTY_PATH}",
        factory = YamlPropertyLoaderFactory.class)
public class StagingDataSourceConfig {


    @Value("${fincluez.staging.datasource.url}")
    public String dataSourceUrl;

    @Value("${fincluez.staging.datasource.username}")
    public String dataSourceUsername;

    @Value("${fincluez.staging.datasource.password}")
    public String dataSourcePassword;

    @Value("${fincluez.staging.datasource.driver}")
    public String dataSourceDriver;

    @Bean(name = "stagingDataSource")
    public DataSource stagingDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();


        dataSource.setDriverClassName(dataSourceDriver);
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUsername);
        dataSource.setPassword(dataSourcePassword);


        return dataSource;
    }

    @Bean(name = "stagingEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean stagingEntityManagerFactory
            (EntityManagerFactoryBuilder builder) {

        Map<String, Object> properties = new HashMap<String, Object>();
        //properties.put("hibernate.hbm2ddl.auto", "update");
        //properties.put("hibernate.implicit_naming_strategy","org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");
        //properties.put("hibernate.physical_naming_strategy","org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        return builder
                .dataSource(stagingDataSource())
                .properties(properties)
                .packages("com.profinch.fincluez.fincluezcasatransformer.entities.stagingEntities",
                        "com.profinch.fincluez.finclueztlibrary.entities.stagingEntities")
                .build();
    }

    //@Primary
    @Bean(name = "stagingTransactionManager")
    public PlatformTransactionManager stagingTransactionManager(
            final @Qualifier("stagingEntityManagerFactory") LocalContainerEntityManagerFactoryBean stagingEntityManagerFactory) {
        return new JpaTransactionManager(stagingEntityManagerFactory.getObject());
    }
}