package com.hackingbuzz.shoppingcart11.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hackingbuzz.shoppingcart11.R;
import com.hackingbuzz.shoppingcart11.constant.Constant;

import com.hackingbuzz.shoppingcart11.library.helper.Cart;

import com.hackingbuzz.shoppingcart11.model.Product;
import com.hackingbuzz.shoppingcart11.util.CartHelper;

/**
 * Created by Avi Hacker on 3/13/2017.
 */

public class ProductActivity extends AppCompatActivity {

    private static final String TAG = "ProductActivity";

    TextView tvProductName;
    TextView tvProductDesc;
    ImageView ivProductImage;
    Spinner spQuantity;
    Button bOrder;

    Product product;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity);


        Bundle data = getIntent().getExtras();
        product = (Product) data.getSerializable("product");

        //Set Shopping Cart link  //the the TextView
        setShoppingCartLink();


        //Retrieve views
        retrieveViews();

        //Set product properties
        setProductProperties();

        //Initialize quantity   //setting spinner..
        initializeQuantity();

        //On ordering of product
        onOrderProduct();

    }


    private void setShoppingCartLink() {
        TextView tvViewShoppingCart = (TextView) findViewById(R.id.tvViewShoppingCart);
        SpannableString content = new SpannableString(getText(R.string.shopping_cart));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tvViewShoppingCart.setText(content);
        tvViewShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, ShoppingCart.class);
                startActivity(intent);
            }
        });
    }


    private void retrieveViews() {
        tvProductName = (TextView) findViewById(R.id.tvProductName);
        tvProductDesc = (TextView) findViewById(R.id.tvProductDesc);
        ivProductImage = (ImageView) findViewById(R.id.ivProductImage);
        spQuantity = (Spinner) findViewById(R.id.spQuantity);
        bOrder = (Button) findViewById(R.id.bOrder);
    }


    private void setProductProperties() {
        tvProductName.setText(product.getpName());
        tvProductDesc.setText(product.getpDescription());
        ivProductImage.setImageResource(this.getResources().getIdentifier(product.getpImageName(), "drawable", this.getPackageName()));
    }


    private void initializeQuantity() {

        // creating spinner..
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, Constant.QUANTITY_LIST);  // giving arraylist to our spinner adapter...
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQuantity.setAdapter(dataAdapter);
    }


// just for creating ShoppingCart..we just  library package ,  CartItem from model package...n implementhing Saleable in Product class..
    private void onOrderProduct() {
        bOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Cart cart = CartHelper.getCart();
               Log.d(TAG, "Adding product: " + product.getpName());
             // Log.i("SPINNER",""+spQuantity.getSelectedItem().toString());
                // what we just doing below it just putting product and its quantity together.. so that we could that in cart..
                cart.add(product, Integer.valueOf(spQuantity.getSelectedItem().toString()));   // product must implemet saleable..if wana use add method... // we PASSING PROUDCT AS A Saleable....
                Intent intent = new Intent(ProductActivity.this, ShoppingCart.class);
                startActivity(intent);
            }
        });
    }
}