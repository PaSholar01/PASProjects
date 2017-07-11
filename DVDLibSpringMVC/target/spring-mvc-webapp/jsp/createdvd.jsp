<%-- 
    Document   : dvds
    Created on : Jun 22, 2017, 10:33:06 AM
    Author     : apprentice
--%>

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
            <h1 class="jumbotron text-center">Create DVD</h1>
            <div class="row">
                <form class="form-horizontal" role="form" id="editDVDForm" action="createDVD" method="POST">
                    <div class="form-group">
                        <label for="dvdTitleCreate" class="col-sm-2 control-label">
                            Dvd Title:
                        </label>
                        <div class="col-sm-6">
                            <input type="text"
                                   class="form-control"
                                   name="dvdTitleCreate"
                                   placeholder="Title" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="releaseYearCreate" class="col-sm-2 control-label">
                            Release Year:
                        </label>
                        <div class="col-sm-6">
                            <input type="text"
                                   class="form-control"
                                   name="releaseYearCreate"
                                   placeholder="Date" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="director" class="col-sm-2 control-label">
                            Director:
                        </label>
                        <div class="col-sm-6">
                            <input type="text"
                                   class="form-control"
                                   name="directorCreate"
                                   placeholder="Director" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="rating" class="col-sm-2 control-label">
                            Rating:
                        </label>
                        <div class="col-sm-2">
                            <select class="form-control" name="ratingCreate">
                                <option value="PG">PG</option>
                                <option value="PG-13">PG-13</option>
                                <option value="R">R</option>
                                <option value="NR">NR</option>
                                <option value="NR-17">NR-17</option>
                            </select>
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
                                      name="extraNotesCreate"
                                      placeholder="Type text input here--Optional"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2 text-right">
                            <a href="displayDVDsTable">
                                <button 
                                    type="button"
                                    id="cancelButtonCreate"
                                    class="btn btn-default">
                                    Cancel
                                </button>
                            </a>
                        </div>
                        <div class="col-sm-3">
                            <button 
                                type="submit"
                                id="createDVDButton"
                                class="btn btn-default">
                                Save Changes 
                            </button>
                        </div>
                    </div>
                </form> 
            </div>
        </div>
    </body>
</html>
