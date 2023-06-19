<%@page import="org.jfree.chart.ChartUtilities"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="util.BarChartBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍管理システム</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/view/css/showSalesByMonth.css">
</head>
<body>

	<%@ include file="/common/header.jsp"%>

	<table id="soi_menu_tbl">
		<tr>
			<td class="soi_menu_td1">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>

			<td id="soi_menu_title">売上げ状況グラフ</td>
			<td class="soi_menu_td1">&nbsp;</td>
			<td class="soi_menu_td1">&nbsp;</td>
		</tr>
	</table>

	<hr id="soi_menu_hr">



	<img src="<%=request.getContextPath()%>/view/chart.jsp" >


	<a href="<%=request.getContextPath()%>/showSalesByMonth">売上状況に戻る</a>

	<%@ include file="/common/footer.jsp"%>

</body>
</html>