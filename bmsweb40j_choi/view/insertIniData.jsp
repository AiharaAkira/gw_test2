<%@page import="bean.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍管理システム</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/view/css/insertIniData.css">
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table id="iid_menu_tbl">
		<tr>
			<td class="iid_menu_td">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td id="iid_menu_title">初期データ登録</td>
			<td class="iid_menu_td">&nbsp;</td>
			<td class="iid_menu_td">&nbsp;</td>
		</tr>
	</table>

	<hr id="iid_menu_hr">

	<h2 id="iid_msg">初期データとして以下のデータを登録しました。</h2>

	<table id="iid_tbl">
		<tr>
			<th class="iid_th">isbn</th>
			<th class="iid_th">title</th>
			<th class="iid_th">価格</th>
		</tr>
		<%
			ArrayList<Book> list = (ArrayList<Book>) request.getAttribute("book_list");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Book books = (Book) list.get(i);
		%>
		<tr>
			<td class="iid_td"><%=books.getIsbn()%></td>
			<td class="iid_td"><%=books.getTitle()%></td>
			<td class="iid_td">\<%=books.getPrice()%></td>

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