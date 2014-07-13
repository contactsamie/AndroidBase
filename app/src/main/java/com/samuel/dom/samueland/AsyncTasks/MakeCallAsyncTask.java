package com.samuel.dom.samueland.AsyncTasks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.samuel.dom.samueland.Interfaces.AsyncTaskCompleteListener;
import com.samuel.dom.samueland.Models.Samples.SamplePojoInPut;
import com.samuel.dom.samueland.Models.Samples.SamplePojoOutPut;

/**
 * Created by dom on 7/6/2014.
 */
public class MakeCallAsyncTask  extends AsyncTask<String, Integer, Intent> {

    private Context context;
    private AsyncTaskCompleteListener<Intent> listener;


    public MakeCallAsyncTask(Context ctx, AsyncTaskCompleteListener<Intent> listener)
    {
        this.context = ctx;
        this.listener = listener;
    }
    protected void onPreExecute()
    {
        super.onPreExecute();
    }
    @Override
    protected Intent doInBackground(String... params) {
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + params[0]));

        return callIntent;
    }

    @Override
    protected void onPostExecute(Intent myPojo)
    {
        super.onPostExecute(myPojo);
        listener.onTaskComplete(myPojo);
    }
}
