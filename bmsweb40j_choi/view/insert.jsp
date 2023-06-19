<%@page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<title>書籍管理システム</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/view/css/insert.css">

</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<table id="i_menu_tbl">
		<tr>
			<td class="i_menu_td1">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>
			<td class="i_menu_td1">[<a
				href="<%=request.getContextPath()%>/list">書籍一覧</a>]
			</td>
			<td id="i_menu_title">書籍登録</td>
			<td class="i_menu_td2">&nbsp;</td>
			<td class="i_menu_td2">&nbsp;</td>
		</tr>
	</table>
	<hr id="i_menu_hr">
	<div id="i_div">
		<form action="<%=request.getContextPath()%>/insert" method="post" enctype="multipart/form-data">

			<table id="i_tbl">

				<tr>
					<td class="i_td">ISBN</td>
					<td><input name="isbn"></td>
				</tr>
				<tr>
					<td class="i_td">TITLE</td>
					<td><input name="title"></td>
				</tr>
				<tr>
					<td class="i_td">価格</td>
					<td><input name="price"></td>
				</tr>
				<tr>
					<td class="i_td">画像</td>
					<td><input type="file" name="img"></td>
				</tr>
			</table>


			<button id="i_btn">登録</button>
		</form>
	</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>