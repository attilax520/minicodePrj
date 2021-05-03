package sprbtPKg;


/*
 * */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
 
 
@SpringBootApplication
public class ApplicationCli   {
 
//  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//      return application.sources(Application.class);
//  }
 
  public static void main(String[] args) throws Exception {
    
      new SpringApplicationBuilder(ApplicationCli.class)
      .web(WebApplicationType.NONE)
      .run(args); 
      
      System.out.println("ff");
      
    //also can use in cfg file
      //no include in pom 
      
  }
  
  
  @Configuration
 
  @ComponentScan(lazyInit = true)
  static class LocalConfig {
  }
}

