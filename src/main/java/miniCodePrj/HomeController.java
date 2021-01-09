package miniCodePrj;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller  javax.servlet.ServletException: Circular view path [error]:   or   this class not in app path..or need add scan path
@RestController
public class HomeController {

    @RequestMapping("/")
    @ResponseBody
    String index() {
        return "Hello World! - ken.io";
    }
    
    
    
    @RequestMapping("/halo")
    @ResponseBody
    String halo() {
        return "Hello World! - ken.io";
    }
}