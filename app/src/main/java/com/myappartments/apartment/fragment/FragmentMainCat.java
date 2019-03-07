package com.myappartments.apartment.fragment;

import android.app.ProgressDialog;
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

import com.myappartments.apartment.R;
import com.myappartments.apartment.activity.MainActivity;
import com.myappartments.apartment.adapter.AdapterMainCat;
import com.myappartments.apartment.api.Api;
import com.myappartments.apartment.api.ApiClient;
import com.myappartments.apartment.model.MainCatModel;
import com.myappartments.apartment.utils.Constants;
import com.myappartments.apartment.utils.CustomLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMainCat extends Fragment {

    private AdapterMainCat tAdapter;
    private FragmentManager tFragmentManager;
    private List<MainCatModel> tModels;
    private ProgressDialog tDialog;
    @BindView(R.id.rvMainCat)
    protected RecyclerView rvMainCat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_main_cat, container, false);
        ButterKnife.bind(this, view);
        RecyclerView.LayoutManager tLayoutManager = new LinearLayoutManager(getContext());
        rvMainCat.setLayoutManager(tLayoutManager);
        tFragmentManager = getFragmentManager();

        initMain();
        return view;
    }
    private void initMain(){
        MainActivity tActivity = (MainActivity) getActivity();
        if (tActivity != null){
            tActivity.setTextToolbar("Apartment Services");
        }
        tDialog = new ProgressDialog(getContext());
        tDialog.setMessage("Loading...");
        tDialog.show();
        new CallApiAsyncTask(tActivity).execute();

    }


    private class CallApiAsyncTask extends AsyncTask<Void, Void, Void> {
        private MainActivity tActivity;


        public CallApiAsyncTask(MainActivity tActivity) {
            this.tActivity = tActivity;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Api api = ApiClient.getApiClients().create(Api.class);
            Call<List<MainCatModel>> catModelCall = api.mainCate();
            catModelCall.enqueue(new Callback<List<MainCatModel>>() {
                @Override
                public void onResponse(Call<List<MainCatModel>> call, Response<List<MainCatModel>> response) {
                    tModels = response.body();
                    tAdapter = new AdapterMainCat(getContext(), tModels, tFragmentManager);
                    rvMainCat.setAdapter(tAdapter);
                }
                @Override
                public void onFailure(Call<List<MainCatModel>> call, Throwable t) {
                    CustomLog.d(Constants.TAG, "Not Responding : "+ t);
                    if (tDialog.isShowing()){
                        tDialog.cancel();
                    }
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tDialog.cancel();
        }
    }

}
