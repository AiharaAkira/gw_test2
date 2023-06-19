<%@page import="bean.Sale"%>
<%@page import="bean.User"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Book"%>
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
<html>
<head>
<title>書籍管理システム</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/view/css/list.css">
</head>
<body>
	<%@ include file="/common/header.jsp"%>


	<table id="l_menu_tbl">
		<tr>
			<td class="l_menu_link">[<a
				href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]
			</td>

			<%
				if (user.getAuthority().equals("1")) {
			%>
			<td class="l_menu_link">[<a
				href="<%=request.getContextPath()%>/showCart">カート状況</a>]
			</td>
			<%
				}
			%>


			<%
				if (user.getAuthority().equals("2")) {
			%>
			<td class="l_menu_link">[<a
				href="<%=request.getContextPath()%>/view/insert.jsp">書籍登録</a>]
			</td>
			<%
				}
			%>


			<td id="l_menu_title">書籍一覧</td>
			<td class="l_menu_link2">&nbsp;</td>
			<td class="l_menu_link2">&nbsp;</td>
		</tr>
	</table>

	<hr id="l_menu_hr">
	<div id="l_search_div">

		<table id="l_search_tbl">
			<tr>
				<td>
					<form action="<%=request.getContextPath()%>/search">
						isbn：<input type=text size="30" name="isbn"></input> title：<input
							type=text size="30" name="title"></input> 価格：<input type=text
							size="30" name="price"></input> <input type="submit"
							name="search" value="検"></input>
					</form>
				</td>
				<td>
					<form action="<%=request.getContextPath()%>/list">
						<input type="submit" name="searchall" value="全件表示"></input>
					</form>
				</td>

				<td>
					<form action="<%=request.getContextPath()%>/createPdf">
						<input type="submit" value="PDF表示"></input>
					</form>
				</td>

				<td>
					<form action="<%=request.getContextPath()%>/createExcel">
						<input type="submit" value="Excelダウンロード"></input>
					</form>
				</td>
			</tr>
		</table>

		<table id="l_td">
			<tr>
				<th class="l_th">イメージ</th>
				<th class="l_th">isbn</th>
				<th class="l_th">title</th>
				<th class="l_th">価格</th>

				<%
					if (user.getAuthority().equals("2")) {
				%>
				<th class="l_th" colspan="2"></th>
				<%
					}
				%>


				<%
					if (user.getAuthority().equals("1")) {
				%>

				<th class="l_th">購入数</th>
				<th class="l_th"></th>
				<%
					}
				%>




			</tr>
			<%
				ArrayList<Sale> list = (ArrayList<Sale>) request.getAttribute("book_list");
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						Sale books = (Sale) list.get(i);
			%>
			<tr>
				<td class="l_td1"><a
					href="<%=request.getContextPath()%>/detail"></a><img
					src="<%=request.getContextPath()%>/img/<%=books.getImg()%>"
					width="100px" alt="<%=request.getContextPath()%>/img/alt.jpg"></td>
				<td class="l_td1"><%=books.getIsbn()%></td>
				<td class="l_td1"><%=books.getTitle()%></td>
				<td class="l_td1"><%=books.getPrice()%>円</td>

				<%
					if (user.getAuthority().equals("2")) {
				%>
				<td id="l_td2"><a
					href="<%=request.getContextPath()%>/detail?isbn=<%=books.getIsbn()%>&cmd=update">変更</a>
				</td>
				<td id="l_td3"><a
					href="<%=request.getContextPath()%>/delete?isbn=<%=books.getIsbn()%>">削除</a>
				</td>
				<%
					}
				%>

				<%
					if (user.getAuthority().equals("1")) {
				%>
				<td id="l_td4"><%=books.getQuantity()%></td>
				<td id="l_td4"><a
					href="<%=request.getContextPath()%>/insertIntoCart?isbn=<%=books.getIsbn()%>">カートに入れる</a>
				</td>
				<%
					}
				%>




			</tr>
			<%
				}
				} else {
			%>
			<tr>
				<td class="l_td5">&nbsp;</td>
				<td class="l_td5">&nbsp;</td>
				<td class="l_td5">&nbsp;</td>
				<td class="l_td5">&nbsp;</td>
				<td class="l_td5" colspan="2">&nbsp;</td>
			</tr>
			<%
				}
			%>
		</table>

	</div>


	<%@ include file="/common/footer.jsp"%>
</body>
</html>