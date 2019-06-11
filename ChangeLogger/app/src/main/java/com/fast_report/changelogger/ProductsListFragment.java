package com.fast_report.changelogger;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.ProductVM;

public class ProductsListFragment extends Fragment {

    public ProductsListFragment() {
        // Required empty public constructor
    }

    ProductLab Lab = ProductLab.getInstance();
    List<ProductVM> mProducts;
    ProductAdapter mProductAdapter = new ProductAdapter(mProducts);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("Products");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);

        mProducts = Lab.getAllProducts();
        ProductAdapter mProductAdapter = new ProductAdapter(mProducts);

    }

    private class ProductAdapter extends RecyclerView.Adapter <ProductAdapter.ProductViewHolder>{

        ArrayList<ProductVM> mProductList;

        public ProductAdapter (ArrayList<ProductVM> productList){
            this.mProductList = productList;
        }

        public class ProductViewHolder extends RecyclerView.ViewHolder {

            private ProductVM mProduct;

            private TextView mProductOrderLabel;
            private TextView mProductNameLabel;
            private TextView mProductComitedLabel;
            private TextView mProductDocRepLabel;
            private TextView mProductDocRepLink;
            private TextView mProductAvgBuildLabel;
            private TextView mProductLangLabel;

            private Button mDeleteButton;
            private Button mEditButton;

            public ProductViewHolder (View itemView){
                super(itemView);
            }

            public void bindProduct(ProductVM product) {
                //binding
            }

        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product,parent,false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            ProductVM product = mProductList.get(position);
            holder.bindProduct(product);
        }

        @Override
        public int getItemCount() {
            return mProductList.size();
        }
    }
}


