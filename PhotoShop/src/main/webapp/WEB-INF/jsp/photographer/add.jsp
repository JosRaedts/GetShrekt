<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">

      <form class="form-signin" method="post" action="${baseurl}/photographer/add/addphotographer">
        <h2 class="form-signin-heading"><spring:message code="photographerText" text="%photographerText" /></h2>
        <label for="photographerName" class="sr-only">Name:</label>
        <input type="text" id="inputName" name="name" class="form-control" placeholder="<spring:message code="photographerName" text="%photographerName" />" required autofocus>
        <label for="Wachtwoord" class="sr-only">Password:</label>
        <input type="password" name="password" id="inputSchoolcode" class="form-control" placeholder="<spring:message code="password" text="%password" />" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="createphotographer" text="%createphotographer" /></button>
      </form>
    </div> <!-- /container -->

<jsp:include page="/WEB-INF/jsp/footer.jsp" />