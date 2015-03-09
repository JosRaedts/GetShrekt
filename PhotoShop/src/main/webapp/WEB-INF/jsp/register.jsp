<jsp:include page="header.jsp" />
<div class="container">

      <form class="form-signin">
        <h2 class="form-signin-heading">${admin}</h2>
        <p class="form-signin-heading">${photographer}</p>
        <label for="photographername" class="sr-only">Name:</label>
        <input type="text" id="inputName" class="form-control" placeholder="photographer name" required autofocus>
        <label for="Wachtwoord" class="sr-only">Password:</label>
        <input type="password" id="inputSchoolcode" class="form-control" placeholder="password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
      </form>
    </div> <!-- /container -->

<jsp:include page="footer.jsp" />
