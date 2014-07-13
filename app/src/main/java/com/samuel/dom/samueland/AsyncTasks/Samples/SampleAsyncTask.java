package com.samuel.dom.samueland.AsyncTasks.Samples;

import android.content.Context;
import android.os.AsyncTask;

import com.samuel.dom.samueland.Interfaces.AsyncTaskCompleteListener;
import com.samuel.dom.samueland.Models.Samples.SamplePojoInPut;
import com.samuel.dom.samueland.Models.Samples.SamplePojoOutPut;

/**
 * Created by dom on 7/6/2014.
 */
public class SampleAsyncTask extends AsyncTask<SamplePojoInPut, Integer, SamplePojoOutPut> {

    private Context context;
    private AsyncTaskCompleteListener<SamplePojoOutPut> listener;


    public SampleAsyncTask(Context ctx, AsyncTaskCompleteListener<SamplePojoOutPut> listener)
    {
        this.context = ctx;
        this.listener = listener;
    }
    protected void onPreExecute()
    {
        super.onPreExecute();
    }
    @Override
    protected SamplePojoOutPut doInBackground(SamplePojoInPut... params) {
        SamplePojoOutPut output=new SamplePojoOutPut();



        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        output.SampleProperty=params[0].SampleProperty;
        return output;
    }

    @Override
    protected void onPostExecute(SamplePojoOutPut myPojo)
    {
        super.onPostExecute(myPojo);
        listener.onTaskComplete(myPojo);
    }
}
