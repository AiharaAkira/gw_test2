package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Book;
import bean.User;
import dao.BookDAO;
import dao.UserDAO;

public class LoginServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	// doPost()メソッド
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";
		String cmd = "";
		String link = "";


		try {

			request.setCharacterEncoding("UTF-8");

			String id = request.getParameter("id");
			String pass = request.getParameter("pass");

			String nullCheck = "  ";
			String isbnIsNotNull = "  ";

			User user = new User();

			user.setUserid(id);
			user.setPassword(pass);

			if (id.equals("")) {
				nullCheck = "エラー\n\n 入力isbnが未入力のため、書籍登録処理は行えませんでした。";
				request.setAttribute("isbnIsNotNull", isbnIsNotNull);
				request.setAttribute("nullCheck", nullCheck);

				request.setAttribute("errorLinkText", "login");
				request.setAttribute("errorLink", "/logout");


				request.getRequestDispatcher("view/error.jsp").forward(request, response);
				return;
			}

			User u = UserDAO.login(request, user);
			System.out.println(u);
			if (u == null) {
				request.setAttribute("message", "入力データが間違っています！");
				request.getRequestDispatcher("view/login.jsp").forward(request, response);
			}

		}catch (IllegalStateException e) {

			error = "DB接続エラーの為、書籍登録処理は行えませんでした。";
			cmd = "menu";
			link = "/view/menu.jsp";

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			// エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {

				// エラーが無い場合はListServletにフォワード
				request.getRequestDispatcher("/view/menu.jsp").forward(request, response);

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
