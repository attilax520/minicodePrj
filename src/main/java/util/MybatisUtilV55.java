package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class MybatisUtilV55 {

	public static  SqlSessionFactory getSqlSessFac(DataSource dataSourceDb2, String mapPath) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSourceDb2);
		// String mapPath = "classpath:mapper/**/*.xml";
	 

		factoryBean.setMapperLocations(getRsces(mapPath));
		return factoryBean.getObject();
	}

	public static  Resource[] getRsces(String mapPath) throws IOException {
		Set<Resource> resourcesSet = new HashSet();
		String[] paths = mapPath.split(",");
		for (String pth : paths) {
			Resource[] resources = new PathMatchingResourcePatternResolver().getResources(pth);
			resourcesSet.addAll(Arrays.asList(resources));
		}
		// [file
		// [C:\Users\ATI\eclipse-workspace\miniCodePrj\target\classes\mapper\rmDao.xml]

		Resource[] ResourceMappers = resourcesSet.stream().toArray(Resource[]::new);
		return  ResourceMappers;
	}

	public static SqlSessionFactory getSqlsessFac(String resource) throws IOException {
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		return sqlSessionFactory;
	}

	public Object selectList(String statement, Object parameter, SqlSessionFactory SqlSessionFactory2) {
		SqlSession SqlSession2 = SqlSessionFactory2.openSession(true);

		return SqlSession2.selectList(statement, parameter);
	}

	public int update(String statement, Object parameter, SqlSessionFactory SqlSessionFactory2) {
		SqlSession SqlSession2 = SqlSessionFactory2.openSession(true);

		return SqlSession2.update(statement, parameter);
	}

}
