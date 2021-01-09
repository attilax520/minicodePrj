package miniCodePrjPkg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller  javax.servlet.ServletException: Circular view path [error]:   or   this class not in app path..or need add scan path
@RestController

@CrossOrigin
public class HomeController {

    @RequestMapping("/")
    @ResponseBody
    Object index() {
    	List li=new ArrayList() {{
    		
    		this.add(new HashMap () {{
    			this.put("id", 1);this.put("age", 1111);
    		}});
    		this.add(new HashMap () {{
    			this.put("id", 2);this.put("age", 2222);
    		}});
    		
    	}};
        return  li;
    }
    
    
    
    @RequestMapping("/halo")
    @ResponseBody
    String halo() {
        return "Hello World! - ken.io";
    }
}