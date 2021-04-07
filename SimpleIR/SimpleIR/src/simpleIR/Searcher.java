package simpleIR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;

public class Searcher {
	String path;
	String query;
	public Searcher(String path) {
		super();
		this.path = path;

	}
	
	public void CalcSim() throws IOException, ClassNotFoundException {
		FileInputStream fileStream = new FileInputStream(path+"/index.post");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileStream);
		
		Object object = objectInputStream.readObject();
		
//		System.out.println("읽어올 객체의 type -> " + object.getClass());
		
		HashMap hashmap =(HashMap)object;
		Iterator<String> it =hashmap.keySet().iterator();
		
		int id_num=0;
		while(it.hasNext()) {
			String key = it.next();
			String value = (String)hashmap.get(key);
			if(key=="초밥") {
				break;
			}
			if(id_num<Integer.valueOf(value.split(" ")[value.split(" ").length-2])) {
				id_num=Integer.valueOf(value.split(" ")[value.split(" ").length-2]);
			}
			
		}
		System.out.println("문서의 개수는 " + (id_num+1) + "개 입니다.");
		Scanner sc = new Scanner(System.in);
		System.out.println("뭐리를 입력하세요");
		query = sc.nextLine();
		

		String all_key="";
		
		KeywordExtractor ke = new KeywordExtractor();
		KeywordList kl = ke.extractKeyword(query, true);
		for (int j = 0 ;j<kl.size();j++) {
			Keyword kword = kl.get(j);
			all_key += kword.getString()+ " 1 ";	
		}
		
		int key_num=all_key.split(" ").length/2;
		
		System.out.println(all_key);
		
		double[] q = new double[id_num+1];
		
			for(int j = 0;j<key_num;j++) {
				String key=all_key.split(" ")[j*2];
				if(hashmap.containsKey(key)) {
					for(int k = 0; k<((String)hashmap.get(key)).split(" ").length/2;k++) {
						q[Integer.valueOf(((String)hashmap.get(key)).split(" ")[k*2])]+=Double.valueOf(((String)hashmap.get(key)).split(" ")[k*2+1]);
					}
					
				}
					
			}
		double[] q_sort = q.clone();
		File xmlfile = new File(path+"/collection.xml");
		String t="";
		String[] id_title= new String[q.length]; 
		try{
	        @SuppressWarnings("resource")
			Scanner scan = new Scanner(xmlfile);
	        
	        while(scan.hasNextLine()){
	        	t += scan.nextLine();
	        	
	        }
	    }catch (FileNotFoundException e) {
	       
	    }
		for(int i=0;i<q.length;i++) {
			id_title[i]=t.split("<title>")[i+1].split("</title>")[0];
		}
		
		
		int[] max= {0,0,0};
		for(int i=0;i<3;i++) {
			for(int j=0;j<q.length;j++) {
				if(q[max[i]]<q[j]) {
					max[i]=j;
				}
			}
			q[max[i]]=-1;
		}
		for(int i=0;i<3;i++) {
			if(max[i]>=0)
				System.out.println((i+1)+"."+id_title[max[i]]+" (유사도:"+q_sort[max[i]]+")");
		}
		
		

		
	}

}
