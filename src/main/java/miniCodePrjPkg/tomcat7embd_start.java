package miniCodePrjPkg;

import javax.servlet.ServletException;

//package tomcat7embd;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class tomcat7embd_start {

	//TomcatV6U66
		public static void main(String[] args) throws LifecycleException, ServletException {
			
		//	javax.servlet
			//javax.servlet.GenericServlet 
			//javax.servlet.
			
		  	System.out.println(javax.servlet.GenericFilter.class);
			System.out.println("-start");
			
			Tomcat tomcat=new Tomcat();	
			tomcat.setPort(8080);
			
			
			//set basedir
		 	String basedir = ".";  //  dot . is cur prj dir   ,just add webapps dir is ok
			 tomcat.setBaseDir(basedir);	
			Context Context1_Webapp=	tomcat.addWebapp("/", basedir);//addWebapp()过时了		
			
			
			
			
			//--------add svlt rest api
			
			String servletName = "servletName";
			tomcat.addServlet(Context1_Webapp, servletName,  new MvcServlet());
			Context1_Webapp.addServletMapping("/halo",servletName);//配置servlet映射路径
			
			
			// jsp support  and htm suport already
			
			
			//start
			tomcat.start();
			tomcat.getServer().await();
			System.out.println("f");
		}
}



