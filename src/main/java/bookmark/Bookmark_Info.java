package bookmark;

import java.sql.Timestamp;

public class Bookmark_Info {
	int id;
	int group_id;
	String bookmarkname;
	String wifiname;
	Timestamp registertime;
	String detail_id;
	double distance;
	public String getBookmarkname() {
		return bookmarkname;
	}
	public void setBookmarkname(String bookmarkname) {
		this.bookmarkname = bookmarkname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id=group_id;
	}
	public String getWifiname() {
		return wifiname;
	}
	public void setWifiname(String wifiname) {
		this.wifiname = wifiname;
	}
	public Timestamp getRegistertime() {
		return registertime;
	}
	public void setRegistertime(Timestamp registertime) {
		this.registertime = registertime;
	}
	
	
	public String getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(String detail_id) {
		this.detail_id = detail_id;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
}
