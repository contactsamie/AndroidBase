package com.samuel.dom.samueland.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.samuel.dom.samueland.AsyncTasks.HttpGetAsyncTask;
import com.samuel.dom.samueland.AsyncTasks.HttpPostAsyncTask;
import com.samuel.dom.samueland.AsyncTasks.MakeCallAsyncTask;
import com.samuel.dom.samueland.AsyncTasks.SendSMSAsyncTask;
import com.samuel.dom.samueland.Interfaces.AsyncTaskCompleteListener;
import com.samuel.dom.samueland.Models.SMSModel;
import com.samuel.dom.samueland.Models.Samples.SamplePojoInPut;
import com.samuel.dom.samueland.Models.Samples.SamplePojoOutPut;
import com.samuel.dom.samueland.R;
import com.samuel.dom.samueland.AsyncTasks.Samples.SampleAsyncTask;
import com.samuel.dom.samueland.SensorListeners.ShakeListener;

import java.util.HashMap;
import java.util.Locale;


public class MyActivity extends Activity  implements TextToSpeech.OnInitListener  {
    private TextToSpeech tts;
    private ShakeListener mShaker;

    Runnable r=new Runnable() {

        @Override
        public void run() {
            speakOut("executing your command");

            onHold=false;
        }
    };

private Handler myHandler;
    private Boolean onHold=false;
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
              //  Log.e("TTS", "This Language is not supported");
            } else {

             //   speakOut();
            }

        } else {
           // Log.e("TTS", "Initilization Failed!");
        }
    }
    @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        tts = new TextToSpeech(this, this);
//int threshold,int duration,int shakeCount
        mShaker = new ShakeListener(this,1000,2000,10);
        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
            public void onShake()
            {

//                new AlertDialog.Builder(MyActivity.this)
//                        .setPositiveButton(android.R.string.ok, null)
//                        .setMessage("Shooken!")
//                        .show();
                speakOut("Please hold on");
                ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(3000);

if(onHold==false){
    onHold=true;
    myHandler=      new Handler();
    myHandler .postDelayed(r, 3000);
}else{
    myHandler.removeCallbacks(r);
    onHold=false;
    speakOut("I said hold on. don't you listen");
    ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(500);


}






            }
        });
    }

    @Override
    public void onResume()
    {
        mShaker.resume();
        super.onResume();
    }
    @Override
    public void onPause()
    {
        mShaker.pause();
        super.onPause();
    }
    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
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

    public void RunTaskInBack(View view){
//        ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(4000);
//        Toast.makeText(getApplicationContext(), "Hi Please enter the right Words......  ", Toast.LENGTH_LONG).show();

//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//
//
//            }
//        }, 4000);

    }
//    speakOut("Hello to every one");
//    tts.setLanguage(Locale.CHINESE);
//    speakOut("Hello to every one");
//    tts.setPitch((float) 0.6);
//    speakOut("Hello to every one");
//    tts.setSpeechRate(2);
//    speakOut("Hello to every one");
    private void speakOut(  String text) {

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
    //    SamplePojoInPut input=new SamplePojoInPut();
//    input.SampleProperty="Hi this is sam";
//    new SampleAsyncTask(this, new SampleAsyncTaskCompleteListener()).execute(input);
    public class SampleAsyncTaskCompleteListener implements AsyncTaskCompleteListener<SamplePojoOutPut>
    {
        @Override
        public void onTaskComplete(SamplePojoOutPut result)
        {
            Toast.makeText(getApplicationContext(), result.SampleProperty, Toast.LENGTH_LONG).show();
        }
    }
//    new HttpGetAsyncTask(this, new HttpTaskCompleteListener()).execute("http://httpbin.org/get");
    public class HttpTaskCompleteListener implements AsyncTaskCompleteListener<String>
    {
        @Override
        public void onTaskComplete(String result)
        {
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            TextView textView=(TextView) findViewById(R.id.shower);

            textView.setText(result);
        }
    }




//    HashMap<String, String> data = new HashMap<String, String>();
//    data.put("ts", "value1");
//    data.put("key2", "value2");
//    new HttpPostAsyncTask(this, new HttpPostTaskCompleteListener(),data).execute("http://requestb.in/nwfkuxnw");
    public class HttpPostTaskCompleteListener implements AsyncTaskCompleteListener<String>
    {
        @Override
        public void onTaskComplete(String result)
        {
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            TextView textView=(TextView) findViewById(R.id.shower);

            textView.setText(result);
        }
    }

//    SMSModel sms=new SMSModel();
//    sms.smsMessage="testing 123";
//    sms.smsNumber="6477197264";
//    new SendSMSAsyncTask(this, new SMSTaskCompleteListener()).execute(sms);
    public class SMSTaskCompleteListener implements AsyncTaskCompleteListener<String>
    {
        @Override
        public void onTaskComplete(String result)
        {
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            TextView textView=(TextView) findViewById(R.id.shower);

            textView.setText(result);
        }
    }


// new MakeCallAsyncTask(this, new MakeCallTaskCompleteListener()).execute("6477613264");
    public class MakeCallTaskCompleteListener implements AsyncTaskCompleteListener<Intent>
    {
        @Override
        public void onTaskComplete(Intent callIntent)
        {
            startActivity(callIntent);
        }
    }





}
