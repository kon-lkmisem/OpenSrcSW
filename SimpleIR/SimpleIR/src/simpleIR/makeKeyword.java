package simpleIR;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class makeKeyword {
static String path;
	
	public makeKeyword(String string) {
		path = string;
	}

	static void collcection_to_index() throws ParserConfigurationException, FileNotFoundException, TransformerException {  
	File xmlfile = new File(path+"/collection.xml");
	String t="";
	try{
        @SuppressWarnings("resource")
		Scanner scan = new Scanner(xmlfile);
        
        while(scan.hasNextLine()){
        	t += scan.nextLine();
        	
        }
        //System.out.println(t);
    }catch (FileNotFoundException e) {
       
    }
	
	String[] kkma = new String[t.split("<doc id=").length-1];
	
	for(int i =0 ; i<kkma.length;i++) {
		kkma[i]=t.split("<doc id=")[i+1].split("<title>")[1].split("</title>")[0]+"\n";
		String temps=t.split("<doc id=")[i+1].split("<body>")[1].split("</body>")[0];
		KeywordExtractor ke = new KeywordExtractor();
		KeywordList kl = ke.extractKeyword(temps, true);
		for (int j = 0 ;j<kl.size();j++) {
			Keyword kword = kl.get(j);
			kkma[i] += kword.getString()+ ":" +kword.getCnt()+"#";
			
		}
		//System.out.println(kkma[i]);
	}
	
	DocumentBuilderFactory docFacroty1 = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder1 = docFacroty1.newDocumentBuilder();
	
	Document xml1 = docBuilder1.newDocument();
	
	Element docs1 = xml1.createElement("docs");
	xml1.appendChild(docs1);
		
	for(int i = 0 ; i<kkma.length;i++) {
		Element doc = xml1.createElement("doc");
		docs1.appendChild(doc);
		doc.setAttribute("id", String.valueOf(i));
		
		Element title = xml1.createElement("title");
		title.appendChild(xml1.createTextNode(kkma[i].split("\n")[0]));
		doc.appendChild(title);
		
		Element body = xml1.createElement("body");
		body.appendChild(xml1.createTextNode(kkma[i].split("\n")[1]));
		doc.appendChild(body);
	}
	TransformerFactory transformerFactory1 = TransformerFactory.newInstance();
	
	Transformer transformer1 = transformerFactory1.newTransformer();
	transformer1.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	
	DOMSource source1 = new DOMSource(xml1);
	StreamResult result1 = new StreamResult(new FileOutputStream(new File(path+"/index.xml")));
	
	transformer1.transform(source1, result1);
	}
}
