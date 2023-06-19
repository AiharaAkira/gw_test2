<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

			<td id="soi_menu_title">売上げ状況グラフ</td>
			<td class="soi_menu_td1">&nbsp;</td>
			<td class="soi_menu_td1">&nbsp;</td>
		</tr>
	</table>


	<div>無料体験用メールアドレス[xxxx@xxxx]</div>

	<table>

		<tr>
			<td>お名前：警告文</td>
			<td>お問い合わせタイトル：警告文</td>
		</tr>

		<tr>
			<td>name</td>
			<td>title</td>
		</tr>

		<tr>
			<td>電話番号：警告文</td>
			<td>お問い合わせ内容：警告文</td>
		</tr>

		<tr>
			<td>tel</td>
			<td rowspan="5">content</td>
		</tr>

		<tr>
			<td>メールアドレス：警告文</td>
		</tr>

		<tr>
			<td>mail</td>
		</tr>


		<tr>
			<td>住所：</td>
		</tr>

		<tr>
			<td><textarea name="address"></textarea></td>
		</tr>

		<tr>
			<td>上記の内容で正しければ、送信ボタンを押してください。</td>
			<td><form action="<%=request.getContextPath()%>/inquiry">
					<input type="submit" name="cmd" value="送信する">
				</form>
				<form>
					<input type="submit" name="cmd" value="送信する">
				</form></td>
		</tr>

		<%
			if (false) {
		%>

		<%
			}
		%>

	</table>


	<%@ include file="/common/footer.jsp"%>

</body>
</html>