<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">

      <form class="form-signin" method="post" action="${baseurl}/student/login/checkLogin">
        <h2 class="form-signin-heading"><spring:message code="loginText" text="%loginText" /></h2>
        <p class="form-signin-heading text-danger"><spring:message code="loginstatus" text="%loginstatus" /></p>
        <label for="inputName" class="sr-only">Name:</label>
        <input type="text" name="name" id="inputName" class="form-control" placeholder="<spring:message code="name" text="%name" />" required autofocus>
        <label for="inputSchoolcode" class="sr-only">Password:</label>
        <input type="password" name="schoolcode" id="inputSchoolcode" class="form-control" placeholder="<spring:message code="schoolcode" text="%schoolcode" />" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="login" text="%login" /></button>
      </form>

    </div> <!-- /container -->

<jsp:include page="/WEB-INF/jsp/footer.jsp" />