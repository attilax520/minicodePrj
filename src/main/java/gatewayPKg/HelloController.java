package gatewayPKg;

 
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
//    @Autowired
//    NamedParameterJdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    String direct2(HttpServletRequest rq) throws  Exception {
    	//网关的其他效验  日志 权限 流量控制等
    	String targetHost="http://127.0.0.1:8080";
    	
    	String uri=rq.getRequestURI();
    	String query=rq.getQueryString();     
          String newUrl = targetHost+uri+"?"+query;
		CloseableHttpResponse response =HttpClients.createDefault().execute(new HttpGet(newUrl)) ;    

        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }
    
    // Http://localhost/direct?uri=/xxxxx&p1=1&p2=22
    @RequestMapping("/direct")
    String direct(HttpServletRequest rq) throws  Exception {
    	
    	//网关的其他效验  日志 权限 流量控制等
    	String targetHost="http://127.0.0.1:8080";
    	
    	String uri=rq.getParameter("uri");
    	String query=rq.getQueryString();     
          String newUrl = targetHost+uri+"?"+query;
		CloseableHttpResponse response =HttpClients.createDefault().execute(new HttpGet(newUrl)) ;    

        return EntityUtils.toString(response.getEntity(), "UTF-8");
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
