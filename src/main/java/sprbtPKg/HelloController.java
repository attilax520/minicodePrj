package sprbtPKg;

 
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import other.Log4jTet;

@RestController
public class HelloController {
 
	
	@Autowired
	SqlSession SqlSession1;

    @RequestMapping("/db")
    Object db() {
    	
    	  Logger logger =  Logger.getLogger(Log4jTet.class);
    	  logger.info( SqlSession1.selectList("qry1","select 1 t1"));
    	
    //	org.sqlite.JDBC
      //  return   SqlSession1.selectList("qry1","select 2 t22");
    	SqlSessionFactory SqlSessionFactory2=	(SqlSessionFactory) Application.cfgAppContext.getBean("sqlSessionFactoryDb2");
    	SqlSession SqlSession2=SqlSessionFactory2.openSession(true);
    	return   SqlSession2.selectList("qry1","select 3 t33");
    }
    
    

    @RequestMapping("/")
    String hello() {
        return "Hello World!";
    }
    
    
    @RequestMapping("/m2")
    String m2() {
        return "m2 out !";
    }

//    @Data
//    static class Result {
//        private final int left;
//        private final int right;
//        private final long answer;
//    }

//    // SQL sample
//    @RequestMapping("calc")
//    Result calc(@RequestParam int left, @RequestParam int right) {
//        MapSqlParameterSource source = new MapSqlParameterSource()
//                .addValue("left", left)
//                .addValue("right", right);
//        return jdbcTemplate.queryForObject("SELECT :left + :right AS answer", source,
//                (rs, rowNum) -> new Result(left, right, rs.getLong("answer")));
//    }
}
