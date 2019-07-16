package com.myappartments.laundry.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.myappartments.laundry.R;
import com.myappartments.laundry.activity.MainActivity;
import com.myappartments.laundry.adapter.AdapterCartView;
import com.myappartments.laundry.api.Api;
import com.myappartments.laundry.api.ApiClient;
import com.myappartments.laundry.model.cart.ModelCartView;
import com.myappartments.laundry.storage.SharedPrefManager;
import com.myappartments.laundry.utils.Constant;

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
    private AdapterCartView tAdapter;

    private MainActivity tActivity;

    @BindView(R.id.rv_cart)
    protected RecyclerView rvCart;
    @BindView(R.id.swipeRefreshCartView)
    protected SwipeRefreshLayout swipeRefreshCartView;
    @BindView(R.id.ll_emptyCart)
    protected LinearLayout ll_emptyCart;
    @BindView(R.id.btn_cartViewCheckout)
    protected Button btn_cartViewCheckout;


    private String strUserType;
    private String strUserId;
    private String strDbUserId;


    public static FragmentCartView newInstance(String strDbUserId) {
        FragmentCartView fragment = new FragmentCartView();
        fragment.strDbUserId = strDbUserId;
        return fragment;
    }

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
        strUserType = tSharedPrefManager.getUserType();

        if (strUserType.equalsIgnoreCase("0")){
            strUserId = tSharedPrefManager.getUserId();
        } else {strUserId = strDbUserId;}


        tActivity = (MainActivity)getActivity();
        if (tActivity != null){
            tActivity.setTextToolbar("My Cart");
        }
        tLayoutManager = new LinearLayoutManager(tContext);
        rvCart.setLayoutManager(tLayoutManager);
        callApi();
        swipeRefreshCartView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshCartView.setRefreshing(false);
                callApi();
            }
        });
    }
    private void callApi(){

        tActivity.uiThreadHandler.sendEmptyMessage(Constant.SHOW_PROGRESS_DIALOG);
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<List<ModelCartView>> call = api.cartView(strUserId);
        call.enqueue(new Callback<List<ModelCartView>>() {
            @Override
            public void onResponse(Call<List<ModelCartView>> call, Response<List<ModelCartView>> response) {
                List<ModelCartView> tModels = response.body();
                assert tModels != null;
                if (tModels.size()==0){
                    ll_emptyCart.setVisibility(View.VISIBLE);
                    swipeRefreshCartView.setVisibility(View.GONE);
                }
                else {
                    ll_emptyCart.setVisibility(View.GONE);
                    swipeRefreshCartView.setVisibility(View.VISIBLE);
                    tAdapter = new AdapterCartView(tContext, tModels, tFragmentManager, strUserId, rvCart);
                    rvCart.setAdapter(tAdapter);
                    btn_cartViewCheckout.setVisibility(View.VISIBLE);
                    tActivity.uiThreadHandler.sendMessageDelayed(tActivity.uiThreadHandler.obtainMessage(Constant.HIDE_PROGRESS_DIALOG),Constant.HIDE_PROGRESS_DIALOG_DELAY);
                }
            }
            @Override
            public void onFailure(Call<List<ModelCartView>> call, Throwable t) {
                ll_emptyCart.setVisibility(View.VISIBLE);
                swipeRefreshCartView.setVisibility(View.GONE);
                btn_cartViewCheckout.setVisibility(View.GONE);
                tActivity.uiThreadHandler.sendMessageDelayed(tActivity.uiThreadHandler.obtainMessage(Constant.HIDE_PROGRESS_DIALOG),Constant.HIDE_PROGRESS_DIALOG_DELAY);
            }
        });
    }
    @OnClick(R.id.btn_cartViewCheckout)
    public void clickedCheckout(View view){
        tFragmentManager.beginTransaction().replace(R.id.container_main, FragmentCartCheckout.newInstance(strUserId)).addToBackStack(null).commit();
    }
    @OnClick(R.id.btn_cartEmpty)
    public void btnCartEmptyClicked(View view){
        tFragmentManager.beginTransaction().replace(R.id.container_main, FragmentLaundry.newInstance("1",strUserId )).commit();
    }

}
