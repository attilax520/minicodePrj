package sprbtPKg;


/*
 * */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
 
 
@SpringBootApplication
public class Application   {
 
//  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//      return application.sources(Application.class);
//  }
 
  public static void main(String[] args) throws Exception {
      SpringApplication.run(Application.class, args);
      
      
      
      
  }
  
  
  @Configuration
 
  @ComponentScan(lazyInit = true)
  static class LocalConfig {
  }
}

