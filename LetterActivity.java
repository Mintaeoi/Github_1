package org.techtown.pickgame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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

public class LetterActivity extends AppCompatActivity {
    private static final String TAG = "tag";

    Button button_letter_back;
    Button button_letter_random;
    TextView textView_letter1;
    TextView textView_letter2;

    String[] letterTexts1 = {"ㄱ","ㄴ","ㄷ","ㄹ","ㅁ","ㅂ","ㅅ","ㅇ","ㅈ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"};
    String[] letterTexts2 = {"ㄱ","ㄴ","ㄷ","ㄹ","ㅁ","ㅂ","ㅅ","ㅇ","ㅈ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"};

    private InterstitialAd mInterstitialAd_letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //전체화면으로 작업표시줄 없애기
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //가로화면 전환 방지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //다크모드 방지
        setContentView(R.layout.activity_letter);

        button_letter_back = findViewById(R.id.button_letter_back);
        button_letter_random = findViewById(R.id.button_letter_random);
        textView_letter1 = findViewById(R.id.textView_letter1);
        textView_letter2 = findViewById(R.id.textView_letter2);

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
                        mInterstitialAd_letter = interstitialAd;
                        mInterstitialAd_letter.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdClicked() {
                            }
                            @Override
                            public void onAdImpression() {
                            }
                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                mInterstitialAd_letter = null;
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent();
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                mInterstitialAd_letter = null;
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd_letter = null;
                    }
                });


        button_letter_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int random1 = (int) (Math.random()*letterTexts1.length);
                textView_letter1 .setText(letterTexts1[random1]);

                int random2 = (int) (Math.random()*letterTexts2.length);
                textView_letter2 .setText(letterTexts2[random2]);


            }
        });

        button_letter_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("number_before",1);
                startActivity(intent);
                finish();
                if(mInterstitialAd_letter != null){
                    mInterstitialAd_letter.show(LetterActivity.this);

                }else {
                    Log.d(TAG, "광고가 실행되지 않음.");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

}
