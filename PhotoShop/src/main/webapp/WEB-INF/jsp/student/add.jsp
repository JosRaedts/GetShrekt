<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">

      <form class="form-signin">
        <h2 class="form-signin-heading"><spring:message code="addstudent" text="%addstudent" /></h2>
        <label for="Adminname" class="sr-only">Name:</label>
        <input type="text" id="inputName" class="form-control" placeholder="<spring:message code="studentname" text="&studentname" />" required autofocus>
        <label for="Wachtwoord" class="sr-only">Password:</label>
        <input type="password" id="inputSchoolcode" class="form-control" placeholder="<spring:message code="password" text="%password" />" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="createstudent" text="%createstudent" /></button>
      </form>
    </div> <!-- /container -->

<jsp:include page="/WEB-INF/jsp/footer.jsp" />
