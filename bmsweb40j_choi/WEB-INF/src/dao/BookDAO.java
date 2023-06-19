package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Book;
import bean.Order;
import bean.Sale;
import bean.User;

public class BookDAO {

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

	public static List<Book> search(String isbn, String title, String price) {

		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;

		if (price.equals("")) {
			price = "0";
			System.out.println(price);
		}

		List<Book> books = new ArrayList<>();

		try {
			String sql = "select * from bookinfo where isbn like '%" + isbn + "%'or title like '%" + title
					+ "%' or price >=" + price + " order by price asc";

			if (isbn.equals("")) {

				sql = "select * from bookinfo where title like '%" + title + "%' or price >=" + price
						+ " order by price asc";

				if (title.equals("")) {
					sql = "select * from bookinfo where price >=" + price + " order by price asc";
				}

				if (price.equals("0")) {
					sql = "select * from bookinfo where title like  '%" + title + "%'";
				}
			}

			if (title.equals("")) {

				sql = "select * from bookinfo where isbn like '%" + isbn + "%' or price >=" + price
						+ " order by price asc";

				if (isbn.equals("")) {
					sql = "select * from bookinfo where price >=" + price + " order by price asc";
				}

				if (price.equals("0")) {
					sql = "select * from bookinfo where isbn like  '%" + isbn + "%'";
				}
			}

			if (price.equals("0")) {

				sql = "select * from bookinfo where isbn like '%" + isbn + "%' or title = '%" + title + "%'";

				if (isbn.equals("")) {
					sql = "select * from bookinfo where title like  '%" + title + "%'";
				}

				if (title.equals("")) {
					sql = "select * from bookinfo where isbn like  '%" + isbn + "%'";
				}
			}

			System.out.println(sql);
			con = getConnection();
			smt = con.createStatement();
			rs = smt.executeQuery(sql);

			while (rs.next()) {

				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(Integer.parseInt(rs.getString("price")));

				books.add(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException(e);
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

		return books;

	}

	public static int update(Book book) {

		Connection con = null;
		Statement smt = null;

		try {

			String sql = "update bookinfo set title = '" + book.getTitle() + "',price = " + book.getPrice()
					+ " where isbn = '" + book.getIsbn() + "'";

			con = getConnection();
			smt = con.createStatement();
			if (smt.executeUpdate(sql) >= 1) {

				System.out.println("db success");
				return 1;
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

		return 0;

	}

	public static int delete(Book book) {

		Connection con = null;
		Statement smt = null;
		int result = 0;

		try {

			String sql = "delete from bookinfo where isbn = '" + book.getIsbn() + "'";

			con = getConnection();
			smt = con.createStatement();
			if (smt.executeUpdate(sql) >= 1) {

				System.out.println("db delete success");
				result = 1;
			} else {
				System.out.println("db error");
				result = 0;

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

		System.out.println(result);
		return result;

	}

	public static Sale selectByIsbn(String isbn, User user) {

		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;

		Sale book = new Sale();

		try {
			String sql = "SELECT  b.isbn, b.title, b.price, count(o.quantity) FROM bookinfo b left JOIN orderinfo o ON b.isbn=o.isbn where b.isbn=1 and user='"
					+ user.getUserid() + "' group by b.isbn";

			System.out.println(sql);
			con = getConnection();
			smt = con.createStatement();
			rs = smt.executeQuery(sql);

			while (rs.next()) {

				book.setIsbn(rs.getString("b.isbn"));
				book.setTitle(rs.getString("b.title"));
				book.setPrice(Integer.parseInt(rs.getString("b.price")));
				book.setQuantity(Integer.parseInt(rs.getString("count(o.quantity)")));

			}

		} catch (Exception e) {
			System.out.println(e);
			throw new IllegalStateException(e);
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
		return book;
	}

	public static Book selectByIsbn(String isbn) {

		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;

		Book book = new Book();

		try {
			String sql = "select * from bookinfo where isbn like '" + isbn + "'";

			con = getConnection();
			smt = con.createStatement();
			rs = smt.executeQuery(sql);

			while (rs.next()) {

				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(Integer.parseInt(rs.getString("price")));

			}

		} catch (Exception e) {
			System.out.println(e);
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
		return book;
	}

	public static ArrayList<Sale> selectAll() {

		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;

		ArrayList<Sale> bookList = new ArrayList<Sale>();

		try {

			String sql = "SELECT  b.image,b.isbn, b.title, b.price, count(o.quantity) FROM bookinfo b left JOIN orderinfo o ON b.isbn=o.isbn group by b.isbn";

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
				book.setImg(rs.getString("b.image"));

				bookList.add(book);
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
		return bookList;
	}

	public static int isbnSearch(HttpServletRequest request) {

		String isbn = request.getParameter("isbn");
		int result = 1;
		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;

		try {

			Book book = new Book();
			String sql = "select * from bookinfo where isbn = '" + isbn + "'";

			con = getConnection();
			smt = con.createStatement();
			rs = smt.executeQuery(sql);

			while (rs.next()) {

				book.setIsbn(rs.getString("isbn"));

				if (book.getIsbn().equals(isbn)) {
					result = 0;
				}

			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
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

		return result;
	}

	public static void insert(Book book) {

		Connection con = null;
		Statement smt = null;

		try {

			String isbn = book.getIsbn();
			String title = book.getTitle();
			int price = book.getPrice();
			String img = book.getImg();

			String sql = "insert into bookinfo(isbn, title, price, image) values('" + isbn + "', '" + title + "',"
					+ price + ",'" + img + "');";

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
