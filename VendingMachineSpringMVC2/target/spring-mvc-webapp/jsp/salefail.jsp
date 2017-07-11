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
        <link href="css/home.css" rel="stylesheet"/>
    </head>
    <body>
        <div class="container" style="height: 100%;" id="wholeThing">
            <div class="jumbotron text-center">
                <h1>EZ-VEND-O-MATIC</h1>
            </div>
            <hr style="border-bottom: 1px solid black;"/>

            <div class="row-fluid">

                <div class="col-sm-9" id="contentWrapper">
                    <div id="vendingItemsDiv">
                        <c:forEach var="currentItem" items="${itemList}">
                            <div class="col-sm-4">

                                <button onclick="addItem(${currentItem.inventoryId})" class="btn btn-default" type="button" id="itemBox" style="border: 2px solid black; height: 150px; width: 100%;">
                                    <span class="itemstylez" name="selection" style="margin-right: 55%;"><c:out value="${currentItem.inventoryId}"/></span><br>
                                    <span class="itemstylez" style="font-weight: bold; font-size: 20px;"><c:out value="${currentItem.itemType}"/></span><br>
                                    <span class="itemstylez" style="margin-top: 10%;">$ <c:out value="${currentItem.cost}"/></span><br>
                                    <span class="itemstylez" style="margin-top: 10%;">Quantity remaining  <c:out value="${currentItem.itemQuantity}"/></span>
                                </button>

                            </div>
                        </c:forEach>
                    </div> 
                </div>
                <div class="col-sm-3 text-center" id="leftForm">
                    <form id="makePurchaseForm" action="makePurchase" method="POST" class="form-inline" role="form">
                        <p style="font-weight: bold; font-size: 25px;">Total $ In</p>
                        <input type="text" class="text-center form-control" name ="balanceIn" id="theOne" value="${bUserMoney}" style="border: 1px solid black; width: 50%; margin-left: 5%; margin-bottom: 15%;" placeholder="${bUserMoney}"/>
                        <button type="button" style="margin: 1%; width: 45%;" class="btn btn-info" onclick="addDollar()">Add Dollar</button>
                        <button type="button" style="margin: 1%; width: 45%;" class="btn btn-info" onclick="addQuarter()">Add Quarter</button>
                        <button type="button" style="margin: 1%; width: 45%;" class="btn btn-info" onclick="addDime()">Add Dime</button>
                        <button type="button" style="margin: 1%; width: 45%;" class="btn btn-info" onclick="addNickel()">Add Nickel</button>
                        <hr style="border-bottom: 1px solid black;"/>
                        <p style="font-weight: bold; font-size: 25px;">Messages</p>
                        <p class="text-center" id="compMessage" style="border: 1px solid black; width: 90%; height: 40px; margin-left: 5%;">
                            <c:out value="${error.message}"/>
                        </p>

                        <div class="form-group">
                            <label style="width: 10%; font-size: 15px; font-weight: bold; margin-right: 2%;" for="purchaseSelection" class="control-label">Item:</label>
                            <input name="selection"
                                   id="purchaseSelection"
                                   type="text"
                                   class="form-control"
                                   placeholder="${itemID}"
                                   value="${itemID}"
                                   style="width:75%; margin-left: 6%;"/>
                        </div>
                        <button type="submit"
                                class="btn btn-info"
                                id="makePurchaseButton"
                                style="margin-top: 5%; width: 90%;">
                            Make Purchase
                        </button>
                    </form>
                    <hr style="border-bottom: 1px solid black;"/>
                    <p style="font-weight: bold; font-size: 25px;">Change</p>
                    <p class="text-center" id="changeMessage" style="border: 1px solid black; width: 90%; height: 40px; margin-left: 5%;"></p>
                    <button type="button"
                            class="btn btn-info"
                            id="makeChangeButton"
                            style="margin-top: 5%; width: 90%;">
                        Change Return
                    </button>
                </div>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/vendingFail.js"></script>

    </body>
</html>
