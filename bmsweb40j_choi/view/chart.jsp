<%@page import="util.BarChartBean"%>
<%@ page contentType="image/jpeg"%>
<%@ page import="org.jfree.chart.*"%>
<%
	ServletOutputStream sos = null;
	try {

		sos = response.getOutputStream();
		BarChartBean bcb = new BarChartBean();
		JFreeChart chart = bcb.getBarChart();
		ChartUtilities.writeChartAsPNG(sos, chart, 400, 400);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		sos.close();
	}
%>
