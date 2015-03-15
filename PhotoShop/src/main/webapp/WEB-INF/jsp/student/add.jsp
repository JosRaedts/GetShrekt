<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">

      <form class="form-signin">
        <h2 class="form-signin-heading">${admin}</h2>
        <p class="form-signin-heading">${adminlogin}</p>
        <label for="Adminname" class="sr-only">Name:</label>
        <input type="text" id="inputName" class="form-control" placeholder="<spring:message code="adminaccount" text="Admin Account" />" required autofocus>
        <label for="Wachtwoord" class="sr-only">Password:</label>
        <input type="password" id="inputSchoolcode" class="form-control" placeholder="<spring:message code="password" text="%password" />" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="register" text="%register" /></button>
      </form>
    </div> <!-- /container -->

<jsp:include page="/WEB-INF/jsp/footer.jsp" />
