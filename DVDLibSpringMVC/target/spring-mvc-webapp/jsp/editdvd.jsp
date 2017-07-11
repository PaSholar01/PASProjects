<%-- 
    Document   : editdvd
    Created on : Jun 22, 2017, 3:33:43 PM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayDVDsTable">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySearchPage">Search List</a></li>
                </ul>    
            </div>
            <h1 class="jumbotron" >Edit DVD: ${dvd.dvdTitle}</h1>
            <div class="row">
                <sf:form class="form-horizontal" modelAttribute="dvd" role="form" action="editDVD" method="POST">
                    <div class="form-group">
                        <label for="dvdTitleEdit" class="col-sm-2 control-label">
                            Title:
                        </label>
                        <div class="col-sm-6">
                            <sf:input type="text"
                                      class="form-control"
                                      name="dvdTitleEdit"
                                      path="dvdTitle"
                                      placeholder="Title"/>
                            <sf:errors path="dvdTitle" cssClass="error"></sf:errors>

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="releaseYearEdit" class="col-sm-2 control-label">
                                Release Year:
                            </label>
                            <div class="col-sm-6">
                            <sf:input type="text"
                                      class="form-control"
                                      name="releaseYearEdit"
                                      path="releaseDate"
                                      placeholder="Date"/>
                            <sf:errors path="releaseDate" cssClass="error"></sf:errors>

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="directorEdit" class="col-sm-2 control-label">
                                Director:
                            </label>
                            <div class="col-sm-6">
                            <sf:input type="text"
                                      class="form-control"
                                      name="directorEdit"
                                      path="director"
                                      placeholder="Director"/>
                            <sf:errors path="director" cssClass="error"></sf:errors>

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ratingEdit" class="col-sm-2 control-label">
                                Rating:
                            </label>
                            <div class="col-sm-2">
                                <sf:select path="rating" class="form-control" name="ratingEdit">
                                <sf:option value="PG">PG</sf:option>
                                    <sf:option value="PG-13">PG-13</sf:option>
                                        <sf:option value="R">R</sf:option>
                                            <sf:option value="NR">NR</sf:option>
                                                <sf:option value="NR-17">NR-17</sf:option>
                                                    <sf:errors path="rating" cssClass="error"></sf:errors>

                                                    </sf:select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="extraNotes" class="col-sm-2 control-label">
                                                    Notes:
                                                </label>
                                                <div class="col-sm-6">
                                                    <textarea rows="4"
                                                              cols="50"
                                                              class="form-control"
                                                              name="extraNotesEdit"
                                                              placeholder="Type text input here--Optional">${dvd.extraNotes}</textarea>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-2 text-right">
                                                    <input type="hidden" name="edit-dvd-id">
                                                    <a href="displayDVDsTable">
                                                    <button
                                                        type="button"
                                                        id="cancelButtonEdit"
                                                        class="btn btn-default">
                                                        Cancel
                                                    </button>
                                                </a>
                                            </div>
                                            <div class="col-sm-3">
                                                <button type="submit"
                                                        id="saveEdit"
                                                        class="btn btn-default">
                                                    Save Changes 
                                                </button>
                                            </div>
                                        </div>
                                        </sf:form> 
                                    </div>
                                </div>
                            </body>
                        </html>
