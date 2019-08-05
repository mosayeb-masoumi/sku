package com.example.sku.activities.product_register_detailed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sku.R;
import com.example.sku.models.product_register_detail.ProductRegisterDetailData;
import com.example.sku.models.product_register_detail.ProductRegisterDetailDataList;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolder> {

    ProductRegisterDetailDataList productRegisterDetailDataList;
    Context context;
    private LayoutInflater layoutInflater;

    public ParentAdapter(ProductRegisterDetailDataList productRegisterDetailDataList, Context context) {
        this.productRegisterDetailDataList = productRegisterDetailDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_parent_product_register_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductRegisterDetailData model = productRegisterDetailDataList.data.get(position);

        holder.txtTitle.setText(model.title);

        String type = model.type;





    }

    @Override
    public int getItemCount() {
        return productRegisterDetailDataList.data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        Spinner spinnerRowParent;
        TextView txtTitle;
        EditText editText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle=itemView.findViewById(R.id.row_txtTitleTypeTextParent);
            editText = itemView.findViewById(R.id.row_edtTitleTypeTextParent);
            spinnerRowParent = itemView.findViewById(R.id.spinnerRowParent);
        }
    }
}
