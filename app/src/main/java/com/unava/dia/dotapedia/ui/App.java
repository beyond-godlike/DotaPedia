package com.unava.dia.dotapedia.ui;

import android.app.Application;

import com.unava.dia.dotapedia.network.di.DaggerNetComponent;
import com.unava.dia.dotapedia.network.di.NetComponent;
import com.unava.dia.dotapedia.network.di.NetModule;

public class App extends Application {
    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // after building
        netComponent = DaggerNetComponent
                .builder()
                .netModule(new NetModule())
                .build();


    }

    public NetComponent getNetComponent() {
        return netComponent;
    }
}
