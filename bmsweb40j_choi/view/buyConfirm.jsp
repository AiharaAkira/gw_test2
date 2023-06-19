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
	href="<%=request.getContextPath()%>/view/css/buyConfirm.css">
</head>
<body>


	<%@ include file="/common/header.jsp"%>

	<table id="bc_menu_tbl">
		<tr>
			<td class="bc_menu_td">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td class="bc_menu_td">[<a
				href="<%=request.getContextPath()%>/list">書籍一覧</a>]
			</td>
			<td id="bc_menu_title">購入品確認</td>
			<td class="bc_menu_td2">&nbsp;</td>
			<td class="bc_menu_td2">&nbsp;</td>
		</tr>
	</table>

	<hr id="bc_menu_hr">


	<h2 class="bc_msg">下記の商品を購入しました。</h2>
	<h2 class="bc_msg">ご利用ありがとうございました。</h2>

	<table id="bc_tbl" style="margin: auto">
		<tr>
			<th class="bc_th">isbn</th>
			<th class="bc_th">title</th>
			<th class="bc_th">価格</th>
			<th class="bc_th">購入数</th>
			<th class="bc_th">小計</th>
		</tr>
		<%
			int sum = 0;
			ArrayList<Book> list = (ArrayList<Book>) request.getAttribute("book_list");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Book books = (Book) list.get(i);
					sum += books.getPrice();
		%>
		<tr>
			<td class="bc_td"><a
				href="<%=request.getContextPath()%>/detail?isbn=<%=books.getIsbn()%>&cmd=detail"><%=books.getIsbn()%></a></td>
			<td class="bc_td"><%=books.getTitle()%></td>
			<td class="bc_td"><%=books.getPrice()%>円</td>
			<td class="bc_td">購入数</td>
			<td class="bc_td">小計</td>


		</tr>
		<%
			}
			} else {
		%>
		<tr>
			<td class="bc_td">&nbsp;</td>
			<td class="bc_td">&nbsp;</td>
			<td class="bc_td">&nbsp;</td>
		</tr>
		<%
			}
		%>
	</table>

	<hr
		style="text-align: center; height: 2px; background-color: black; width: 950px">

	<div id="bc_sum_div">
		<div id="bc_sum">合計</div>\<%=sum%>
	</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>