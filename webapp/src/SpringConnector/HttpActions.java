package SpringConnector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;

public class HttpActions {
	Links link = Links.POST;
	HttpOpener open;
	HttpRequest httpRequest;
	HttpResult result;
	public void Post() {
		 open = new HttpOpener();
	 httpRequest = new HttpRequest(HttpMethods.POST);
		 result = new HttpResult();
		httpRequest.setUrl("http://localhost:8080/POST");
		httpRequest.setContent(""+ System.lineSeparator()
				+ "public class Main{" + System.lineSeparator()
				+ "public static void main(String[]args){" + System.lineSeparator()
				+ "System.out.println(\"Hello World\");" + System.lineSeparator()
				+ "}" + System.lineSeparator()
				+ "}"+System.lineSeparator());
		httpRequest.setHeader("Content-Type", "text/plain");
		Gdx.net.sendHttpRequest(httpRequest, result);
		
		open.Open(link.getID());
	}
	
	public void Delete() {
		open = new HttpOpener();
		httpRequest = new HttpRequest(HttpMethods.DELETE);
		result = new HttpResult();
		httpRequest.setUrl("http://localhost:8080/DelPost");

		Gdx.net.sendHttpRequest(httpRequest, result);
	}
}
