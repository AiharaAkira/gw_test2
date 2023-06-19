package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bean.Order;
import bean.OrderedItem;
import bean.User;

public class OrderedItemDAO {

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

	public static ArrayList<OrderedItem> selectAll() {

		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;

		ArrayList<OrderedItem> orderedItems = new ArrayList<OrderedItem>();

		try {

			String sql = "SELECT count(o.quantity),o.user,b.title,o.date FROM bookinfo b INNER JOIN orderinfo o ON b.isbn=o.isbn  group by o.date, b.title";

			con = getConnection();
			smt = con.createStatement();
			rs = smt.executeQuery(sql);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
			while (rs.next()) {
				OrderedItem oi = new OrderedItem();

				oi.setUserid(rs.getString("o.user"));
				oi.setDate(rs.getString("o.date"));
				oi.setTitle(rs.getString("b.title"));
				oi.setQuantity(Integer.parseInt(rs.getString("count(o.quantity)")));

				orderedItems.add(oi);
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
		return orderedItems;

	}

	public static ArrayList<OrderedItem> selectAllUserHistory(User user2) {

		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;

		ArrayList<OrderedItem> orderedItems = new ArrayList<OrderedItem>();

		try {

			String sql = "SELECT count(o.quantity),o.user,b.title,o.date FROM bookinfo b INNER JOIN orderinfo o ON b.isbn=o.isbn where o.user = '"
					+ user2.getUserid() + "' group by o.date, b.title";

			System.out.println(sql);
			con = getConnection();
			smt = con.createStatement();
			rs = smt.executeQuery(sql);

			while (rs.next()) {
				OrderedItem oi = new OrderedItem();

				oi.setUserid(rs.getString("o.user"));
				oi.setDate(rs.getString("o.date"));
				oi.setTitle(rs.getString("b.title"));
				oi.setQuantity(Integer.parseInt(rs.getString("count(o.quantity)")));

				orderedItems.add(oi);
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
		return orderedItems;

	}

}
