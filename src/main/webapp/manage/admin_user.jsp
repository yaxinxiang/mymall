<%--
  Created by IntelliJ IDEA.
  User: Yaxin
  Date: 2021/7/15
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file = "admin_menu.jsp"%>
	<!--/sidebar-->
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list"><i class="icon-font"></i><a href="admin_index.jsp">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">用户管理</span></div>
		</div>
		<div class="search-wrap">
			<div class="search-content">
				<form action="${pageContext.request.contextPath}/manage/admin_douserselect" method="get">
					<table class="search-tab">
						<tr>
							<th width="70">关键字:</th>
							<td><input class="common-text" placeholder="关键字" name="kwd" value="${param.kwd}" id="" type="text"></td>
							<td><input class="btn btn-primary btn2"  type="submit"></td> <!--name="sub" value="查询"-->
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="result-wrap">
			<form name="myform" id="myform" method="post">
				<div class="result-title">
					<div class="result-list">
						<a href="admin_useradd.jsp"><i class="icon-font"></i>新增用户</a>
						<a id="batchDel" href="javascript:void(0)"><i class="icon-font"></i>批量删除</a>
						<a id="updateOrd" href="javascript:void(0)"><i class="icon-font"></i>更新排序</a>
					</div>
				</div>
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc" width="5%"><input class="allChoose" name="" type="checkbox"></th>
							<th>ID</th>
							<th>姓名</th>
							<th>性别</th>
							<th>email</th>
							<th>手机</th>
							<th>操作</th>
						</tr>

						<c:forEach var="user" items="${userlist}">
							<tr>
								<td class="tc"><input name="id[]" value="${user.user_id}" type="checkbox"></td>
								<td>${user.user_id}</td>
								<td>${user.user_name}</td>
								<td>${user.user_sex == 'T' ? '男' : '女'}</td>
								<td>${user.user_email}</td>
								<td>${user.user_tel}</td>
								<td>
									<a class="link-update" href="${pageContext.request.contextPath}/manage/admin_dousermodify?user_id=${user.user_id}&cpage=${cpage}">修改</a>
									<a class="link-del" href="#">删除</a>
								</td>
							</tr>
						</c:forEach>

					</table>
					<div class="list-page">
						共${tsum}条记录 当前${cpage}/${tpage}页
						<a href="?cp=1${searchParams}">首页</a>
						<a href="?cp=${cpage - 1 < 1 ? 1 : cpage - 1}${searchParams}">上一页</a>
						<a href="?cp=${cpage + 1 > tpage ? tpage : cpage + 1}${searchParams}">下一页</a>
						<a href="">尾页</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!--/main-->
</div>
</body>
</html>
