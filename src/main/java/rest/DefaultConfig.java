package rest;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.template.Engine;
 
 
public class DefaultConfig extends JFinalConfig {
 
	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);  
	}
 
	@Override
	public void configHandler(Handlers arg0) {
		// TODO Auto-generated method stub
		
	}
 
	@Override
	public void configInterceptor(Interceptors arg0) {
		// TODO Auto-generated method stub
		
	}
 
	@Override
	public void configPlugin(Plugins arg0) {
		// TODO Auto-generated method stub
		
	}
 
	@Override
	public void configRoute(Routes me) {
		 me.add("/hlCtrl", HelloController.class);  
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub
		
	}
 

 

}
