<jsp:include page="header.jsp" />
<div class="container">
    <div class="page-header">
        <h1>Photoshop</h1>
    </div>  
    <p>Spring says: <span class="blue">${msg}</span><p>
    <p>${test}</p>
    <a href="${cp}/login">Log In</a>
    <h3>File Upload:</h3>
    Select a file to upload: <br />
    <form action="UploadServlet" method="post"
          enctype="multipart/form-data">
        <input type="file" name="file" size="50" />
        <br />
        <input type="submit" value="Upload File" />
    </form>
</div>
<jsp:include page="footer.jsp" />