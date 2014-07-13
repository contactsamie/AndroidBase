package com.samuel.dom.samueland.Activities.Samples;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.samuel.dom.samueland.AsyncTasks.Samples.SampleAsyncTask;
import com.samuel.dom.samueland.Interfaces.AsyncTaskCompleteListener;
import com.samuel.dom.samueland.Models.Samples.SamplePojoInPut;
import com.samuel.dom.samueland.Models.Samples.SamplePojoOutPut;
import com.samuel.dom.samueland.R;

/**
 * Created by dom on 7/6/2014.
 */
public class SampleActivity extends Activity {

    public class SampleAsyncTaskCompleteListener implements AsyncTaskCompleteListener<SamplePojoOutPut>
    {
        @Override
        public void onTaskComplete(SamplePojoOutPut result)
        {
            Toast.makeText(getApplicationContext(), result.SampleProperty, Toast.LENGTH_LONG).show();
        }
    }


    public void RunTaskInBack(View view){
        SamplePojoInPut input=new SamplePojoInPut();
        input.SampleProperty="Hi this is sam";
        new SampleAsyncTask(this, new SampleAsyncTaskCompleteListener()).execute(input);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
