package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Book;
import bean.Sale;
import bean.User;
import dao.BookDAO;
import dao.SaleDAO;

public class SearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {

			request.setCharacterEncoding("UTF-8");

			HttpSession hs = request.getSession();
			// セッションからユーザー情報を取得
			User user = (User) hs.getAttribute("userInfo");
			// セッション切れか確認
			if (user == null) {
				// セッション切れならerror.jspへフォワード
				request.setAttribute("error", "セッション切れの為、メニュー画面が表示できませんでした。");
				request.setAttribute("cmd", "logout");
				request.setAttribute("link", "/view/menu.jsp");
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				return;
			}

			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");

			ArrayList<Sale> books2 = new ArrayList<>();
			if (isbn.equals(null) && title.equals(null) && price.equals(null)) {
				books2 = (ArrayList<Sale>) BookDAO.selectAll();
			} else {
				books2 = (ArrayList<Sale>) SaleDAO.search(isbn, title, price);
			}

			request.setAttribute("book_list", books2);

		} catch (IllegalStateException e) {
			error = "error";
			cmd = "menu";

			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {
				// エラーが無い場合はlist.jspにフォワード
				request.getRequestDispatcher("/view/list.jsp").forward(request, response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.setAttribute("link", "/view/menu.jsp");
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}

}
