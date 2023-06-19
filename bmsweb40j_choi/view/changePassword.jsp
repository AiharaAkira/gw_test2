<%@page import="bean.User"%>
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
	href="<%=request.getContextPath()%>/view/css/changePassword.css">
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table id="l_menu_tbl">
		<tr>
			<td class="l_menu_link">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>



			<td id="l_menu_title">パスワード変更</td>
			<td class="l_menu_link2">&nbsp;</td>
			<td class="l_menu_link2">&nbsp;</td>
		</tr>
	</table>

	<hr id="l_menu_hr">


	<form action="<%=request.getContextPath()%>/changePassword">


		<table id="cp_tbl">
			<tr>
				<td class="cp_th">ユーザー</td>
				<td><%=user.getUserid()%><input type="hidden" name="id"
					value="<%=user.getUserid()%>"></td>
			</tr>
			<tr>
				<td class="cp_th">旧パスワード</td>
				<td><input type="password" name="prePw"></td>
			</tr>
			<tr>
				<td class="cp_th">新パスワード</td>
				<td><input type="password" name="pw"></td>
			</tr>
			<tr>
				<td class="cp_th">新パスワード(確認用)</td>
				<td><input type="password" name="pwCheck"></td>
			</tr>
			<tr>
				<td id="cp_btn" colspan="2"><input type="submit" value="変更"></td>
			</tr>
		</table>


	</form>

	<%@ include file="/common/footer.jsp"%>
</body>
</html>