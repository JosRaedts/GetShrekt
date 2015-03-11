<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="header.jsp" />
<div class="container">

      <form class="form-signin" method="post" action="${baseurl}/photographer/checkLogin/">
        <h2 class="form-signin-heading"><spring:message code="loginTextPhotographer" text="%loginTextPhotograher" /></h2>
        <label for="inputName" class="sr-only">Name:</label>
        <input type="text" name="name" id="inputName" class="form-control" placeholder="<spring:message code="name" text="%name" />" required autofocus>
        <label for="inputPassword" class="sr-only">Password:</label>
        <input type="password" name="password" id="inputSchoolcode" class="form-control" placeholder="<spring:message code="password" text="%password" />" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="login" text="%login" /></button>
      </form>

    </div> <!-- /container -->

<jsp:include page="footer.jsp" />