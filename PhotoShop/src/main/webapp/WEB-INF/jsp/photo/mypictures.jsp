<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">
    <div class="Titelphotoview">
        <h> Persoonlijke photo's van ${studentnaam} </h>    
    </div>

    <c:forEach var="Photo" items="${Photo}">
        <div class="col-md-3 col-sm-6 col-xs-12">
            <div class="box">
                <img border="1" src="${baseurl}/photo/view/low/${Photo.id}" height="auto" width="100%" />
            </div>
        </div>
    </c:forEach>
</div> <!-- /container -->

<jsp:include page="/WEB-INF/jsp/footer.jsp" />
