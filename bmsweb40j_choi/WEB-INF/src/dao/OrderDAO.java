package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Order;

public class OrderDAO {

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

	public static void insert(Order order) {

		Connection con = null;
		Statement smt = null;

		try {

			String sql = "INSERT INTO orderinfo VALUES(NULL,'" + order.getUserid() + "','" + order.getIsbn() + "',"
					+ order.getQuantity() + ",CURDATE())";

			con = getConnection();
			smt = con.createStatement();
			if (smt.executeUpdate(sql) >= 1) {

				System.out.println("db success");

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

}
