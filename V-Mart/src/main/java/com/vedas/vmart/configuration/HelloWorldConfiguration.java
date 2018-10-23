package com.vedas.vmart.configuration;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.vedas.vmart.dao.CategoryListDAOImpl;
import com.vedas.vmart.dao.CartDAO;
import com.vedas.vmart.dao.CartDAOImpl;
import com.vedas.vmart.dao.CategoryListDAO;
import com.vedas.vmart.dao.ContactDAO;
import com.vedas.vmart.dao.ContactDAOImpl;
import com.vedas.vmart.dao.OtpVerificatioDAO;
import com.vedas.vmart.dao.OtpVerificatioDAOImpl;
import com.vedas.vmart.dao.ProductListDAO;
import com.vedas.vmart.dao.ProductListDAOImpl;
import com.vedas.vmart.dao.SignUpDAO;
import com.vedas.vmart.dao.SignUpDAOImpl;



@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.vedas.vmart")

public class HelloWorldConfiguration extends WebMvcConfigurerAdapter{
	
	@Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
     
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/images/**").addResourceLocations("/images/")
	                 .addResourceLocations("classpath:/images/");
	    }
 
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/vmart");
        dataSource.setUsername("root");
        dataSource.setPassword("vedas");
        System.out.print("database connection..."+dataSource);
        return dataSource;
    }
     
    @Bean
    public ContactDAO getContactDAO() {
        return new ContactDAOImpl(getDataSource());
    }
    
    @Bean
    public CategoryListDAO getCategoryListDAO(){
    	return new CategoryListDAOImpl(getDataSource());
    }
    
    @Bean
    public ProductListDAO getProductListDAO(){
    	return new ProductListDAOImpl(getDataSource());
    }
    
    @Bean
    public SignUpDAO getSignUpDAO() {
		return new SignUpDAOImpl(getDataSource());
    	
    }
    
    @Bean
    public OtpVerificatioDAO getOtpVerificatioDAO() {
		return new OtpVerificatioDAOImpl(getDataSource());
    	
    }
    
    @Bean
    public CartDAO getCartDAO() {
		return new CartDAOImpl(getDataSource());
    	
    }

  
}
