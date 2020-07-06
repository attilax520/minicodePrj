package db;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.google.common.collect.Maps;

public class SpringJdbcT {

	public static void main(String[] args) {

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

		//jpa query err ,cant find entity map
//	System.out.println(query(factory,"select json_extract(jsonfld,'$.age') as age from sys_data") );	;
//		System.out.println("f");

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.sqlite.JDBC");
		dataSource.setUrl(properties.get("javax.persistence.jdbc.url").toString());
//	        dataSource.setUsername("guest_user");
//	        dataSource.setPassword("guest_password");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select json_extract(jsonfld,'$.age') as age from sys_data";
		List li = jdbcTemplate.queryForList(sql);
		System.out.println(li);

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
		Query createNativeQuery = em.createNativeQuery(sql ,Map.class );
		
	//	createNativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		 List<Map> result = createNativeQuery.getResultList();
		 return result;
	}
}
