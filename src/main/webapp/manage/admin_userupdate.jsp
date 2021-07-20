<%--
  Created by IntelliJ IDEA.
  User: Yaxin
  Date: 2021/7/20
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file = "admin_menu.jsp"%>
<!--/sidebar-->
<div class="main-wrap">

	<div class="crumb-wrap">
		<div class="crumb-list"><i class="icon-font"></i><a href="admin_index.jsp">首页</a>
			<span class="crumb-step">&gt;</span><a class="crumb-name" href="admin_douserselect">用户管理</a>
			<span class="crumb-step">&gt;</span><span>修改用户</span>
		</div>
	</div>
	<div class="result-wrap">
		<div class="result-content">
			<form action="${pageContext.request.contextPath}/manage/admin_douserupdate" method="post" id="myform" name="myform">
				<input hidden class="common-text required"  name="id" size="50" value="${user.user_id}" type="text">
				<input hidden name="cpage" value="${cpage}">
				<table class="insert-tab" width="100%">
					<tbody>
					<tr>
						<th><i class="require-red">*</i>用户名：</th>
						<td>
							<input class="common-text required"  name="username" size="50" value="${user.user_id}" type="text" disabled>
						</td>
					</tr>
					<tr>
						<th><i class="require-red">*</i>用户姓名：</th>
						<td>
							<input class="common-text required"  name="name" size="50" value="${user.user_name}" type="text">
						</td>
					</tr>
					<tr>
						<th><i class="require-red">*</i>登陆密码：</th>
						<td>
							<input class="common-text required"  name="password" size="50" value="${user.user_pwd}" type="text">
						</td>
					</tr>
					<tr>
						<th>性别：</th>
						<td>
							<input type="radio" name = "sex" value="T" ${user.user_sex == 'T' ? "checked" : ""}>男
							<input type="radio" name = "sex" value="F" ${user.user_sex == 'F' ? "checked" : ""}>女
						</td>
					</tr>
					<tr>
						<th>出生日期：</th>
						<td><input class="common-text" name="birthday" size="50" value="${user.user_birthday}" type="text"></td>
					</tr>
					<tr>
						<th><i class="require-red">*</i>电子邮箱：</th>
						<td>
							<input class="common-text required"  name="email" size="50" value="${user.user_email}" type="text">
						</td>
					</tr>
					<tr>
						<th><i class="require-red">*</i>手机号码：</th>
						<td>
							<input class="common-text required"  name="tel" size="50" value="${user.user_tel}" type="text">
						</td>
					</tr>
					<tr>
						<th><i class="require-red">*</i>送货地址：</th>
						<td>
							<input class="common-text required"  name="address" size="50" value="${user.user_address}" type="text">
						</td>
					</tr>

					<tr>
						<th></th>
						<td>
							<input class="btn btn-primary btn6 mr10" value="提交" type="submit">
							<input class="btn btn6" onClick="history.go(-1)" value="返回" type="button">
						</td>
					</tr>
					</tbody></table>
			</form>
		</div>
	</div>

</div>
<!--/main-->
</div>
</body>
</html>
