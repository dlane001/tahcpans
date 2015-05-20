package com.example.marc.tahcpans.app;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutionException;

import com.example.marc.tahcpans.app.MainActivityFragment.*;

public class loginService extends Service {
    public loginService() {

    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        Log.i("INFO", username + " : " + password);

        AsyncTask loginReturn = new tryLogin().execute(username, password);

        try {
            if (loginReturn.get() == true) {
                Log.i("INFO", "EAT A NUTSACK");
            } else {
                Log.i("INFO", "EAT A DICK");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
