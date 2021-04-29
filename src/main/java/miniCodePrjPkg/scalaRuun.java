package miniCodePrjPkg;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class scalaRuun {
 
	public static void main(String[] args) throws FileNotFoundException, ScriptException {
	
		String fileName = "C:\\Users\\user\\git\\minicodePrj\\src\\main\\java\\miniCodePrjPkg\\Sample.scala";
	//	fileName=args[0].trim();
		ScriptEngine engineByName = new ScriptEngineManager().getEngineByName("scala");
		Object object=engineByName
				.eval(new FileReader(fileName));

      System.out.println(object);

	}

}
