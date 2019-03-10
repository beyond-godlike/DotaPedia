package com.unava.dia.dotapedia.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


import com.unava.dia.dotapedia.network.APIInterface;
import com.unava.dia.dotapedia.network.service.AccInformation;
import com.unava.dia.dotapedia.ui.App;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.network.service.AccInformation;
import com.unava.dia.dotapedia.network.service.ErrorResponse;
import com.unava.dia.dotapedia.utils.GlideUtil;
import com.unava.dia.dotapedia.utils.ToastUtil;
import com.unava.dia.dotapedia.utils.Utils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import retrofit2.HttpException;
import retrofit2.Retrofit;

public class PlayerInformation extends AppCompatActivity {

    @Inject
    Retrofit retrofit;


    AccInformation accInfo;
    String playerId = "235632094"; // default
    String avatarUrl;

    @BindView(R.id.textViewSoloMmr)
    TextView textViewSoloMmr;
    @BindView(R.id.textViewPartyMmr)
    TextView textViewPartyMmr;
    @BindView(R.id.textViewEstimatedMmr)
    TextView textViewEstimatedMmr;
    @BindView(R.id.buttonSearchPlayer)
    Button buttonSearchPlayer;
    @BindView(R.id.matchId)
    EditText editTextPlayerId;
    @BindView(R.id.playerIcon)
    ImageView playerIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_information);
        ButterKnife.bind(this);

        ((App) getApplication()).getNetComponent().inject(this);

        accInfo = new AccInformation();
    }

    public void onSearchPlayerClick(View view) {
        playerId = editTextPlayerId.getText().toString();
        getAccountInfo(playerId);
    }

    private void getAccountInfo(String playerId) {
        Observable<AccInformation> call = retrofit.create(APIInterface.class).getAccountInformation(playerId);
        Observer<AccInformation> observer = new Observer<AccInformation>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AccInformation accInformation) {
                Log.d("ddd", "In onNext()");
                accInfo = accInformation;
            }

            @Override
            public void onError(Throwable e) {
                ErrorResponse error = Utils.parseError(e);
                //Send this error object back to UI for display.
                textViewSoloMmr.setText(error.soloCompetitiveRank);
                textViewPartyMmr.setText(error.competitiveRank);
                textViewEstimatedMmr.setText("null");
            }

            @Override
            public void onComplete() {
                Log.d("ddd", "In onCompleted()");
                // пихаем инфу в вью
                try {
                    textViewSoloMmr.setText(accInfo.soloCompetitiveRank);
                    textViewPartyMmr.setText(accInfo.competitiveRank);
                    textViewEstimatedMmr.setText(accInfo.mmrEstimate.estimate.toString());

                    avatarUrl = accInfo.profile.avatarfull;
                    GlideUtil.setPlayerIcon(playerIcon, avatarUrl);
                }
                catch (Exception e) {
                    textViewSoloMmr.setText("null");
                    textViewPartyMmr.setText("null");
                    textViewEstimatedMmr.setText("null");

                    playerIcon.setImageResource(0);
                    ToastUtil.showToast(getApplicationContext(), "Player not found!");
                }
            }
        };
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    /*
    private void getAccountInfo(String playerId) {
        APIClient.getInstance()
                .getAccountInfo(playerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AccInformation>() {
                    @Override public void onError(Throwable e) {
                        ErrorResponse error = Utils.parseError(e);
                        //Send this error object back to UI for display.
                        textViewSoloMmr.setText(error.soloCompetitiveRank);
                        textViewPartyMmr.setText(error.competitiveRank);
                        textViewEstimatedMmr.setText("null");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("ddd", "In onCompleted()");
                        // пихаем инфу в вью
                        try {
                            textViewSoloMmr.setText(accInfo.soloCompetitiveRank);
                            textViewPartyMmr.setText(accInfo.competitiveRank);
                            textViewEstimatedMmr.setText(accInfo.mmrEstimate.estimate.toString());

                            avatarUrl = accInfo.profile.avatarfull;
                            GlideUtil.setPlayerIcon(playerIcon, avatarUrl);
                        }
                        catch (Exception e) {
                            textViewSoloMmr.setText("null");
                            textViewPartyMmr.setText("null");
                            textViewEstimatedMmr.setText("null");

                           playerIcon.setImageResource(0);
                           ToastUtil.showToast(getApplicationContext(), "Player not found!");
                        }
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override public void onNext(AccInformation accInformation) {
                        Log.d("ddd", "In onNext()");
                        accInfo = accInformation;
                    }
                });
    }
    */
}
