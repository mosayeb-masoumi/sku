package com.example.sku.network;

import com.example.sku.activities.product_register_detailed.EditTextContentsList;
import com.example.sku.models.barcode_check.BarcodeCheck;
import com.example.sku.models.barcode_check.BarcodeCheckSendData;
import com.example.sku.models.barcode_list.BarcodeProductsList;
import com.example.sku.models.barcode_list.BarcodeSendData;
import com.example.sku.models.category.CategoryList;
import com.example.sku.models.city.CityList;
import com.example.sku.models.city.CitySendData;
import com.example.sku.models.login.LoginResult;
import com.example.sku.models.login.LoginSendData;
import com.example.sku.models.product_register.TotalSpnLists;
import com.example.sku.models.product_register.TotalSpnListsSendData;
import com.example.sku.models.product_register_detail.ProductDetailInfoParent;
import com.example.sku.models.product_register_detail.ProductRegisterDetailDataList;
import com.example.sku.models.product_register_detail.ProductRegisterDetail_SendData;
import com.example.sku.models.product_register_send.ProductRegisterSend;
import com.example.sku.models.product_register_send.ProductRegisterSend_SendData;
import com.example.sku.models.province.ProvinceList;
import com.example.sku.models.register_categoty.RegisterCategory;
import com.example.sku.models.register_categoty.RegisterCategorySendData;
import com.example.sku.models.register_shop.RegisterShop;
import com.example.sku.models.register_shop.RegisterShopSendData;
import com.example.sku.models.send_pics.SendPics;
import com.example.sku.models.shop.ShopList;
import com.example.sku.models.sub_brandList_spn.SubBrandList;
import com.example.sku.models.sub_brandList_spn.SubBrandListSendData;
import com.example.sku.models.sub_categoryList_spn.SubCategoryList2;
import com.example.sku.models.sub_categoryList_spn.SubCategoryList2SendData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Service {
    @POST("Logins")
    Call<LoginResult> getLogin(@Body LoginSendData sendData);


    //    @Headers({"Accept: application/json"})
    @GET("Shop")
    Call<ShopList> getShoplist();

    //mes
//    @Headers({"Authorization: Bearer user1@sku.com"})
//    @GET("Shop")
//    Call<ShopList> getShoplist();


//    @Headers({"Authorization: Bearer user1@sku.com"})
    @GET("Shop/Province")
    Call<ProvinceList> getProvinceList();


//    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Shop/City")
    Call<CityList> getCityList(@Body CitySendData citySendData);


//    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Shop/Create")
    Call<RegisterShop> getRegisterShop(@Body RegisterShopSendData registerShopSendData);


//    @Headers({"Authorization: Bearer user1@sku.com"})
    @GET("Category")
    Call<CategoryList> getCategoryList();



    //    @Headers({"Authorization: Bearer user1@sku.com","Accept: application/json"})
//    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Barcode/Check")                                                              ////////////////////////////////////////
    Call<BarcodeCheck> getBarcodeCheck(@Body BarcodeCheckSendData barcodeCheckSendData);


    //    @Headers({"Authorization: Bearer user1@sku.com","Accept: application/json"})
//    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Barcode/List")
    Call<BarcodeProductsList> getBarcodeProductList(@Body BarcodeSendData barcodeSendData);



    //    @Headers({"Authorization: Bearer user1@sku.com", "Accept: application/json"})
//    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Product/MainList")
    Call<TotalSpnLists> getMainSpnLists(@Body TotalSpnListsSendData totalSpnListsSendData);


//    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Product/SubBrandList")
    Call<SubBrandList> getSubBrandList(@Body SubBrandListSendData subBrandListSendData);



//    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Product/SubCategoryList")
    Call<SubCategoryList2> getSubCategoryList2(@Body SubCategoryList2SendData subCategoryList2SendData);



//    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Product/Create")
    Call<ProductRegisterSend> getProductRegisterSend(@Body ProductRegisterSend_SendData productRegisterSend_sendData);




//    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Option/List")
    Call<ProductRegisterDetailDataList> getProductRegisterDetailDatalist(@Body ProductRegisterDetail_SendData productRegisterDetail_sendData);



//    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Product/Detail")
    Call<ProductDetailInfoParent> getProductDetailInfoParent(@Body ProductRegisterDetail_SendData productRegisterDetail_sendData);




    //menu_main
//    @Headers({"Authorization: Bearer user1@sku.com"})
//    @Multipart
////    @FormUrlEncoded
//    @POST("Option/Create")
//    Call<Boolean> get_option_create(@Part("editTextContents") List<EditTextContents> editTextContents,
//                                    @Part("modelSpinner") List<ModelSpinner> modelSpinners,
//                                    @Part("productId") String productId);


//    @Headers({"Authorization: Bearer user1@sku.com"})
//    @Multipart
////    @FormUrlEncoded
//    @POST("Option/Create")
//    Call<Boolean> get_option_create(@Part("editTextContents") EditTextContentsList editTextContentsList,
//                                    @Part("modelSpinner") ModelSpinnerList modelSpinnerList,
//                                    @Part("productId") String productId);


//    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Option/Create")
    Call<Boolean> get_option_create(@Body EditTextContentsList editTextContentsList);


//    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Upload/Create")
    Call<Boolean> send_pics(@Body SendPics sendPics);


    @POST("Category/Create")
    Call<RegisterCategory> getRegisterCategory(@Body RegisterCategorySendData registerCategorySendData);

}
