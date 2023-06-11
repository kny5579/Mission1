package his;

import java.sql.Timestamp;

public class history_Info {
	int id;
	String x;
	String y;
	Timestamp searchTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public Timestamp getSearchTime() {
		return searchTime;
	}
	public void setSearchTime(Timestamp searchTime) {
		this.searchTime = searchTime;
	}
}
