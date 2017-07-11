<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Paul's DVD Library</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayDVDsTable">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySearchPage">Search List</a></li>
                </ul>    
            </div>
            <h2>Home Page</h2>
            <div class="row">
                <div class="col-lg-12">
                    <form class="form-inline" role="form" id="dvd-create-form">

                        <div class="form-group col-sm-4">
                            <a href="displayCreateDVD">
                                <button
                                    type="button"
                                    id="create-button"
                                    class="btn btn-default">
                                    Create DVD
                                </button>
                            </a>
                        </div>
                    </form>
                    <form class="form-inline" role="form" id="dvd-search-form" action="searchDVDs" method="POST">
                        <div class="form-group col-sm-2">
                            <button
                                type="submit"
                                id="search-button"
                                class="btn btn-default">
                                Search DVD
                            </button>
                        </div>
                        <div class="form-group col-sm-3">
                            <select class="form-control" name="search-category" placeholder="Search Category" required>
                                <option value="" disabled selected>Search Category</option>
                                <option value="title">Title</option>
                                <option value="release">Release Year</option>
                                <option value="director">Director Name</option>
                                <option value="rating">Rating</option>
                            </select>
                        </div>
                        <div class="form-group col-sm-3">
                            <input type="text"
                                   class="form-control"
                                   name="search-bar"
                                   placeholder="Search Term" required/>
                        </div>
                    </form>
                </div>
            </div>

            <div class="row">
                <ul class="list-group" id="errorMessages"></ul> <!--This is where error messages go!-->
                <div class="col-lg-12">
                    <div id="dvdContentTableDiv">
                        <table id="contactTable" class="table">
                            <tr>
                                <th class="text-center" width="25%">Title</th>
                                <th class="text-center" width="25%">ReleaseDate</th>
                                <th class="text-center" width="25%"></th>
                                <th class="text-center" width="25%"></th>
                                <th width="20%"></th>
                            </tr>
                            <tbody id="contentRows" class="text-center">
                                <c:forEach var="currentDVD" items="${dvdList}">
                                    <tr>
                                        <td>
                                            <a href="displayDVDDetails?dvdId=${currentDVD.dvdId}">
                                                <c:out value="${currentDVD.dvdTitle}"/>
                                            </a>
                                        </td>
                                        <td>
                                            <c:out value="${currentDVD.releaseDate}"/>
                                        </td>
                                        <td>
                                            <a href="displayEditDVD?dvdId=${currentDVD.dvdId}">
                                                Edit
                                            </a>
                                        </td>
                                        <td>
                                            <a href="deleteDVD?dvdId=${currentDVD.dvdId}">
                                                Delete
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>


        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

