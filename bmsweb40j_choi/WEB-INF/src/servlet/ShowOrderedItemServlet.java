package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderedItem;
import dao.OrderedItemDAO;

public class ShowOrderedItemServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";
		String cmd = "";
		String link = "";

		try {

			ArrayList<OrderedItem> list = OrderedItemDAO.selectAll();

			request.setAttribute("ordered_list", list);

			request.getRequestDispatcher("/view/showOrderedItem.jsp").forward(request, response);

		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、購入状況確認は出来ません。";
			cmd = "logout";
			link = "/logout";

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			// エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {

				// エラーが無い場合はshowOrderedItem.jspにフォワードする
				request.getRequestDispatcher("/view/showOrderedItem.jsp").forward(request, response);

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
