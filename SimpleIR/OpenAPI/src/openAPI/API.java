package openAPI;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class API {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
		System.out.println("202011331 이경민");
		Scanner sc = new Scanner(System.in);
		System.out.print("검색어를 입력하세요:");
		String search = sc.next();
		
		
		String clientId = "UljqEiJKk2iZKP5gJvi9";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "tNbW0haey5";//애플리케이션 클라이언트 시크릿값";
		
		 try {
	            String text = URLEncoder.encode(search,"UTF-8");
	            String apiURL = "https://openapi.naver.com/v1/search/movie?query="+text;
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("GET");
	            con.setRequestProperty("X-Naver-Client-Id", clientId);
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	            // post request
	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else {  // 에러 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            }
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                response.append(inputLine);
	            }
	            br.close();
	            //System.out.println(response.toString());
	            JSONParser jsonParser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.toString());
	            JSONArray infoArray = (JSONArray) jsonObject.get("items");
	            
	            for(int i=0; i<infoArray.size(); i++){
	            System.out.println("=item_"+i+"===========================================");
	            JSONObject itemObject = (JSONObject) infoArray.get(i);
	            System.out.println("title:\t"+itemObject.get("title"));
	            System.out.println("subtitle:\t"+itemObject.get("subtitle"));
	            System.out.println("director:\t"+itemObject.get("director"));
	            System.out.println("actor:\t"+itemObject.get("actor"));
	            System.out.println("userRating:\t"+itemObject.get("userRating")+"\n");
	            }
	            
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	}

}
