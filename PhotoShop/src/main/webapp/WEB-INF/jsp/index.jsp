<jsp:include page="header.jsp" />
<div class="container">
    <div class="page-header">
        <h1 class="MainTitle">Welcome to Photoshop, the place to buy your schoolpictures</h1>
    </div>  
    <div>
        <p>If you had your pictures taken, you can order them here. Please log in with your full name and code to get started</p>
    </div>
    <a href="${baseurl}/login">Log In</a>
    <a href="${baseurl}/register">Register</a>
    <p>Choose:
      <a href="<c:url value=?lang=en'/>">English</a>
      | <a href="<c:url value='?lang=fr'/>">French</a>
      | <a href="<c:url value='?lang=de'/>">German</a>
  </p>
  <p>Greetings: <spring:message code="greetings" text="missing" /></p>
</div>
<jsp:include page="footer.jsp" />