package util;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import bean.Sale;
import dao.BookDAO;

public class IntoPDF {

	public static final String RESULT = "C:\\Users\\Globalway\\Desktop\\abc.pdf";

	public static void main(String[] args) throws DocumentException, IOException {
		new IntoPDF().createPdf(RESULT);
	}

	public void createPdf(String filename) throws DocumentException, IOException {

		ArrayList<Sale> books = BookDAO.selectAll();

		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.open();

		document.add(new Paragraph("<h1>書籍管理システム 書籍リスト</h1>"));

		Chunk chunk = new Chunk("BookList", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD));
		Phrase phrase= new Phrase(chunk);
		Paragraph paragraph = new Paragraph(phrase);
		paragraph.setAlignment("center");
		document.add(paragraph);

		Table table = new Table(3);
		table.setBorderWidth(1);
		table.setBorderColor(new Color(0, 0, 0));
		table.setPadding(5);
		table.setSpacing(0);
		table.setAlignment("center");
		Cell cell = new Cell("");

		cell = new Cell("ISBN");
		table.setBorderColor(new Color(0, 0, 0));
		table.addCell(cell);

		cell = new Cell("title");
		table.setBorderColor(new Color(0, 0, 0));
		table.addCell(cell);

		cell = new Cell("price");
		table.setBorderColor(new Color(0, 0, 0));
		table.addCell(cell);

		for (Sale sale : books) {

			cell = new Cell(sale.getIsbn());
			table.setBorderColor(new Color(0, 0, 0));
			table.addCell(cell);

			cell = new Cell(sale.getTitle());
			table.setBorderColor(new Color(0, 0, 0));
			table.addCell(cell);

			cell = new Cell(String.valueOf(sale.getPrice()) + "￥");
			table.setBorderColor(new Color(0, 0, 0));
			table.addCell(cell);

		}

		document.add(table);

		Chunk chunk2 = new Chunk("list", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.UNDERLINE));
		chunk2.setAnchor("http://localhost:8080/bmsweb40j_choi/list");
		Phrase phrase2= new Phrase(chunk2);
		Paragraph paragraph2 = new Paragraph(phrase2);
		document.add(paragraph2);

		document.close();
	}

}
