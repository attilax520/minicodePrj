package util;

import java.io.IOException;
import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class MybatisUtilV55 {
	
	
	public SqlSessionFactory getSqlSessFac(DataSource dataSourceDb2,	String mapPath ) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSourceDb2);
	//	String mapPath = "classpath:mapper/**/*.xml";
		factoryBean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(mapPath));
		return factoryBean.getObject();
	}
	
	public static SqlSessionFactory getSqlsessFac(String resource) throws IOException {
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		return sqlSessionFactory;
	}
	
	public Object selectList(String statement, Object parameter, SqlSessionFactory SqlSessionFactory2) {
		SqlSession SqlSession2=SqlSessionFactory2.openSession(true);
    
		return   SqlSession2.selectList(statement,parameter);
	}
	
	public int update(String statement, Object parameter, SqlSessionFactory SqlSessionFactory2) {
		SqlSession SqlSession2=SqlSessionFactory2.openSession(true);
    
		return   SqlSession2.update( statement,parameter);
	}

}
