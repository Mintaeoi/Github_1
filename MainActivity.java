package org.techtown.pickgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "tag";

    Button button_card;
    Button button_bomb;
    Button button_letter;
    Button button_balence;

    private AdView adView_main;

    private long backKeyPressedTime = 0;
    private Toast toast;

    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //전체화면 작업표시줄 없애기
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //가로화면 전환 방지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //다크모드 방지

        int a = 0;//처음 MainActivity를 열은 상태
/*
        Intent intent = getIntent();
        if (intent.getIntExtra("number_before", 0) == 1) {
            a = getIntent().getExtras().getInt("number_before");
        }
        if(a == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("주의");
            builder.setMessage("지나친 음주는 뇌졸중, 기억력 손상이나 치매를 유발합니다. 임신 중 음주는 기형아 출생 위험을 높입니다.");
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.cancel();
                }
            });
            builder.show();
        }else{
            Log.d(TAG,"과도한 음주에 대한 경고 메세지가 뜨지 않음");
        }
*/




        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        adView_main = findViewById(R.id.adView_main);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView_main.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("\n" +"ca-app-pub-7754099565688705/4812524818");
        //나의 하단 배너 광고 ID ca-app-pub-7754099565688705/4812524818
        //개발자 공용 배너 ID ca-app-pub-3940256099942544/6300978111


        button_card = findViewById(R.id.button_card);
        button_bomb = findViewById(R.id.button_bomb);
        button_letter = findViewById(R.id.button_letter);
        button_balence = findViewById(R.id.button_balence);


        button_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CardActivity.class);
                startActivity(intent);

                finish();

            }
        });
        //카드뽑기 화면으로 전환하기

        button_bomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BombActivity.class);
                startActivity(intent);

                finish();

            }
        });
        //폭탄돌리기 화면으로 전환하기

        button_letter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LetterActivity.class);
                startActivity(intent);

                finish();

            }
        });
        //초성퀴즈 화면으로 전환하기

        button_balence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BalenceActivity.class);
                startActivity(intent);

                finish();
            }
        });
        //풍선게임 화면으로 전환하기

    }

    @Override
    public void onBackPressed() {

        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간에 2.5초를 더해 현재 시간과 비교 후
        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간이 2.5초가 지났으면 Toast 출력
        // 2500 milliseconds = 2.5 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2500) {
        backKeyPressedTime = System.currentTimeMillis();
        toast = Toast.makeText(this, "종료하시려면 한 번 더 누르세요!", Toast.LENGTH_LONG);
        toast.show();
        return;
         }
        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간에 2.5초를 더해 현재 시간과 비교 후
        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간이 2.5초가 지나지 않았으면 종료
        if (System.currentTimeMillis() <= backKeyPressedTime + 2500) {
            finish();
            toast.cancel();
        }
    }

}