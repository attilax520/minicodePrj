package rest;
 

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.FatalBeanException;

 

public class Tomcat85Start {

	//TomcatV6U66
		public static void main(String[] args) throws LifecycleException, ServletException {
			
 
			System.out.println("---start");
			
			Tomcat tomcat=new Tomcat();	
			tomcat.setPort(80);
			
			
			//set basedir
		 	String basedir = ".";
			tomcat.setBaseDir(basedir);	
			Context Context1_Webapp=	tomcat.addWebapp("/", basedir);//addWebapp()过时了		
			
		// 	FatalBeanException
			
			
			//--------add svlt rest api
			
		//	String servletName = "servletName";
		//	tomcat.addServlet(Context1_Webapp, servletName,  new MvcServlet());
		//	Context1_Webapp.addServletMapping("/halo",servletName);//配置servlet映射路径
			
			
			// jsp support  and htm suport already
			
			
			//start
			tomcat.start();
			tomcat.getServer().await();
			System.out.println("f");
		}
		
		
		public static void iniRedisData() {
//			// 池基本配置 
//	        JedisPoolConfig config = new JedisPoolConfig(); 
////	        config.setMaxActive(20); 
////	        config.setMaxIdle(5); 
////	        config.setMaxWait(1000l); 
//	        config.setTestOnBorrow(false); 
//	        // slave链接 
//	        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(); 
//	        shards.add(new JedisShardInfo("127.0.0.1", 6379, "master")); 
//
//	        // 构造池 
//	        ShardedJedisPool    shardedJedisPool = new ShardedJedisPool(config, shards); 
//			
//			application.redis.helper.CacheHelper cacheHelper = new application.redis.helper.CacheHelper();
//			cacheHelper.setShardedJedisPool(shardedJedisPool);
//			
//			  Jedis jedis = new Jedis("localhost");
//			  jedis.set("LHC_NEED_11086", "1");
		}
}
