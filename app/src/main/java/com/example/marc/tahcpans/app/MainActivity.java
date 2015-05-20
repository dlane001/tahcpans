package com.example.marc.tahcpans.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.util.Log.*;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        EditText usrName;
        EditText psWrd;

        Intent i = new Intent(this, loginService.class);

        usrName = (EditText)findViewById(R.id.username_editText);
        psWrd = (EditText)findViewById(R.id.password_editText);

        i.putExtra("username", usrName.getText().toString());
        i.putExtra("password", psWrd.getText().toString());

        if (startService(i) == null) {
            Log.i("INFO", "SERVICE NOT CREATED");
            //Toast.makeText(getApplicationContext(), (String)"Service did not start", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.i("INFO", "SERVICE CREATED");
            //Toast.makeText(getApplicationContext(), (String) "Service Started", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
