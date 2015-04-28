<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">
    <div class="Titelphotoview">
        <h1><spring:message code="pictures" text="%pictures" /> ${studentnaam} </h1>    
    </div>

    <c:forEach var="Photo" items="${Photo}">
        <div class="col-md-3 col-sm-6 col-xs-12">
            <div id="boxes" class="box">
                <a href="${baseurl}/photo/detail/${Photo.id}"><img border="1" src="${baseurl}/photo/view/low/${Photo.id}" height="auto" width="100%" /></a>
            </div>
        </div>
    </c:forEach>
</div> <!-- /container -->

<jsp:include page="/WEB-INF/jsp/footer.jsp" />
