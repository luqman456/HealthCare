package com.example.healthcare.Progress;

import android.content.Context;

public class CustomProgressDialog {

    android.app.ProgressDialog loading;
    Context context;


    public CustomProgressDialog(Context context)
    {
        this.context = context;

    }


    public void StartCircularPrograsBar(String title, String message)
    {
        try{

            loading = android.app.ProgressDialog.show(context,title,message,true,false);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void DismissPrograsBar()
    {

        if (loading!=null) {
            loading.dismiss();
        }
    }
}
