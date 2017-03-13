package com.hackingbuzz.shoppingcart11.library.helper;

import java.math.BigDecimal;

/**
 * Implements this interface for any product which can be added to shopping cart
 */
public interface Saleable {
    // see here is the concept..we implemnt this interface to make sure...this name thing it must it must have Name and Price..otherwise it will give error
    // implementing this inteface in Proudct class....os

    BigDecimal getPrice();   // getter retun price..i just crerated a method with diff name inplemented in Prouct class that return product..i set his name accoding to getter..it just for you guys to know

    String getpName();    // getpName is our getter in our Proudct class..we created the intefrace with the same getter name...its a concept..we just making that getter must..nothing else..
}
