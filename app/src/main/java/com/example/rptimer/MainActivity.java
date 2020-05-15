package com.example.rptimer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    public int total;
    public int total2;
    public int choice;
    static CountDownTimer waitTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText t1=(EditText)findViewById(R.id.tv1);
        final EditText t2=(EditText) findViewById(R.id.tv2);
        final EditText t3=(EditText) findViewById(R.id.tv3);
        final Button start=(Button)findViewById(R.id.button);
        final TextView result=(TextView)findViewById(R.id.rem);
        final Button stop=(Button)findViewById(R.id.button2);

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                choice=0;
                start.setVisibility(View.INVISIBLE);
                stop.setText("Stop");
                int hr;
                if(t1.getText().toString().equals(""))
                    hr=0;
                else
                    hr=Integer.parseInt(t1.getText().toString());
                int min;
                if(t2.getText().toString().equals(""))
                    min=0;
                else
                    min=Integer.parseInt(t2.getText().toString());
                int sec;
                if(t3.getText().toString().equals(""))
                    sec=0;
                else
                    sec= Integer.parseInt(t3.getText().toString());
                if(hr==0 && min==0 &&sec==0 && total2!=0)
                    total=total2;
                else
                    total = (hr*3600)+(min*60)+sec;
                total2=total;
                    waitTimer = new CountDownTimer(total * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                            result.setText(String.valueOf(total2));
                            total2--;

                        }

                        @Override
                        public void onFinish() {
                            result.setText("STOP");
                            stop.setText("Reset");
                        }
                    }.start();
                }

        });
        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                if(choice==0) {
                    waitTimer.cancel();
                    waitTimer = null;
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    choice = 1;
                    start.setVisibility(View.VISIBLE);
                    stop.setText("Reset");
                }
                else{
                    total2=0;
                    result.setText("RESET");
                    choice=0;
                    stop.setText("Exit");
                }

            }
        });
    }
}
