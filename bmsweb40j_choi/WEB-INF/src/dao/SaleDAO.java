package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bean.Book;
import bean.OrderedItem;
import bean.Sale;
import bean.User;

public class SaleDAO {

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

	public static ArrayList<Sale> selectBySales(String year, String month) {

		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;

		ArrayList<Sale> sales = new ArrayList<>();

		try {

			if (Integer.parseInt(month) < 10) {
				month = "0" + month;
			}

			String sql = "SELECT b.isbn, title, price, sum(quantity) as quantity FROM orderinfo o inner join bookinfo b ON o.isbn=b.isbn "
					+ " WHERE date LIKE '" + year + "-" + month + "%' GROUP BY b.isbn ORDER BY b.isbn;";

			System.out.println(sql);

			con = getConnection();
			smt = con.createStatement();
			rs = smt.executeQuery(sql);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
			while (rs.next()) {
				Sale sale = new Sale();

				sale.setIsbn(rs.getString("b.isbn"));
				sale.setTitle(rs.getString("title"));
				sale.setPrice(Integer.parseInt(rs.getString("price")));
				sale.setQuantity(Integer.parseInt(rs.getString("quantity")));

				sales.add(sale);
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

		return sales;
	}

	public static Sale selectBySales(String isbn) {

		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;

		Sale sale = new Sale();

		try {

			String sql = "SELECT b.isbn, title, price, count(quantity) as quantity FROM orderinfo o inner join bookinfo b ON o.isbn=b.isbn "
					+ " WHERE b.isbn LIKE '" + isbn + "%' GROUP BY b.isbn ORDER BY b.isbn;";

			System.out.println(sql);

			con = getConnection();
			smt = con.createStatement();
			rs = smt.executeQuery(sql);

			while (rs.next()) {

				sale.setIsbn(rs.getString("b.isbn"));
				sale.setTitle(rs.getString("title"));
				sale.setPrice(Integer.parseInt(rs.getString("price")));
				sale.setQuantity(1);

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

		return sale;
	}

	public static ArrayList<Sale> search(String isbn, String title, String price) {
		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;

		if (price.equals("")) {
			price = "0";
			System.out.println(price);
		}

		List<Sale> books = new ArrayList<>();

		try {
			String sql = "SELECT  b.isbn, b.title, b.price, count(o.quantity) FROM bookinfo b left JOIN orderinfo o ON b.isbn=o.isbn group by b.isbn where b.isbn like '%" + isbn + "%'or b.title like '%" + title
					+ "%' or b.price >=" + price + " order by b.price asc";

			if (isbn.equals("")) {

				sql = "SELECT  b.isbn, b.title, b.price, count(o.quantity) FROM bookinfo b left JOIN orderinfo o ON b.isbn=o.isbn where b.title like '%" + title + "%' or b.price >=" + price
						+ "  group by b.isbn order by b.price asc";

				if (title.equals("")) {
					sql = "SELECT  b.isbn, b.title, b.price, count(o.quantity) FROM bookinfo b left JOIN orderinfo o ON b.isbn=o.isbn where b.price >=" + price + "group by b.isbn  order by price asc";
				}

				if (price.equals("0")) {
					sql = "SELECT  b.isbn, b.title, b.price, count(o.quantity) FROM bookinfo b left JOIN orderinfo o ON b.isbn=o.isbn  where b.title like  '%" + title + "%'group by b.isbn";
				}
			}

			if (title.equals("")) {

				sql = "SELECT  b.isbn, b.title, b.price, count(o.quantity) FROM bookinfo b left JOIN orderinfo o ON b.isbn=o.isbn  where b.isbn like '%" + isbn + "%' or b.price >=" + price
						+ "  group by b.isbn order by b.price asc";

				if (isbn.equals("")) {
					sql = "SELECT  b.isbn, b.title, b.price, count(o.quantity) FROM bookinfo b left JOIN orderinfo o ON b.isbn=o.isbn  where b.price >=" + price + " group by b.isbn order by b.price asc";
				}

				if (price.equals("0")) {
					sql = "SELECT  b.isbn, b.title, b.price, count(o.quantity) FROM bookinfo b left JOIN orderinfo o ON b.isbn=o.isbn  where b.isbn like  '%" + isbn + "%'group by b.isbn";
				}
			}

			if (price.equals("0")) {

				sql = "SELECT  b.isbn, b.title, b.price, count(o.quantity) FROM bookinfo b left JOIN orderinfo o ON b.isbn=o.isbn where b.isbn like '%" + isbn + "%' or b.title = '%" + title + "%'group by b.isbn"
;

				if (isbn.equals("")) {
					sql = "SELECT  b.isbn, b.title, b.price, count(o.quantity) FROM bookinfo b left JOIN orderinfo o ON b.isbn=o.isbn where b.title like  '%" + title + "%' group by b.isbn";
				}

				if (title.equals("")) {
					sql = "SELECT  b.isbn, b.title, b.price, count(o.quantity) FROM bookinfo b left JOIN orderinfo o ON b.isbn=o.isbn where b.isbn like  '%" + isbn + "%' group by b.isbn";
				}
			}

			System.out.println(sql);
			con = getConnection();
			smt = con.createStatement();
			rs = smt.executeQuery(sql);

			while (rs.next()) {

				Sale book = new Sale();
				book.setIsbn(rs.getString("b.isbn"));
				book.setTitle(rs.getString("b.title"));
				book.setPrice(Integer.parseInt(rs.getString("b.price")));
				book.setQuantity(Integer.parseInt(rs.getString("count(o.quantity)")));

				books.add(book);
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

		return (ArrayList<Sale>) books;
	}



}
