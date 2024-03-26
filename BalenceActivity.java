package org.techtown.pickgame;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class BalenceActivity extends AppCompatActivity {
    private static final String TAG = "tag";

    Button button_balence_back;
    ImageView imageView_balloon;
    TextView textView_balloon_number;


    int[] balloon_num = {2,3,4,5,6,7,8,9,10,10,11,11,12,12,13,13,14,14,15,15,16,16,17,17,18,18,19,19,
            20,20,21,21,22,22,23,23,23,24,24,24,25,25,25,26,26,26,27,27,27,28,28,28,28,29,29,29,29,29,30,30,30,30,
            31,31,31,31,31,32,32,32,32,32,33,33,33,33,33,34,34,34,34,34,34,35,35,35,35,35,35,36,36,36,36,36,36,36,
            37,37,37,37,37,37,37,37,38,38,38,38,39,40};

    //언제 터질지 모르는 풍선을 터트리지 않고\n최대한 크게 부풀린 사람이승리하는 게임입니다. \n(단, 풍선이 터지면 즉시 패배) 이렇게 바꾸어도 좋을듯함
    int random = (int) (Math.random()*balloon_num.length);
    int clicknum_0 = 0;

    private InterstitialAd mInterstitialAd_balence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //전체화면으로 작업표시줄 없애기
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //가로화면 전환 방지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //다크모드 방지
        setContentView(R.layout.activity_balence);

        textView_balloon_number = findViewById(R.id.textView_balloon_number);
        imageView_balloon = findViewById(R.id.imageView_balloon);
        button_balence_back = findViewById(R.id.button_balence_back);

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
                        mInterstitialAd_balence = interstitialAd;
                        mInterstitialAd_balence.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdClicked() {
                            }
                            @Override
                            public void onAdImpression() {
                            }
                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                mInterstitialAd_balence = null;
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent();
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                mInterstitialAd_balence = null;
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd_balence = null;
                    }
                });


        imageView_balloon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicknum_0 = clicknum_0 + 1;

                if(clicknum_0 == 1){
                    imageView_balloon.setImageResource(R.drawable.balloon_1);
                }else if(clicknum_0 == 2){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_2);
                    }
                }else if(clicknum_0 == 3){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_3);
                    }
                }else if(clicknum_0 == 4){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_4);
                    }
                }else if(clicknum_0 == 5){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_5);
                    }
                }else if(clicknum_0 == 6){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_6);
                    }
                }else if(clicknum_0 == 7){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_7);
                    }
                }else if(clicknum_0 == 8){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_8);
                    }
                }else if(clicknum_0 == 9){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_9);
                    }
                }else if(clicknum_0 == 10){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_10);
                    }
                }else if(clicknum_0 == 11){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_11);
                    }
                }else if(clicknum_0 == 12){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_12);
                    }
                }else if(clicknum_0 == 13){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_13);
                    }
                }else if(clicknum_0 == 14){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_14);
                    }
                }else if(clicknum_0 == 15){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_15);
                    }
                }else if(clicknum_0 == 16){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_16);
                    }
                }else if(clicknum_0 == 17){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_17);
                    }
                }else if(clicknum_0 == 18){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_18);
                    }
                }else if(clicknum_0 == 19){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_19);
                    }
                }else if(clicknum_0 == 20){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_20);
                    }
                }else if(clicknum_0 == 21){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_21);
                    }
                }else if(clicknum_0 == 22){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_22);
                    }
                }else if(clicknum_0 == 23){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_23);
                    }
                }else if(clicknum_0 == 24){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_24);
                    }
                }else if(clicknum_0 == 25){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_25);
                    }
                }else if(clicknum_0 == 26){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_26);
                    }
                }else if(clicknum_0 == 27){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_27);
                    }
                }else if(clicknum_0 == 28){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_28);
                    }
                }else if(clicknum_0 == 29){
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_29);
                    }
                }else if(clicknum_0 == 30) {
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_30);
                    }
                }else if(clicknum_0 == 31) {
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_31);
                    }
                }else if(clicknum_0 == 32) {
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_32);
                    }
                }else if(clicknum_0 == 33) {
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_33);
                    }
                }else if(clicknum_0 == 34) {
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_34);
                    }
                }else if(clicknum_0 == 35) {
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_35);
                    }
                }else if(clicknum_0 == 36) {
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_36);
                    }
                }else if(clicknum_0 == 37) {
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_37);
                    }
                }else if(clicknum_0 == 38) {
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_38);
                    }
                }else if(clicknum_0 == 39) {
                    if (clicknum_0 == balloon_num[random]) {
                        imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_39);
                    }
                }else if(clicknum_0 == 40) {
                    if (clicknum_0 == balloon_num[random]) {
                            imageView_balloon.setImageResource(R.drawable.balloon_bomb);
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(2500);
                        view.setEnabled(false);
                    } else {
                        imageView_balloon.setImageResource(R.drawable.balloon_40);
                    }
                }




                textView_balloon_number.setText("현재 누른 횟수 : " + clicknum_0);
            }
        });


        button_balence_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("number_before",1);
                startActivity(intent);
                finish();
                if(mInterstitialAd_balence != null){
                    mInterstitialAd_balence.show(BalenceActivity.this);
                }else {
                    Log.d(TAG, "광고가 실행되지 않음");
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

}
