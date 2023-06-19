package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

public class UpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";
		String cmd = "";
		String link = "";

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
				request.setAttribute("link", "/logout");
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				return;
			}

			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");

			String nullCheck = "  ";
			String isbnIsNotNull = "  ";

			Book book = new Book();

			book.setIsbn(isbn);
			book.setTitle(title);
			book.setPrice(Integer.parseInt(price));

			if (title.equals("")) {
				error = "タイトルが未入力の為、書籍更新処理は行えませんでした。";
				cmd = "list";
				link = "/list";
				return;
			}
			if (request.getParameter("price").equals("")) {
				error = "価格が未入力の為、書籍更新処理は行えませんでした。";
				cmd = "list";
				link = "/list";
				return;
			}

			if (BookDAO.update(book) == 1) {
				ArrayList<Sale> books = BookDAO.selectAll();

				request.setAttribute("book_list", books);
				request.getRequestDispatcher("view/list.jsp").forward(request, response);

			}

		} catch (NumberFormatException e) {
			error = "価格の値が不正の為、書籍更新処理は行えませんでした。";
			cmd = "list";
			link = "/list.jsp";

			e.printStackTrace();
		} catch (IllegalStateException e) {

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
				request.getRequestDispatcher("/list").forward(request, response);

			} else {

				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.setAttribute("link", link);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}

		}

	}

	// doPost()メソッド
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
