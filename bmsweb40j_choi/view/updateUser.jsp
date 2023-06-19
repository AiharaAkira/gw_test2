<%@page import="bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	User user = (User) request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍管理システム</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/view/css/updateUser.css">
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table id="l_menu_tbl">
		<tr>
			<td class="l_menu_link">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>

			<td class="l_menu_link">[<a
				href="<%=request.getContextPath()%>/view/insertUser.jsp">ユーザー登録</a>]
			</td>

			<td class="l_menu_link">[<a
				href="<%=request.getContextPath()%>/listUser">ユーザー一覧</a>]
			</td>

			<td id="l_menu_title">書籍一覧</td>
			<td class="l_menu_link2">&nbsp;</td>
			<td class="l_menu_link2">&nbsp;</td>
		</tr>
	</table>

	<hr id="l_menu_hr">

	<form action="<%=request.getContextPath()%>/updateUser">
		<table id="u_tbl">
			<tr>
				<td id="u_th" colspan="2">&lt;&lt;変更前情報&gt;&gt;</td>
				<td id="u_th2">&lt;&lt;変更後情報&gt;&gt;</td>
			</tr>
			<tr>
				<td class="u_td1">ユーザー</td>
				<td class="u_td2"><%=user.getUserid()%></td>
				<td class="u_td3"><%=user.getUserid()%><input type="hidden"
					name="id" value="<%=user.getUserid()%>"></td>
			</tr>
			<tr>
				<td class="u_td1">パスワード</td>
				<td class="u_td2"><%=user.getPassword()%></td>
				<td class="u_td3"><input name="pw"></td>
			</tr>
			<tr>
				<td class="u_td1">パスワード(確認用)</td>
				<td ></td>
				<td class="u_td3"><input name="pwCheck"></td>
			</tr>

			<tr>
				<td class="u_td1">Eメール</td>
				<td class="u_td2"><%=user.getEmail()%></td>
				<td class="u_td3"><input name="email"
					value="<%=user.getEmail()%>"></td>
			</tr>

			<tr>
				<td class="u_td1">権限</td>
				<td class="u_td2"><%=user.getAuthority()%></td>
				<td class="u_td3"><select id="uu_select" name="authority"><option
							value="1">一般ユーザー</option>
						<option value="2">管理者</option></select></td>
			</tr>

		</table>
		<div id="u_div">
			<input id="u_btn" type="submit" value="変更完了">
		</div>
	</form>


	<%@ include file="/common/footer.jsp"%>

</body>
</html>