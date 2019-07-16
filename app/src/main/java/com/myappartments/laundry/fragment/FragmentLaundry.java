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

import com.myappartments.laundry.R;
import com.myappartments.laundry.activity.MainActivity;
import com.myappartments.laundry.adapter.AdapterLaundry;
import com.myappartments.laundry.api.Api;
import com.myappartments.laundry.api.ApiClient;
import com.myappartments.laundry.model.ModelCount;
import com.myappartments.laundry.model.ModelDescription;
import com.myappartments.laundry.model.ModelSubCat;
import com.myappartments.laundry.storage.SharedPrefManager;
import com.myappartments.laundry.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLaundry extends Fragment {
    private MainActivity tActivity;
    private ModelCount tCount;
    private Context tContex;
    private List<ModelSubCat> tModels;
    private List<ModelDescription> tDescription;
    private AdapterLaundry tAdapter, tAdapterDes;
    private FragmentManager tFragmentManager;
    @BindView(R.id.rv_laundry)
    protected RecyclerView tRecyclerView;
    @BindView(R.id.btn_go_cart_laundry)
    protected Button btnGoCart;
    @BindView(R.id.swipeRefreshLaundry)
    protected SwipeRefreshLayout swipeRefreshLaundry;
    private SharedPrefManager tSharedPrefManager;
    private  String strUserId;
    private  String strUserType;


    private String strMainCatId;
    private String strDbUserId;

    public static FragmentLaundry newInstance(String strMainCatId, String strDbUserId) {

        FragmentLaundry fragment = new FragmentLaundry();
        fragment.strMainCatId = strMainCatId;
        fragment.strDbUserId = strDbUserId;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_laundry, container, false);
        ButterKnife.bind(this, view);
        initFrag();
        return view;
    }
    private void initFrag(){
        tContex = getContext();
        tFragmentManager = getFragmentManager();
        RecyclerView.LayoutManager tLayoutManger = new LinearLayoutManager(tContex);
        tRecyclerView.setLayoutManager(tLayoutManger);
        tSharedPrefManager = new SharedPrefManager(tContex);
        strUserType = tSharedPrefManager.getUserType();

        if (strUserType.equalsIgnoreCase("0")) {
            strUserId = tSharedPrefManager.getUserId();
        }else {
            strUserId = strDbUserId;
        }

        tActivity  = (MainActivity) getActivity();
        if (tActivity != null){
            tActivity.setTextToolbar("My Laundry");
        }
        callApiLaundry();

        swipeRefreshLaundry.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLaundry.setRefreshing(false);
                callApiLaundry();
            }
        });

    }
    @OnClick(R.id.btn_go_cart_laundry)
    public void btnGoCartClicked(View view){

        tFragmentManager.beginTransaction().replace(R.id.container_main, FragmentCartView.newInstance(strUserId)).addToBackStack(null).commit();
    }

//    private void callApiLaundry() {
//        String strUserId = tSharedPrefManager.getUserId();
//        MainCatPresenter.callApiManCat(strUserId, tActivity);
//    }

    public void onResponseMainCat(Response<List<ModelSubCat>> tResponse){

    }
    public void onFailourMainCat(Call<List<ModelSubCat>> tCall){

    }



    private void callApiLaundry(){
        tActivity.uiThreadHandler.sendEmptyMessage(Constant.SHOW_PROGRESS_DIALOG);
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<List<ModelSubCat>> call = api.getSubCat(strMainCatId, strUserId);
        call.enqueue(new Callback<List<ModelSubCat>>() {
            @Override
            public void onResponse(Call<List<ModelSubCat>> call, Response<List<ModelSubCat>> response) {
                tModels = response.body();
                tAdapter = new AdapterLaundry(tContex, tModels, tFragmentManager, tCount, strUserId);
                tRecyclerView.setAdapter(tAdapter);
                tActivity.uiThreadHandler.sendMessageDelayed(tActivity.uiThreadHandler.obtainMessage(Constant.HIDE_PROGRESS_DIALOG),Constant.HIDE_PROGRESS_DIALOG_DELAY);
            }
            @Override
            public void onFailure(Call<List<ModelSubCat>> call, Throwable t) {
                tActivity.uiThreadHandler.sendMessageDelayed(tActivity.uiThreadHandler.obtainMessage(Constant.HIDE_PROGRESS_DIALOG),Constant.HIDE_PROGRESS_DIALOG_DELAY);

            }
        });
    }

}
