package simpleIR;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class main {

	public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, ClassNotFoundException {
		System.out.println("202011331 이경민");
		String path=args[1];
		
		makeCollection collection = new makeCollection(path);
		collection.html_to_xml();
		
		makeKeyword output = new makeKeyword(path);
		makeKeyword.collcection_to_index();
		
		indexer index = new indexer(path);
		index.index_to_hashMap();
		
//		readPost readpost = new readPost(path);
//		readpost.read_Post();
	}

}
