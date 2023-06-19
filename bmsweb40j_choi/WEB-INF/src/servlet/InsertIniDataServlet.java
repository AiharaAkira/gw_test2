package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Book;
import bean.Sale;
import bean.User;
import dao.BookDAO;
import util.FileIn;

public class InsertIniDataServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";
		String cmd = "";
		String link = "";
		FileIn in = new FileIn();

		try {

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

			ArrayList<Sale> list = BookDAO.selectAll();

			if (list.size() > 0) {


				request.setAttribute("error", "DBにはデータが存在するので、初期データ登録は出来ません。");
				request.setAttribute("cmd", "menu");
				request.setAttribute("link", "/view/menu.jsp");

				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			} else {

				String path = getServletContext().getRealPath("file\\initial_data.csv");

				// ファイルのオープンをおこない、ファイルのオープンに失敗した場合はerror.jspにフォワードする
				if (!(in.open(path))) {
					error = "初期データファイルが無い為、登録は行えません。";
					cmd = "menu";
					link = "/view/menu.jsp";
					return;
				}

				String[] strData = null; // 読み込みデータの分割格納用配列

				ArrayList<Book> list2 = new ArrayList<>();

				try {
					// 教科データファイルの読み込み
					Scanner sin = new Scanner(new File(path));

					// 全データを配列に読み込む
					while (sin.hasNextLine()) {
						// 読み込み1行データカンマで分割
						strData = sin.nextLine().split(",");

						// 各配列にデータを格納
						Book b = new Book(strData[0], strData[1], Integer.parseInt(strData[2]),strData[3]);
						list2.add(b);

						BookDAO.insert(b);
					}

					sin.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				request.setAttribute("book_list", list2);

			}

		} catch (IllegalStateException e) {

			error = "初期データに不備がある為、登録は行えません。";
			cmd = "menu";
			link = "/view/menu.jsp";

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			// エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {

				// エラーが無い場合、insertIniData.jspにフォワード
				request.getRequestDispatcher("/view/insertIniData.jsp").forward(request, response);
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
