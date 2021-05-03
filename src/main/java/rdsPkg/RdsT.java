package rdsPkg;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RdsT {

	public static void main(String[] args) throws  Exception {
		
		String url="http://localhost:83/set?k=k12&v=v1222222";
		CloseableHttpResponse response =HttpClients.createDefault().execute(new HttpGet(url)) ;    

        System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8")); ;
        
        
		  url="http://localhost:83/get?k=k11";
		  response =HttpClients.createDefault().execute(new HttpGet(url)) ;    

        System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8")); ;

	}

}
