package com.myappartments.apartment.fragment;

import android.content.Context;
import android.os.AsyncTask;
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
import android.widget.Button;

import com.myappartments.apartment.R;
import com.myappartments.apartment.activity.MainActivity;
import com.myappartments.apartment.adapter.AdapterLaundry;
import com.myappartments.apartment.api.Api;
import com.myappartments.apartment.api.ApiClient;
import com.myappartments.apartment.model.ModelCount;
import com.myappartments.apartment.model.ModelDescription;
import com.myappartments.apartment.model.ModelSubCat;
import com.myappartments.apartment.storage.SharedPrefManager;
import com.myappartments.apartment.utils.Constant;

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
    private SharedPrefManager tSharedPrefManager;


    private String strMainCatId;

    public static FragmentLaundry newInstance(String strMainCatId) {

        FragmentLaundry fragment = new FragmentLaundry();
        fragment.strMainCatId = strMainCatId;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_laundry, container, false);
        ButterKnife.bind(this, view);

       // callApiLaundry();
        initFrag();
        return view;
    }

    @OnClick(R.id.btn_go_cart_laundry)
    public void btnGoCartClicked(View view){

        tFragmentManager.beginTransaction().replace(R.id.container_main, new FragmentCart()).addToBackStack(null).commit();
    }
    private void initFrag(){
        tContex = getContext();
        tFragmentManager = getFragmentManager();
        RecyclerView.LayoutManager tLayoutManger = new LinearLayoutManager(tContex);
        tRecyclerView.setLayoutManager(tLayoutManger);
        tSharedPrefManager = new SharedPrefManager(tContex);
        tActivity  = (MainActivity) getActivity();
        if (tActivity != null){
            tActivity.setTextToolbar("My Laundry");
        }
       // new CallApiAsyncTask(tActivity).execute();
        callApiLaundry();

    }
    private void callApiLaundry(){
        tActivity.uiThreadHandler.sendEmptyMessage(Constant.SHOW_PROGRESS_DIALOG);
        final String strUserId = tSharedPrefManager.getUserId();
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<List<ModelSubCat>> call = api.getSubCat("2");
        call.enqueue(new Callback<List<ModelSubCat>>() {
            @Override
            public void onResponse(Call<List<ModelSubCat>> call, Response<List<ModelSubCat>> response) {
                tModels = response.body();
                tAdapter = new AdapterLaundry(tContex, tModels, tFragmentManager, tCount, strMainCatId, strUserId);
                tRecyclerView.setAdapter(tAdapter);
                tActivity.uiThreadHandler.sendMessageDelayed(tActivity.uiThreadHandler.obtainMessage(Constant.HIDE_PROGRESS_DIALOG),Constant.HIDE_PROGRESS_DIALOG_DELAY);

            }
            @Override
            public void onFailure(Call<List<ModelSubCat>> call, Throwable t) {
                tActivity.uiThreadHandler.sendMessageDelayed(tActivity.uiThreadHandler.obtainMessage(Constant.HIDE_PROGRESS_DIALOG),Constant.HIDE_PROGRESS_DIALOG_DELAY);

            }
        });
    }

    private class CallApiAsyncTask extends AsyncTask<Void, Void, Void> {
        private MainActivity tActivity;


        public CallApiAsyncTask(MainActivity tActivity) {
            this.tActivity = tActivity;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
                  }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // tDialog.cancel();
        }
    }
}
