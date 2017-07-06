/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.controller;

import com.tsguild.flooringmastery.dao.FlooringMasteryFilePersistenceException;
import com.tsguild.flooringmastery.model.Order;
import com.tsguild.flooringmastery.service.FlooringMasteryDoesNotExistException;
import com.tsguild.flooringmastery.dao.FlooringMasteryFileValidationException;
import com.tsguild.flooringmastery.model.Product;
import com.tsguild.flooringmastery.model.Tax;
import com.tsguild.flooringmastery.service.FlooringMasteryInvalidProductException;
import com.tsguild.flooringmastery.service.FlooringMasteryInvalidStateException;
import com.tsguild.flooringmastery.service.FlooringMasteryServiceLayer;
import com.tsguild.flooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryController {

    private final FlooringMasteryView view;
    private final FlooringMasteryServiceLayer service;

    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        try {
            String configurationType = service.getConfig();

            displayConfigBanner(configurationType);

            boolean isRunning = true;

            try {
                load();
            } catch (FlooringMasteryFilePersistenceException | FlooringMasteryFileValidationException e) {
                view.displayErrorMessage(e.getMessage());
            }
            while (isRunning) {
                int userSelection = menuSelection();

                switch (userSelection) {

                    case 1:
                        boolean isDisplayOkay = false;
                        int counterDisplay = 0;
                        while (!isDisplayOkay) {

                            try {
                                displayOrders();
                                isDisplayOkay = true;
                            } catch (FlooringMasteryDoesNotExistException e) {
                                view.displayErrorMessage(e.getMessage());
                                counterDisplay++;
                                if (counterDisplay > 2) {
                                    if (!view.confirmContinue()) {
                                        view.displayReturnToMainMenu();
                                        isDisplayOkay = true;

                                    }
                                }
                            }
                        }
                        //display orders for date
                        break;

                    case 2:
                        boolean isNewInfoOkay = false;
                        int counterNewInfo = 0;
                        while (!isNewInfoOkay) {

                            try {
                                getNewInfo();
                                isNewInfoOkay = true;
                            } catch (FlooringMasteryInvalidProductException | FlooringMasteryInvalidStateException e) {
                                view.displayErrorMessage(e.getMessage());
                                if (e.getMessage().contains("STATE")) {
                                    view.displayStates(service.getStates());
                                }
                                if (e.getMessage().contains("PRODUCT")) {
                                    view.displayProducts(service.getProducts());
                                }
                                counterNewInfo++;
                                if (counterNewInfo > 2) {
                                    if (!view.confirmContinue()) {
                                        view.displayReturnToMainMenu();
                                        isNewInfoOkay = true;
                                    }
                                }
                            }
                        }
                        //add an order
                        break;

                    case 3:
                        boolean isEditOkay = false;
                        int counterEdit = 0;
                        while (!isEditOkay) {

                            try {
                                editExistingOrder(configurationType);
                                isEditOkay = true;
                            } catch (FlooringMasteryDoesNotExistException e) {
                                view.displayErrorMessage(e.getMessage());
                                counterEdit++;
                                if (counterEdit > 2) {
                                    if (!view.confirmContinue()) {
                                        view.displayReturnToMainMenu();
                                        isEditOkay = true;
                                    }
                                }
                            }
                        }
                        //edit and order
                        break;

                    case 4:
                        boolean isDeleteOkay = false;
                        int counterDelete = 0;
                        while (!isDeleteOkay) {

                            try {
                                deleteAnOrder(configurationType);
                                isDeleteOkay = true;
                            } catch (FlooringMasteryDoesNotExistException e) {
                                view.displayErrorMessage(e.getMessage());
                                counterDelete++;
                                if (counterDelete > 2) {
                                    if (!view.confirmContinue()) {
                                        view.displayReturnToMainMenu();
                                        isDeleteOkay = true;
                                    }
                                }
                            }
                        }
                        //remove an order
                        break;

                    case 5:
                        try {
                            if (configurationType.contains("production")) {
                                saveProgressProduction();
                            } else if (configurationType.contains("training")) {
                                saveProgressTraining();
                            } else {
                                saveProgressTraining();
                            }
                        } catch (FlooringMasteryFilePersistenceException e) {
                            view.displayErrorMessage(e.getMessage());
                        }
                        //save current work
                        break;

                    case 6:

                        //exit the system
                        isRunning = false;
                        break;

                    default:
                        //unknowncommand error
                        unknownCommand();
                }

            }

            exitSystem(configurationType);
        } catch (FlooringMasteryFilePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    public int menuSelection() {
        return view.printMenuAndGetSelection();
    }

    public void exitSystem(String configurationType) throws FlooringMasteryFilePersistenceException {
        if (configurationType.contains("production")) {
            saveProgressProduction();
            view.displayExitSystemBanner();
        } else if (configurationType.contains("training")) {
            view.displayExitSystemBanner();
        } else {
            view.displayExitSystemBanner();
        }
    }

    public void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    public void load() throws FlooringMasteryFilePersistenceException, FlooringMasteryFileValidationException {
        service.load();
    }

    public void displayOrders() throws FlooringMasteryDoesNotExistException {
        LocalDate date = view.getDateToSearch();
        service.validateDateToSearch(date);
        List<Order> filteredOrders = service.getOrdersByDate(date);
        view.displayOrdersByDate(filteredOrders);
    }

    public void getNewInfo() throws FlooringMasteryFilePersistenceException, FlooringMasteryInvalidProductException, FlooringMasteryInvalidStateException {

        Order newOrder = view.getNewOrderInfo();
        if (service.validateNewOrderState(newOrder)) {

            if (service.validateNewOrderProduct(newOrder)) {

                Order toAdd = service.populateNewOrderInfo(newOrder);

                if (view.confirmNewOrderInfo(toAdd)) {

                    service.addNewOrder(toAdd);
                    view.displayAddSuccessBanner();

                }
            }

        }

    }

    public void editExistingOrder(String configurationType) throws FlooringMasteryDoesNotExistException {
        LocalDate dateToSearch = view.getDateToSearchForEdit();
        service.validateDateToSearch(dateToSearch);
        String orderIDToSearch = view.getOrderID();
        Order editedOrder = view.getEditOrderInfo(service.getOrderByID(orderIDToSearch));
        service.editExistingOrder(orderIDToSearch, editedOrder, configurationType);
        view.displayEditSuccessBanner();
    }

    public void deleteAnOrder(String configurationType) throws FlooringMasteryDoesNotExistException {

        LocalDate dateToSearch = view.getDateToSearchForRemoval();
        service.validateDateToSearch(dateToSearch);
        String orderIDToRemove = view.getOrderIDForRemoval();
        Order orderToRemove = service.getOrderByID(orderIDToRemove);
        view.displayOrderToRemoveInfo(orderToRemove);
        if (view.displayDeleteConfirmation()) {
            service.deleteOrder(orderToRemove, configurationType);
            view.displayDeleteSuccessBanner();
        }
    }

    public void saveProgressProduction() throws FlooringMasteryFilePersistenceException {
        view.displaySavingProgressBanner();
        service.saveCurrentProgress();
        view.displaySavingSuccessBanner();
    }

    public void saveProgressTraining() throws FlooringMasteryFilePersistenceException {
        view.displaySavingProgressBanner();
        view.displaySavingSuccessBanner();
        view.displayTrainingModeSaveBanner();
    }

    public void displayConfigBanner(String configurationType) {
        if (configurationType.contains("production")) {
            view.displayProductionModeBanner();
        } else if (configurationType.contains("training")) {
            view.displayTrainingModeBanner();
        } else {
            view.displayTrainingModeBanner();
        }
    }

}
