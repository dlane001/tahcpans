package com.example.marc.tahcpans.app;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public static class tryLogin extends AsyncTask<String, String, Boolean> {
        private final String LOG_TAG = tryLogin.class.getSimpleName();

        private boolean getLoginValidation(String loginStr) {
            if (loginStr == "yes") {
                return true;
            } else return false;
        }

        protected Boolean doInBackground(String... params) {
            if (params.length == 0) {
                return false;
            }

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String loginValidation = null;

            try {
                final String LOGIN_BASE_URL = "http://10.0.0.31/login.php?";
                final String ID_PARAM = "id";
                final String PW_PARAM = "pw";

                Uri builtUri = Uri.parse(LOGIN_BASE_URL).buildUpon().appendQueryParameter(ID_PARAM, params[0])
                        .appendQueryParameter(PW_PARAM, params[1])
                        .build();

                URL url = new URL(builtUri.toString());

                Log.i(LOG_TAG, "Built URI " + builtUri.toString());

                // Create the request to LoginPage, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    // Nothing to do.
                    return false;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }

                loginValidation = buffer.toString();

                Log.i(LOG_TAG, "LOGIN String: " + loginValidation);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);

                return false;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                return getLoginValidation(loginValidation);
            } catch (Exception e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            return false;
        }

        protected void onPostExecute(String result) {
            if (result != null) {
                Log.i(LOG_TAG, "Returned " + result);
            }
        }
    }
}
