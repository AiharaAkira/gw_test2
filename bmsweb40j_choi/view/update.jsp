<%@page import="bean.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Book book = (Book) request.getAttribute("book");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍管理システム</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/view/css/update.css">
</head>
<body>
	<%@ include file="/common/header.jsp"%>


	<table id="u_menu_tbl">
		<tr>
			<td class="u_menu_td">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td class="u_menu_td">[<a
				href="<%=request.getContextPath()%>/list">書籍一覧</a>]
			</td>
			<td class="u_menu_td">[<a
				href="<%=request.getContextPath()%>/view/insert.jsp">書籍登録</a>]
			</td>
			<td id="u_menu_title">書籍詳細画面</td>
			<td class="u_menu_td2">&nbsp;</td>
			<td class="u_menu_td2">&nbsp;</td>
		</tr>
	</table>
	<hr id="u_menu_hr">

	<form action="<%=request.getContextPath()%>/update">
		<table id="u_tbl" border="1">
			<tr>
				<td id="u_th" colspan="2">&lt;&lt;変更前情報&gt;&gt;</td>
				<td id="u_th2">&lt;&lt;変更後情報&gt;&gt;</td>
				<td id="u_th2">&lt;&lt;変更後画像&gt;&gt;</td>
			</tr>
			<tr>
				<td class="u_td1">ISBN</td>
				<td class="u_td2"><%=book.getIsbn()%></td>
				<td class="u_td3"><%=book.getIsbn()%><input type="hidden" name="isbn"
					value="<%=book.getIsbn()%>"></td>
				<td class="u_td2" rowspan="4"><%=book.getIsbn()%></td>

			</tr>
			<tr>
				<td class="u_td1">TITLE</td>
				<td class="u_td2"><%=book.getTitle()%></td>
				<td class="u_td3"><input name="title" value="<%=book.getTitle()%>"></td>
			</tr>
			<tr>
				<td class="u_td1">価格</td>
				<td class="u_td2">\<%=book.getPrice()%></td>
				<td class="u_td3"><input name="price" value="<%=book.getPrice()%>"></td>
			</tr>
			<tr>
				<td class="u_td1">画像</td>
				<td class="u_td2" colspan="2"><input type="file" name="img"></td>
			</tr>
		</table>
		<input type="hidden" value="<%=book.getIsbn()%>">
		<div id="u_div">
			<input id="u_btn" type="submit" value="変更完了">
		</div>
	</form>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>