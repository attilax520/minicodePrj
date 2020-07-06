package miniCodePrjPkg;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

//import com.wix.mysql.EmbeddedMysql;
//
//import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
//import static com.wix.mysql.ScriptResolver.classPathScript;
//import static com.wix.mysql.distribution.Version.v5_7_latest;

public class DslQueryCollList {
	public static void main(String[] args) throws Exception {

		// apache comm coll cant ,only array is ok..cant json_object eff
		// Map m=Maps.
		Map myMap = Maps.newHashMap(ImmutableMap.of("name", 999999999, "age", 22));
		Map myMap2 = Maps.newHashMap(ImmutableMap.of("name", 8888888, "age", 33));

		List li = new ImmutableList.Builder().add(myMap).add(myMap2).build();
		System.out.println(li);
		// /db/xx.sql
//	 EmbeddedMysql mysqld = anEmbeddedMysql(v5_7_latest)
//			    .addSchema("aschema", classPathScript("iniListCache.sql"))
//			    .start();

		// this just start..and u need a cliednt as common to conn..looks trouble than
		// sqlite
		String sql = " json_extract(jsonfld,'$.age')>30";

		List<Map<String, Object>> query = queryList(sql, li);
		System.out.println(query);
		// run.query(conn, sql, rsh)
	}

	private static List<Map<String, Object>> queryList(String sql_query, List li)
	
			throws ClassNotFoundException, SQLException, JsonProcessingException {
	sql_query="SELECT * FROM sys_data where "+sql_query;
	
		String sql = null;
		Class.forName("org.sqlite.JDBC");

		Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement stmt = c.createStatement();

		String sql2 = "drop TABLE sys_data ";
		exeUpdateSafe(stmt, sql2);
		sql2 = "CREATE TABLE sys_data (jsonfld json  )";
		exeUpdateSafe(stmt, sql2);

		// insert into facts values(json_object("mascot", "Our mascot is a dolphin name
		// sakila"));
		//
		for (Object object : li) {
			String jsonstr = new ObjectMapper().writeValueAsString(object);
			sql = "insert into sys_data values('" + jsonstr + "');";
			// sql = "insert into sys_data values('{\"id\":\"19\", \"name\":\"Lida\"}');";
			exeUpdateSafe(stmt, sql);

		}

		//sql = "SELECT json_extract(jsonfld,'$.name') as name1 FROM sys_data limit 1;";
	//	System.out.println(sql);
		QueryRunner run = new QueryRunner();
		// maphandler scare_handler
		System.out.println(sql_query);
		List<Map<String, Object>> query = run.query(c, sql_query, new MapListHandler());
		System.out.println(query);
		List li9=Lists.newArrayList();
		for (Map<String, Object> map : query) {
			li9.add(map.get("jsonfld"));
		}
		return li9;
	}

	private static void exeUpdateSafe(Statement stmt, String sql2) throws SQLException {
		try {
			System.out.println(sql2);
			System.out.println(stmt.executeUpdate(sql2));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
