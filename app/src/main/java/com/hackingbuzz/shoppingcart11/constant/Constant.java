package com.hackingbuzz.shoppingcart11.constant;

import com.hackingbuzz.shoppingcart11.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avi Hacker on 3/12/2017.
 */

public class Constant {


    public static final Product PRODUCT1 = new Product(1, "Samsung Galaxy S6", BigDecimal.valueOf(199.996), "Worldly looks and top-notch specs make the impressive, metal Samsung Galaxy S6 the Android phone to beat for 2015", "samsung_galaxy_s6");
    public static final Product PRODUCT2 = new Product(2, "HTC One M8", BigDecimal.valueOf(449.9947), "Excellent overall phone. Beautifull, battery life more than 20 hours daily and great customization in any way. 100% configuration on any aspect", "htc_one_m8");
    public static final Product PRODUCT3 = new Product(3, "Xiaomi Mi3", BigDecimal.valueOf(319.998140), "Xiaomi's Mi 3 is a showcase of how Chinese phonemakers can create quality hardware without breaking the bank. If you don't need 4G LTE, and you can get hold of it, this is one of the best smartphones you can buy in its price range.", "xiaomi_mi3");


    // giving this list to updateProducts method through main
    public static final List<Product> PRODUCT_LIST = new ArrayList<Product>();


// our list is static so ..need static to initize static object..that its gonna hold ...n how our object is static...so now we can add objects to our static list...but it must be in static block...so that static list can execute its (add) method...
    static {
        PRODUCT_LIST.add(PRODUCT1);
        PRODUCT_LIST.add(PRODUCT2);
        PRODUCT_LIST.add(PRODUCT3);
    }

    public static final String CURRENCY = "$";


// creating array for spinner.... 11 items in the spinner
    public static final List<Integer> QUANTITY_LIST = new ArrayList<Integer>();

    static {
        for (int i = 1; i < 11; i++)
            QUANTITY_LIST.add(i);
    }

}

