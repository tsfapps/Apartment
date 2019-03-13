package com.myappartments.apartment.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.myappartments.apartment.R;
import com.myappartments.apartment.activity.MainActivity;
import com.myappartments.apartment.adapter.AdapterLaundry;
import com.myappartments.apartment.adapter.AdapterMainCat;
import com.myappartments.apartment.api.Api;
import com.myappartments.apartment.api.ApiClient;
import com.myappartments.apartment.model.MainCatModel;
import com.myappartments.apartment.model.ModelCount;
import com.myappartments.apartment.model.ModelDescription;
import com.myappartments.apartment.model.ModelSubCat;
import com.myappartments.apartment.utils.Constants;
import com.myappartments.apartment.utils.CustomLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLaundry extends Fragment {
    private ModelCount tCount;
    private Context tCtx;
    private List<ModelSubCat> tModels;
    private List<ModelDescription> tDescription;
    private AdapterLaundry tAdapter, tAdapterDes;
    private FragmentManager tFragmentManager;
    @BindView(R.id.rv_laundry)
    protected RecyclerView tRecyclerView;
    @BindView(R.id.btn_go_cart_laundry)
    protected Button btnGoCart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_laundry, container, false);
        ButterKnife.bind(this, view);
        tCtx = getContext();
        tFragmentManager = getFragmentManager();
        RecyclerView.LayoutManager tLayoutManger = new LinearLayoutManager(tCtx);
        tRecyclerView.setLayoutManager(tLayoutManger);
       // callApiLaundry();
        setTitle();
        return view;
    }

    @OnClick(R.id.btn_go_cart_laundry)
    public void btnGoCartClicked(View view){

        tFragmentManager.beginTransaction().replace(R.id.container_main, new FragmentCart()).addToBackStack(null).commit();
    }
    private void setTitle(){
        MainActivity tActivity = (MainActivity) getActivity();
        if (tActivity != null){
            tActivity.setTextToolbar("My Laundry");
        }
        new CallApiAsyncTask(tActivity).execute();
    }
//    private void callApiLaundry(){
//
//    }

    private class CallApiAsyncTask extends AsyncTask<Void, Void, Void> {
        private MainActivity tActivity;


        public CallApiAsyncTask(MainActivity tActivity) {
            this.tActivity = tActivity;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
          //  tDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Api api = ApiClient.getApiClients().create(Api.class);
            Call<List<ModelSubCat>> call = api.getSubCat("2");
            call.enqueue(new Callback<List<ModelSubCat>>() {
                @Override
                public void onResponse(Call<List<ModelSubCat>> call, Response<List<ModelSubCat>> response) {
                    tModels = response.body();
                    tAdapter = new AdapterLaundry(tCtx, tModels, tFragmentManager, tCount);
                    tRecyclerView.setAdapter(tAdapter);

                }

                @Override
                public void onFailure(Call<List<ModelSubCat>> call, Throwable t) {

                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
           // tDialog.cancel();
        }
    }
}
