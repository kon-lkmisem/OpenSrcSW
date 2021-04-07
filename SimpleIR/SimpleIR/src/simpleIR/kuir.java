package simpleIR;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class kuir {

	public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, ClassNotFoundException {
		System.out.println("202011331 이경민");
		String i =args[0];
		String path=args[1];
		
		if (i.equals("-c")) {
			makeCollection collection = new makeCollection(path);
			collection.html_to_xml();
		} else if (i.equals("-k")) {
			makeKeyword output = new makeKeyword(path);
			makeKeyword.collcection_to_index();
		} else if (i.equals("-i")) {
			indexer index = new indexer(path);
			index.index_to_hashMap();
		} else if (i.equals("-s")) {
			Searcher seacher = new Searcher(path);
			seacher.CalcSim();
		}
		
		
		
		
		
		
		
		
		
	}

}
