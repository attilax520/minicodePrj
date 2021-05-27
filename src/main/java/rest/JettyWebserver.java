package rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.webapp.AbstractConfiguration;
import org.eclipse.jetty.webapp.Configuration;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

import com.fasterxml.classmate.AnnotationConfiguration;
import  rest.HtmlSvlt;

public class JettyWebserver {
	
	/**
	 * <pre>
	 *     一个简单的 jetty 服务
	 * </pre>
	 *
	 * @author saligia
	 * @date 17-10-10
	 */
 
	    public static void main(String [] args) throws Exception {
	      //  int port = 13140;
	    //    int port=Integer.parseInt(args[0].trim()) ;
			Server server = new Server(80);  // 创建一个 jetty 服务
//	        server.setHandler( new AbstractHandler () {
//
//				@Override
//				public void handle(String target, Request rq, HttpServletRequest htrq, HttpServletResponse rsp)
//						throws IOException, ServletException {
//					   System.out.println("target  :" + target);
//				        System.out.println("request : " + rq.getRequestURI());
//				        System.out.println("requestServ : " + htrq.getRequestURI());
//					
//				}});
	        
	        WebAppContext context = new WebAppContext();
	        context.setResourceBase("webapps/");
	    //    context.setDescriptor("web/WEB-INF/web.xml");
	        context.setConfigurations(new Configuration[]{
	                   new WebXmlConfiguration(),
	                    new WebInfConfiguration(), 
	             new MetaInfConfiguration(),
	                    new FragmentConfiguration(),new JettyWebXmlConfiguration()});
	     //   AbstractConfiguration
	     
	        //  ,  new AnnotationConfiguration(), new EnvConfiguration()

	        context.setContextPath("/");
	        context.setParentLoaderPriority(true);
	        server.setHandler(context);
			
			
//------------ add svlt is ok.....			
			ServletContextHandler context2 = new ServletContextHandler(ServletContextHandler.SESSIONS);
			context2.setContextPath("/ct2");
			context2.addServlet(new ServletHolder(new rest.HtmlSvlt()), "/html");
		//	context.addServlet(new ServletHolder(new Iphone()), "/iphone");       
			server.setHandler(context2);
	        
	        server.start();
	        server.dumpStdErr();
	    
	        server.join();
	    
	}
	    
	    /**
	     * <pre>
	     * </pre>
	     *
	     * @author
	     * @param target              - 目标请求，可以是一个URI或者是一个转发到这的处理器的名字
	     * @param request             - Jetty自己的没有被包装的请求，一个可变的Jetty请求对象
	     * @param httpServletRequest  - 被filter或者servlet包装的请求，一个不可变的Jetty请求对象
	     * @param httpServletResponse - 响应，可能被filter或者servlet包装过
	     * @return
	     */

}
