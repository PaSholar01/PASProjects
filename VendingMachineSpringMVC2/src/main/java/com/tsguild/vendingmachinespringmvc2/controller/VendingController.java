package com.tsguild.vendingmachinespringmvc2.controller;

import com.tsguild.vendingmachinespringmvc2.dao.ErrorMessage;
import com.tsguild.vendingmachinespringmvc2.model.Change;
import com.tsguild.vendingmachinespringmvc2.model.Item;
import com.tsguild.vendingmachinespringmvc2.service.VendingMachineDoesNotExistException;
import com.tsguild.vendingmachinespringmvc2.service.VendingMachineInsufficientFundsException;
import com.tsguild.vendingmachinespringmvc2.service.VendingMachineNullInventoryException;
import com.tsguild.vendingmachinespringmvc2.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VendingController {

    private VendingMachineServiceLayer service;
  

    @Inject
    public VendingController(VendingMachineServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = {"/", "/displayNewSale"}, method = RequestMethod.GET)
    public String displayVendingHome(Model model) {

        List<Item> itemList = service.listTotalInventory();
        model.addAttribute("itemList", itemList);

        return "vendinghome";
    }

    @RequestMapping(value = "/makePurchase", method = RequestMethod.POST)
    public String makePurchase(HttpServletRequest request, Model model) {

        Change userChange = null;
        Item purchaseItem = null;
        boolean canBuy;
        double userMoney;
        long inventoryId;
        try {
            userMoney = Double.parseDouble(request.getParameter("balanceIn"));
        } catch (NumberFormatException e){
            ErrorMessage error = new ErrorMessage();
            error.setMessage("Value entered is not a number...");
            List<Item> itemList = service.listTotalInventory();
            model.addAttribute("error", error);
            model.addAttribute("itemList", itemList);
            return "salefail";
        }
        
        BigDecimal bUserMoney = new BigDecimal(userMoney).setScale(2, RoundingMode.HALF_UP);
        
        try{
            inventoryId = Long.parseLong(request.getParameter("selection"));
        } catch(NumberFormatException e) {
            ErrorMessage error = new ErrorMessage();
            error.setMessage("Item choice must be a valid Item Number");
            List<Item> itemList = service.listTotalInventory();
            model.addAttribute("error", error);
            model.addAttribute("itemList", itemList);
            model.addAttribute("bUserMoney", bUserMoney);
            return "salefail";
        }
        
        try {
            purchaseItem = service.getItemBySelection(inventoryId);
        } catch (VendingMachineNullInventoryException | VendingMachineDoesNotExistException e) {
            //add to error object that will be returned if errors exist
            //item is sold out exception!
            //or item does not exist exception!
            ErrorMessage error = new ErrorMessage();
            error.setMessage(e.getMessage());
            List<Item> itemList = service.listTotalInventory();
            model.addAttribute("error", error);
            model.addAttribute("itemList", itemList);
            model.addAttribute("bUserMoney", bUserMoney);
            return "salefail";
        }

        try {
            canBuy = service.validateCanBuy(userMoney, purchaseItem);
        } catch (VendingMachineInsufficientFundsException e) {
            //add to error object that will be returned if errors exist
            //insufficientfunds exception
            ErrorMessage error = new ErrorMessage();
            error.setMessage(e.getMessage());
            Long itemID = purchaseItem.getInventoryId();
            List<Item> itemList = service.listTotalInventory();
            model.addAttribute("error", error);
            model.addAttribute("itemList", itemList);
            model.addAttribute("bUserMoney", bUserMoney);
            model.addAttribute("itemID", itemID);
            return "salefail";
        }

        if (canBuy) {
            service.removeItem(inventoryId);
            userChange = service.makeSale(userMoney, purchaseItem);
        }
        
        List<Item> itemList = service.listTotalInventory();
        model.addAttribute("userChange", userChange);
        model.addAttribute("itemList", itemList);
        return "salesuccess";
    }

}
