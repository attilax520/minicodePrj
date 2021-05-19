package rest;


import com.jfinal.core.Controller;
public class HelloController extends Controller {
 
	/**
	 * 默认路径
	 */
    public void index() {
        render("/index.jsp");
    }
 
    /**
     * 指定路径,route里的url+/hello
     */
    public void hello() {
        renderText("Hello JFinal World......");
    }
}
 