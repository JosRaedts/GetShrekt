<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="header.jsp" />
<div class="container">

      <form class="form-signin">
        <h2 class="form-signin-heading"><spring:message code="photographerText" text="%photographerText" /></h2>
        <label for="photographerName" class="sr-only">Name:</label>
        <input type="text" id="inputName" class="form-control" placeholder="<spring:message code="photographerName" text="%photographerName" />" required autofocus>
        <label for="Wachtwoord" class="sr-only">Password:</label>
        <input type="password" id="inputSchoolcode" class="form-control" placeholder="<spring:message code="password" text="%password" />" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="createphotographer" text="%createphotographer" /></button>
        <h2 class="form-signin-heading"><spring:message code="schoolText" text="%schoolText" /></h2>
        <label for="schoolName" class="sr-only">Name:</label>
        <input type="text" id="inputName" class="form-control" placeholder="<spring:message code="schoolName" text="%schoolName" />" required autofocus>
        <label for="Wachtwoord" class="sr-only">Password:</label>
        <input type="password" id="inputSchoolcode" class="form-control" placeholder="<spring:message code="password" text="%password" />" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="createschool" text="%createschool" /></button>  
      </form>
    </div> <!-- /container -->

<jsp:include page="footer.jsp" />
