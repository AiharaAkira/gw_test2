<%@page import="bean.User"%>
<%@page import="bean.Book"%>
<%@page contentType="text/html; charset=UTF-8"%>
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
<%
	Book book = (Book) request.getAttribute("book");
%>

<html>
<head>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/view/css/detail.css">

<title>書籍管理システム</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<table id="d_menu_tbl">
		<tr>
			<td class="d_menu_td">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>


			<td class="d_menu_td">[<a
				href="<%=request.getContextPath()%>/listInquiry">書籍一覧</a>]
			</td>





			<td id="d_menu_title">書籍詳細画面</td>
			<td class="d_menu_td">&nbsp;</td>
			<td class="d_menu_td">&nbsp;</td>
		</tr>
	</table>
	<hr id="d_menu_hr">
	<div id="d_div">

		<form action="<%=request.getContextPath()%>/deleteInquiry">

			<table id="d_tbl">

				<tr>
					<td class="d_td1">No.</td>
					<td class="d_td1">日付</td>
					<td class="d_td1">時間</td>
					<td class="d_td1">タイトル</td>
					<td class="d_td1">名前</td>
				</tr>
				<tr>
					<td class="d_td2">No.</td>
					<td class="d_td2">日付</td>
					<td class="d_td2">時間</td>
					<td class="d_td2">タイトル</td>
					<td class="d_td2">名前</td>
				</tr>
				<tr>
					<td class="d_td1" colspan="4">E-Mail</td>
					<td class="d_td1">電話番号</td>
				</tr>
				<tr>
					<td class="d_td2" colspan="4">E-Mail</td>
					<td class="d_td2">電話番号</td>
				</tr>
				<tr>
					<td class="d_td1" colspan="5">お問い合わせ内容</td>
				</tr>

				<tr>
					<td class="d_td2" colspan="5">お問い合わせ内容</td>
				</tr>

				<tr>
					<td class="d_td2" colspan="5"><input type="submit" value="削除"></td>
				</tr>


			</table>

		</form>

	</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>