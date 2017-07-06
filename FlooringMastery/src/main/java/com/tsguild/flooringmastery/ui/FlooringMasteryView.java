/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.ui;

import com.tsguild.flooringmastery.model.Order;
import com.tsguild.flooringmastery.model.Product;
import com.tsguild.flooringmastery.model.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryView {

    private UserIO io;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public boolean confirmContinue() {
        return io.readStringYesOrNo("Do you want to continue?\n"
                + "Enter yes or no...");
    }

    public int printMenuAndGetSelection() {
        io.print("==============================================================");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("==============================================================");
        io.print("============FLOORING-RESOURCE-MANAGEMENT-SYSTEMS==============");
        io.print("==========================SWC-CORP============================");
        io.print("==============================================================");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("==============================================================");
        return io.readInt("=================CHOOSE FROM THE BELOW OPTIONS================\n"
                + "|| 1. DISPLAY ORDERS BY DATE\n"
                + "|| 2. ADD AN ORDER\n"
                + "|| 3. EDIT AN ORDER\n"
                + "|| 4. REMOVE AN ORDER\n"
                + "|| 5. SAVE CURRENT WORK\n"
                + "|| 6. EXIT THE SYSTEM\n"
                + "______________________________________________________________");
    }
    //DISPLAY ORDERS METHODS-------------------------------------------------------------------------------------------------------
    //*******************************************************************************************************************************
    //*******************************************************************************************************************************

    public LocalDate getDateToSearch() {
        return io.readLocalDate("Please enter a date to search by.\n"
                + "The date must be in the following format.\n"
                + "              YYYY-MM-DD");
    }

    public void displayOrdersByDate(List<Order> filteredOrders) {
        // String dateFile = filteredOrders.get(0).getOrderDate();
        io.print("==============================================================");
        io.print("===============THE FOLLOWING ORDERS WERE FOUND================");
        io.print("===============TOTAL MATCHES FOUND BY QUERY: " + filteredOrders.size() + "==========");
        for (Order currentOrder : filteredOrders) {
            io.print("|| ID: " + currentOrder.getOrderID() + " || Name: " + currentOrder.getCustomerName()
                    + " || State: " + currentOrder.getState() + " || TaxRate: " + currentOrder.getTaxRate()
                    + " || Product: " + currentOrder.getProductName() + " ||" + " || MatCostSqFt: " + currentOrder.getMatCostSqFt()
                    + " || LaborCostSqFt: " + currentOrder.getLaborCostSqFt() + " || MatCost: " + currentOrder.getMatCost()
                    + " || LaborCost: " + currentOrder.getLaborCost() + " || Area: " + currentOrder.getArea()
                    + " || OrderCost: " + currentOrder.getTotalOrderCost() + " || OrderTax: " + currentOrder.getOrderTax()
                    + " || OrderDate: " + currentOrder.getOrderDate());
        }
    }

    public void displayProducts(List<Product> productList) {
        io.print("==============================================================");
        io.print("===================PRODUCT INVENTORY LIST=====================");
        for (Product currentProduct : productList) {
            io.print("|| " + currentProduct.getProductName() + "\n");
        }
        io.print("===================PRODUCT INVENTORY LIST=====================");
        io.print("==============================================================");

    }

    public void displayStates(List<Tax> taxList) {
        int counter = 0;
        io.print("==============================================================");
        io.print("=================== ACCEPTED STATES LIST =====================");
        for (Tax currentTax : taxList) {
            io.printSameLn(" || " + currentTax.getState());
            counter++;
            if (counter == 10) {
                counter = 0;
                io.print("\n");
            }
        }
        io.print("=================== ACCEPTED STATES LIST =====================");
        io.print("==============================================================");
    }

    //END DISPLAY ORDERS METHODS-----------------------------------------------------------------------------------------------------
    //*******************************************************************************************************************************
    //*******************************************************************************************************************************
    //*******************************************************************************************************************************
    //ADD AN ORDER METHODS-----------------------------------------------------------------------------------------------------------
    //*******************************************************************************************************************************
    //*******************************************************************************************************************************
    public Order getNewOrderInfo() {

        Order newOrder = new Order();

        newOrder.setCustomerName(io.readString("Please enter a customer name."));
        newOrder.setState(io.readString("Please enter a state of residence."));
        newOrder.setProductName(io.readString("Please enter the desired product type."));
        newOrder.setArea(io.readDoubleReturnBigD("Please enter the desired area of coverage."));
        if (io.readStringYesOrNo("Would you like to enter an order date? If no, we will auto-generate one for you.")) {
            LocalDate ld = io.readLocalDate("Please enter a date to append the order to.\n"
                    + "The date must be in the following format.\n"
                    + "              YYYY-MM-DD");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
            String rawDate = ld.format(formatter);
            newOrder.setOrderDate(rawDate);
        }

        return newOrder;
    }

    public boolean confirmNewOrderInfo(Order newOrder) {
        io.print("==============================================================");
        io.print("=================== ORDER TO BE ADDED ========================");
        io.print("==============================================================");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("==============================================================");
        io.print("Order ID: " + newOrder.getOrderID() + "\n");
        io.print("Order Date: " + newOrder.getOrderDate().substring(24, 26)
                + "-" + newOrder.getOrderDate().substring(26, 28)
                + "-" + newOrder.getOrderDate().substring(28, 32)
                + "\n");
        io.print("Customer Name: " + newOrder.getCustomerName() + "\n");
        io.print("State: " + newOrder.getState() + "\n");
        io.print("State Tax Rate: %" + newOrder.getTaxRate() + "\n");
        io.print("Product Type: " + newOrder.getProductName() + "\n");
        io.print("Product Material Cost per Square Foot: $" + newOrder.getMatCostSqFt() + "\n");
        io.print("Product Labor Cost per Square Foot: $" + newOrder.getLaborCostSqFt() + "\n");
        io.print("Order Material Cost: $" + newOrder.getMatCost() + "\n");
        io.print("Order Labor Cost: $" + newOrder.getLaborCost() + "\n");
        io.print("Order Total Tax: $" + newOrder.getOrderTax() + "\n");
        io.print("Order Total Cost: $" + newOrder.getTotalOrderCost() + "\n");
        io.print("==============================================================");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("==============================================================");
        return io.readStringYesOrNo("Is this information correct? Please enter yes or no.");
    }

    //END ADD AN ORDER METHODS------------------------------------------------------------------------------------------------------
    //*******************************************************************************************************************************
    //*******************************************************************************************************************************
    //EDIT AN ORDER METHODS-----------------------------------------------------------------------------------------------------------
    //*******************************************************************************************************************************
    //*******************************************************************************************************************************
    public LocalDate getDateToSearchForEdit() {
        return io.readLocalDate("Please enter a date to search by.\n"
                + "The date must be in the following format.\n"
                + "              YYYY-MM-DD");
    }

    public String getOrderID() {
        return io.readString("Please enter the order number of the order you wish to edit.");
    }

    //GET NEW NAME OR KEEP OLD NAME
    public String getNewNameOrKeepOld(Order editedOrder) {

        String newName = io.readString("Enter customer name (" + editedOrder.getCustomerName() + "): ");
        if (newName.equals("")) {
            newName = editedOrder.getCustomerName();
        }
        return newName;
    }

    //GET NEW STATE OR KEEP OLD STATE
    public String getNewStateOrKeepOld(Order editedOrder) {

        String newState = io.readString("Enter customer state (" + editedOrder.getState() + "): ");
        if (newState.equals("")) {
            newState = editedOrder.getState();
        }
        return newState;
    }

    //GET NEW PRODUCT NAME OR KEEP OLD PRODUCT
    public String getNewProductNameOrKeepOld(Order editedOrder) {

        String newProductName = io.readString("Enter product type (" + editedOrder.getProductName() + "): ");
        if (newProductName.equals("")) {
            newProductName = editedOrder.getProductName();
        }
        return newProductName;
    }

    //GET NEW DATE OR KEEP OLD DATE
    public String getNewDateOrKeepOld(Order editedOrder) {

        String newOrderDate = "";
        boolean canParse = false;
        while (!canParse) {

            newOrderDate = io.readString("Enter a order date (" + editedOrder.getOrderDate().substring(24, 32) + "): (YYYY-MM-DD): ");
            if (newOrderDate.equals("")) {

                String stringSubYear = editedOrder.getOrderDate().substring(28, 32);
                String stringSubMonth = editedOrder.getOrderDate().substring(24, 26);
                String stringSubDay = editedOrder.getOrderDate().substring(26, 28);

                newOrderDate = stringSubYear + "-" + stringSubMonth + "-" + stringSubDay;
                canParse = true;

            } else {
                try {
                    LocalDate ld = LocalDate.parse(newOrderDate);
                    canParse = true;
                } catch (DateTimeParseException e) {
                    canParse = false;
                    System.out.println("Please enter a correct Date Value.\n"
                            + "The format is YYYY-MM-DD.");
                }
            }
        }

        return newOrderDate;
    }

    //GET NEW TAXRATE OR KEEP OLD RATE
    public BigDecimal getNewTaxRateOrKeepOld(Order editedOrder) {

        BigDecimal newTaxRate = readStringGetTheBigD(editedOrder, "Enter the new tax rate (" + editedOrder.getTaxRate() + "): ");

        return newTaxRate;
    }

    //GET NEW AREA OF KEEP OLD AREA
    public BigDecimal getNewAreaOrKeepOld(Order editedOrder) {

        BigDecimal newArea = readStringGetTheBigD(editedOrder, "Enter the new area (" + editedOrder.getArea() + "): ");

        return newArea;
    }

    //GET NEW MATERIAL COST PER SQUARE FOOT OR KEEP OLD
    public BigDecimal getNewMatCostSqFtOrKeepOld(Order editedOrder) {

        BigDecimal newMatCostSqFt = readStringGetTheBigD(editedOrder, "Enter the new material cost per square foot (" + editedOrder.getMatCostSqFt() + "): ");

        return newMatCostSqFt;
    }

    //GET NEW LABOR COST PER SQUARE FOOT OR KEEP OLD
    public BigDecimal getNewLaborCostSqFtOrKeepOld(Order editedOrder) {

        BigDecimal newLaborCostSqFt = readStringGetTheBigD(editedOrder, "Enter the new labor cost per square foot (" + editedOrder.getLaborCostSqFt() + "): ");

        return newLaborCostSqFt;
    }

    //GET NEW MATERIAL COST OR KEEP OLD
    public BigDecimal getNewMatCostOrKeepOld(Order editedOrder) {

        BigDecimal newMatCost = readStringGetTheBigD(editedOrder, "Enter the new Material Cost (" + editedOrder.getLaborCost() + "): ");

        return newMatCost;
    }

    //GET NEW LABOR COST OR KEEP OLD
    public BigDecimal getNewLaborCostOrKeepOld(Order editedOrder) {

        BigDecimal newLaborCost = readStringGetTheBigD(editedOrder, "Enter the new Labor Cost (" + editedOrder.getLaborCost() + "): ");

        return newLaborCost;
    }

    //GET NEW TAX TOTAL OR KEEP OLD
    public BigDecimal getNewTaxTotalOrKeepOld(Order editedOrder) {

        BigDecimal newTaxTotal = readStringGetTheBigD(editedOrder, "Enter the new Total Tax (" + editedOrder.getOrderTax() + "): ");

        return newTaxTotal;
    }

    //GET NEW ORDER TOTAL COST OR KEEP OLD
    public BigDecimal getNewOrderTotalCostOrKeepOld(Order editedOrder) {

        BigDecimal newOrderTotalCost = readStringGetTheBigD(editedOrder, "Enter the new Total Cost (" + editedOrder.getTotalOrderCost() + "): ");

        return newOrderTotalCost;
    }

    public Order getEditOrderInfo(Order editedOrder) {
        Order editedNewOrder = new Order();

        editedNewOrder.setOrderID(editedOrder.getOrderID());
        editedNewOrder.setOrderDate(getNewDateOrKeepOld(editedOrder));
        editedNewOrder.setCustomerName(getNewNameOrKeepOld(editedOrder));
        editedNewOrder.setState(getNewStateOrKeepOld(editedOrder));
        editedNewOrder.setTaxRate(getNewTaxRateOrKeepOld(editedOrder));
        editedNewOrder.setProductName(getNewProductNameOrKeepOld(editedOrder));
        editedNewOrder.setMatCostSqFt(getNewMatCostSqFtOrKeepOld(editedOrder));
        editedNewOrder.setLaborCostSqFt(getNewLaborCostSqFtOrKeepOld(editedOrder));
        editedNewOrder.setArea(getNewAreaOrKeepOld(editedOrder));
        editedNewOrder.setMatCost(getNewMatCostOrKeepOld(editedOrder));
        editedNewOrder.setLaborCost(getNewLaborCostOrKeepOld(editedOrder));
        editedNewOrder.setOrderTax(getNewTaxTotalOrKeepOld(editedOrder));
        editedNewOrder.setTotalOrderCost(getNewOrderTotalCostOrKeepOld(editedOrder));

        return editedNewOrder;

    }

    public BigDecimal readStringGetTheBigD(Order editedOrder, String prompt) {

        BigDecimal toReturn = null;
        boolean canParse = false;

        while (!canParse) {

            String stringNewBigD = io.readString(prompt);
            if (stringNewBigD.equals("")) {
                toReturn = editedOrder.getTaxRate();
                canParse = true;
            } else {
                try {
                    double inputDouble = Double.parseDouble(stringNewBigD);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a correct numerical value.");
                }
            }

            while (!canParse) {
                double inputDouble = Double.parseDouble(stringNewBigD);
                try {
                    toReturn = new BigDecimal(inputDouble);
                    canParse = true;
                } catch (NumberFormatException e) {
                    canParse = false;
                    System.out.println("Please enter a correct numerical value.");
                }
            }
        }
        return toReturn;
    }

//END EDIT AN ORDER METHODS------------------------------------------------------------------------------------------------------
//*******************************************************************************************************************************
//*******************************************************************************************************************************
//REMOVE AN ORDER METHODS-------------------------------------------------------------------------------------------------------
//==============================================================================================================================
//==============================================================================================================================
    public LocalDate getDateToSearchForRemoval() {
        return io.readLocalDate("Please enter a date to search by.\n"
                + "The date must be in the following format.\n"
                + "              YYYY-MM-DD");
    }

    public String getOrderIDForRemoval() {
        return io.readString("Please enter the order number of the order you wish to edit.");
    }

    public void displayOrderToRemoveInfo(Order orderToRemove) {

        io.print("==============================================================");
        io.print("===================Order To Be Removed========================");
        io.print("==============================================================");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("==============================================================");
        io.print("Order ID: " + orderToRemove.getOrderID() + "\n");
        io.print("Customer Name: " + orderToRemove.getCustomerName() + "\n");
        io.print("State: " + orderToRemove.getState() + "\n");
        io.print("State Tax Rate: %" + orderToRemove.getTaxRate() + "\n");
        io.print("Product Type: " + orderToRemove.getProductName() + "\n");
        io.print("Product Material Cost per Square Foot: $" + orderToRemove.getMatCostSqFt() + "\n");
        io.print("Product Labor Cost per Square Foot: $" + orderToRemove.getLaborCostSqFt() + "\n");
        io.print("Order Material Cost: $" + orderToRemove.getMatCost() + "\n");
        io.print("Order Labor Cost: $" + orderToRemove.getLaborCost() + "\n");
        io.print("Order Total Tax: $" + orderToRemove.getOrderTax() + "\n");
        io.print("Order Total Cost: $" + orderToRemove.getTotalOrderCost() + "\n");
        io.print("==============================================================");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("==============================================================");

    }

    public boolean displayDeleteConfirmation() {
        io.print("==============================================================");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("==============================================================");
        return io.readStringYesOrNo("Please confirm permanent deletion of this information.\n"
                + "There are no take backsies....Enter yes or no.");
    }

    //END REMOVE AN ORDER METHODS----------------------------------------------------------------------------------------------------
    //*******************************************************************************************************************************
    //*******************************************************************************************************************************
    //BANNER METHODS-----------------------------------------------------------------------------------------------------------------
    public void displayExitSystemBanner() {
        io.print("==============================================================");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("==============================================================");
        io.print("============FLOORING-RESOURCE-MANAGEMENT-SYSTEMS==============");
        io.print("==========================SWC-CORP============================");
        io.print("==============================================================");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("==============================================================");
        io.print("=======================SO LONG FOR NOW========================");
    }

    public void displayUnknownCommandBanner() {
        io.print("==============================================================");
        io.print("**************************************************************");
        io.print("==============================================================");
        io.readString("=Sorry, the command was unknown. Please hit enter to continue=");
    }

    public void displaySavingProgressBanner() {

        io.print("==============================================================");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("==============================================================");
        io.print("==============================================================");
        io.print("=====================SAVING PROGRESS NOW======================");
    }

    public void displaySavingSuccessBanner() {

        io.print("==============================================================");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("==============================================================");
        io.print("====================SAVING WAS SUCCESSFUL=====================");
        io.readString("=================PLEASE HIT ENTER TO CONTINUE=================");
    }

    public void displayProductionModeBanner() {

        io.print("==============================================================");
        io.print("  * * * * * * * * * * PRODUCTION-MODE * * * * * * * * * * * * ");
        io.print("==============================================================");
    }

    public void displayTrainingModeBanner() {

        io.print("==============================================================");
        io.print("  * * * * * * * * * * TRAINING-MODE * * * * * * * * * * * * * ");
        io.print("==============================================================");
    }

    public void displayTrainingModeSaveBanner() {

        io.print("==============================================================");
        io.print("  * * * WE ARE TRAINING SO DON'T WORRY,  NOTHING SAVED! * * * ");
        io.print("==============================================================");
    }

    public void displayErrorMessage(String errorMessage) {

        io.print("==============================================================");
        io.print("  * * * * * * * * * * ERROR OCCURED * * * * * * * * * * * * * ");
        io.print("==============================================================");
        io.print(errorMessage);
    }

    public void displayReturnToMainMenu() {

        io.print("==============================================================");
        io.print(" * * * * * * * * RETURNING TO MAIN MENU * * * * * * * * * * * ");
        io.print("==============================================================");
    }

    public void displayEditSuccessBanner() {

        io.print("==============================================================");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("==============================================================");
        io.print("==================== EDIT WAS SUCCESSFUL =====================");
        io.readString("=================PLEASE HIT ENTER TO CONTINUE=================");
    }

    public void displayAddSuccessBanner() {

        io.print("==============================================================");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("==============================================================");
        io.print("====================ADDING WAS SUCCESSFUL=====================");
        io.readString("=================PLEASE HIT ENTER TO CONTINUE=================");
    }

    public void displayDeleteSuccessBanner() {

        io.print("==============================================================");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("==============================================================");
        io.print("====================DELETE WAS SUCCESSFUL=====================");
        io.readString("=================PLEASE HIT ENTER TO CONTINUE=================");
    }

}
