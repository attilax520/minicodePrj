package rdsPkg;

import java.util.Base64;

/*
 * */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSONObject;

@SpringBootApplication
public class ApplicationRds {

	
	//http://localhost:83/get?k=k1
//  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//      return application.sources(Application.class);
//  }

	public static void main(String[] args) throws Exception {
	 
		ConfigurableApplicationContext caContext1 = new SpringApplicationBuilder(ApplicationRds.class)
				.web(WebApplicationType.SERVLET).run(args);

	 

		// srt.opsForValue().append("msg","hello");

		// srt.ops
		
		System.out.println("ff");

		// also can use in cfg file
		// no include in pom

	}

	@Configuration

	@ComponentScan(lazyInit = true)
	static class LocalConfig {
	}
}
