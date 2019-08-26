package com.example.sku.activities.product_register_detailed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.sku.R;
import com.example.sku.helpers.App;
import com.example.sku.models.product_register_detail.ProductRegisterDetailData;
import com.example.sku.models.product_register_detail.ProductRegisterDetailDataList;

import java.util.ArrayList;
import java.util.List;


public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolder> {

    ProductRegisterDetailDataList productRegisterDetailDataList;
    Context context;
    private LayoutInflater layoutInflater;
    Presenter presenter;

    List<ParentAdapterGetIdText> parentIdValueList = new ArrayList<>();


    public ParentAdapter(ProductRegisterDetailDataList productRegisterDetailDataList, Context context ,Presenter presenter ) {
        this.productRegisterDetailDataList = productRegisterDetailDataList;
        this.context = context;
        this.presenter = presenter;
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


        // to get id and text of title of each row
        parentIdValueList.add(new ParentAdapterGetIdText(model.id ,model.title));




        String type = model.type;
        if (type.equals("dropdown")) {
            holder.rlSpn_row_parent_registerDatail.setVisibility(View.VISIBLE);
            presenter.setSpinner(holder.spinnerRowParent,position,model.id , model.title);
            App.spnPosition = position;
//            String a =model.id;
//            String b = model.title;
//
//            String aa = a;
//            String bb = b;
        }

        if (type.equals("text")) {
            holder.editText.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return productRegisterDetailDataList.data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        Spinner spinnerRowParent;
        TextView txtTitle;
        EditText editText;
        RelativeLayout rlSpn_row_parent_registerDatail, rlFlavour_row_parent_registerDatail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.row_txtTitleTypeTextParent);
            editText = itemView.findViewById(R.id.row_edtTitleTypeTextParent);
            spinnerRowParent = itemView.findViewById(R.id.spinnerRowParent);

            rlFlavour_row_parent_registerDatail = itemView.findViewById(R.id.rlFlavour_row_parent_registerDatail);
            rlSpn_row_parent_registerDatail = itemView.findViewById(R.id.rlSpn_row_parent_registerDatail);
        }
    }





}
