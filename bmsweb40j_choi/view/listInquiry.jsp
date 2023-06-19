<%@page import="bean.Sale"%>
<%@page import="bean.User"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book"%>
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
<html>
<head>
<title>書籍管理システム</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/view/css/list.css">
</head>
<body>
	<%@ include file="/common/header.jsp"%>


	<table id="l_menu_tbl">
		<tr>
			<td class="l_menu_link">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>

			<%
				if (user.getAuthority().equals("1")) {
			%>
			<td class="l_menu_link">[<a
				href="<%=request.getContextPath()%>/showCart">カート状況</a>]
			</td>
			<%
				}
			%>





			<td id="l_menu_title">お問い合わせ一覧</td>
			<td class="l_menu_link2">&nbsp;</td>
			<td class="l_menu_link2">&nbsp;</td>
		</tr>
	</table>

	<hr id="l_menu_hr">
	<div id="l_search_div">


		<table id="l_td">
			<tr>
				<th class="l_th">No.</th>
				<th class="l_th">タイトル</th>
				<th class="l_th">名前</th>
				<th class="l_th">日付</th>
				<th class="l_th"></th>





			</tr>
			<%
				ArrayList<Sale> list = (ArrayList<Sale>) request.getAttribute("book_list");
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						Sale books = (Sale) list.get(i);
			%>
			<tr>
				<td class="l_td1"></td>
				<td class="l_td1"><%=books.getIsbn()%></td>
				<td class="l_td1"><%=books.getTitle()%></td>
				<td class="l_td1"><%=books.getPrice()%>円</td>
				<td class="l_td1"><a
					href="<%=request.getContextPath()%>/detailInquiry">詳細</a><a
					href="<%=request.getContextPath()%>/deleteInquiry">削除</a></td>
			</tr>

<%} }else{%>
			<tr>
				<td class="l_td5">&nbsp;</td>
				<td class="l_td5">&nbsp;</td>
				<td class="l_td5">&nbsp;</td>
				<td class="l_td5">&nbsp;</td>
				<td class="l_td5">&nbsp;</td>
			</tr>
<%} %>
		</table>

	</div>


	<%@ include file="/common/footer.jsp"%>
</body>
</html>