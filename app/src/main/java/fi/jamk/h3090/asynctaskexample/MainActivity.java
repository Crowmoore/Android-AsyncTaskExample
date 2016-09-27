package fi.jamk.h3090.asynctaskexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private Button asyncTaskButton;
    private ProgressBar progressbar;
    private ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asyncTaskButton = (Button) findViewById(R.id.button);
        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        progressbar.setVisibility(View.INVISIBLE);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
    }

    public void asyncTaskClicked(View view) {
        new ExampleTask().execute();
    }

    private class ExampleTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... parameters) {
            for (int i = 0; i < 100; i++) {
                publishProgress(i);
                try{
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Log.e("Error", "Thread interrupted in AsyncTask");
                }
            };
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... parameters) {
            progressbar.setProgress(parameters[0]);
            progressBar2.setProgress(parameters[0]);
        }

        @Override
        protected void onPreExecute() {
            progressbar.setVisibility(View.VISIBLE);
            asyncTaskButton.setEnabled(false);
        }

        @Override
        protected void onPostExecute(Void parameters) {
            asyncTaskButton.setEnabled(true);
            progressbar.setVisibility(View.INVISIBLE);
            progressBar2.setProgress(0);
        }
    }

}
