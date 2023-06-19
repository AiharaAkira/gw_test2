package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import dao.BookDAO;

public class DetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";
		String cmd = "";
		String link = "";

		try {

			request.setCharacterEncoding("UTF-8");

			String isbn = request.getParameter("isbn");
			cmd = request.getParameter("cmd");
			Book book = BookDAO.selectByIsbn(isbn);
			request.setAttribute("book", book);

			// 詳細情報のエラーチェック
			if (book.getIsbn() == null) {
				error = "表示対象の書籍が存在しない為、詳細情報は表示出来ませんでした。";
				cmd = "list";
				link = "/list";
				return;
			}



		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、書籍登録処理は行えませんでした。";
			cmd = "menu";
			link = "/view/menu.jsp";

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			if (cmd.equals("update")) {

				request.getRequestDispatcher("view/update.jsp").forward(request, response);
			} else if(cmd.equals("detail")){
				request.getRequestDispatcher("view/detail.jsp").forward(request, response);

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
