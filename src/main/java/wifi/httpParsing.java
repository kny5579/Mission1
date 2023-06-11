package wifi;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import okhttp3.*;

public class httpParsing {
    public static ArrayList<API_Info> call(int start,int end){
    	try {
    		OkHttpClient client=new OkHttpClient(); //인스턴스 생성
    		String strURL="http://openapi.seoul.go.kr:8088/51415159686b6e7931323179456c7a4d/json/TbPublicWifiInfo/"+start+"/"+end+"/";
    		Request.Builder builder=new Request.Builder().url(strURL).get(); //get요쳥을 위한 build
            builder.addHeader("Content-type", "application/json"); //json을 주고받는 경우 헤더에 추가
            Request request = builder.build();//request 객체 생성    
            Response response = client.newCall(request).execute();//request를 요청하고 그 결과를 response 객체로 응답 받음
        	ArrayList<API_Info> valueList=new ArrayList<>();
            //응답처리 및 파싱
            if(response.isSuccessful()){
            	Gson gson=new Gson();
           		ResponseBody body = response.body();
            	String responseString = body.string();
            	if(responseString!=""||responseString!=null) {
            		JsonObject obj = new Gson().fromJson(responseString, JsonObject.class);
            		obj=(JsonObject)obj.get("TbPublicWifiInfo");
            		JsonArray arr=(JsonArray)obj.get("row");
            		for(Object o:arr) {
            			valueList.add(gson.fromJson(o.toString(), API_Info.class));
            		}
            	}
               	body.close();
            }
            return valueList;
        }catch(Exception e){
        	e.printStackTrace();
    	}
    	return null;
    }

}


