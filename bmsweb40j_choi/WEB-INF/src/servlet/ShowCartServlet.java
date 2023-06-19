package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Book;
import bean.Order;
import bean.Sale;
import bean.User;
import dao.BookDAO;
import dao.SaleDAO;

public class ShowCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";
		String cmd = "";
		String link = "";

		try {

			String delno = request.getParameter("delno");

			HttpSession hs = request.getSession();
			User user = (User) hs.getAttribute("userInfo");

			HttpSession hs2 = request.getSession();

			if (user == null) {
				error = "セッション切れの為、カート状況は確認出来ません。";
				cmd = "logout";
				link = "/logout";
				return;

			}

			ArrayList<Order> order_list = (ArrayList<Order>) hs2.getAttribute("order_list");
			Sale book = null;

			if (delno != null) {

				order_list.remove(Integer.parseInt(delno));
				hs2.setAttribute("order_list", order_list);

			}

			ArrayList<Sale> books = new ArrayList<>();

			for (Order order2 : order_list) {

				book = new Sale(BookDAO.selectByIsbn(order2.getIsbn()).getIsbn(),
						BookDAO.selectByIsbn(order2.getIsbn()).getTitle(),
						BookDAO.selectByIsbn(order2.getIsbn()).getPrice(), order2.getQuantity());
				books.add(book);
			}

			request.setAttribute("book_list", books);


		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、カート状況は確認出来ません。";
			cmd = "logout";
			link = "/logout";

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			// エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {

				// エラーが無い場合はshowCart.jspにフォワード
				request.getRequestDispatcher("/view/showCart.jsp").forward(
						request, response);
			} else {

				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.setAttribute("link", link);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}

		}

	}

}
