package util;

import java.awt.Color;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import bean.Sale;
import dao.BookDAO;

public class BarChartBean {

	public static void main(String arg[]) {
		BarChartBean bcb = new BarChartBean();
		JFreeChart chart = bcb.getBarChart();
		ChartFrame frame1 = new ChartFrame("Bar Chart", chart);
		frame1.setSize(400, 350);
		frame1.setVisible(true);
	}

	public JFreeChart getBarChart() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		ArrayList<Sale> sales = BookDAO.selectAll();

		for (Sale sale : sales) {
			dataset.setValue(sale.getPrice() * sale.getQuantity(), "price", sale.getIsbn());

		}

		JFreeChart chart = ChartFactory.createBarChart("", "ISBN", "price", dataset, PlotOrientation.VERTICAL, false,
				false, false);
		chart.setBackgroundPaint(Color.LIGHT_GRAY);
		chart.getTitle().setPaint(Color.LIGHT_GRAY);
		CategoryPlot p = chart.getCategoryPlot();
		p.setRangeGridlinePaint(Color.gray);
		p.setBackgroundPaint(Color.white);
		return chart;
	}
}