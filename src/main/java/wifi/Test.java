//package wifi;
//import java.util.ArrayList;
//
//public class Test {
//	
//	public int parsingToDB() {
//		connectDB cnDB = new connectDB();
//		int start = 1;
//		int end = 1000;
//		int valueSize = 1000;
//		int cnt=0;
//		try {
//			while (valueSize == 1000) {
//				ArrayList<API_Info> info = httpParsing.call(start, end);
//				valueSize = info.size();
//				if (valueSize == 0) {
//					break;
//				}
//				cnDB.InsertAPI(info);
//				start += 1000;
//				end += 1000;
//				cnt+=valueSize;
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		return cnt;
//	}
//
//}
