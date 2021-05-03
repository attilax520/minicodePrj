package mybatisPkg;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import util.HttpUtilV5v44;

public class MybatisTest {

	public static void main(String[] args) throws Exception {
		// com.mysql.cj.jdbc.Driver
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

		SqlSession sess = sqlSessionFactory.openSession(true);
		List<Map> li;
		//quryBySql();
		
		qryByRq(sess);
		
//		mltQry(sess);
//pagging(sess);

	}
	@Test
	public   void test_qryByRqFstval () throws Exception {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

		SqlSession sess = sqlSessionFactory.openSession(true);
		List<Map> li;
		MockHttpServletRequest req=new MockHttpServletRequest();
		req.setParameter("p1", "1'23");req.setParameter("p2", "22");
		List objLI = sess.selectList("qryByRqFstval",HttpUtilV5v44.getParamMap2Mybatis( req));
		System.out.println(JSON.toJSONString(objLI));
		
	}

	private static void qryByRq(SqlSession sess) {
		MockHttpServletRequest req=new MockHttpServletRequest();
		req.setParameter("p1", "1'23");req.setParameter("p2", "22");
		List objLI = sess.selectList("qryByRq", req.getParameterMap());
		System.out.println(JSON.toJSONString(objLI));
		
	}

	private static void quryBySql() {
		// = sess.selectList("stt2");
		//System.out.println(JSON.toJSONString(li));
		//
		//li=	 sess.selectList("qry","select 2 t2");
		//System.out.println(JSON.toJSONString(li));
		System.out.println(1);
	}

	private static void mltQry(SqlSession sess) {
		// mlt query,,cant use in mybatis3.4.6
		List<List<Map>> objLI = sess.selectList("qryMltQry2", "select 2 t2;select 3 t3;");
		System.out.println(JSON.toJSONString(objLI));
	}

	private static void pagging(SqlSession sess) {
		// ���÷�ҳ������Parameters:pageNum ҳ��pageSize ÿҳ��ʾ����count �Ƿ����count��ѯ
		PageHelper.startPage(1, 3, true);
		// PageHelper.startPage(1,10);
		// ��ʱ�Ѿ���ҳ��
		List objLI = sess.selectList("qry", "select 2 t2;select 3 t3;");
		System.out.println(JSON.toJSONString(objLI));
		// ����ʹ��PageInfo �鿴��ҳ��Ϣ
		System.out.println(JSON.toJSONString(new PageInfo(objLI)));
	}

}
