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
	href="<%=request.getContextPath()%>/view/css/showHistoryOrderedItem.css">
</head>
<body>

	<%@ include file="/common/header.jsp"%>

	<table id="iid_menu_tbl">
		<tr>
			<td class="iid_menu_td">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td id="iid_menu_title">購入履歴</td>
			<td class="iid_menu_td">&nbsp;</td>
			<td class="iid_menu_td">&nbsp;</td>
		</tr>
	</table>

	<hr id="iid_menu_hr">


	<table id="iid_tbl">
		<tr>
			<th class="iid_th">Title</th>
			<th class="iid_th">数量</th>
			<th class="iid_th">注文日</th>
		</tr>
		<%
			ArrayList<OrderedItem> list = (ArrayList<OrderedItem>) request.getAttribute("ordered_list");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					OrderedItem oi = (OrderedItem) list.get(i);
		%>
		<tr>
			<td class="iid_td"><%= oi.getTitle() %></td>
			<td class="iid_td"><%= oi.getQuantity() %></td>
			<td class="iid_td"><%= oi.getDate() %></td>

		</tr>
		<%
			}
			} else {
		%>
		<tr>
			<td class="iid_td">&nbsp;</td>
			<td class="iid_td">&nbsp;</td>
			<td class="iid_td">&nbsp;</td>
		</tr>
		<%
			}
		%>
	</table>

	<%@ include file="/common/footer.jsp"%>
</body>
</html>