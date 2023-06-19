<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍管理システム</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/view/css/insertUser.css">
</head>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table id="l_menu_tbl">
		<tr>
			<td class="l_menu_link">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>

			<td class="l_menu_link">[<a
				href="<%=request.getContextPath()%>/view/listUser">ユーザー一覧</a>]
			</td>


			<td id="l_menu_title">ユーザー登録</td>
			<td class="l_menu_link2">&nbsp;</td>
			<td class="l_menu_link2">&nbsp;</td>
		</tr>
	</table>

	<hr id="l_menu_hr">

	<form  id="lu_form" action="<%=request.getContextPath()%>/insertUser">

		<table id="iu_tbl">

			<tr>
				<td class="iu_th">ユーザー</td><td><input name="id"></td>
			</tr>
			<tr>
				<td class="iu_th">パスワード</td><td><input name="pw"></td>
			</tr>
			<tr>
				<td class="iu_th">パスワード(確認用)</td><td><input name="pw2"></td>
			</tr>
			<tr>
				<td class="iu_th">Eメール</td><td><input name="email"></td>
			</tr>
			<tr>
				<td class="iu_th">権限</td><td><select class="iu_select" name="authority"> <option value="2">管理者</option><option value="1">一般ユーザー</option> </select></td>
			</tr>

			<tr>
				<td id="iu_btn" colspan="2"><input type="submit" value="登録"></td>
			</tr>
		</table>

	</form>




	<%@ include file="/common/footer.jsp"%>
</body>
</html>