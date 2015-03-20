<%-- 
    Document   : home
    Created on : Mar 17, 2015, 10:14:29 AM
    Author     : Willem
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/admin/header.jsp" />
<div id="page-wrapper">
    <c:if test="${Type == 'admin'}">
        <form class="form-accountinfo" method="post" action="${baseurl}/photographer/modify/">
            <br>
            <br>
            <table border="0" style="">
                <tr>
                    <td width="200px"><spring:message code="accountTypeText" text="%accountTypeText" />:</td>
                    <td><spring:message code="photographer" text="%photographer" /></td>
                </tr>
                <tr>
                    <td width="200px"><spring:message code="name" text="%name" />:</td>
                    <td><input required type="textfield" name="name" value="${Name}" /></td>
                </tr>
                <tr>
                    <td width="200px"><spring:message code="username" text="%username" />:</td>
                    <td><input required type="textfield" name="username" value="${UserName}" /></td>
                </tr>
            </table> 
            <br>
            <spring:message code="changepassword" text="%changepassword" />
            <br>
            <table border="0" style="">
                <tr>
                    <td width="200px"><spring:message code="newPassword" text="%newPassword" />:</td>
                    <td><input type="password" name="newPassword" /></td>
                </tr>
                <tr>
                    <td width="200px"><spring:message code="confirmPassword" text="%confirmPassword" />:</td>
                    <td><input type="password" name="confirmPassword" /></td>
                </tr>
            </table>
        </c:if>
        <br>
        <button style="width: 100px" class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="save" text="%save" /></button>
    </form>
</div><!-- /#page-wrapper -->
</body>
</html>
