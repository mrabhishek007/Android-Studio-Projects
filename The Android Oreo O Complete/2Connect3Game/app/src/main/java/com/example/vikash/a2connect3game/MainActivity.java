package com.example.vikash.a2connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    ImageView row1col1,row1col2,row1col3;
    ImageView row2col1,row2col2,row2col3;
    ImageView row3col1,row3col2,row3col3;
    Button play_again_btn;
    TextView winner_txt;

//    0 = yellow , 1 = red , 2 = empty

    int activePlayer = 0 ;

    int[]gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2} ;

    int[][] winningPositions ={ {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6} } ; //0,1,2....,8 are imageview tags don't confuse it with the yellow or red colours values

    boolean gameActive = true ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setImageViewId();
        listenImageviewAction();

    }

    private void listenImageviewAction() {
        row1col1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performTransition(v);
            }
        });

        row1col2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performTransition(v);
            }
        });

        row1col3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performTransition(v);
            }
        });

        row2col1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performTransition(v);
            }
        });

        row2col2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performTransition(v);
            }
        });

        row2col3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performTransition(v);
            }
        });

        row3col1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performTransition(v);
            }
        });

        row3col2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performTransition(v);
            }
        });

        row3col3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performTransition(v);
            }
        });

        play_again_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Resetting game to default value

                winner_txt.setText("");
                winner_txt.setVisibility(View.INVISIBLE);
                play_again_btn.setVisibility(View.INVISIBLE);

               GridLayout gridLayout =  findViewById(R.id.grid_layout_id) ;

               for(int i=0;i<gridLayout.getChildCount();i++){
                    ImageView child_iv = (ImageView)gridLayout.getChildAt(i);
                    child_iv.setImageDrawable(null);
               }
               for(int i = 0;i<gameState.length;i++){
                   gameState[i] = 2;
               }
               activePlayer = 0;
               gameActive=true;


            }
        });

    }

    private void performTransition(View v){
         ImageView imageView  = (ImageView) v ;

        //return tapped imageview id
        int tappedCounter = Integer.parseInt( imageView.getTag().toString() ) ;

        if(gameState[tappedCounter]==2 && gameActive) {

            checkGameWin(tappedCounter);

            imageView.setTranslationY(-1500);
            if (activePlayer == 0) {
                imageView.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                imageView.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            imageView.animate().translationYBy(1500).rotation(3600).setDuration(300);
        }


    }

    private void checkGameWin(int tappedCounter) {


        gameState[tappedCounter] = activePlayer ;

        for(int[] winningPosition :winningPositions){

            //If all the values in winningpositions are same player win i.e. all th colors are of same type,red or yellow

            if(gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]]== gameState[winningPosition[2]] && gameState[winningPosition[0]]!=2)
            {
                //Player has won
                String winner = "";
                if(activePlayer==0){
                winner = "Yellow" ;
            }else
                {
                    winner="Red";
                }

                winner_txt.setVisibility(View.VISIBLE);
                winner_txt.setText(winner + " has won..");
                play_again_btn.setVisibility(View.VISIBLE);
                gameActive=false;

            }


        }
    }

    private void setImageViewId() {
        row1col1 =  findViewById(R.id.imageView_row1col1);
        row1col2 =  findViewById(R.id.imageView_row1col2);
        row1col3 =  findViewById(R.id.imageView_row1col3);


        row2col1 =  findViewById(R.id.imageView_row2col1);
        row2col2 =  findViewById(R.id.imageView_row2col2);
        row2col3 =  findViewById(R.id.imageView_row2col3);

        row3col1 =  findViewById(R.id.imageView_row3col1);
        row3col2 =  findViewById(R.id.imageView_row3col2);
        row3col3 =  findViewById(R.id.imageView_row3col3);

        play_again_btn = findViewById(R.id.retry_btn) ;
        winner_txt = findViewById(R.id.winner_txt);
    }
}
