package com.example.sku.activities.barcode_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sku.R;
import com.example.sku.models.barcode_list.BarcodeProducts;
import com.example.sku.models.barcode_list.BarcodeProductsList;
import com.squareup.picasso.Picasso;

public class AdapterBarcodelist extends RecyclerView.Adapter<AdapterBarcodelist.ViewHolder> {
    BarcodeProductsList barcodeProductsList;
    Context context;
    private LayoutInflater layoutInflater;

    public AdapterBarcodelist(BarcodeProductsList barcodeProductsList, Context context) {
        this.barcodeProductsList = barcodeProductsList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterBarcodelist.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_barcode_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBarcodelist.ViewHolder holder, int position) {

        BarcodeProducts model = barcodeProductsList.data.get(position);
        holder.row_txt_brand.setText(model.getBrand());
        holder.row_txt_sub_brand.setText(String.valueOf(model.getSubBrand()));
        holder.row_txt_productCategory.setText(model.getCategory());
        holder.row_txt_owner.setText(model.getOwner());
        holder.row_txt_subcategory1.setText(model.getSubCategory());
        holder.row_txt_subcategory2.setText(model.getSubCategory2());

        String link = model.getImage();



        if(!link.equals("")){
            holder.row_img_barcodelist.setVisibility(View.VISIBLE);
            Picasso.get()
//                    .load("https://test.rahbarbazar.com/sku/files/1562754063.5622.jpg")
                    .load(link)
//                    .placeholder(R.drawable.ic_phone_android)
                    .error(R.drawable.ic_phone_android)
                    .into(holder.row_img_barcodelist);
        }else{
            holder.row_img_barcodelist.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return barcodeProductsList.data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView row_img_barcodelist;
        TextView row_txt_brand, row_txt_sub_brand, row_txt_productCategory, row_txt_owner, row_txt_subcategory1, row_txt_subcategory2;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            row_img_barcodelist = itemView.findViewById(R.id.row_img_barcodelist);
            row_txt_brand = itemView.findViewById(R.id.row_txt_brand);
            row_txt_sub_brand = itemView.findViewById(R.id.row_txt_sub_brand);
            row_txt_productCategory = itemView.findViewById(R.id.row_txt_productCategory);
            row_txt_owner = itemView.findViewById(R.id.row_txt_owner);
            row_txt_subcategory1 = itemView.findViewById(R.id.row_txt_subcategory1);
            row_txt_subcategory2 = itemView.findViewById(R.id.row_txt_subcategory2);
        }
    }
}
