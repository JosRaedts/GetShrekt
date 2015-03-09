<jsp:include page="header.jsp" />
<div class="container">

      <form class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputName" class="sr-only">Name:</label>
        <input type="text" name="name" id="inputName" class="form-control" placeholder="Full name" required autofocus>
        <label for="inputSchoolcode" class="sr-only">Password:</label>
        <input type="password" name="schoolcode" id="inputSchoolcode" class="form-control" placeholder="School code" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

    </div> <!-- /container -->

<jsp:include page="footer.jsp" />