package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class Dbutil {

	static Logger logger = LoggerFactory.getLogger(Dbutil.class);

	public static List<Map<String, Object>> query(String sql, DataSource ds) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		logger.info(sql);
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql);
		logger.info("queryForList:" + queryForList.size());
		if (queryForList.size() <= 10)
			logger.info(JSON.toJSONString(queryForList, true));
		return queryForList;

	}

	public static int insert(DriverManagerDataSource dataSource, String tableName, Map<String, Object> parameters_map) {
		SimpleJdbcInsert SimpleJdbcInsert1 = new SimpleJdbcInsert(dataSource).withTableName(tableName);
		Set st = parameters_map.keySet();

		List<String> columnNames = new ArrayList<String>();
		columnNames.addAll(st);
		SimpleJdbcInsert1.setColumnNames(columnNames);
		// SimpleJdbcInsert1.usingColumns(columnNames)
		int i = SimpleJdbcInsert1.execute(parameters_map);
		return i;
	}

	public static Object updt(DriverManagerDataSource dataSource, String tableName, Map parameterMapFstVal) {
		String sql = "update " + tabNameChk(tableName) + "Set " + setparam(parameterMapFstVal) + " where "
				+ whereExp(parameterMapFstVal);
		JdbcTemplate jt = new JdbcTemplate(dataSource);

		return jt.update(sql);
	}

	/**
	 * only ._- use..
	 * 
	 * @param tableName
	 * @return
	 */
	private static String tabNameChk(String tableName) {
		if (tableName.contains("espcharxxxx"))
			throw new RuntimeException("tabname err" + tableName);
		return tableName;
	}

	private static String setparam(Map parameterMapFstVal) {
		List lst = Lists.newArrayList();
		parameterMapFstVal.forEach(new BiConsumer<String, String>() {

			@Override
			public void accept(String k, String v) {
				lst.add(k + "=" + sqlParamEncodeStr(v));

			}
		});
		return Joiner.on(",").join(lst);
	}

	protected static String sqlParamEncodeStr(String v) {

		return "'" + v.replaceAll("'", "''") + "'";
	}

//   ?$stmt=updt$tab1&c=1&$where$d=1&$where$e=3
	/**
	 * return d=1 and e=3
	 * 
	 * @param parameterMapFstVal
	 * @return
	 */
	private static String whereExp(Map parameterMapFstVal) {
		List lst = Lists.newArrayList();
		parameterMapFstVal.forEach(new BiConsumer<String, String>() {

			@Override
			public void accept(String k, String v) {
				if (k.startsWith("$where"))
					k = k.substring(6);// remove $where$
				lst.add(k + "=" + sqlParamEncodeStr(v));

			}
		});
		return Joiner.on(" and ").join(lst);
	}
	
	private static String whereExpSmpl(Map parameterMapFstVal) {
		List lst = Lists.newArrayList();
		parameterMapFstVal.forEach(new BiConsumer<String, String>() {

			@Override
			public void accept(String k, String v) {
				 
				lst.add(k + "=" + sqlParamEncodeStr(v));

			}
		});
		return Joiner.on(" and ").join(lst);
	}

	public static Object del(DriverManagerDataSource dataSource, String tableName, Map parameterMapFstVal) {
		String sql = "delete  from " + tabNameChk(tableName) +  " where "
				+ whereExp(parameterMapFstVal);
		JdbcTemplate jt = new JdbcTemplate(dataSource);

		return jt.update(sql);
	}

	public static Object qry(DriverManagerDataSource dataSource, String tableName, Map parameterMapFstVal) {
		String sql = "select *  from " + tabNameChk(tableName) +  " where "
				+ whereExpSmpl(parameterMapFstVal);
		JdbcTemplate jt = new JdbcTemplate(dataSource);

		List<Map> queryForList = jt.queryForList(sql, Map.class);
		return queryForList;
	}

}
