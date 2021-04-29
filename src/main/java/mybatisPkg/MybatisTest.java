package mybatisPkg;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class MybatisTest {

	public static void main(String[] args) throws Exception {
		// com.mysql.cj.jdbc.Driver
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

		SqlSession sess = sqlSessionFactory.openSession(true);
		List<Map> li;
		// = sess.selectList("stt2");
//System.out.println(JSON.toJSONString(li));
//
//li=	 sess.selectList("qry","select 2 t2");
//System.out.println(JSON.toJSONString(li));
//
		// mlt query,,cant use in mybatis3.4.6
		List<List<Map>> objLI = sess.selectList("qryMltQry2", "select 2 t2;select 3 t3;");
		System.out.println(JSON.toJSONString(objLI));
//pagging(sess);

	}

	private static void pagging(SqlSession sess) {
		// 设置分页条件，Parameters:pageNum 页码pageSize 每页显示数量count 是否进行count查询
		PageHelper.startPage(1, 3, true);
		// PageHelper.startPage(1,10);
		// 此时已经分页了
		List objLI = sess.selectList("qry", "select 2 t2;select 3 t3;");
		System.out.println(JSON.toJSONString(objLI));
		// 可以使用PageInfo 查看分页信息
		System.out.println(JSON.toJSONString(new PageInfo(objLI)));
	}

}
