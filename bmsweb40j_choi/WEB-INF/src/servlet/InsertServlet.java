package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bean.Book;
import bean.User;
import dao.BookDAO;

public class InsertServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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


			String path = request.getSession().getServletContext().getRealPath("img");
			MultipartRequest mr = new MultipartRequest(request, path, 30 * 1024 * 1024, "utf-8",
					new DefaultFileRenamePolicy());

			String isbn = mr.getParameter("isbn");
			String title = mr.getParameter("title");
			int price = Integer.parseInt(mr.getParameter("price"));
			String img = mr.getFilesystemName("img");

			// 全データの空白チェック（データが入力されているかどうか）
			if (isbn.equals("")) {
				error = "ISBNが未入力の為、書籍登録処理は行えませんでした。";
				cmd = "list";
				link = "/list";
				return;
			}

			if (title.equals("")) {
				error = "タイトルが未入力の為、書籍登録処理は行えませんでした。";
				cmd = "list";
				link = "/list";
				return;
			}

			if (mr.getParameter("price").equals("")) {
				error = "価格が未入力の為、書籍登録処理は行えませんでした。";
				cmd = "list";
				link = "/list";
				return;
			}

			if (BookDAO.isbnSearch(request) == 0) {

				error = "エラー\\n入力isbnは既に登録済みのため、書籍登録処理は行えませんでした。";
				cmd = "list";
				link = "/list";
				return;
			}

			BookDAO.insert(new Book(isbn, title, price, img));

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
