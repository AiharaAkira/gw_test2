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
	href="<%=request.getContextPath()%>/view/css/userInfo.css">
</head>
<body>
	<div id="u_div">
		<div>
			名前：<%=user.getUserid()%></div>
		<%
			if (user.getAuthority().equals("2")) {
		%>
		<div>権限：管理者</div>
		<%
			} else if (user.getAuthority().equals("1")) {
		%>
		<div>権限：一般ユーザー</div>
		<%
			}
		%>
	</div>


</body>
</html>