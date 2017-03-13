package com.hackingbuzz.shoppingcart11.model;

// cart...has got Product n the quantity of Product.... see amazon or flipkart of any ecommerce if you dont get it..



public class CartItem {
    private Product product;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
