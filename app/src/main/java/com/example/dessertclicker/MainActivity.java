package com.example.dessertclicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG =MainActivity.class.getSimpleName();
    private TextView revenue_text;
    private TextView amount_sold_text;
    private ImageView dessert_button;
    private int counter ;
    private float total ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dessert_button =findViewById(R.id.dessert_button);
        revenue_text= findViewById(R.id.revenue_text);
        amount_sold_text= findViewById(R.id.amount_sold_text);

        dessert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                total= 50*counter;
                amount_sold_text.setText(""+counter);
                revenue_text.setText(" "+total);
            }
        });

        //to save the counter no as it is even if the view is changed to landscape from portrait instead of count 0
        if(savedInstanceState !=null){
            //output show in this stage using getInt
            counter = savedInstanceState.getInt("COUNTER");
            total= savedInstanceState.getFloat("TOTAL");
            amount_sold_text.setText(" "+counter);
            revenue_text.setText(" "+total);
        }
        Log.d(TAG,"onCreate");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    //saveInstanceState saves the state of the counter in this case
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState");
        //data stores in the state so that we can use in the line 34(input store data)
        outState.putInt("COUNTER", counter);
        outState.putFloat("TOTAL", total);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.share_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.share_button:
                share();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void share() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(shareIntent.EXTRA_TEXT,"I have clicked "+ counter + " Desserts for a total of "+total +"$");
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, "Share Using"));
    }
}