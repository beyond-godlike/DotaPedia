package com.unava.dia.dotapedia.network.di;

import com.unava.dia.dotapedia.ui.activity.PlayerInformation;

import dagger.Component;

@Component(modules = {NetModule.class})
public interface NetComponent {
    void inject(PlayerInformation playerInformation);

}
