package org.techtown.pickgame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class CardActivity extends AppCompatActivity {
    private static final String TAG = "tag";
    Button button_card_back;
    CardView card_card;
    TextView textView_card;
    TextView textView_warning;

    String[] cardTexts = {"옆 사람이 나에게 벌칙 주기", "왼쪽, 오른쪽 중 한명에게 벌칙 주기", "내가 싫어하는 사람 벌칙 주기",
            "내가 좋아하는 사람 벌칙 주기", "나빼고 모두 벌칙 주기", "나랑 눈마주치는 사람 벌칙 주기", "내가 좋아하는 사람과 벌칙 받기", "청바지 입은 사람 벌칙 주기",
            "주량 가장 강한 사람 벌칙 주기", "갤럭시폰인 사람 벌칙 주기", "아이폰인 사람 벌칙 주기", "연애중인 사람 벌칙 주기", "생일 가장 빠른 사람 벌칙 주기",
            "최근에 화장실 다녀온 사람 벌칙 주기", "내가 물구나무 서기 혹은 옆 사람에게 벌칙 받기", "옆 사람과 서로 벌칙 주기", "내가 나에게 주는 벌칙",
            "이전 사람 벌칙과 똑같은 벌칙 받기", "가장 최근에 연락한 사람과의 연락 공개하기 또는 옆 사람에게 벌칙 받기", "무반주 10초 댄스 혹은 옆 사람에게 벌칙 받기", "계산하던지 양 옆 사람에게 벌칙 받기",
            "간식 사오기 혹은 옆 사람에게 벌칙 받기", "남자 모두 벌칙 주기", "여자 모두 벌칙 주기", "성이 김씨인 사람 벌칙 주기", "성이 박씨인 사람 벌칙 주기",
            "성이 이씨인 사람 벌칙 주기", "가장 최근 키스 말하기 혹은 옆 사람에게 벌칙 받기", "무반주 5초 댄스 혹은 옆 사람에게 벌칙 받기", "다음 벌칙 면제 받기", "옆 사람과 수위 높은 벌칙 하기 \n\n (다른 사람들이 인정해야함)",
            "다음 사람에게 카드 넘기기", "검은색 상의 입은 사람 벌칙 주기", "흰색 상의 입은 사람 벌칙 주기", "나이 가장 많은 사람 벌칙 주기",
            "나이 가장 적은 사람 벌칙 주기", "염색한 사람 벌칙 주기", "왼쪽 사람이 주는 벌칙 내가 받기", "오른쪽 사람이 주는 벌칙 내가 받기", "가장 최신폰인 사람 벌칙 주기",
            "잘생긴 사람 벌칙 주기", "예쁜 사람 벌칙 주기", "SNS에 지금 찍은 셀카 올리기 혹은 옆 사람에게 벌칙 받기", "내가 다음에 걸린 벌칙 다른 사람에게 양도하기",
            "아무에게 벌칙 받기", "청바지 입은 사람 벌칙 주기", "검은색 하의 입은 사람 벌칙 주기", "원하는 사람에게 카드 넘기기",
            "옆 사람이 낸 퀴즈 못 맞추면 벌칙 받기", "성이 최씨인 사람 벌칙 주기", "성이 정씨인 사람 벌칙 주기", "성이 강씨인 사람 벌칙 주기", "무반주 10초 노래하기 혹은 옆 사람에게 벌칙 받기",
            "옆 사람과 가위바위보해서 진 사람에게 벌칙 주기", "옆 사람과 묵찌빠해서 이긴 사람 벌칙 주기", "옆 사람에게 딱밤 맞거나 벌칙 받기", "아무나 벌칙 주기",
            "다음 벌칙 면제 받기", "5초 동안 애교하기 혹은 옆 사람에게 벌칙 받기", "옆 사람에게 벌칙 받고 또 뽑기",
            "지목 2명 벌칙 주기", "지금 넘어간 후 다음 사람 벌칙 대신 받기", "10초 안에 눈물 흘리지 않으면 옆 사람에게 벌칙 받기", "옆 사람 생일(월) 맞추지 못하면 벌칙 받기", "옆 사람과 뽀뽀하기 혹은 옆 사람에게 벌칙 받기",
            "생일 가장 빠른 사람 벌칙 주기", "생일 가장 느린 사람 벌칙 주기", "열사람 벌칙 주고 내가 또 카드 뽑기"};


    /*
    {"옆 사람이 나에게 벌칙 주기", "왼쪽, 오른쪽 중 한명에게 벌칙 주기", "내가 싫어하는 사람 벌칙 주기",
            "내가 좋아하는 사람 벌칙 주기", "나빼고 모두 벌칙 주기", "나랑 눈마주치는 사람 벌칙 주기", "내가 좋아하는 사람과 벌칙 받기", "청바지 입은 사람 벌칙 주기",
            "주량 가장 강한 사람 벌칙 주기", "갤럭시폰인 사람 벌칙 주기", "아이폰인 사람 벌칙 주기", "연애중인 사람 벌칙 주기", "생일 가장 빠른 사람 벌칙 주기",
            "최근에 화장실 다녀온 사람 벌칙 주기", "내가 물구나무 서기 혹은 옆 사람에게 벌칙 받기", "옆 사람과 서로 벌칙 주기", "내가 나에게 주는 벌칙",
            "이전 사람 벌칙과 똑같은 벌칙 받기", "가장 최근에 연락한 사람과의 연락 공개하기 또는 옆 사람에게 벌칙 받기", "무반주 10초 댄스 혹은 옆 사람에게 벌칙 받기", "계산하던지 양 옆 사람에게 벌칙 받기",
            "간식 사오기 혹은 옆 사람에게 벌칙 받기", "남자 모두 벌칙 주기", "여자 모두 벌칙 주기", "성이 김씨인 사람 벌칙 주기", "성이 박씨인 사람 벌칙 주기",
            "성이 이씨인 사람 벌칙 주기", "가장 최근 키스 말하기 혹은 옆 사람에게 벌칙 받기", "무반주 5초 댄스 혹은 옆 사람에게 벌칙 받기", "물 한 잔 마시기", "옆 사람과 수위 높은 벌칙 하기 \n\n (다른 사람들이 인정해야함)",
            "다음 사람에게 카드 넘기기", "검은색 상의 입은 사람 벌칙 주기", "흰색 상의 입은 사람 벌칙 주기", "나이 가장 많은 사람 벌칙 주기",
            "나이 가장 적은 사람 벌칙 주기", "염색한 사람 벌칙 주기", "왼쪽 사람이 주는 벌칙 내가 받기", "오른쪽 사람이 주는 벌칙 내가 받기", "가장 최신폰인 사람 벌칙 주기",
            "잘생긴 사람 벌칙 주기", "예쁜 사람 벌칙 주기", "SNS에 지금 찍은 셀카 올리기 혹은 옆 사람에게 벌칙 받기", "내가 다음에 걸린 벌칙 다른 사람에게 양도하기",
            "물 두 잔 마시기", "청바지 입은 사람 벌칙 주기", "검은색 하의 입은 사람 벌칙 주기", "원하는 사람에게 카드 넘기기",
            "옆 사람이 낸 퀴즈 못 맞추면 벌칙 받기", "성이 최씨인 사람 벌칙 주기", "성이 정씨인 사람 벌칙 주기", "성이 강씨인 사람 벌칙 주기", "무반주 10초 노래하기 혹은 옆 사람에게 벌칙 받기",
            "옆 사람과 가위바위보해서 진 사람에게 벌칙 주기", "옆 사람과 묵찌빠해서 이긴 사람 벌칙 주기", "옆 사람에게 딱밤 맞거나 벌칙 받기", "물 세 잔 마시기",
            "물 한 잔 마시기", "5초 동안 애교하기 혹은 옆 사람에게 벌칙 받기", "옆 사람에게 벌칙 받고 또 뽑기",
            "지목 2명 벌칙 주기", "지금 넘어간 후 다음 사람 벌칙 대신 받기", "10초 안에 눈물 흘리지 않으면 옆 사람에게 벌칙 받기", "물 한 잔 마시기", "옆 사람과 뽀뽀하기 혹은 옆 사람에게 벌칙 받기",
            "생일 가장 빠른 사람 벌칙 주기", "생일 가장 느린 사람 벌칙 주기", "열사람 벌칙 주고 내가 또 카드 뽑기"};
     */
    // 질문 리스트 작성

    int a = 0; //카드가 뒤집히지 않은 상태
    int card_count = 0; //현재 카드를 뒤집은 개수

    private InterstitialAd mInterstitialAd_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //전체화면으로 작업표시줄 없애기
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //가로화면 전환 방지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //다크모드 방지
        setContentView(R.layout.activity_card);

        button_card_back = findViewById(R.id.button_card_back);
        card_card = findViewById(R.id.card_card);
        textView_card = findViewById(R.id.textView_card);
        textView_warning = findViewById(R.id.textView_warning);

        Handler handler = new Handler();


        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        final float distance = card_card.getCameraDistance() * (scale + (scale / 3));

        AdRequest adRequest = new AdRequest.Builder().build();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        //나의 전면 배너 광고 ID ca-app-pub-7754099565688705/1682092309
        //개발자 공용 배너 ID ca-app-pub-3940256099942544/1033173712
        InterstitialAd.load(this, "ca-app-pub-7754099565688705/1682092309", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd_card = interstitialAd;
                        mInterstitialAd_card.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdClicked() {
                            }
                            @Override
                            public void onAdImpression() {
                            }
                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                mInterstitialAd_card = null;
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent();
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                mInterstitialAd_card = null;
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd_card = null;
                    }
                });

        button_card_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("number_before",1);
                startActivity(intent);
                finish();
                if (mInterstitialAd_card != null) {
                    mInterstitialAd_card.show(CardActivity.this);

                }else {
                    Log.d(TAG, "광고가 실행되지 않음.");
                }

            }
        });

        card_card.setBackground(getResources().getDrawable(R.drawable.card_corner_filled4));
        //card_corner_filled4로 카드 안뒤집어진 상태 이미지
        card_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "카드 눌림");
                Log.d(TAG, "현재 a 값 : " + a);
                card_card.setCameraDistance(distance);
                card_card.setEnabled(false);
                card_card.animate().withLayer()
                        .rotationY(90)
                        .setDuration(150)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                if(a == 0){
                                    card_card.setBackground(getResources().getDrawable(R.drawable.card_corner_unfilled));
                                    int random = (int) (Math.random()*cardTexts.length);
                                    textView_card.setText(""+cardTexts[random]);
                                    textView_warning.setText("한번 더 눌러 넘겨주세요.");
                                    card_count ++;
                                    a = 1;
                                    Log.d(TAG, "현재 question_count : "+card_count);

                                }else if(a == 1){
                                    card_card.setBackground(getResources().getDrawable(R.drawable.card_corner_filled4));
                                    int random = (int) (Math.random()*cardTexts.length);
                                    textView_card.setText("");
                                    textView_warning.setText("");
                                    a = 0;
                                    card_count++;
                                    Log.d(TAG, "현재 question_count : "+card_count);

                                }
                                card_card.setRotationY(-90);
                                card_card.animate().withLayer()
                                        .rotationY(0)
                                        .setDuration(250)
                                        .start();


                            }
                        }).start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        card_card.setEnabled(true);
                    }
                },500);

            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

}
