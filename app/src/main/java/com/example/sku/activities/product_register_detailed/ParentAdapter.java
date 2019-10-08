package com.example.sku.activities.product_register_detailed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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


   List<EditTextContents> editTextContents = new ArrayList<>();


    String[] edtStrings;

    boolean allow= false;


    public ParentAdapter(ProductRegisterDetailDataList productRegisterDetailDataList, Context context ,Presenter presenter ) {
        this.productRegisterDetailDataList = productRegisterDetailDataList;
        this.context = context;
        this.presenter = presenter;

        edtStrings = new String[productRegisterDetailDataList.data.size()];

//        setHasStableIds(true);

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

            holder.editText.setVisibility(View.GONE);

            presenter.setSpinner(holder.spinnerRowParent,position,model.id , model.title);
            App.spnPosition = position;

        }

        if (type.equals("text")) {
            holder.editText.setVisibility(View.VISIBLE);

            holder.rlSpn_row_parent_registerDatail.setVisibility(View.GONE);

            holder.editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    allow = true;

                    if(allow){
                        edtStrings[holder.getAdapterPosition()]=s.toString();

                        editTextContents.add(new EditTextContents(model.id,model.title, edtStrings[holder.getAdapterPosition()]));
                    }
                    allow = false;
                }

            });

          allow = false;

        }

    }


    @Override
    public int getItemCount() {
        return productRegisterDetailDataList.data.size();
    }


    // add below method to remove the bug swipe editText getText
    @Override
    public long getItemId(int position) {
        return position;
    }

    // add below method to remove the bug swipe editText getText
    @Override
    public int getItemViewType(int position) {
        return position;
    }



    public String[] getEdtStrings() {
        return edtStrings;
    }

    public List<EditTextContents> getEditTextContents() {
        return editTextContents;
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
