<%-- 
    Document   : search
    Created on : Jun 22, 2017, 10:32:50 AM
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
        <title>Search Page</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Paul's DVD Library</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayDVDsTable">Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displaySearchPage">Search List</a></li>
                </ul>    
            </div>
            <div id="searchTable">
                <div class="row">
                    <ul class="list-group" id="errorMessages"></ul> <!--This is where error messages go!-->
                    <div class="col-lg-12">
                        <div id="dvdContentTableDiv">
                            <table id="contactTable" class="table">
                                <tr>
                                    <th class="text-center" width="25%">Title</th>
                                    <th class="text-center" width="25%">ReleaseDate</th>
                                    <th class="text-center" width="25%">Director</th>
                                    <th class="text-center" width="25%">Rating</th>
                                    <th width="20%"></th>
                                </tr>
                                <tbody id="contentRows" class="text-center">
                                    <c:forEach var="currentDVD" items="${dvdList}">
                                    <td>
                                        <c:out value="${currentDVD.dvdTitle}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentDVD.releaseDate}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentDVD.director}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentDVD.rating}"/>
                                    </td>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
