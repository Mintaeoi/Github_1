package org.techtown.pickgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class BombActivity extends AppCompatActivity {
    private static final String TAG = "tag";

    Button button_bomb_back;
    Button button_bomb_random;
    TextView textView_bomb;
    TextView textView_bomb_random_text;
    CountDownTimer countDownTimer;

    Animation anim_a;
    Animation anim_b;
    Animation anim_c;
    Animation anim_fade_out;

    private long randomTime_a;
    private long randomTime_b;
    private long randomTime_c;
    private long randomTime_d;
    private long randomTime_e;

    int a = 0;//폭탄 설정 버튼을 안누른 상태

    String[] bombTexts = {"수도이름", "아프리카나라이름", "우리나라도시", "여자가수노래", "남자가수노래", "축구선수이름", "한국역대대통령", "동물이름",
            "탈것이름", "과일이름", "하계스포츠종목", "농구선수이름", "동계스포츠종목", "SNS종류", "위인이름", "아메리카나라이름", "아시아도시이름", "아시아나라이름",
            "직업이름", "의류브랜드이름", "대학교이름", "꽃이름", "카페음료이름", "신체부위이름", "한국영화", "외국영화", "신발브랜드이름", "소주이름",
            "맥주이름", "우리나라지역이름"};

    private InterstitialAd mInterstitialAd_bomb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //전체화면으로 작업표시줄 없애기
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //가로화면 전환 방지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //다크모드 방지
        setContentView(R.layout.activity_bomb);

        button_bomb_back = findViewById(R.id.button_bomb_back);
        button_bomb_random = findViewById(R.id.button_bomb_random);
        textView_bomb = findViewById(R.id.textView_bomb);
        textView_bomb_random_text = findViewById(R.id.textView_bomb_random_text);

        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        AdRequest adRequest = new AdRequest.Builder().build();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        //나의 전면 광고 ID ca-app-pub-7754099565688705/1682092309
        //개발자 공용 배너 ID ca-app-pub-3940256099942544/1033173712
        InterstitialAd.load(this, "ca-app-pub-7754099565688705/1682092309", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd_bomb = interstitialAd;
                        mInterstitialAd_bomb.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdClicked() {
                            }
                            @Override
                            public void onAdImpression() {
                            }
                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                mInterstitialAd_bomb = null;
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent();
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                mInterstitialAd_bomb = null;
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd_bomb = null;
                    }
                });




        //랜덤타이머 및 제시어 설정 버튼을 눌렀을때 일어나는 코드
        button_bomb_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomTime_a = (Long.parseLong(String.valueOf(0))*60000) + (Long.parseLong(String.valueOf(30))*1000);
                randomTime_b = (Long.parseLong(String.valueOf(0))*60000) + (Long.parseLong(String.valueOf(35))*1000);
                randomTime_c = (Long.parseLong(String.valueOf(0))*60000) + (Long.parseLong(String.valueOf(40))*1000);
                randomTime_d = (Long.parseLong(String.valueOf(0))*60000) + (Long.parseLong(String.valueOf(45))*1000);
                randomTime_e = (Long.parseLong(String.valueOf(0))*60000) + (Long.parseLong(String.valueOf(50))*1000);

                if(a == 0){
                    button_bomb_random.setText("리셋하기");

                    //bombTexts에 담긴 개수를 int 값 random으로 변환
                    int random = (int) (Math.random()*bombTexts.length);
                    //random 값에 해당하는 bombTexts를 textView_bomb_random_text에 적용
                    textView_bomb_random_text .setText(bombTexts[random]);


                    //폭탄 터지는 시간 30초, 35초, 40초, 45초, 50초를 담아둔 randomTime
                    long[] randomTime = {randomTime_a, randomTime_b, randomTime_c, randomTime_d, randomTime_e};

                    //randomTime에 담긴 개수를 int 값 randomTime_Num으로 변환
                    int randomTime_Num = (int) (Math.random()*randomTime.length);
                    Log.d(TAG, "처음 변동 : " + randomTime[randomTime_Num]);
                    countDownTimer = new CountDownTimer(randomTime[randomTime_Num], 1000) {
                        @Override
                        public void onTick(long l) {
                            if(l> randomTime[randomTime_Num]-1500){
                                textView_bomb.setText("제한 시간");
                            }else if(randomTime[randomTime_Num]-15000< l && l <= randomTime[randomTime_Num]-1500){
                                textView_bomb.setText("제한 시간");
                                textView_bomb.startAnimation(anim_a);
                            }else if(randomTime[randomTime_Num]-25000 < l && l <= randomTime[randomTime_Num]-15000){
                                textView_bomb.setText("제한 시간");
                                textView_bomb.startAnimation(anim_b);
                            }else if(0<l && l <= randomTime[randomTime_Num]-25000){
                                textView_bomb.setText("제한 시간");
                                textView_bomb.startAnimation(anim_c);
                            }
                        }
                        //textView_bomb에 폭탄의 남은시간을 계속 띄우고
                        // 폭탄의 남은 시간이 15초 이하일때 설정해둔 anim라는 애니메이션을 textView_bomb에 실행한다

                        @Override
                        public void onFinish() {
                            textView_bomb.setText("시간 종료");
                            button_bomb_random.setEnabled(true);
                            vibrator.vibrate(2500);

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    textView_bomb.clearAnimation();
                                    button_bomb_random.setText("랜덤 타이머 설정 및 제시어 설정");
                                }
                            },2500);
                        }
                        //시간이 끝났을 때 시간종료라고 텍스트를 띄우고 진동이 2.5초간 울리도록 설정함
                        //handler를 이용하여 시간이 끝나고 2.5초 뒤에 계속 진행되고 있던 애니메이션을 종료함

                    };
                    countDownTimer.start();
                    a = 1; //폭탄 설정 버튼 누른 상태
                }else if(a == 1){
                    textView_bomb.setText("제한 시간");
                    textView_bomb_random_text.setText("제시어");
                    textView_bomb.clearAnimation();
                    countDownTimer.cancel();
                    button_bomb_random.setText("랜덤 타이머 설정 및 제시어 설정");
                    a = 0;

                    //현재 handler로 중간에 1초 쉬는 도중 a==1 상태인 버튼을 누르면 이상하게 textView_bomb이 작동함
                }

            }
        });

        anim_a = new AlphaAnimation(0.0f, 1.0f);
        anim_a.setDuration(400);
        anim_a.setStartOffset(20);
        anim_a.setRepeatCount(Animation.INFINITE);
        //anim.setRepeatMode(Animation.REVERSE);
        //anim라는 애니메이션 설정작업

        anim_b = new AlphaAnimation(0.0f, 1.0f);
        anim_b.setDuration(200);
        anim_b.setStartOffset(20);
        anim_b.setRepeatCount(Animation.INFINITE);

        anim_c = new AlphaAnimation(0.0f, 1.0f);
        anim_c.setDuration(100);
        anim_c.setStartOffset(20);
        anim_c.setRepeatCount(Animation.INFINITE);

        anim_fade_out = new AlphaAnimation(1.0f, 0.0f);
        anim_fade_out.setDuration(1000);
        anim_fade_out.setStartOffset(20);
        anim_fade_out.setRepeatCount(Animation.INFINITE);

        button_bomb_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("number_before",1);
                startActivity(intent);
                finish();
                if(mInterstitialAd_bomb != null){
                    mInterstitialAd_bomb.show(BombActivity.this);
                    vibrator.cancel();
                    if (countDownTimer != null) {
                        countDownTimer.cancel();
                    }
                }else {
                    vibrator.cancel();
                    if (countDownTimer != null) {
                        countDownTimer.cancel();
                    }
                    Log.d(TAG, "광고가 실행되지 않음.");
                    //폭탄이 메인화면으로 돌아가도 진동이 울림 finish()가 해결해줌?????
                }


            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

}



/*

package org.techtown.pickgame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BombActivity extends AppCompatActivity {
    Button button_bomb_back;
    Button button_bomb_random;
    TextView textView_bomb;
    TextView textView_bomb_random_text;
    CountDownTimer countDownTimer;

    private long randomTime_a;
    private long randomTime_b;
    private long randomTime_c;
    private long randomTime_d;
    private long randomTime_e;

    String[] bombTexts = {"수도이름", "아프리카나라이름", "우리나라도시", "여자가수노래", "남자가수노래", "축구선수이름", "한국역대대통령", "동물이름",
            "탈것이름", "과일이름", "하계스포츠종목", "농구선수이름", "동계스포츠종목", "SNS 종류", "위인이름", "아메리카나라이름", "아시아도시이름", "아시아나라이름",
            "직업이름", "의류브랜드이름", "대학교이름"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bomb);

        button_bomb_back = findViewById(R.id.button_bomb_back);
        button_bomb_random = findViewById(R.id.button_bomb_random);
        textView_bomb = findViewById(R.id.textView_bomb);
        textView_bomb_random_text = findViewById(R.id.textView_bomb_random_text);

        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        button_bomb_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomTime_a = (Long.parseLong(String.valueOf(0))*60000) + (Long.parseLong(String.valueOf(40))*1000);
                randomTime_b = (Long.parseLong(String.valueOf(0))*60000) + (Long.parseLong(String.valueOf(50))*1000);
                randomTime_c = (Long.parseLong(String.valueOf(1))*60000) + (Long.parseLong(String.valueOf(0))*1000);
                randomTime_d = (Long.parseLong(String.valueOf(1))*60000) + (Long.parseLong(String.valueOf(10))*1000);
                randomTime_e = (Long.parseLong(String.valueOf(1))*60000) + (Long.parseLong(String.valueOf(20))*1000);

                button_bomb_random.setEnabled(false);

                int random = (int) (Math.random()*bombTexts.length);
                textView_bomb_random_text .setText(bombTexts[random]);

                long[] randomTime = {randomTime_a, randomTime_b, randomTime_c, randomTime_d, randomTime_e};
                int randomTime_Num = (int) (Math.random()*randomTime.length);
                countDownTimer = new CountDownTimer(randomTime[randomTime_Num], 1000) {
                    @Override
                    public void onTick(long l) {
                        textView_bomb.setText((l / 1000)/60 + "분" + (l / 1000)%60 + "초");
                    }

                    @Override
                    public void onFinish() {
                        textView_bomb.setText("시간 종료");
                        button_bomb_random.setEnabled(true);
                        vibrator.vibrate(2500);
                    }
                };
                countDownTimer.start();
            }
        });

        button_bomb_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
    }
}

 */