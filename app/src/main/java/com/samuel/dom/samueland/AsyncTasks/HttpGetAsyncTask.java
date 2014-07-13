package com.samuel.dom.samueland.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.samuel.dom.samueland.Interfaces.AsyncTaskCompleteListener;
import com.samuel.dom.samueland.Models.Samples.SamplePojoInPut;
import com.samuel.dom.samueland.Models.Samples.SamplePojoOutPut;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dom on 7/6/2014.
 */
public class HttpGetAsyncTask extends AsyncTask<String, Integer, String> {

    private Context context;
    private AsyncTaskCompleteListener<String> listener;


    public HttpGetAsyncTask(Context ctx, AsyncTaskCompleteListener<String> listener)
    {
        this.context = ctx;
        this.listener = listener;
    }
    protected void onPreExecute()
    {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... params) {
        String response = "";

            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(params[0]);
            try {
                HttpResponse execute = client.execute(httpGet);
                InputStream content = execute.getEntity().getContent();

                BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                String s = "";
                while ((s = buffer.readLine()) != null) {
                    response += s;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

      
        return response;
    }

    @Override
    protected void onPostExecute(String httpGetContent)
    {
        super.onPostExecute(httpGetContent);
        listener.onTaskComplete(httpGetContent);
    }
}
