package util;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.fastjson.JSON;

public class Dbutil {

	static	Logger logger = LoggerFactory.getLogger(Dbutil.class);
		public	static List<Map<String, Object>> query(String sql,DataSource ds) {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
			logger.info(sql);
			List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql);
			logger.info("queryForList:" + queryForList.size());
			if (queryForList.size() <= 10)
				logger.info(JSON.toJSONString(queryForList, true));
			return queryForList;
		 
	}

}
