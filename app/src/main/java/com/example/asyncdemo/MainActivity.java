package com.example.asyncdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * very simple demo of a AsyncTask.
 * Starts a AsyncTask to to display the progress from 0 to 100 (in increments of 5).
 *
 * AsyncTask was deprecated in API level 30.
 */
public class MainActivity extends AppCompatActivity {
    TextView Progress;
    Button Button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Progress = findViewById(R.id.textView1);
        Button1 = findViewById(R.id.button1);
        Button1.setOnClickListener(new Button.OnClickListener() {
            /**
             * starts the asynctask.
             */
            @Override
            public void onClick(View view) {
                CountingTask task = new CountingTask();
                task.execute(0);
            }
        });
    }

    /**
     * This is a very simple AsyncTask that counts to 100 and sets to the text view in the
     * layout.
     */
    public class CountingTask extends AsyncTask<Integer, Integer, Integer> {

        CountingTask() {
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            int i = params[0];
            while (i < 100) {
                SystemClock.sleep(250);
                i++;
                if (i % 5 == 0) {
                    //update UI
                    publishProgress(i);
                }
            }
            return i;
        }

        protected void onProgressUpdate(Integer... progress) {
            Progress.setText("Progress: " + progress[0] + "%");
        }

        protected void onPostExecute(Integer result) {
            Progress.setText("Completed: " + result + "%");
        }
        protected void onPreExecute() {
            //invoked on the UI thread before the task is executed.
            //his step is normally used to setup the task, for instance by showing a progress bar in the user interface.
            Progress.setText("About to start.");
        }

    }
}
