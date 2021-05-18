package other;

 import org.apache.log4j.Logger;
 //from  log4j.ar
import org.apache.log4j.spi.LoggerFactory;

// import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
// fro log4j api.jar maybe for log4j not god
public class Log4jTet {

	public static void main(String[] args) {
		
	 Logger logger =  Logger.getLogger(Log4jTet.class);
		logger.info("ttt");
		logger.error("error");
	}

}
