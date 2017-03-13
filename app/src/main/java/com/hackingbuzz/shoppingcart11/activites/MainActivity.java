package com.hackingbuzz.shoppingcart11.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.hackingbuzz.shoppingcart11.R;

import com.hackingbuzz.shoppingcart11.adapters.ProductListAdapter;
import com.hackingbuzz.shoppingcart11.constant.Constant;
import com.hackingbuzz.shoppingcart11.model.Product;

public class MainActivity extends AppCompatActivity {

    TextView tvShoppingCart;
    ListView lvProducts;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
        initObjects();



        // styling textView
         stylingTextView();
        settingListenerToTextView();

        // adding header to our listView
       addingHeaderToListView();


// creating adapter... setting it to list view...n calling updateProudcts n giving our array list to it
        creatingAdapter();

        // setting listener to listView

        listenerToListView();


    }




    private void listenerToListView() {

        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {  //starts with position 1...but our array list starts with position 0 ..so we have to get the product from zero position..
                Product product = Constant.PRODUCT_LIST.get(position - 1);
                //   Log.d(TAG, "View product: " + position);
                //  Log.d(TAG, "View product: " + (position-1));
                Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", product);    // this will only work if Product class implents serilizable..   // taking product object from one acitivty to another through putSerializable method..
                //  Log.d(TAG, "View product: " + product.getpName());   // the product we got ..we getting the name of it..so that we get to know what product we got..
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void creatingAdapter() {

        ProductListAdapter productAdapter = new ProductListAdapter(this);
        productAdapter.updateProducts(Constant.PRODUCT_LIST);

        lvProducts.setAdapter(productAdapter);
    }

    private void addingHeaderToListView() {

        lvProducts.addHeaderView(getLayoutInflater().inflate(R.layout.product_list_header, lvProducts, false));

    }

    private void settingListenerToTextView() {


        tvShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ShoppingCart.class);
                startActivity(intent);

            }
        });
    }

    private void stylingTextView() {

        // to style our textView text.....we making it underline  for more styles search for spannable string
        SpannableString content = new SpannableString(getText(R.string.shopping_cart));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

        tvShoppingCart.setText(content);
    }

    private void initObjects() {
    }

    private void initListeners() {
    }

    private void initViews() {

       tvShoppingCart =  (TextView)findViewById(R.id.tvViewShoppingCart);

         lvProducts = (ListView) findViewById(R.id.lvProducts);
    }


}
