package sprbtPKg;


import org.apache.commons.io.IOUtils;

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
 
 
@SpringBootApplication
public class ApplicationCli   {
 
//  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//      return application.sources(Application.class);
//  }
 
  public static void main(String[] args) throws Exception {
	  IOUtils.readLines(input)
    
	  ConfigurableApplicationContext caContext1=    new SpringApplicationBuilder(ApplicationCli.class)
      .web(WebApplicationType.NONE)
      .run(args); 
      
      StringRedisTemplate  srt=caContext1.getBean(StringRedisTemplate.class);
      
      srt.opsForValue().append("msg","hello");
      
  //    srt.ops
      System.out.println("ff");
      
    //also can use in cfg file
      //no include in pom 
      
  }
  
  
  @Configuration
 
  @ComponentScan(lazyInit = true)
  static class LocalConfig {
  }
}

