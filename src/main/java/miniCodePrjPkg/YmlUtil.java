package miniCodePrjPkg;

import org.checkerframework.checker.units.qual.Speed;//
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yaml.snakeyaml.Yaml;

import com.google.common.collect.Maps;

//@SpringBootApplication
public class YmlUtil {
	public static void main(String[] args) {
		//Maps.newLinkedHashMap()
		  Yaml yaml = new Yaml();
		Object ret =  yaml.load(YmlUtil.class.getClassLoader()
		            .getResourceAsStream("bootstrap.yml"));
		    System.out.println(ret);
		    
		    
		    ExpressionParser parser = new SpelExpressionParser();  
		    EvaluationContext context3 = new StandardEvaluationContext();  
		    context3.setVariable("map8", ret);  
		    Object result3 = new SpelExpressionParser().parseExpression("#map8['spring']['profiles']['active']").getValue(context3);
		System.out.println(result3);
		    System.out.println("f");
	}

}
