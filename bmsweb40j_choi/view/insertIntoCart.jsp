<%@page import="bean.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% Book book= (Book)request.getAttribute("book"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍管理システム</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/view/css/insertIntoCart.css">
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table id="iic_menu_tbl" >
		<tr>
			<td class="iic_menu_td" >[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td class="iic_menu_td" >[<a href="<%=request.getContextPath()%>/list">書籍一覧</a>]
			</td>
			<td class="iic_menu_title" >カートに追加</td>
			<td class="iic_menu_td2"  >&nbsp;</td>
			<td class="iic_menu_td2"  >&nbsp;</td>
		</tr>
	</table>

	<hr id="iic_menu_hr">

	<h2 id="iic_msg">下記の書籍をカートに追加しました。</h2>

	<form action="<%=request.getContextPath()%>/showCart">
		<table id="iic_tbl">
			<tr>
				<td class="iic_td1">ISBN</td><td class="iic_td2"><%= book.getIsbn() %></td>
			</tr>

			<tr>
				<td class="iic_td1">TITLE</td><td class="iic_td2"><%= book.getTitle() %></td>
			</tr>

			<tr>
				<td class="iic_td1">価格</td><td class="iic_td2"><%= book.getPrice() %>円</td>
			</tr>

			<tr>
				<td class="iic_td1">購入数</td><td class="iic_td2"> 1 冊</td>
			</tr>

			<tr>
				<td colspan="2" id="iic_btn"><input type="submit" value="カート確認"></td>
			</tr>

		</table>
	</form>

	<%@ include file="/common/footer.jsp"%>
</body>
</html>