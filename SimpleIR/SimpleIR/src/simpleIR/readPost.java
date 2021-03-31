package simpleIR;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;

public class readPost {
	String path;

	public readPost(String path) {
		super();
		this.path = path;
	}
	
	public void read_Post() throws IOException, ClassNotFoundException {
		FileInputStream fileStream = new FileInputStream(path+"/index.post");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileStream);
		
		Object object = objectInputStream.readObject();
		
		System.out.println("읽어올 객체의 type -> " + object.getClass());
		
		HashMap hashmap =(HashMap)object;
		Iterator<String> it =hashmap.keySet().iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			String value = (String)hashmap.get(key);
			System.out.println(key+" --> "+value);
		}
		
	}

}
