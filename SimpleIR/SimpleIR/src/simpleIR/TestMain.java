package simpleIR;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestMain {

	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
		String path = "input";
		File file = new File(path);
		File list[] = file.listFiles();
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ArrayList<File> h_list = new ArrayList();
		for(int i = 0; i<list.length; i++) {
			System.out.println(list[i]);
			if (list[i].toString().contains(".html")){
				h_list.add(list[i]);
			}
		}
		String content[] = new String[h_list.size()];
		for(int i = 0;i<h_list.size();i++) {
			try{
	            Scanner scan = new Scanner(h_list.get(i));
	            while(scan.hasNextLine()){
	            	String a = scan.nextLine();
	                if(a.contains("<title>")) {
	                	content[i]= a.split("<title>")[1].split("</title>")[0]+"\n";
	                }
	                if(a.contains("<p>")) {
	                	content[i]+= a.split("<p>")[1].split("</p>")[0];
	                }
	            }
	          
	        }catch (FileNotFoundException e) {
	           
	        }
			
			System.out.println(content[i]);

		}
		
		DocumentBuilderFactory docFacroty = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFacroty.newDocumentBuilder();
		
		Document xml = docBuilder.newDocument();
		
		Element docs = xml.createElement("docs");
		xml.appendChild(docs);
			
		for(int i = 0 ; i<content.length;i++) {
			Element doc = xml.createElement("doc");
			docs.appendChild(doc);
			doc.setAttribute("id", String.valueOf(i));
			
			Element title = xml.createElement("title");
			title.appendChild(xml.createTextNode(content[i].split("\n")[0]));
			doc.appendChild(title);
			
			Element body = xml.createElement("body");
			body.appendChild(xml.createTextNode(content[i].split("\n")[1]));
			doc.appendChild(body);
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
		DOMSource source = new DOMSource(xml);
		StreamResult result = new StreamResult(new FileOutputStream(new File("result/collection.xml")));
		
		transformer.transform(source, result);
	}

}
