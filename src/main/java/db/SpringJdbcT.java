package db;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import miniCodePrjPkg.Log4j2HelloWorld;

public class SpringJdbcT {
	
    private static final Logger logger_log4j2 = LogManager.getLogger(Log4j2HelloWorld.class);

	public static void main(String[] args) throws JsonProcessingException {

		Map properties = Maps.newLinkedHashMap();
		properties.put("javax.persistence.jdbc.driver", "org.sqlite.JDBC");

		properties.put("javax.persistence.jdbc.url", "jdbc:sqlite:test" + Math.random() + ".db");

		System.out.println(properties);
		// Create a new EntityManagerFactory using the System properties.
		// The "hellojpa" name will be used to configure based on the
		// corresponding name in the META-INF/persistence.xml file
		// from hibernate-jpa-2.1-api jar
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("HbntUnit", properties);

		exeUpdate(factory, "CREATE TABLE sys_data (jsonfld json  )");
		exeUpdate(factory, "insert into sys_data values('{\"age\":88}')");

		//----------------- insert by spring jdbc
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.sqlite.JDBC");
		dataSource.setUrl(properties.get("javax.persistence.jdbc.url").toString());
	 
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("sys_data");
		Map<String, Object> parameters = new HashMap<String, Object>();
		Map rec1 = Maps.newHashMap(ImmutableMap.of("name", 999999999, "age", 22));
		
	 //.defaultPrettyPrintingWriter()
		 
		parameters.put("jsonfld",    new ObjectMapper().writeValueAsString(rec1)  );
		simpleJdbcInsert.execute(parameters);

		
		//--------------query sprin gjdbc 
		// jpa query err ,cant find entity map
//	System.out.println(query(factory,"select json_extract(jsonfld,'$.age') as age from sys_data") );	;
//		System.out.println("f");

//	 	        dataSource.setUsername("guest_user");
//	        dataSource.setPassword("guest_password");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select json_extract(jsonfld,'$.age') as age from sys_data";
		sql = "select *   from sys_data";
		sql="select 1 as t";
		List li = jdbcTemplate.queryForList(sql);

		System.out.println(li);

	}
	
	@Test
	public void testQuery()
	{
		
		logger_log4j2.info("-------------log4j2 outmsg----------");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	//	dataSource.setDriverClassName("org.sqlite.JDBC");
		dataSource.setDriverClassName("net.sf.log4jdbc.DriverSpy");
		
	//	dataSource.setUrl("jdbc:p6spy:sqlite:test" + Math.random() + ".db");
		dataSource.setUrl("jdbc:log4jdbc:sqlite:test" + Math.random() + ".db");
		
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select json_extract(jsonfld,'$.age') as age from sys_data";
		sql = "select *   from sys_data";
		sql="select 1 as t";
		List li = query(jdbcTemplate, sql);

		System.out.println(li);
	}

	private List query(JdbcTemplate jdbcTemplate, String sql) {
		logger_log4j2.info(sql);
		List li = jdbcTemplate.queryForList(sql);
		logger_log4j2.info("li.size():"+li.size());
		if(li.size()<20)
		logger_log4j2.info(JSON.toJSONString(li,true));
		return li;
	}

	private static int exeUpdate(EntityManagerFactory factory, String sql) {
		try {
			EntityManager em = factory.createEntityManager();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			///// sql

			// sql = MessageFormat.format(sql, "'" + getUpflag() + "'", "'" + getUpflag() +
			// "'", "'" + getUpflag() + "'");
			System.out.println(sql);
			Query createNativeQuery = em.createNativeQuery(sql);
			int executeUpdate = createNativeQuery.executeUpdate();
			System.out.println(executeUpdate);

			transaction.commit();
			return executeUpdate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	private static List<Map> query(EntityManagerFactory factory, String sql) {
		EntityManager em = factory.createEntityManager();
		Query createNativeQuery = em.createNativeQuery(sql, Map.class);

		// createNativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map> result = createNativeQuery.getResultList();
		return result;
	}
}
