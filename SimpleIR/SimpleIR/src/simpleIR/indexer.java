package simpleIR;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class indexer {
	String path;

	public indexer(String path) {
		super();
		this.path = path;
	}
	
	public void index_to_hashMap() throws IOException {
		HashMap<String , String> hashmap = new HashMap<>();
		
		File xmlfile = new File(path+"/index.xml");
		String t="";
		try{
	        @SuppressWarnings("resource")
			Scanner scan = new Scanner(xmlfile);
	        
	        while(scan.hasNextLine()){
	        	t += scan.nextLine();
	        	
	        }
	    }catch (FileNotFoundException e) {
	       
	    }
		int num=t.split("<doc id=").length-1;
		String[] id = new String[num];
		for(int i = 0;i<num;i++) {
			id[i] = t.split("<doc id=")[i+1].split("<body>")[1].split("</body>")[0];
		}
		
		for(int i = 0 ;i<num;i++) {
			for(int j=0;j<id[i].split("#").length;j++) {
				String key = id[i].split("#")[j].split(":")[0];
				String hv="";
				
				if(!hashmap.containsKey(key)) {
					double df=0,tf=0;;
					for (int k=0;k<num;k++) {
						
						for(int l =0 ; l<id[k].split("#").length;l++) {
							
							if(id[k].split("#")[l].split(":")[0].equals(key)) {
								df+=1;
								break;
							}	
						}
	
					}
					double w;
					
					for (int k=0;k<num;k++) {
						for(int l =0 ; l<id[k].split("#").length;l++) {
							if(id[k].split("#")[l].split(":")[0].equals(key)) {
								double value = Double.valueOf(id[k].split("#")[l].split(":")[1]);
								tf=value;
								w=tf*Math.log(num/df);
								hv+=String.valueOf(k)+" "+ String.format("%.2f", Math.round(w*1000)/1000.0)+" ";
							
							}	
						}
					}
					hashmap.put(key, hv);

				}
				
				
				
			}
			
		}
		
		
		FileOutputStream fileStream = new FileOutputStream(path+"/index.post");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileStream);
		
		
		
		objectOutputStream.writeObject(hashmap);
		
		objectOutputStream.close();
		
	}
	
	
}
