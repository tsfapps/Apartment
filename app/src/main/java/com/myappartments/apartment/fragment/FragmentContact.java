package com.myappartments.apartment.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.myappartments.apartment.R;
import com.myappartments.apartment.activity.MainActivity;
import com.myappartments.apartment.presenter.ContactPresenter;
import com.myappartments.apartment.storage.SharedPrefManager;
import com.myappartments.apartment.utils.CustomToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentContact extends Fragment {
    private Context tContext;
    private SharedPrefManager tSharedPrefManager;

    @BindView(R.id.et_contactUs)
    protected EditText et_contactUs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_contact, container, false);
        ButterKnife.bind(this, view);
        initFrag();
        return view;
    }

    private void initFrag(){
        MainActivity tActivity = (MainActivity) getActivity();
        if (tActivity != null){
            tActivity.setTextToolbar("Contact Us");
        }
        tContext = getContext();
        tSharedPrefManager = new SharedPrefManager(tContext);

    }

    @OnClick(R.id.btn_submitContact)
    public void submitContactClicked(View view){
        String strUserId = tSharedPrefManager.getUserId();
        String strMessage = et_contactUs.getText().toString().trim();
        if (!strMessage.equals("")) {
            ContactPresenter.callApiContact(strUserId, strMessage, tContext);
        }
        else {
            CustomToast.tToastTop(tContext, "Kindly write your feedback or comments...");
        }
    }
    @OnClick(R.id.tv_contactCall)
    public void callContactClicked(View view){
        String strPhone = "+919591323678";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", strPhone, null));
        startActivity(intent);
    }
    @OnClick(R.id.tv_contactMail)
    public void mailContactClicked(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","myapartments.info@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My Laundry");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Write Message");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}
