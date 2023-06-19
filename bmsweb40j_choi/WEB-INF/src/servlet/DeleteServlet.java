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

public class DeleteServlet extends HttpServlet {

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

			String nullCheck = "  ";
			String isbnIsNotNull = "  ";

			Book book = BookDAO.selectByIsbn(isbn);

			// 更新対象の有無のエラーチェック
			if (book.getIsbn() == null) {
				error = "削除対象の書籍が存在しない為、書籍削除処理は行えませんでした。";
				cmd = "list";
				link = "/list";
				return;
			}

			if (BookDAO.delete(book) >= 1) {
				ArrayList<Sale> books = BookDAO.selectAll();

				request.setAttribute("book_list", books);

			}

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

}
