package application.web.system;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.Configurator;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import application.web.controller.BudanController;

public class DatasourceTest {

	private static final Log log = LogFactory.getLog(HKlhcTest2.class);

	static java.util.logging.Logger logr_javalog = java.util.logging.Logger.getLogger("LoggingDemo");

	public static void main(String[] args) {

		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

		// 获取IoC容器中JdbcTemplate实例
		
		DataSource ds=	 (DataSource) ac.getBean("dataSource");
		JdbcTemplate jdbcTemplate =new JdbcTemplate(ds);
				//(JdbcTemplate) ac.getBean("jdbcTemplate");
		String sql = "insert into user (name,deptid) values (?,?)";
		sql = "select 2";
		// 6 int count= jdbcTemplate.update(sql, new Object[]{"caoyc",3});
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql);
		System.out.println(queryForList);
		logr_javalog.info(queryForList.toString());
		log.info(queryForList);
	}

}
