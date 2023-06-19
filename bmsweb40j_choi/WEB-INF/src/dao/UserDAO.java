package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.Order;
import bean.User;

public class UserDAO {

	// データベース接続情報
	private static String RDB_DRIVE = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/mybookdb";
	private static String USER = "root";
	private static String PASS = "root123";

	// データベース接続を行うメソッド
	// データベース接続用定義を基にデータベースへ接続し、戻り値としてコネクション情報を返す
	public static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public static User login(HttpServletRequest request, User user) {
		String userId = user.getUserid();
		String userPw = user.getPassword();

		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;

		user = new User();
		try {
			String sql = "select * from userinfo where user = '" + userId + "'";
			con = getConnection();
			smt = con.createStatement();
			rs = smt.executeQuery(sql);

			if (rs.next()) {
				String dbPW = rs.getString("password");

				if (userPw.equals(dbPW)) {

					user.setUserid(rs.getString("user"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setAuthority(rs.getString("authority"));

					HttpSession hs = request.getSession();
					hs.setAttribute("userInfo", user);
					hs.setMaxInactiveInterval(1800);

					HttpSession hs2 = request.getSession();
					hs2.setAttribute("order_list", new ArrayList<Order>());
					hs2.setMaxInactiveInterval(1800);

					System.out.println("login success");

				} else {
					System.out.println("pw worng!");
					user = null;

				}
			} else {

				System.out.println("no userdata");
				user = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}

		}
		return user;

	}

	public static void changePw(String id, String pw) {

		Connection con = null;
		Statement smt = null;

		try {
			String sql = "update userinfo set password = '" + pw + "' where user = '" + id + "'";
			System.out.println(sql);

			con = getConnection();
			smt = con.createStatement();

			if (smt.executeUpdate(sql) >= 1) {

				System.out.println("db update success");
			} else {
				System.out.println("db error");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}

	}

	public static void insert(User user) {

		Connection con = null;
		Statement smt = null;

		try {
			String sql = "INSERT INTO userinfo VALUES('" + user.getUserid() + "','" + user.getPassword() + "','"
					+ user.getEmail() + "','" + user.getAuthority() + "')";
			System.out.println(sql);

			con = getConnection();
			smt = con.createStatement();

			if (smt.executeUpdate(sql) >= 1) {

				System.out.println("db insert success");
			} else {
				System.out.println("db error");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}

	}

	public static ArrayList<User> selectAll() {

		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;

		ArrayList<User> users = new ArrayList<>();
		try {
			String sql = "select * from userinfo";
			con = getConnection();
			smt = con.createStatement();
			rs = smt.executeQuery(sql);

			while (rs.next()) {
				User user = new User();
				System.out.println(rs.getString("user"));

				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));

				if (rs.getString("authority").equals("2")) {

					user.setAuthority("管理者");
				} else {
					user.setAuthority("一般ユーザー");

				}

				users.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}

		}
		return users;
	}

	public static User selectUserById(String userid) {

		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;
		User user = new User();

		try {
			String sql = "SELECT * FROM userinfo WHERE user = '" + userid + "'";

			con = getConnection();
			smt = con.createStatement();
			rs = smt.executeQuery(sql);

			while (rs.next()) {

				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));

				if (rs.getString("authority").equals("2")) {

					user.setAuthority("管理者");
				} else {
					user.setAuthority("一般ユーザー");

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}

		}
		return user;
	}

	public static void update(User user2) {

		Connection con = null;
		Statement smt = null;

		try {

			String sql = "update userinfo set password = '" + user2.getPassword() + "', email = '" + user2.getEmail()
					+ "',authority='" + user2.getAuthority() + "' where user = '" + user2.getUserid() + "'";

			System.out.println(sql);
			con = getConnection();
			smt = con.createStatement();
			if (smt.executeUpdate(sql) >= 1) {

				System.out.println("db success");
			} else {
				System.out.println("db error");

			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}

	}

	public static void delete(User user) {

		Connection con = null;
		Statement smt = null;

		try {

			String sql = "delete from userinfo where user = '" + user.getUserid() + "' and password ='"
					+ user.getPassword() + "'";

			con = getConnection();
			smt = con.createStatement();
			if (smt.executeUpdate(sql) >= 1) {

				System.out.println("db delete success");
			} else {
				System.out.println("db error");

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}

	}

	public static ArrayList<User> selectByID(String user2) {
		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;

		ArrayList<User> users = new ArrayList<>();
		try {
			String sql = "select * from userinfo where user like '%"+user2+"%'";


			con = getConnection();
			smt = con.createStatement();
			rs = smt.executeQuery(sql);

			while (rs.next()) {
				User user = new User();
				System.out.println(rs.getString("user"));

				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));

				if (rs.getString("authority").equals("2")) {

					user.setAuthority("管理者");
				} else {
					user.setAuthority("一般ユーザー");

				}

				users.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ignore) {
				}
			}
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}

		}
		return users;
	}



}
