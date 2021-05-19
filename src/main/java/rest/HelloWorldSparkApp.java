package rest;

import static spark.Spark.*;

import org.apache.log4j.Logger;

import other.Log4jTet;

public class HelloWorldSparkApp {
	
    public static void main(String[] args) {
    	  Logger logger =  Logger.getLogger(Log4jTet.class);
    	//http://localhost:4567/hello
    	logger.info( "...start");
        get("/hello", (req, res) -> "Hello World222333");
    }
}

 