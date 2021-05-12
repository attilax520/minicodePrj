package sprbtPKg;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import ch.qos.logback.core.db.dialect.DBUtil;
import util.Dbutil;
import util.HttpUtilV5v44;

@RestController
public class CommApiCtrlr {
//    @Autowired
//    NamedParameterJdbcTemplate jdbcTemplate;

	@RequestMapping("/invokeJavaBySpel")
	Object invokeJavaBySpel(HttpServletRequest rq, HttpServletResponse rsps) {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(rq.getParameter("$javaStmtSpel"));

		return exp.getValue();
	}

	@RequestMapping("/invokeMybatis")
	Object invokeMybatis(HttpServletRequest rq, HttpServletResponse rsps) throws IOException {
		String stmt = rq.getParameter("$stmt");
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

		SqlSession sess = sqlSessionFactory.openSession(true);
		List<Map> li;

		List objLI = sess.selectList(stmt, HttpUtilV5v44.getParamMap2Mybatis(rq));

		return objLI;
	}

	/**
	 * ?$stmt=qry$tab1&b=1&c=2
	 * ?$stmt=insert$tab1&b=1&c=2
	 * ?$stmt=updt$tab1&b=1&c=2&$where$e=1
	 *  ?$stmt=del$tab1&b=1
	 * @param rq
	 * @param rsps
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/invokeUrlDsl")
	Object invokeUrlDsl(HttpServletRequest rq, HttpServletResponse rsps) throws IOException {
		String stmt = rq.getParameter("$stmt");
		String[] a = stmt.split("$");
		String op = a[0];
		String tableName = a[1];

		DriverManagerDataSource dataSource = God.getConnDs();

		if (op.equals("insert")) {
			return Dbutil.insert(dataSource, tableName, HttpUtilV5v44.getParameterMapFstVal(rq));

		}
		if (op.equals("updt")) {
			return Dbutil.updt(dataSource, tableName, HttpUtilV5v44.getParameterMapFstVal(rq));

		}
		if (op.equals("del")) {
			return Dbutil.del(dataSource, tableName, HttpUtilV5v44.getParameterMapFstVal(rq));

		}
		if (op.equals("qry")) {
			return Dbutil.qry(dataSource, tableName, HttpUtilV5v44.getParameterMapFstVal(rq));

		}

		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

		SqlSession sess = sqlSessionFactory.openSession(true);
		List<Map> li;

		List objLI = sess.selectList("qry", stmt);

		return objLI;
	}

	@RequestMapping("/invokeSql")
	Object invokeSql(HttpServletRequest rq, HttpServletResponse rsps) throws IOException {
		String stmt = rq.getParameter("sql");
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

		SqlSession sess = sqlSessionFactory.openSession(true);
		List<Map> li;

		List objLI = sess.selectList("qry", stmt);

		return objLI;
	}
}
