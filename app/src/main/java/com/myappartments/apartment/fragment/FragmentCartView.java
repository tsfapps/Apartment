package com.myappartments.apartment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.myappartments.apartment.R;
import com.myappartments.apartment.activity.MainActivity;
import com.myappartments.apartment.adapter.CartViewAdapter;
import com.myappartments.apartment.api.Api;
import com.myappartments.apartment.api.ApiClient;
import com.myappartments.apartment.model.cart.ModelCartView;
import com.myappartments.apartment.storage.SharedPrefManager;
import com.myappartments.apartment.utils.Constant;
import com.myappartments.apartment.utils.CustomLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class FragmentCartView extends Fragment {

    private Context tContext;
    private SharedPrefManager tSharedPrefManager;
    private FragmentManager tFragmentManager;
    private RecyclerView.LayoutManager tLayoutManager;
    private CartViewAdapter tAdapter;

    private MainActivity tActivity;

    @BindView(R.id.rv_cart)
    protected RecyclerView rvCart;
    @BindView(R.id.ll_emptyCart)
    protected LinearLayout ll_emptyCart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_cart_view, container, false);
        ButterKnife.bind(this,view);
        initFrag();
        return view;
    }

    private void initFrag(){
        tContext = getContext();
        tFragmentManager = getFragmentManager();
        tSharedPrefManager = new SharedPrefManager(tContext);
        tActivity = (MainActivity)getActivity();
        if (tActivity != null){
            tActivity.setTextToolbar("My Cart");
        }
        tLayoutManager = new LinearLayoutManager(tContext);
        rvCart.setLayoutManager(tLayoutManager);
        callApi();
    }
    private void callApi(){
        tActivity.uiThreadHandler.sendEmptyMessage(Constant.SHOW_PROGRESS_DIALOG);
        final String strUserId = tSharedPrefManager.getUserId();
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<List<ModelCartView>> call = api.cartView(strUserId);
        call.enqueue(new Callback<List<ModelCartView>>() {
            @Override
            public void onResponse(Call<List<ModelCartView>> call, Response<List<ModelCartView>> response) {
                List<ModelCartView> tModels = response.body();
                assert tModels != null;
                CustomLog.d(Constant.TAG, "Cart Size : "+tModels.size());
                if (tModels.size()==0){
                    ll_emptyCart.setVisibility(View.VISIBLE);
                    rvCart.setVisibility(View.GONE);
                }
                else {
                    ll_emptyCart.setVisibility(View.GONE);
                    rvCart.setVisibility(View.VISIBLE);
                    tAdapter = new CartViewAdapter(tContext, tModels, tFragmentManager, strUserId, rvCart);
                    rvCart.setAdapter(tAdapter);
                    tActivity.uiThreadHandler.sendMessageDelayed(tActivity.uiThreadHandler.obtainMessage(Constant.HIDE_PROGRESS_DIALOG),Constant.HIDE_PROGRESS_DIALOG_DELAY);
                }
            }
            @Override
            public void onFailure(Call<List<ModelCartView>> call, Throwable t) {
                ll_emptyCart.setVisibility(View.VISIBLE);
                rvCart.setVisibility(View.GONE);
                CustomLog.d(Constant.TAG, "CartView not Responding : "+t);
                tActivity.uiThreadHandler.sendMessageDelayed(tActivity.uiThreadHandler.obtainMessage(Constant.HIDE_PROGRESS_DIALOG),Constant.HIDE_PROGRESS_DIALOG_DELAY);
            }
        });
    }
    @OnClick(R.id.btn_cartViewCheckout)
    public void clickedCheckout(View view){
        tFragmentManager.beginTransaction().replace(R.id.container_main, new FragmentCartCheckout()).addToBackStack(null).commit();
    }
    @OnClick(R.id.btn_cartEmpty)
    public void btn_cartEmptyClicked(View view){
        tFragmentManager.beginTransaction().replace(R.id.container_main, new FragmentLaundry()).commit();
    }
}
