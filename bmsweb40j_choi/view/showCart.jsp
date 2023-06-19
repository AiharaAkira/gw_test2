<%@page import="bean.Sale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍管理システム</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/view/css/showCart.css">
</head>
<body>

	<%@ include file="/common/header.jsp"%>


	<table id="sc_menu_tbl">
		<tr>
			<td class="sc_menu_td">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td class="sc_menu_td">[<a
				href="<%=request.getContextPath()%>/list">書籍一覧</a>]
			</td>
			<td class="sc_menu_title">カート内容</td>
			<td class="sc_menu_td2">&nbsp;</td>
			<td class="sc_menu_td2">&nbsp;</td>
		</tr>
	</table>

	<hr id="sc_hr">

	<table id="sc_tbl">
		<tr>
			<th class="sc_th">isbn</th>
			<th style="background-color: #6666ff; width: 200px">title</th>
			<th style="background-color: #6666ff; width: 200px">価格</th>
			<th style="background-color: #6666ff; width: 200px">購入数</th>
			<th style="background-color: #6666ff; width: 200px"></th>
		</tr>
		<%
			int sum = 0;
			int cnt = 0;
			ArrayList<Sale> list = (ArrayList<Sale>) request.getAttribute("book_list");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {

					Sale books = (Sale) list.get(i);
					sum += books.getPrice();
		%>
		<tr>
			<td class="sc_td"><a
				href="<%=request.getContextPath()%>/detail?isbn=<%=books.getIsbn()%>&cmd=detail"><%=books.getIsbn()%></a></td>
			<td class="sc_td"><%=books.getTitle()%></td>
			<td class="sc_td"><%=books.getPrice()%>円</td>
			<td class="sc_td"><%=books.getQuantity() %>冊</td>

			<td class="sc_td"><a
				href="<%=request.getContextPath()%>/showCart?delno=<%=cnt%>">削除</a>
			</td>
		</tr>
		<%
			cnt++;}
			} else {
		%>
		<tr>
			<td class="sc_td2">&nbsp;</td>
			<td class="sc_td2">&nbsp;</td>
			<td class="sc_td2">&nbsp;</td>
			<td class="sc_td2" colspan="2">&nbsp;</td>
		</tr>
		<%
			}
		%>
	</table>

	<hr id="sc_last_hr">

	<div id="sc_sum_div">
		<div id="sc_sum">合計</div>\<%=sum%>
	</div>

	<form action="<%=request.getContextPath()%>/buyConfirm">
		<input id="sc_btn" type="submit" value="購入">
	</form>



	<%@ include file="/common/footer.jsp"%>
</body>
</html>