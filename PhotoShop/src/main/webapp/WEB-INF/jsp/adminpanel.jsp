<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="header.jsp" />
<div class="container">

      <form class="form-signin">
        <h2 class="form-signin-heading">${adminpanel}</h2>
        <h2 class="form-signin-heading">${Registreerfotograaf}</h2>
        <label for="photographername" class="sr-only">Name:</label>
        <input type="text" id="inputName" class="form-control" placeholder="<spring:message code="photographerName" text="%photographerName" />" required autofocus>
        <label for="Wachtwoord" class="sr-only">Password:</label>
        <input type="password" id="inputSchoolcode" class="form-control" placeholder="<spring:message code="password" text="%password" />" required>
        <h2 class="form-signin-heading">${Registeerschool}</h2>
        <label for="photographername" class="sr-only">Name:</label>
        <input type="text" id="inputName" class="form-control" placeholder="<spring:message code="schoolname" text="%schoolname" />" required autofocus>
        <label for="Wachtwoord" class="sr-only">Password:</label>
        <input type="password" id="inputSchoolcode" class="form-control" placeholder="<spring:message code="password" text="%password" />" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="adminpanel" text="%adminpanel" /></button>
        
      </form>
    </div> <!-- /container -->

<jsp:include page="footer.jsp" />
