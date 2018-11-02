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
import com.vedas.vmart.dao.ContactUsDAO;
import com.vedas.vmart.dao.ContactUsDAOImpl;
import com.vedas.vmart.dao.OrdersListDAO;
import com.vedas.vmart.dao.OrdersListDAOImpl;
import com.vedas.vmart.dao.OtpVerificatioDAO;
import com.vedas.vmart.dao.OtpVerificatioDAOImpl;
import com.vedas.vmart.dao.ProductListDAO;
import com.vedas.vmart.dao.ProductListDAOImpl;
import com.vedas.vmart.dao.SignUpDAO;
import com.vedas.vmart.dao.SignUpDAOImpl;

import com.vedas.vmart.dao.TimingsListDAO;
import com.vedas.vmart.dao.TimingsListDAOImpl;
import com.vedas.vmart.dao.UserAddressDAO;
import com.vedas.vmart.dao.UserAddressDAOImpl;

import com.vedas.vmart.dao.VMartAddressDAO;
import com.vedas.vmart.dao.VMartAddressDAOImpl;


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
 
	//--------------------------Configuared DataSource as a Bean--------------------------------
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

    //--------------------------Configuared CategoryListDAO as a Bean--------------------------------
    @Bean
    public CategoryListDAO getCategoryListDAO(){
    	return new CategoryListDAOImpl(getDataSource());
    }
    
	//--------------------------Configuared ProductListDAO as a Bean--------------------------------
    @Bean
    public ProductListDAO getProductListDAO(){
    	return new ProductListDAOImpl(getDataSource());
    }
    
	//--------------------------Configuared SignUpDAO as a Bean--------------------------------

    @Bean
    public SignUpDAO getSignUpDAO() {
		return new SignUpDAOImpl(getDataSource());
    	
    }
    
	//--------------------------Configuared OtpVerificatioDAO as a Bean--------------------------------

    @Bean
    public OtpVerificatioDAO getOtpVerificatioDAO() {
		return new OtpVerificatioDAOImpl(getDataSource());
    	
    }
    
	//--------------------------Configuared CartDAO as a Bean--------------------------------
    @Bean
    public CartDAO getCartDAO() {
		return new CartDAOImpl(getDataSource());
    	
    }

    
   @Bean
   public TimingsListDAO geTimingsListDAO() {
	   return new TimingsListDAOImpl(getDataSource());
   }

    
	//--------------------------Configuared UserAddressDAO as a Bean--------------------------------
    @Bean
    public UserAddressDAO getUserAddressDAO() {
    	return new UserAddressDAOImpl(getDataSource());
    }
    
    @Bean
    public VMartAddressDAO getVMartAddressDAO() {
		return new VMartAddressDAOImpl(getDataSource());     	
    }
    
    @Bean
    public OrdersListDAO getOrdersListDAO() {
    	return new OrdersListDAOImpl(getDataSource());
    }
    
    @Bean
    public ContactUsDAO getContactUsDAO() {
    	return new ContactUsDAOImpl();
    }
    
  
}
