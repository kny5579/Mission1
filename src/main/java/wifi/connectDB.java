package wifi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import bookmark.BookmarkGroup_Info;
import bookmark.Bookmark_Info;
import his.history_Info;


public class connectDB {
	//-------------------------------------API--------------------------------------------
	
	public int InsertAPI() {
	    String url = "jdbc:mariadb://127.0.0.1:3306/wifiproject";
	    String dbUserId = "testuser1";
	    String dbPassword = "zerobase";
	    
	    try {
	        Class.forName("org.mariadb.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    
	    Connection connection = null;
	    PreparedStatement preparedstatement = null;
	    ResultSet rs = null;

	    try {
	        connection = DriverManager.getConnection(url, dbUserId, dbPassword);
	        connection.setAutoCommit(false); // 수동 커밋 설정

	        int start = 1;
	        int end = 1000;
	        int valueSize = 1000;
	        int cnt = 0;

	        try {
	            while (valueSize == 1000) {
	                ArrayList<API_Info> info = httpParsing.call(start, end);
	                valueSize = info.size();

	                if (valueSize == 0) {
	                    break;
	                }

	                String sql = "insert ignore into api_info(X_SWIFI_MGR_NO,X_SWIFI_WRDOFC,X_SWIFI_MAIN_NM,"
	                        + "X_SWIFI_ADRES1,X_SWIFI_ADRES2,X_SWIFI_INSTL_FLOOR,X_SWIFI_INSTL_TY,X_SWIFI_INSTL_MBY,"
	                        + "X_SWIFI_SVC_SE,X_SWIFI_CMCWR,X_SWIFI_CNSTC_YEAR,X_SWIFI_INOUT_DOOR,X_SWIFI_REMARS3,LAT,LNT,WORK_DTTM) "
	                        + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

	                preparedstatement = connection.prepareStatement(sql);

	                for (int i = 0; i < valueSize; i++) {
	                    preparedstatement.setString(1, info.get(i).getX_SWIFI_MGR_NO());
	                    preparedstatement.setString(2, info.get(i).getX_SWIFI_WRDOFC());
	                    preparedstatement.setString(3, info.get(i).getX_SWIFI_MAIN_NM());
	                    preparedstatement.setString(4, info.get(i).getX_SWIFI_ADRES1());
	                    preparedstatement.setString(5, info.get(i).getX_SWIFI_ADRES2());
	                    preparedstatement.setString(6, info.get(i).getX_SWIFI_INSTL_FLOOR());
	                    preparedstatement.setString(7, info.get(i).getX_SWIFI_INSTL_TY());
	                    preparedstatement.setString(8, info.get(i).getX_SWIFI_INSTL_MBY());
	                    preparedstatement.setString(9, info.get(i).getX_SWIFI_SVC_SE());
	                    preparedstatement.setString(10, info.get(i).getX_SWIFI_CMCWR());
	                    preparedstatement.setString(11, info.get(i).getX_SWIFI_CNSTC_YEAR());
	                    preparedstatement.setString(12, info.get(i).getX_SWIFI_INOUT_DOOR());
	                    preparedstatement.setString(13, info.get(i).getX_SWIFI_REMARS3());
	                    preparedstatement.setDouble(14, info.get(i).getLNT());
	                    preparedstatement.setDouble(15, info.get(i).getLAT());
	                    preparedstatement.setString(16, info.get(i).getWORK_DTTM());
	                    preparedstatement.addBatch();
	                }

	                preparedstatement.executeBatch();
	                connection.commit();

	                start += 1000;
	                end += 1000;
	                cnt += valueSize;
	            }
	        } catch (SQLException e) {
	            try {
	                connection.rollback(); // 롤백 수행
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	            throw new RuntimeException(e);
	        } finally {
	            try {
	                if (rs != null && !rs.isClosed()) {
	                    rs.close();
	                }
	            } catch (SQLException e) {
	                throw new RuntimeException(e);
	            }
	            try {
	                if (preparedstatement != null && !preparedstatement.isClosed()) {
	                    preparedstatement.close();
	                }
	            } catch (SQLException e) {
	                throw new RuntimeException(e);
	            }
	            try {
	                if (connection != null && !connection.isClosed()) {
	                    connection.close();
	                }
	            } catch (SQLException e) {
	                throw new RuntimeException(e);
	            }
	        }
	        return cnt;
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}

	
	//-------------------------History-----------------------------
	
	public void InsertHistory(history_Info his_info) {
		String url = "jdbc:mariadb://127.0.0.1:3306/wifiproject";
		String dbUserId = "testuser1";
		String dbPassword = "zerobase";
		// 1. 드라이버 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		// 2. 커넥션 객체 생성
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			// 3. 스테이트먼트 객체 생성

			String sql = "insert into history_info(x,y,searchTime) values(?,?,?); ";
			preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setString(1, his_info.getX());
			preparedstatement.setString(2, his_info.getY());
			preparedstatement.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
			preparedstatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// 객체 연결 해제(close)
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (preparedstatement != null && !preparedstatement.isClosed()) {
					preparedstatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}
	
	public ArrayList<history_Info> LoadHistory() {
		String url = "jdbc:mariadb://127.0.0.1:3306/wifiproject";
		String dbUserId = "testuser1";
		String dbPassword = "zerobase";
		// 1. 드라이버 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		ArrayList<history_Info> list=new ArrayList<>();
		// 2. 커넥션 객체 생성
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			// 3. 스테이트먼트 객체 생성

			String sql = "select * from history_Info order by id desc;";
			preparedstatement = connection.prepareStatement(sql);
			rs=preparedstatement.executeQuery();
			// 5. 결과 수행
			while (rs.next()) {
				history_Info hisinfo=new history_Info();
				hisinfo.setId(rs.getInt("id"));
				hisinfo.setX(rs.getString("x"));
				hisinfo.setY(rs.getString("y"));
				hisinfo.setSearchTime(rs.getTimestamp("searchTime"));
				list.add(hisinfo);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// 객체 연결 해제(close)
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (preparedstatement != null && !preparedstatement.isClosed()) {
					preparedstatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return list;
	}
	
	public void DeleteHistory(String id) {
		String url = "jdbc:mariadb://127.0.0.1:3306/wifiproject";
		String dbUserId = "testuser1";
		String dbPassword = "zerobase";
		// 1. 드라이버 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		// 2. 커넥션 객체 생성
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			// 3. 스테이트먼트 객체 생성

			String sql = "delete from history_info where id=? ";
			preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setInt(1,Integer.parseInt(id));
			preparedstatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// 객체 연결 해제(close)
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (preparedstatement != null && !preparedstatement.isClosed()) {
					preparedstatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}
	
	//------------------------------wifi_20_data---------------------------------
	
	public ArrayList<Wifi_Info> LoadData(double lat,double lnt) {
		String url = "jdbc:mariadb://127.0.0.1:3306/wifiproject";
		String dbUserId = "testuser1";
		String dbPassword = "zerobase";
		// 1. 드라이버 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		ArrayList<Wifi_Info> list=new ArrayList<>();
		// 2. 커넥션 객체 생성
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			// 3. 스테이트먼트 객체 생성

			String sql = "SELECT *, "
				    +"("
				    + "      6371 * acos ("
				    + "      cos ( radians(?) )"
				    + "      * cos( radians( LAT ) )"
				    + "      * cos( radians( LNT ) - radians(?) )"
				    + "      + sin ( radians(?) )"
				    + "      * sin( radians( LAT ) )"
				    + "    )"
				    + ") AS distance "
					+ "FROM api_info "
					+ "ORDER BY distance "
					+ "LIMIT 20; ";
			preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setDouble(1, lat);
			preparedstatement.setDouble(2, lnt);
			preparedstatement.setDouble(3, lat);
			
			rs=preparedstatement.executeQuery();
			// 5. 결과 수행
			while (rs.next()) {
				Wifi_Info info=new Wifi_Info();
				double distance=rs.getDouble("distance");
				distance=Math.round(distance*10000)/10000.0;
				info.setDistance(distance);
				info.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
				info.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
				info.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
				info.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
				info.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
				info.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
				info.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
				info.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
				info.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
				info.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
				info.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
				info.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
				info.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
				info.setLAT(rs.getDouble("LAT"));
				info.setLNT(rs.getDouble("LNT"));
				info.setWORK_DTTM(rs.getString("WORK_DTTM"));
				
				list.add(info);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// 객체 연결 해제(close)
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (preparedstatement != null && !preparedstatement.isClosed()) {
					preparedstatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return list;
	}
	
	
	
	//--------------------------------detail----------------------------
	
	public Wifi_Info LoadDetail(String id,double distance) {
		String url = "jdbc:mariadb://127.0.0.1:3306/wifiproject";
		String dbUserId = "testuser1";
		String dbPassword = "zerobase";
		// 1. 드라이버 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		Wifi_Info info=new Wifi_Info();
		// 2. 커넥션 객체 생성
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			// 3. 스테이트먼트 객체 생성

			String sql = "SELECT * "
					+ "FROM api_info "
					+ "WHERE X_SWIFI_MGR_NO=?";
			preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setString(1, id);
			
			rs=preparedstatement.executeQuery();
			// 5. 결과 수행
			while (rs.next()) {
				info.setDistance(distance);
				info.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
				info.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
				info.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
				info.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
				info.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
				info.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
				info.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
				info.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
				info.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
				info.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
				info.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
				info.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
				info.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
				info.setLAT(rs.getDouble("LAT"));
				info.setLNT(rs.getDouble("LNT"));
				info.setWORK_DTTM(rs.getString("WORK_DTTM"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// 객체 연결 해제(close)
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (preparedstatement != null && !preparedstatement.isClosed()) {
					preparedstatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return info;
	}
	
	//---------------------bookmark_group----------------------------
	
	public void InsertBookmarkGroup(BookmarkGroup_Info info) {
		String url = "jdbc:mariadb://127.0.0.1:3306/wifiproject";
		String dbUserId = "testuser1";
		String dbPassword = "zerobase";
		// 1. 드라이버 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		// 2. 커넥션 객체 생성
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			// 3. 스테이트먼트 객체 생성

			String sql = "insert into bookmarkgroup_info(name,num,registertime) values(?,?,?); ";
			preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setString(1, info.getName());
			preparedstatement.setInt(2, info.getOrder());
			preparedstatement.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
			preparedstatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// 객체 연결 해제(close)
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (preparedstatement != null && !preparedstatement.isClosed()) {
					preparedstatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}
	
	

	public ArrayList<BookmarkGroup_Info> LoadBookmarkGroup() {
		String url = "jdbc:mariadb://127.0.0.1:3306/wifiproject";
		String dbUserId = "testuser1";
		String dbPassword = "zerobase";
		// 1. 드라이버 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		ArrayList<BookmarkGroup_Info> list=new ArrayList<>();
		// 2. 커넥션 객체 생성
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			// 3. 스테이트먼트 객체 생성

			String sql = "select * from bookmarkgroup_info order by num asc;";
			preparedstatement = connection.prepareStatement(sql);
			rs=preparedstatement.executeQuery();
			// 5. 결과 수행
			while (rs.next()) {
				BookmarkGroup_Info info=new BookmarkGroup_Info();
				info.setId(rs.getInt("id"));
				info.setName(rs.getString("name"));
				info.setOrder(rs.getInt("num"));
				info.setRegisterTime(rs.getTimestamp("registertime"));
				info.setEditTime(rs.getTimestamp("edittime"));
				list.add(info);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// 객체 연결 해제(close)
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (preparedstatement != null && !preparedstatement.isClosed()) {
					preparedstatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return list;
	}
	
	public void editBookmarkJoinGroup(String updatebookmarkname,int num,int id) {
		String url = "jdbc:mariadb://127.0.0.1:3306/wifiproject";
		String dbUserId = "testuser1";
		String dbPassword = "zerobase";

		// 1. 드라이버 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		// 2. 커넥션 객체 생성
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			// 3. 스테이트먼트 객체 생성
			String sql = "update bookmarkgroup_info "
					+"set name=?,num=?,edittime=? "
					+"where id=?;";
			preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setString(1, updatebookmarkname);
			preparedstatement.setInt(2, num);
			preparedstatement.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
			preparedstatement.setInt(4, id);
			// 4. 쿼리 실행
			preparedstatement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// 6. 객체 연결 해제(close)
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (preparedstatement != null && !preparedstatement.isClosed()) {
					preparedstatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public void DeleteBookmarkJoinGroup(int id) {
		String url = "jdbc:mariadb://127.0.0.1:3306/wifiproject";
		String dbUserId = "testuser1";
		String dbPassword = "zerobase";
		// 1. 드라이버 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		// 2. 커넥션 객체 생성
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			// 3. 스테이트먼트 객체 생성

			String sql = "delete from bookmark_info where group_id=?";
			preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setInt(1,id);
			preparedstatement.executeUpdate();
			String sql2="delete from bookmarkgroup_info where id=?";
			preparedstatement = connection.prepareStatement(sql2);
			preparedstatement.setInt(1,id);
			preparedstatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// 객체 연결 해제(close)
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (preparedstatement != null && !preparedstatement.isClosed()) {
					preparedstatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	
	//---------------------bookmark-------------------------
	
	public void InsertBookmark(Bookmark_Info info) {
		String url = "jdbc:mariadb://127.0.0.1:3306/wifiproject";
		String dbUserId = "testuser1";
		String dbPassword = "zerobase";
		// 1. 드라이버 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		// 2. 커넥션 객체 생성
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			// 3. 스테이트먼트 객체 생성

			String sql = "insert into bookmark_info(group_id,wifiname,registertime,detail_id,distance) values(?,?,?,?,?); ";
			preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setInt(1, info.getGroup_id());
			preparedstatement.setString(2, info.getWifiname());
			preparedstatement.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
			preparedstatement.setString(4, info.getDetail_id());
			preparedstatement.setDouble(5, info.getDistance());
			preparedstatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// 객체 연결 해제(close)
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (preparedstatement != null && !preparedstatement.isClosed()) {
					preparedstatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}
	
	public ArrayList<Bookmark_Info> LoadBookmark() {
		String url = "jdbc:mariadb://127.0.0.1:3306/wifiproject";
		String dbUserId = "testuser1";
		String dbPassword = "zerobase";
		// 1. 드라이버 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		ArrayList<Bookmark_Info> list=new ArrayList<>();
		// 2. 커넥션 객체 생성
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			// 3. 스테이트먼트 객체 생성

			String sql = "select b.id,bg.name,b.wifiname,b.registertime,b.detail_id,b.distance "
					+"from bookmark_info b "
					+"join bookmarkgroup_info bg on b.group_id=bg.id; ";
			preparedstatement = connection.prepareStatement(sql);
			rs=preparedstatement.executeQuery();
			// 5. 결과 수행
			while (rs.next()) {
				Bookmark_Info info=new Bookmark_Info();
				info.setId(rs.getInt("id"));
				info.setBookmarkname(rs.getString("name"));
				info.setWifiname(rs.getString("wifiname"));
				info.setRegistertime(rs.getTimestamp("registertime"));
				info.setDetail_id(rs.getString("detail_id"));
				info.setDistance(rs.getDouble("distance"));
				list.add(info);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// 객체 연결 해제(close)
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (preparedstatement != null && !preparedstatement.isClosed()) {
					preparedstatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return list;
	}
	
	public void DeleteBookmark(int id) {
		String url = "jdbc:mariadb://127.0.0.1:3306/wifiproject";
		String dbUserId = "testuser1";
		String dbPassword = "zerobase";
		// 1. 드라이버 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		// 2. 커넥션 객체 생성
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			// 3. 스테이트먼트 객체 생성

			String sql = "delete from bookmark_info where id=? ";
			preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setInt(1,id);
			preparedstatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// 객체 연결 해제(close)
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (preparedstatement != null && !preparedstatement.isClosed()) {
					preparedstatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}
	
}
