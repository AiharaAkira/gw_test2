<%@page import="bean.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍管理システム</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/view/css/listUser.css">
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




			<td id="l_menu_title">ユーザー一覧</td>
			<td class="l_menu_link2">&nbsp;</td>
			<td class="l_menu_link2">&nbsp;</td>
		</tr>
	</table>

	<hr id="l_menu_hr">

	<table id="l_search_tbl">
		<tr>
			<td>
				<form action="<%=request.getContextPath()%>/listUser">
					ユーザー：<input type=text size="30" name="user"><input
						type="submit" name="search" value="検索">
				</form>
			</td>
			<td>
				<form action="<%=request.getContextPath()%>/listUser">
					<input type="submit" name="searchall" value="全件表示"></input>
				</form>
			</td>
		</tr>
	</table>

	<div class="lu_blank"></div>

	<table id="l_td" >
		<tr>
			<th class="l_th">ユーザー</th>
			<th class="l_th">パスワード</th>
			<th class="l_th">Eメール</th>
			<th class="l_th">権限</th>
			<th class="l_th" colspan="2"></th>
		</tr>
		<%
			ArrayList<User> list = (ArrayList<User>) request.getAttribute("users");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					User user = (User) list.get(i);
		%>

		<tr>
			<td class="l_td1"><a
				href="<%=request.getContextPath()%>/detailUser?user=<%=user.getUserid()%>&cmd=detailUser">
					<%=user.getUserid()%>
			</a></td>
			<td class="l_td1"><%=user.getPassword()%></td>
			<td class="l_td1"><%=user.getEmail()%></td>
			<td class="l_td1"><%=user.getAuthority()%></td>
			<td class="l_td1"><a
				href="<%=request.getContextPath()%>/detailUser?user=<%=user.getUserid()%>&cmd=updateUser">変更</a></td>
			<td class="l_td1"><a
				href="<%=request.getContextPath()%>/deleteUser?user=<%=user.getUserid()%>">削除</a></td>



		</tr>
		<%
			}
			} else{
		%>
		<tr>
			<td class="l_td5">&nbsp;</td>
			<td class="l_td5">&nbsp;</td>
			<td class="l_td5">&nbsp;</td>
			<td class="l_td5" style="text-align: center; width: 200px"
				colspan="2">&nbsp;</td>
		</tr>
		<%
			}
		%>
	</table>

	<div class="lu_blank_bot"></div>



	<%@ include file="/common/footer.jsp"%>

</body>
</html>