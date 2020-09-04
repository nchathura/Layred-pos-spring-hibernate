package lk.ijse.dep.pos;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource ds){
        LocalSessionFactoryBean lfs = new LocalSessionFactoryBean();
        lfs.setDataSource(ds);
        lfs.setPackagesToScan("lk.ijse.dep.pos.entity");
        lfs.setHibernateProperties(hibernateProperties());
        return lfs;
    }

    @Bean
    public DataSource dataSource(){

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("ijse");
        ds.setUrl("jdbc:mysql://localhost:3306/dep5");
        return ds;
    }

    public Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.allow_refresh_detached_entity", false);
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sf){
        return new HibernateTransactionManager(sf);
    }
}
