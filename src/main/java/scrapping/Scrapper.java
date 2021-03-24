package scrapping;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlMeta;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Scrapper {

 
	public static void main(String[] args) {
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
		System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "ERROR");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "ERROR");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "ERROR");
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.INFO);
		java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.INFO);
		//3758196 nga  ne 6000000 duhen scrapur
		AtomicInteger atmoic = new AtomicInteger(5329436);

		for (int thread = 0; thread < 100; thread++) {

			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					for (; atmoic.get() < 6000000; atmoic.incrementAndGet()) {
						int index = atmoic.get();
						String searchUrl = "https://www.merrjep.al/njoftimi/automjete/makina/shitet/" +index;

						WebClient client = new WebClient();
						client.getOptions().setCssEnabled(false);
						client.getOptions().setJavaScriptEnabled(false);
						client.getOptions().setThrowExceptionOnScriptError(false);
						client.getOptions().setThrowExceptionOnScriptError(false);
						client.getOptions().setGeolocationEnabled(false);
						client.addRequestHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
						client.addRequestHeader("Accept-Charset", "UTF-8");
						client.getOptions().setThrowExceptionOnFailingStatusCode(false);
						client.getOptions().setThrowExceptionOnScriptError(false);
						client.getOptions().setPrintContentOnFailingStatusCode(false);

						// 1.1.
						client.addRequestHeader("Pragma", "no-cache"); // HTTP 1.0.
						client.addRequestHeader("Expires", "0"); //
						try {
							// + URLEncoder.encode("7380961", "UTF-8");
 							WebRequest webRequest = new WebRequest(new URL(searchUrl));
							webRequest.setCharset(Charset.forName("UTF-8"));
							HtmlPage page = client.getPage(webRequest);

							List<HtmlElement> itemss = page
									.getByXPath("//div[@class='col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12']");
							String txt = index + ",";
							for (HtmlElement htmlItem : itemss) {
								//System.out.print(htmlItem.asText().replaceAll("\\r|\\n", ",").trim() + ",");
								txt = txt + htmlItem.asText().replaceAll("\\r|\\n", "").trim() + ",";
							}

							List<HtmlElement> items = page.getByXPath("//div[@class='card-body']");
							if (items.isEmpty()) {
								//System.out.println("No items found !");
							} else {
								for (HtmlElement htmlItem : items) {
									//System.out.print(htmlItem.asText().replaceAll("\\r|\\n", ",") + ",");
									txt = txt + htmlItem.asText().replaceAll("\\r|\\n", "") + ",";
								}
							}

							if (txt.length() > 15 && txt.contains("Transmetuesi"))
							{								System.out.println(index);

								writeFile1(txt, Thread.currentThread().getId());

							}

							client.close();
						} 
					
						catch (Exception e) {
 						}
						finally
						{
							if(client !=null)
							client.close();
						}
					}
				}
			}).start(); 
		}

	}

	public static synchronized void writeFile1(String txt, long thread) throws IOException {
		File fout = new File("out" + thread + ".csv");
		txt =txt.replace("Lloji i njoftimit", ",");
		txt =txt.replace("Reklamuar nga", ",");
		txt =txt.replace("Viti", ",");
		txt =txt.replace("Transmetuesi", ",");
		txt =txt.replace("Kilometrazha", ",");
		txt =txt.replace("Karburanti", ",");
		txt =txt.replace("Targa", ",");
		txt =txt.replace("Ngjyra", ",");
		txt =txt.replace("Komuna", ",");
		txt =txt.replace("Struktura e veturës", ",");
		txt =txt.replace("Prodhuesi", ",");
		txt =txt.replace("Modeli", ",");
		
		  
		 
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fout, true), "UTF-8"));

		writer.append(txt + "\n");

		writer.close();
	}
	
}
