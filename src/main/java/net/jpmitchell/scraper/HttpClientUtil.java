package net.jpmitchell.scraper;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpClientUtil {
    public static String get(String url){
        return get(url, null);
    }
	public static String get(String url, String cookie){
		String content = null;
		HttpClient client = new HttpClient();
		//url = "http://google.com";
		client.getHostConfiguration().setProxy("localhost", 8888);

		
		GetMethod method = new GetMethod(url);
		if(cookie != null) method.setRequestHeader("Cookie", cookie);
		method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.52 Safari/536.5");
		
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			content = new String(method.getResponseBodyAsString());			
		
		

		} catch (Exception e) {
			System.err.println("Exception" + e);
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		
		}  
		return content;
		
	}
	
	
	public static void downloadFile(String url,  String fileName, String dir){
	    downloadFile(url, null, fileName, dir);
	}
	public static void downloadFile(String url, String cookie, String fileName, String dir){
		
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);
		FileOutputStream out = null;
		client.getHostConfiguration().setProxy("localhost", 8888);
		if(cookie != null) method.setRequestHeader("Cookie", cookie);
		method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.52 Safari/536.5");
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}
			
			byte[] buf = new byte[1024];
			InputStream torrentIn = method.getResponseBodyAsStream();
			out = new FileOutputStream(dir + fileName);
			
			int n = 0;
			while((n=torrentIn.read(buf))>0){
				out.write(buf, 0, n);
			}
			out.close();
			

		} catch (Exception e) {
			System.err.println("Exception" + e);
			e.printStackTrace();
		} finally {
			if(out != null) try {
				out.close();
			}catch(Exception e){
				System.err.println("Exception" + e);
				e.printStackTrace();
			}
			
			// Release the connection.
			method.releaseConnection();
		
		}  
		
		
	}

}
