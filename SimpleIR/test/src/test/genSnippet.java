package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class genSnippet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int f = 0 ,q=0;
		
		for(int i=0;i<args.length;i++) {
			if(args[i].equals("-f")) {
				f=i;
			}else if(args[i].equals("-q")) {
				q=i;
			}
		}
		System.out.println(args[f+1]);
		
		File file = new File(args[f+1]+"/input.txt");
		String t="";
		try{
	        @SuppressWarnings("resource")
			Scanner scan = new Scanner(file);
	        
	        while(scan.hasNextLine()){
	        	t += scan.nextLine();
	        	
	        }
	        //System.out.println(t);
	    }catch (FileNotFoundException e) {
	       
	    }
		
		System.out.println(t);
		
	}

}
