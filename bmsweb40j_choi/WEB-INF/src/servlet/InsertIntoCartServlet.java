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
import dao.OrderDAO;

public class InsertIntoCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";
		String cmd = "";
		String link = "";

		try {

			request.setCharacterEncoding("UTF-8");

			HttpSession hs = request.getSession();
			User user = (User) hs.getAttribute("userInfo");

			HttpSession hs2 = request.getSession();


			if(user == null) {

				request.setAttribute("error", "セッション切れの為、カートに追加できません");
				request.setAttribute("cmd", "logout");
				request.setAttribute("link", "/logout");


				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

			String isbn = request.getParameter("isbn");

			Book book = BookDAO.selectByIsbn(isbn);
			request.setAttribute("book", book);

			Order order = new Order();
			order.setIsbn(isbn);
			order.setUserid(user.getUserid());
			order.setQuantity(1);

			ArrayList<Order> list = (ArrayList<Order>) hs2.getAttribute("order_list");
			if (list == null) {
				list = new ArrayList<Order>();
			}

			list.add(order);

			hs2.setAttribute("order_list", list);




		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、カートに追加は出来ません。";
			cmd = "logout";
			link = "/logout";

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			// エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {

				// エラーが無い場合はListServletにフォワード
				request.getRequestDispatcher("/view/insertIntoCart.jsp").forward(request, response);

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
