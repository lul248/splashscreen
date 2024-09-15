package com.example.splash;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delay for a few seconds
        new Handler().postDelayed(() -> {
            if (isConnectedToInternet()) {
                // Proceed to MainActivity if internet is available
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish(); // Close SplashActivity
            } else {
                // Show Snackbar if no internet and stay in SplashActivity
                Snackbar.make(findViewById(R.id.splash_logo), "No Internet Connection. Please check your network.", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry", view -> {
                            // Retry after Snackbar action is clicked
                            recreate();
                        }).show();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    // Method to check internet connectivity
    private boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
        return false;
    }
}
