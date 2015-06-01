<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">
    <c:if test="${Type == 'student'}">
        <form class="form-accountinfo" method="post" action="${baseurl}/student/modify/">
            <br>
            <br>
            <table border="0" style="">
                <tr>
                    <td width="200px"><spring:message code="accountTypeText" text="%accountTypeText" />:</td>
                    <td><spring:message code="student" text="%student" /></td>
                </tr>
                <tr>
                    <td width="200px"><spring:message code="studentnumber" text="%studentnumber" />:</td>
                    <td>${Studentnumber}</td>
                </tr>
                <tr>
                    <td width="200px"><spring:message code="name" text="%name" />:</td>
                    <td>${Name}</td>
                </tr>
                <tr>
                    <td width="200px"><spring:message code="schoolcode" text="%schoolcode" />:</td>
                    <td style="margin-top-5px">${Schoolcode}</td>

                </tr>
                <tr>
                    <td width="200px"><spring:message code="username" text="%username" />:</td>
                    <td><input required type="textfield" name="username" value="${UserName}" /></td>
                </tr>
            </table>
            <table border="0" style="">
                <br>
                <tr>
                    <td width="200px"><spring:message code="city" text="%city" />:</td>
                    <td><input required type="textfield" name="city" value="${City}" /></td>
                </tr>
                <tr>
                    <td width="200px"><spring:message code="zipcode" text="%zipcode" />:</td>
                    <td width="200px"><input required type="textfield" name="zipcode" value="${Zipcode}" /></td>
                </tr>
                <tr>
                    <td width="200px"><spring:message code="address" text="%address" />:</td>
                    <td width="200px"><input required type="textfield" name="address" value="${Address}" /></td>
                </tr>
            </table>
            <br>
            <spring:message code="changepassword" text="%changepassword" />:
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
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />