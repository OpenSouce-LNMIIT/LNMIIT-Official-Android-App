package lnmiit.android.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import lnmiit.android.app.R;
/* Created by Chanpreet
   on 11 August 2016
 */
public class SplashActivity extends AppCompatActivity {

      private Thread splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        splash = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {   // Launching the main Activity
                    Intent mainIntent = new Intent("lnmiit.android.app.main");
                    startActivity(mainIntent);
                    finish();
                }
            }
        };
        splash.start();
    }
}
