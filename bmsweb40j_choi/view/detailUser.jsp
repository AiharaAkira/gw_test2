<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍管理システム</title>

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/view/css/detailUser.css">
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

			<td id="l_menu_title">ユーザー詳細情報</td>
			<td class="l_menu_link2">&nbsp;</td>
			<td class="l_menu_link2">&nbsp;</td>
		</tr>
	</table>
	<hr>

	<table id="d_tbl">



		<tr>
			<td class="d_td"><form
					action="<%=request.getContextPath()%>/detailUser">
					<input type="hidden" name="isbn" value="<%/* book.getIsbn() */%>"><input
						type="hidden" name="cmd" value="update"><input
						type="submit" class="d_btn" value="変">
				</form></td>
			<td class="d_td"><form
					action="<%=request.getContextPath()%>/deleteUser">
					<input type="hidden" name="isbn" value="<%/* book.getIsbn() */%>"><input
						type="submit" class="d_btn" value="削">
				</form></td>
		</tr>



		<tr>
			<td class="d_td1">ユーザー</td>
			<td class="d_td2"></td>
		</tr>
		<tr>
			<td class="d_td1">パスワード</td>
			<td class="d_td2"></td>
		</tr>
		<tr>
			<td class="d_td1">Eメール</td>
			<td class="d_td2"></td>
		</tr>
		<tr>
			<td class="d_td1">権限</td>
			<td class="d_td2"></td>
		</tr>
	</table>

	<hr id="l_menu_hr">

	<%@ include file="/common/footer.jsp"%>

</body>
</html>