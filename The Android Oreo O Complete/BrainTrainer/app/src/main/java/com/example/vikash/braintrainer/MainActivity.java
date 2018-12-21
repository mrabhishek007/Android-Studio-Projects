package com.example.vikash.braintrainer;

import android.animation.LayoutTransition;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button game_start_btn,play_again_btn;
    private Button btn0,btn1,btn2,btn3;
    private TextView que_txtview,answer_txt,score_txt,timer_txt;
    public ArrayList<Integer> option_holder;
    int total_no_of_que = 0;
    int total_correct_ans=0;
    CountDownTimer timer;
    ConstraintLayout game_ui_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignIds();
        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim);


        game_start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game_start_btn.setVisibility(View.INVISIBLE);
                game_ui_layout.setVisibility(View.VISIBLE);
                game_ui_layout.setAnimation(animation);
                newQuestion();
            }
        });

        play_again_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
                newQuestion();
            }
        });
    }

    private void newQuestion() {

        Random random = new Random();
        int a = random.nextInt(49);
        int b = random.nextInt(49);
        que_txtview.setText(a + " + " +b);
        timer_txt.setText("30s");

        int correct_ans = a+b;
        final int currect_ans_loc = random.nextInt(4);
        option_holder = new ArrayList<Integer>();

        for(int i=0;i<4;i++){
            if(i==currect_ans_loc)
            {
                option_holder.add(correct_ans);
            }
            else{
                int wrong_answer = generateRandomAnsOption(currect_ans_loc);
                option_holder.add(wrong_answer);
            }
        }
        btn0.setText(String.valueOf(option_holder.get(0)));
        btn1.setText(String.valueOf(option_holder.get(1)));
        btn2.setText(String.valueOf(option_holder.get(2)));
        btn3.setText(String.valueOf(option_holder.get(3)));

        timer = new CountDownTimer(10100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer_txt.setText(( ( (int)millisUntilFinished ) -1 )/1000+"s");
            }

            @Override
            public void onFinish() {
                gameOver();
                answer_txt.setTranslationX(-1500);
                answer_txt.setText("You Lost!");
                answer_txt.setTextColor(getResources().getColor(R.color.dark_red));
                answer_txt.animate().translationXBy(1500).rotation(360).setDuration(500);
            }
        };
        timer.start();

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(v,currect_ans_loc);

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(v,currect_ans_loc);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(v,currect_ans_loc);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(v,currect_ans_loc);
            }
        });
    }

    private void resetGame() {
        play_again_btn.setVisibility(View.INVISIBLE);
        btn0.setEnabled(true);
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        answer_txt.setText("");
        total_no_of_que=0;
        total_correct_ans=0;
        score_txt.setText(total_correct_ans+"/"+total_no_of_que);
    }

    private void checkAnswer(View v, int currect_ans_loc) {
        int tag =  Integer.parseInt(((Button)v).getTag().toString());
        if(currect_ans_loc==tag){
            answer_txt.setText("    Nice");
            answer_txt.setTextColor(getResources().getColor(R.color.green_clr));
            total_correct_ans++;
            timer.cancel();
            newQuestion();
        }
        else{
            gameOver();
        }

        total_no_of_que++ ;
        score_txt.setText(total_correct_ans+"/"+total_no_of_que);
    }

    private void gameOver() {
        answer_txt.setTranslationX(-500);
        answer_txt.setText("  Wrong !");
        answer_txt.setTextColor(getResources().getColor(R.color.dark_red));
        answer_txt.animate().translationXBy(500).rotation(720).setDuration(150);
        btn0.setEnabled(false);
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        timer.cancel();
        play_again_btn.setVisibility(View.VISIBLE);
    }

    private int generateRandomAnsOption(int currect_ans_loc){

        Random number_generator = new Random();

        int wrong_ans = number_generator.nextInt(98);

        while (wrong_ans==currect_ans_loc){
            wrong_ans = number_generator.nextInt(98);
        }
        return wrong_ans;
    };


    private void assignIds() {
        game_start_btn = findViewById(R.id.start_game_btn);
        game_ui_layout = findViewById(R.id.game_ui_layout);
        que_txtview = findViewById(R.id.question_txtview);
        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        answer_txt =findViewById(R.id.answer_txt);
        score_txt = findViewById(R.id.score_txtview);
        timer_txt = findViewById(R.id.timer_txtview);
        play_again_btn = findViewById(R.id.play_again_btn);
    }
}
