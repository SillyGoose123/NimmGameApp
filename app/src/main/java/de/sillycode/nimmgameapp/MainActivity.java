package de.sillycode.nimmgameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private TextView münzInf;
    private TextView infStat;
    private int münzen;
    private int humantokens;
    private boolean compwin = false;
    private boolean playerWin = false;
    private boolean Playing = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        münzInf = findViewById(R.id.coinLabel);
        infStat = findViewById(R.id.info);
        münzInf.setVisibility(View.INVISIBLE);


        button1.setOnClickListener(v -> {
            if(Playing == true) {
                if(münzen != 0) {
                    münzen -= 1;
                    humantokens = 1;
                    Computer();
                } else {
                    compwin = true;
                    playerWin = false;
                    lock();
                }

            } else {
                Play();
            }
        });

        button2.setOnClickListener(v -> {
            if(Playing == true) {
                if(münzen != 0) {
                    münzen -= 2;
                    humantokens = 2;
                    Computer();
                } else {
                    compwin = true;
                    playerWin = false;
                    lock();
                }

            } else {
                finishAndRemoveTask();
            }

        });

        button3.setOnClickListener(v -> {
            if(Playing == true) {
                if(münzen != 0) {
                    münzen -= 3;
                    humantokens = 3;
                    Computer();
                } else {
                    compwin = true;
                    lock();
                }
            } else {
                button2.callOnClick();
            }
        });

        Start();
    }

    private void Start() {
        münzen = (int) ((Math.random() * (20 - 10)) + 10);
        MakeLabelText(münzInf,"Coins: " + str(münzen));
    }

    private void PlayAgain() {
        münzen = (int) ((Math.random() * (20 - 10)) + 10);
        MakeLabelText(münzInf, str(münzen));
        compwin = false;
        playerWin = false;
        button1.setText("Play Again");
        button2.setEnabled(true);
        button2.setText("Quit");
        button3.setEnabled(true);
        button3.setText("Quit");
    }

    private void Update() {
        MakeLabelText(münzInf, "Coins: " + str(münzen));
        if(playerWin == true) {
            MakeLabelText(infStat, "Du hast Gewonnen");
            Playing = false;
            PlayAgain();
        } else if(compwin == true) {
            MakeLabelText(infStat, "Du hast Verloren");
            Playing = false;
            PlayAgain();
        }
    }

    private void Play() {
        Playing  = true;;
        button1.setText("1");
        button2.setText("2");
        button3.setText("3");
        münzInf.setVisibility(View.VISIBLE);
        delock();
    }


    private void Computer() {
        lock();
        if(münzen > 0) {
            int computertokens = 4 - humantokens;
            if(münzen - computertokens > 0) {
                münzen -= computertokens;
                MakeLabelText(infStat, "Computer zieht " + computertokens );
            } else   {
                computertokens = 1;
                while(münzen - computertokens > 0){
                    computertokens++;
                }
                münzen -= computertokens;
                MakeLabelText(infStat, "Computer zieht " + computertokens );
            }

            if(münzen == 0) {
                playerWin = true;
                compwin = false;
            }

        } else  {
            compwin = true;
            playerWin = false;
        }

        delock();
        Update();
    }

    private String str(int i) {
        String str = String.valueOf(i);
        return str;
    }


    private void MakeLabelText(TextView L , String Text) {
        L.setText(Text);
    }

    private void lock() {
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        Update();
    }

    private void delock() {
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
    }

}