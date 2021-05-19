package util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.io.Charsets;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import com.mysql.cj.core.conf.url.ConnectionUrlParser;

import db.mybatisXmlParser;
import utils.PropertieUtil;
import utils.SpringUtil;

public class MybatisUtilV55 {

	public static SqlSessionFactory getSqlSessFac(DataSource dataSourceDb2, String mapPaths) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSourceDb2);
		// String mapPath = "classpath:mapper/**/*.xml";

		factoryBean.setMapperLocations(getRsces(mapPaths));
		return factoryBean.getObject();
	}

	public static SqlSessionFactory getSqlSessionFactoryBySprbtCfgfile(String mapPaths) {
		try {
			List<String> mpList = Arrays.asList(mapPaths.split(","));
			String mysqlConnUrl = SpringUtil.getDbUrlFrmSprbtCfg();
			String mybatisCfg_result = mybatisCfgTxtV2("mybatisCfgTmplt.xml", mysqlConnUrl);

			mybatisCfg_result = mybatisXmlParser.addmapper2xml(mpList, mybatisCfg_result);
			System.out.println(mybatisCfg_result);
			InputStream is2 = new ByteArrayInputStream(mybatisCfg_result.getBytes());
			// ����sqlSession �Ĺ���
			return new SqlSessionFactoryBuilder().build(is2);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
//	public static SqlSessionFactory getSqlSessionFactory(String MybatisCfgTmpltPathResDir,String mysqlConnUrl,List<String> mpList) {
//		try {
//		 	String mybatisCfg_result = mybatisCfgTxtV2(MybatisCfgTmpltPathResDir,mysqlConnUrl);
//
//			mybatisCfg_result=	mybatisXmlParser.addmapper2xml(mpList, mybatisCfg_result);
//			  System.out.println(mybatisCfg_result);
//			InputStream is2 = new ByteArrayInputStream(mybatisCfg_result.getBytes());
//			// ����sqlSession �Ĺ���
//			return new SqlSessionFactoryBuilder().build(is2);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//
//	}

	public static Resource[] getRsces(String mapPath) throws IOException {
		Set<Resource> resourcesSet = new HashSet();
		String[] paths = mapPath.split(",");
		for (String pth : paths) {
			Resource[] resources = new PathMatchingResourcePatternResolver().getResources(pth);
			resourcesSet.addAll(Arrays.asList(resources));
		}
		// [file
		// [C:\Users\ATI\eclipse-workspace\miniCodePrj\target\classes\mapper\rmDao.xml]

		Resource[] ResourceMappers = resourcesSet.stream().toArray(Resource[]::new);
		return ResourceMappers;
	}

	public static SqlSessionFactory getSqlsessFac(String mybatisCfgfile) throws IOException {
		InputStream is = Resources.getResourceAsStream(mybatisCfgfile);
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

	public static String mybatisCfgTxtV2(String MybatisCfgPathResDir, String mysqlConnUrl) throws Exception {

		String mybatisCfg_result = mybatisCfgTxt(MybatisCfgPathResDir);

		// String getCfgFile = SpringUtil.getCfgFile();

		// String mysqlConnUrl =getMysqlUrlFrmSprbtCfg();
		// C:\Users\ATI\.m2\repository\mysql\mysql-connector-java\6.0.6\mysql-connector-java-6.0.6.jar
		ConnectionUrlParser connStringParser = ConnectionUrlParser.parseConnectionString(mysqlConnUrl);
		System.out.println(connStringParser);

		Object url = mysqlConnUrl;
		Object usr = UrlUtil.parse4Q(connStringParser.getQuery()).get("user");
		Object pwd = UrlUtil.parse4Q(connStringParser.getQuery()).get("password");
		if (pwd == null)
			pwd = "";
		url = cn.hutool.core.util.XmlUtil.escape(url.toString() + "&allowMultiQueries=true");

		mybatisCfg_result = mybatisCfg_result.replaceAll("\\$\\{mysql.url}", url.toString());
		mybatisCfg_result = mybatisCfg_result.replaceAll("\\$\\{mysql.username}", usr.toString());

		mybatisCfg_result = mybatisCfg_result.replaceAll("\\$\\{mysql.password}", pwd.toString());
		return mybatisCfg_result;
	}

	private static String mybatisCfgTxt(String MybatisCfgPathResDir) throws IOException {
		// String MybatisCfgPath =getMybatisCfgPath();
		// "/" + mybatisNamespace + "mybatis.xml";
		// ����mybatis �������ļ�����Ҳ���ع�����ӳ���ļ���
		// ClassLoader classLoader = .getClassLoader();
		InputStream is = MybatisUtilV55.class.getResourceAsStream(MybatisCfgPathResDir);
		String mybatisCfg_result = CharStreams.toString(new InputStreamReader(is, Charsets.UTF_8));
		return mybatisCfg_result;
	}

	private static String getMybatisCfgPath() {

		String mybatisNamespace = "";
		String propFilePath = "/cfg";
		try {
			mybatisNamespace = PropertieUtil.getProperty(propFilePath, "mybatisNamespace");
			if (mybatisNamespace.trim().length() > 0)
				mybatisNamespace = mybatisNamespace.replaceAll("\\.", "/");
			mybatisNamespace = mybatisNamespace + "/";
		} catch (Exception e) {

		}
		String resource = "/" + mybatisNamespace + "mybatis.xml";
		return resource;
	}

}
