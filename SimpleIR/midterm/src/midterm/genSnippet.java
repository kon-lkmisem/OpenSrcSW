package midterm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class genSnippet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 파일을 입력할떄는 그 파일이 위치한 폴더명을 적어야합니다.
		// 예시 -f input -q 라면 물
		int f = 0 ,q=0;
		
		for(int i=0;i<args.length;i++) {
			if(args[i].equals("-f")) {
				f=i;
			}else if(args[i].equals("-q")) {
				q=i;
			}
		}
		String text ="";
		
		for(int i= q+1;i<args.length;i++) {
			text+=args[i]+" ";
		}
		
		
		File file = new File(args[f+1]+"/input.txt");
		String t="";
		try{
	        @SuppressWarnings("resource")
			Scanner scan = new Scanner(file);
	        
	        while(scan.hasNextLine()){
	        	t += scan.nextLine()+"\n";
	        	
	        }
	        //System.out.println(t);
	    }catch (FileNotFoundException e) {
	       
	    }
		
		String[] a= t.split("\n");
		int b[] = new int[a.length];
		for(int i = 0;i<t.split(" ").length;i++) {
			for(int j=0;j<a.length;j++) {
				if(t.split(" ")[i].equals(a[j]))
					b[j]+=1;
			}
		}
		int max=0;
		for(int i =0;i<b.length;i++) {
			if(b[max]<b[i])
				max=i;
		}
		System.out.println();
		System.out.println(a[max]);
	}

}
