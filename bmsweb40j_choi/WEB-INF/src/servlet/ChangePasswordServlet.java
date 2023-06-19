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

public class ChangePasswordServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";
		String cmd = "";
		String link = "";

		try {

			request.setCharacterEncoding("UTF-8");

			HttpSession hs =request.getSession();
			//セッションからユーザー情報を取得
			User user = (User) hs.getAttribute("userInfo");
			//セッション切れか確認
			if (user == null) {
				//セッション切れならerror.jspへフォワード
				request.setAttribute("error", "セッション切れの為、メニュー画面が表示できませんでした。");
				request.setAttribute("cmd", "logout");
				request.setAttribute("link", "/logout");
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				return;
			}

			String id = request.getParameter("id");
			String pw = request.getParameter("pw");

			if(pw.equals("")) {

				error = "パスワードを入力して下さい！";
				cmd = "menu";
				link = "/view/menu.jsp";

			}

			UserDAO.changePw(id,pw);

			user.setPassword(pw);
			hs.setAttribute("userInfo", user);



		}catch (IllegalStateException e) {

			error = "DB接続エラーの為、パスワード変更は行えません。";
			cmd = "logout";
			link = "/logout";

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
