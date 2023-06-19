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

	<form action="<%=request.getContextPath()%>/inquiry">

		<div>無料体験用メールアドレス[xxxx@xxxx]</div>

		<table>

			<tr>
				<td>お名前：警告文</td>
				<td>お問い合わせタイトル：警告文</td>
			</tr>

			<tr>
				<td><input name="name"></td>
				<td><input type="radio" name="title" value="書籍について"><input
					type="radio" name="title" value="購入について"><input
					type="radio" name="title" value="その他"></td>
			</tr>

			<tr>
				<td>電話番号：警告文</td>
				<td>お問い合わせ内容：警告文</td>
			</tr>

			<tr>
				<td><input name="tel"></td>
				<td rowspan="7"><textarea name="content"></textarea></td>
			</tr>

			<tr>
				<td>メールアドレス：警告文</td>
			</tr>

			<tr>
				<td><input name="mail"></td>
			</tr>

			<tr>
				<td>メールアドレス(確認用)：警告文</td>
			</tr>

			<tr>
				<td><input name="mailCheck"></td>
			</tr>

			<tr>
				<td>住所：</td>
			</tr>

			<tr>
				<td><textarea name="address"></textarea></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="確認"></td>
			</tr>

		</table>

	</form>

	<%@ include file="/common/footer.jsp"%>

</body>
</html>