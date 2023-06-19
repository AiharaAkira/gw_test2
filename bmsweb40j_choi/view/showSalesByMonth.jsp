<%@page import="bean.Sale"%>
<%@page import="bean.User"%>
<%@page import="bean.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//セッションからユーザー情報を取得
	User user = (User) session.getAttribute("userInfo");
	//セッション切れか確認
	if (user == null) {
		//セッション切れならerror.jspへフォワード
		request.setAttribute("error", "セッション切れの為、メニュー画面が表示できませんでした。");
		request.setAttribute("cmd", "logout");
		request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		return;
	}
%>
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

			<td id="soi_menu_title">売上げ状況</td>
			<td class="soi_menu_td1">&nbsp;</td>
			<td class="soi_menu_td1">&nbsp;</td>
		</tr>
	</table>

	<hr id="soi_menu_hr">

	<form action="<%=request.getContextPath()%>/showSalesByMonth">
		<div>
			年：<input name="year">月<input name="month"><input
				type="submit" value="検索">
		</div>
	</form>

	<table id="l_td">
		<tr>
			<th class="l_th">ISBN</th>
			<th class="l_th">Title</th>
			<th class="l_th">価格</th>
			<th class="l_th">数量</th>
			<th class="l_th">売上小計</th>

		</tr>
		<%
			int sum = 0;
			ArrayList<Sale> list = (ArrayList<Sale>) request.getAttribute("sales_list");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Sale books = (Sale) list.get(i);
					sum += (books.getPrice() * books.getQuantity());
		%>
		<tr>
			<td class="l_td1"><a
				href="<%=request.getContextPath()%>/detail?isbn=<%=books.getIsbn()%>&cmd=detail"><%=books.getIsbn()%></a></td>
			<td class="l_td1"><%=books.getTitle()%></td>
			<td class="l_td1"><%=books.getPrice()%>円</td>
			<td class="l_td1"><%=books.getQuantity()%></td>
			<td class="l_td1"><%=books.getPrice() * books.getQuantity()%></td>






		</tr>
		<%
			}
			} else {
		%>
		<tr>
			<td class="l_td5">&nbsp;</td>
			<td class="l_td5">&nbsp;</td>
			<td class="l_td5">&nbsp;</td>
			<td class="l_td5">&nbsp;</td>
			<td class="l_td5">&nbsp;</td>
		</tr>
		<%
			}
		%>
	</table>



	<%
		if (sum != 0) {
	%>

	<hr>

	<div id="bc_sum_div">
		<div id="bc_sum">合計</div>
		\<%=sum%>
	</div>

	<%
		}
	%>


	<form action="<%=request.getContextPath()%>/graph"><button>graph</button></form>

	<%@ include file="/common/footer.jsp"%>

</body>
</html>