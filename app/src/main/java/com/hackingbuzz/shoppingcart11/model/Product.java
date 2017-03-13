package com.hackingbuzz.shoppingcart11.model;

import com.hackingbuzz.shoppingcart11.library.helper.Saleable;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Avi Hacker on 3/12/2017.
 */

public class Product implements Serializable , Saleable{    // serializable is an inteface that is required to implement if you are sending this object (Product) from one activity to another...in one acitivity u get it..n in other you sending it..when clicking on an item..


    private int pId;
    private String pName;
    private BigDecimal pPrice;



    //giving birth to our product...u can directly put Prouct appearince in above variable or use setter methods...sAME..

    public Product(int pId, String pName, BigDecimal pPrice, String pDescription, String pImageName) {
        setpId(pId);                 //calling set method n giving value to it..
        setpName(pName);
        setpPrice(pPrice);
        setpDescription(pDescription);
        setpImageName(pImageName);
    }






    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    // Saleable method implemented as the same name as getter...u think of em as same..
    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public BigDecimal getpPrice() {
        return pPrice;
    }

    public void setpPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getpImageName() {
        return pImageName;
    }

    public void setpImageName(String pImageName) {
        this.pImageName = pImageName;
    }

    private String pDescription;
    private String pImageName;

// if something is returning null...its also give you null pointer exception.
    @Override
    public BigDecimal getPrice() {
        return pPrice;
    }


}
