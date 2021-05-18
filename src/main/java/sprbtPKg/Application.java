package sprbtPKg;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/*
 * */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import other.Log4jTet;

@Configuration
@ComponentScan(lazyInit = true)
@SpringBootApplication
public class Application {

	public static ConfigurableApplicationContext cfgAppContext;

	public static void main(String[] args) throws Exception {

		Logger logger = Logger.getLogger(Log4jTet.class);
		logger.info("start ...");
		cfgAppContext = SpringApplication.run(Application.class, args);

	}

	@Bean(name = "ds2")
	@ConfigurationProperties(prefix = "spring.datasource.db2")
	public DataSource newhomeDbDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Autowired
	@Qualifier("ds2")
	private DataSource dataSourceDb2;

	@Bean(name = "sqlSessionFactoryDb2")
	public SqlSessionFactory sqlSessionFactoryDb2() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSourceDb2);
		factoryBean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
		return factoryBean.getObject();
	}

	// 
	@Bean
	public SqlSessionTemplate sqlSessionTemplateDb2() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactoryDb2());
	}

}
