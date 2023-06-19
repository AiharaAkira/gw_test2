package servlet;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Sale;
import dao.BookDAO;

public class CreateExcelServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String error = "";
		String link = "";
		String cmd = "";

		try {

			ArrayList<Sale> books = BookDAO.selectAll();

			FileOutputStream fos = new FileOutputStream("C:\\Users\\Globalway\\Desktop\\kanda\\pdf\\r.csv", true);
			// fos보다 두꺼운
			OutputStreamWriter osw = new OutputStreamWriter(fos, "Shift-JIS");

			BufferedWriter bw = new BufferedWriter(osw);
			bw.write("isbn" + ",");
			bw.write("タイトル" + ",");
			bw.write("価格" + ",");
			bw.write("数量" + ",");
			bw.write("イメージ" + "\r\n");

			for (Sale sale : books) {

				bw.write(sale.getIsbn() + ",");
				bw.write(sale.getTitle() + ",");
				bw.write(sale.getPrice() + ",");
				bw.write(sale.getQuantity() + ",");
				bw.write(sale.getImg() + "\r\n");

			}

			bw.close();
			osw.close();
			fos.close();
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、書籍登録処理は行えませんでした。";
			cmd = "menu";
			link = "/view/menu.jsp";

			e.printStackTrace();
		} catch (IOException e) {
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
