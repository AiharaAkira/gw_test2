<%@page import="bean.OrderedItem"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍管理システム</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/view/css/showOrderedItem.css">
</head>
<body>

	<%@ include file="/common/header.jsp"%>

	<table id="soi_menu_tbl" >
		<tr>
			<td class="soi_menu_td1" >[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>

			<td id="soi_menu_title"  >購入状況</td>
			<td class="soi_menu_td1" >&nbsp;</td>
			<td class="soi_menu_td1" >&nbsp;</td>
		</tr>
	</table>

	<hr id="soi_menu_hr">


	<table id="soi_tbl" >
		<tr>
			<th class="soi_th" >ユーザー</th>
			<th class="soi_th" >TITLE</th>
			<th class="soi_th" >数量</th>
			<th class="soi_th" >注文日</th>
		</tr>
		<%
			int sum = 0;
			ArrayList<OrderedItem> list = (ArrayList<OrderedItem>) request.getAttribute("ordered_list");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					OrderedItem books = (OrderedItem) list.get(i);
		%>
		<tr>
			<td class="soi_td" ><%=books.getUserid()%></td>
			<td class="soi_td" ><%=books.getTitle()%></td>
			<td class="soi_td" ><%=books.getQuantity() %></td>
			<td class="soi_td" ><%=books.getDate()%></td>


		</tr>
		<%
			}
			} else {
		%>
		<tr>
			<td class="soi_td" >&nbsp;</td>
			<td class="soi_td" >&nbsp;</td>
			<td class="soi_td" >&nbsp;</td>
		</tr>
		<%
			}
		%>
	</table>



	<%@ include file="/common/footer.jsp"%>
</body>
</html>