package hcu.info.pro3_w1_ex6;

import static java.lang.String.format;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    TextView tv_result;
    TextView tv_s;

    int randomPicks()  {
        return (int)(Math.random() * 3);
    }

    int checkResult(int p0, int p1) {
        /*
            p0, p1 の出し手 0 ~ 2 の勝敗を判定する。
            p0　が勝った場合 0 を返し、p1 が勝った場合 1 を返す。引き分けは -1 を返す。
            0: グー, 1: チョキ, 2: パーとする
        */

        if (p0 == p1) {
            return -1;
        } else if (p0 < p1) {
            if (p0 == 0 && p1 == 2) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (p0 == 2 && p1 == 0) {
                return 0;
            }
                return 1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String TEXT_ROCK = "グー";
        String TEXT_SCISSOR = "チョキ";
        String TEXT_PAPER = "パー";
        String CONST_DEFAULT_Text = "Select!";
        String CONST_DEFAULT_PL= "-";
        String CONST_DEFAULT_CP = "-";
        String TEXT_YOUR_WIN = "You Win!";
        String TEXT_YOUR_EVEN = "Even";
        String TEXT_YOUR_LOSE = "You Lose...";

        final int[] Pl_Status = {0, 0, 0};
        final int[] Cp_Status = {0, 0, 0};
        final int[] Re_Status = {0, 0, 0};

        // @Override
        View.OnClickListener listener = view -> {
            int buttonId = view.getId();
            int userChoice = -1;
            if (buttonId == R.id.bt1) {
                tv1.setText(TEXT_ROCK);
                userChoice = 0;
                Pl_Status[0] += 1;
            } else if (buttonId == R.id.bt2) {
                tv1.setText(TEXT_SCISSOR);
                userChoice = 1;
                Pl_Status[1] += 1;
            } else if (buttonId == R.id.bt3) {
                tv1.setText(TEXT_PAPER);
                userChoice = 2;
                Pl_Status[2] += 1;
            }

            int computerChoice = randomPicks();

            if (computerChoice == 0) {
                tv2.setText(TEXT_ROCK);
                Cp_Status[0] += 1;
            } else if (computerChoice == 1) {
                tv2.setText(TEXT_SCISSOR);
                Cp_Status[1] += 1;
            } else {
                tv2.setText(TEXT_PAPER);
                Cp_Status[2] += 1;
            }

            int res = checkResult(userChoice, computerChoice);
            if (res == 0) {
                tv_result.setText(TEXT_YOUR_WIN);
                Re_Status[0] += 1;
            } else if (res == 1) {
                tv_result.setText(TEXT_YOUR_LOSE);
                Re_Status[1] += 1;
            } else {
                tv_result.setText(TEXT_YOUR_EVEN);
                Re_Status[2] += 1;
            }
            final float win_rate = (float) Re_Status[0] / (Re_Status[0] + Re_Status[1] + Re_Status[2]) * 100;
            String stat = format(
                    Locale.getDefault(),
                    "[Player] グー: %d, チョキ: %d, パー: %d%n[Computer] グー: %d, チョキ: %d, パー: %d%n[Game] 勝ち: %d, 負け: %d, 引分: %d (勝率: %f %%)",
                    Pl_Status[0], Pl_Status[1], Pl_Status[2],
                    Cp_Status[0], Cp_Status[1], Cp_Status[2],
                    Re_Status[0], Re_Status[1], Re_Status[2],
                    win_rate
            );
            tv_s.setText(stat);
        };

        tv1 = findViewById(R.id.tv1);
        tv1.setText(CONST_DEFAULT_PL);

        tv2 = findViewById(R.id.tv2);
        tv2.setText(CONST_DEFAULT_CP);

        tv_result = findViewById(R.id.tv_result);
        tv_result.setText(CONST_DEFAULT_Text);

        tv_s = findViewById(R.id.tv_s);
        tv_s.setText(CONST_DEFAULT_PL);

        Button bt1 = findViewById(R.id.bt1);
        bt1.setText(TEXT_ROCK);
        bt1.setOnClickListener(listener);

        Button bt2 = findViewById(R.id.bt2);
        bt2.setText(TEXT_SCISSOR);
        bt2.setOnClickListener(listener);

        Button bt3 = findViewById(R.id.bt3);
        bt3.setText(TEXT_PAPER);
        bt3.setOnClickListener(listener);
    }
}