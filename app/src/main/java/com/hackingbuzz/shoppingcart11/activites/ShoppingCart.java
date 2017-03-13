package com.hackingbuzz.shoppingcart11.activites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hackingbuzz.shoppingcart11.R;
import com.hackingbuzz.shoppingcart11.adapters.CartItemAdapter;
import com.hackingbuzz.shoppingcart11.constant.Constant;
import com.hackingbuzz.shoppingcart11.library.helper.Cart;
import com.hackingbuzz.shoppingcart11.library.helper.Saleable;
import com.hackingbuzz.shoppingcart11.model.CartItem;
import com.hackingbuzz.shoppingcart11.model.Product;
import com.hackingbuzz.shoppingcart11.util.CartHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Avi Hacker on 3/12/2017.
 */

// we are required adapter to display list of item in the cart...custom adapter

public class ShoppingCart extends AppCompatActivity {
    private static final String TAG = "ShoppingCart";

    ListView lvCartItems;
    Button bClear;
    Button bShop;
    TextView tvTotalPrice;
    LayoutInflater layoutInflater;
    Cart cart;
    CartItemAdapter cartItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);

        initViews();
        initObjects();
        initListeners();

        // setting header to our list view ....
        lvCartItems.addHeaderView(layoutInflater.inflate(R.layout.cart_header, lvCartItems, false));

        // adapter for our list view..
        cartItemAdapter = new CartItemAdapter(this);
        cartItemAdapter.updateCartItems(getCartItems(cart)); // sending item to adapter so that it can set it

        lvCartItems.setAdapter(cartItemAdapter);


        // shwoing total price when we come to ShowCart Activity...
        tvTotalPrice.setText(Constant.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));



// clearing cart

        clearingCart();

// shop again..will take you to Products list page...again where you started

        shopMore();

        // delete item from cart
        deleteItem();

 // to see the details of a product by clicking on it...means go to Product detail activity..you also go here from ProductActivity.

        seeProductDetail();


    }

    private void seeProductDetail() {

        lvCartItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                List<CartItem> cartItems = getCartItems(cart);
                Product product = cartItems.get(position - 1).getProduct();
                Log.d(TAG, "Viewing product: " + product.getName());
                bundle.putSerializable("product", product);
                Intent intent = new Intent(ShoppingCart.this, ProductActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void deleteItem() {

        // long press on clicking ..if we press yes its gonna remove the item

        lvCartItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(ShoppingCart.this)
                        .setTitle(getResources().getString(R.string.delete_item))
                        .setMessage(getResources().getString(R.string.delete_item_message))
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                List<CartItem> cartItems = getCartItems(cart);
                                cart.remove(cartItems.get(position - 1).getProduct());
                                cartItems.remove(position - 1);
                                cartItemAdapter.updateCartItems(cartItems);
                                cartItemAdapter.notifyDataSetChanged();
                                tvTotalPrice.setText(Constant.CURRENCY + String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), null)
                        .show();
                return false;
            }
        });
    }


    private void shopMore() {

        bShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCart.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    private void clearingCart() {

        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clearing the shopping cart");
                cart.clear();
                cartItemAdapter.updateCartItems(getCartItems(cart));
                cartItemAdapter.notifyDataSetChanged();
                tvTotalPrice.setText(Constant.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
            }
        });

    }

    private void initListeners() {
    }

    private void initObjects() {

         layoutInflater = getLayoutInflater(); // for inflating header layout file for listView..

         cart = CartHelper.getCart();
    }

    private void initViews() {

        lvCartItems = (ListView) findViewById(R.id.lvCartItems);
         tvTotalPrice = (TextView) findViewById(R.id.tvTotalPrice);

        bClear = (Button) findViewById(R.id.bClear);
        bShop = (Button) findViewById(R.id.bShop);


    }

    private List<CartItem> getCartItems(Cart cart) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        Log.d(TAG, "Current shopping cart: " + cart);

        Map<Saleable, Integer> itemMap = cart.getItemWithQuantity();

        for (Map.Entry<Saleable, Integer> entry : itemMap.entrySet()) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct((Product) entry.getKey());
            cartItem.setQuantity(entry.getValue());
            cartItems.add(cartItem);
        }

        Log.d(TAG, "Cart item list: " + cartItems);
        return cartItems;
    }
}