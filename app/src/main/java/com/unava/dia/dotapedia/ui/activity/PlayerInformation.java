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
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import com.unava.dia.dotapedia.network.APIClient;
import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.network.service.AccInformation;
import com.unava.dia.dotapedia.utils.GlideUtil;

import butterknife.ButterKnife;

public class PlayerInformation extends AppCompatActivity {
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

        accInfo = new AccInformation();
    }

    public void onSearchPlayerClick(View view) {
        playerId = editTextPlayerId.getText().toString();
        getAccountInfo(playerId);
    }

    private void getAccountInfo(String playerId) {
        APIClient.getInstance()
                .getInstance()
                .getAccountInfo(playerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AccInformation>() {
                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d("ddd", "In onError()");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("ddd", "In onCompleted()");

                        // пихаем инфу в вью
                        textViewSoloMmr.setText(accInfo.soloCompetitiveRank);
                        textViewPartyMmr.setText(accInfo.competitiveRank);
                        textViewEstimatedMmr.setText(accInfo.mmrEstimate.estimate.toString());
                        avatarUrl = accInfo.profile.avatarfull;

                        GlideUtil.setPlayerIcon(playerIcon, avatarUrl);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override public void onNext(AccInformation accInformation) {
                        Log.d("ddd", "In onNext()");
                        accInfo = accInformation;
                        Log.d("ddd", accInformation.mmrEstimate.estimate.toString());
                    }
                });
    }
}
