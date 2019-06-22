package com.fast_report.changelogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import io.swagger.client.model.ProductVM;
import io.swagger.client.model.UserVM;

public class ProductsListFragment extends Fragment {

    ProductLab mProductLab = ProductLab.getInstance();
    RecyclerView mProductsRecyclerView;

    ProductVMCallbackInterface mProductVMCallback = new ProductVMCallback();
    ArrayList <ProductVM> mProducts = new ArrayList<>(mProductLab.getAllProducts(mProductVMCallback));
    ProductAdapter mProductAdapter = new ProductAdapter(mProducts);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("Products");
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mProductsRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mProductsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProductsRecyclerView.setAdapter(mProductAdapter);
        return view;
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
            private TextView mProductDescriptionLabel;
            private TextView mProductDocRepLabel;
            private TextView mProductAvgBuildLabel;
            private TextView mProductLangLabel;

            private Button mDeleteButton;
            private Button mEditButton;

            public ProductViewHolder (View itemView){
                super(itemView);
                mProductOrderLabel = (TextView) itemView.findViewById(R.id.product_order_label);
                mProductNameLabel = (TextView) itemView.findViewById(R.id.product_name_label);
                mProductComitedLabel = (TextView) itemView.findViewById(R.id.product_comited_label);
                mProductDescriptionLabel = (TextView) itemView.findViewById(R.id.product_description_label);
                mProductDocRepLabel = (TextView) itemView.findViewById(R.id.product_doc_rep_label);
                mProductAvgBuildLabel = (TextView) itemView.findViewById(R.id.product_avg_time_label);
                mProductLangLabel = (TextView) itemView.findViewById(R.id.product_lang_label);
                mEditButton = (Button) itemView.findViewById(R.id.edit_button);
                mEditButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = SingleProductActivity.newIntent(getActivity(), mProduct.getId()); //Неявный вызов putExtra?
                        startActivity(intent);
                    }
                });
            }

            public void bindProduct(ProductVM product) {
                mProduct = product;
                String docLabel = getResources().getString(R.string.product_dok_rep_text);
                String avgTimeLabel = getResources().getString(R.string.product_avg_time_text);
                String langLabel = getResources().getString(R.string.product_lang_text);
                UserVM Author = product.getUser();
                mProductOrderLabel.setText("#" + product.getId().toString());
                mProductNameLabel.setText(product.getName());
                mProductComitedLabel.setText(Author.getName()+" "+Author.getFamilyName());
                mProductDescriptionLabel.setText(product.getDescription());
                mProductDocRepLabel.setText(docLabel+"   "+product.getRepositoryUrl());
                mProductAvgBuildLabel.setText(avgTimeLabel+"   "+(product.getAvgBuildTime()).toString());
                mProductLangLabel.setText(langLabel+"   "+product.getTag());
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