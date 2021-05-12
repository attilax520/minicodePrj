package rdsPkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdxController {
//    @Autowired
//    NamedParameterJdbcTemplate jdbcTemplate;

	@RequestMapping("/")
	String hello() {
		return "Hello World!";
	}

	@Autowired

	ConfigurableApplicationContext caContext1;

	@RequestMapping("/set")
	String set(String k, String v) {
		StringRedisTemplate srt = (StringRedisTemplate) caContext1.getBean(StringRedisTemplate.class);
		srt.opsForValue().set(k, v);
		return "m2 out !";
	}

	@RequestMapping("/get")
	String set(String k) {

		StringRedisTemplate srt = (StringRedisTemplate) caContext1.getBean(StringRedisTemplate.class);

		return srt.opsForValue().get(k);
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
