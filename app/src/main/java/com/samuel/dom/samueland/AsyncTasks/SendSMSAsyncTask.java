package com.samuel.dom.samueland.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.telephony.SmsManager;

import com.samuel.dom.samueland.Interfaces.AsyncTaskCompleteListener;
import com.samuel.dom.samueland.Models.SMSModel;
import com.samuel.dom.samueland.Models.Samples.SamplePojoOutPut;

import java.util.ArrayList;

/**
 * Created by dom on 7/6/2014.
 */
public class SendSMSAsyncTask  extends AsyncTask<SMSModel, Integer, String> {

    private Context context;
    private AsyncTaskCompleteListener<String> listener;


    public SendSMSAsyncTask(Context ctx, AsyncTaskCompleteListener<String> listener)
    {
        this.context = ctx;
        this.listener = listener;
    }
    protected void onPreExecute()
    {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(SMSModel... params) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> parts = smsManager.divideMessage(params[0].smsMessage);
            smsManager.sendMultipartTextMessage(params[0].smsNumber, null, parts, null, null);
        } catch (Exception e) {
            //TODO Handle problems..
        }
        return params[0].smsMessage;
    }

    @Override
    protected void onPostExecute(String myPojo)
    {
        super.onPostExecute(myPojo);
        listener.onTaskComplete(myPojo);
    }
}
