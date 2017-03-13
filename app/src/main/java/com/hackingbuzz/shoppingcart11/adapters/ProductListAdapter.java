package com.hackingbuzz.shoppingcart11.adapters;

/**
 * Created by Avi Hacker on 3/12/2017.
 */


        import java.math.BigDecimal;
        import java.util.ArrayList;
        import java.util.List;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.hackingbuzz.shoppingcart11.R;
        import com.hackingbuzz.shoppingcart11.constant.Constant;
        import com.hackingbuzz.shoppingcart11.model.Product;


public class ProductListAdapter extends BaseAdapter {
    private static final String TAG = "ProductAdapter";

    // created a list view and adding all products to it..through updateProducts method..
    private List<Product> products = new ArrayList<Product>();

    private final Context context;

    public ProductListAdapter(Context context) {
        this.context = context;
    }

    public void updateProducts(List<Product> products) {  // getting all products...getting whole arraylist created in constant..
        this.products.addAll(products);
        notifyDataSetChanged();  // telling adapter that change list with all the products we got
    }

    @Override
    public int getCount() {
        return products.size();  // listview shize its acount to our proudcts...
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView tvName;
        TextView tvPrice;
        ImageView ivImage;

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.product_list, parent, false);

            tvName = (TextView) convertView.findViewById(R.id.tvProductName);  // dont think much ..tvName ,tvPrice n ivImage...these three views we initlized ..n gonna use them ..as we normally do..
            tvPrice = (TextView) convertView.findViewById(R.id.tvProductPrice);
            ivImage = (ImageView) convertView.findViewById(R.id.ivProductImage);  // initilizing view n giving hold to ViewHolder class by sending them in construstor see below line new ViewHolder
            convertView.setTag(new ViewHolder(tvName, tvPrice, ivImage));

        } else {  // if layout inflater in not null  then direct got value from getTage method of View class where we set our view setTag method..when layoutInflater is null
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            tvName = viewHolder.tvProductName;
            tvPrice = viewHolder.tvProductPrice;
            ivImage = viewHolder.ivProductImage;
        }

        final Product product = getItem(position);
        tvName.setText(product.getpName());
        tvPrice.setText(Constant.CURRENCY+String.valueOf(product.getpPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
        Log.d(TAG, "Context package name: " + context.getPackageName());

        // getting image with just its name
        ivImage.setImageResource(context.getResources().getIdentifier(
                product.getpImageName(), "drawable", context.getPackageName()));


        return convertView;
    }

    private static class ViewHolder {
        public final TextView tvProductName;
        public final TextView tvProductPrice;
        public final ImageView ivProductImage;

        public ViewHolder(TextView tvProductName, TextView tvProductPrice, ImageView ivProductImage) {
            this.tvProductName = tvProductName;
            this.tvProductPrice = tvProductPrice;
            this.ivProductImage = ivProductImage;
        }
    }
}
