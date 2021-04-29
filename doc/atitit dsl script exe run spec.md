atitit dsl script exe run spec

c.txt
##ScriptEngineManager spec...


	public static void main(String[] args) throws FileNotFoundException, ScriptException {
	
		String fileName = "C:\\Users\\user\\git\\minicodePrj\\src\\main\\java\\miniCodePrjPkg\\Sample.scala";
	//	fileName=args[0].trim();
		ScriptEngine engineByName = new ScriptEngineManager().getEngineByName("scala");
		Object object=engineByName
				.eval(new FileReader(fileName));

      System.out.println(object);

	}
	
	package miniCodePrjPkg;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class groovyRun {

	public static void main(String[] args) throws FileNotFoundException, ScriptException {
	
		String fileName = "C:\\Users\\user\\git\\minicodePrj\\src\\main\\java\\miniCodePrjPkg\\groovy1.groovy";
	//	fileName=args[0].trim();
		Object object=new ScriptEngineManager().getEngineByName("groovy")
				.eval(new FileReader(fileName));

      System.out.println(object);

	}

}
	

	
	##  java  scriptExplain.jar    xxx.scrpt
	
	
	## APACHE COMMOND API