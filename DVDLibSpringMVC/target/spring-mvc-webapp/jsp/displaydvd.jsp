<%-- 
    Document   : displaydvd
    Created on : Jun 22, 2017, 3:34:37 PM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        

    </head>
    <body>
        <div class="container">
            <h1>Paul's DVD Library</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayDVDsTable">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySearchPage">Search List</a></li>
                </ul>    
            </div>
                <h1 class="jumbotron" id="dvdToDisplay" name="dvdToDisplay">
                    <c:out value="${dvd.dvdTitle}"/>
                </h1>
            <div class="row" id="contentDisplayDVD">
                <div class="col-sm-8"><h5 style="margin-left: 8%;"><strong>Release Year:   </strong>
                        <span id="displayReleaseVal"><c:out value="${dvd.releaseDate}"/></span></h5></div>
                <div class="col-sm-8"><h5 style="margin-left: 12%;"><strong>Director:   </strong>
                        <span id="displayDirectorVal"><c:out value="${dvd.director}"/></span></h5></div>
                <div class="col-sm-8"><h5 style="margin-left: 13.25%;"><strong>Rating:   </strong>
                        <span id="displayRatingVal"><c:out value="${dvd.rating}"/></span></h5></div>
                <div class="col-sm-8"><h5 style="margin-left: 13.75%;"><strong>Notes:   </strong>
                        <span id="displayNotesVal"><c:out value="${dvd.extraNotes}"/></span></h5></div>
            </div>
            <div class="col-sm-2">
                <a href="displayDVDsTable">
                    <button style="margin-left: 50%;"
                            type="button"
                            id="backToMainButton"
                            class="btn btn-default"
                            >
                        Back
                    </button>
                </a>
            </div>
        </div>
    </body>
</html>
