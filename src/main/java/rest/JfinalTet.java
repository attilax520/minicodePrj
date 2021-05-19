package rest;

import com.jfinal.core.JFinal;

public class JfinalTet {

	public static void main(String[] args) {
		 
			int scanIntervalSeconds = 5;
			JFinal.start("WebRoot", 8080, "/", scanIntervalSeconds);
		 

	}

}
