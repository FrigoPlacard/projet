package com.example.ensai.frigomalin;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by ensai on 30/05/17.
 */

public class MonApplication extends Application {

    private static Application MONAPPlICATION;

    public void onCreate() {
        super.onCreate();
        MONAPPlICATION = this;
        //MonApplication.MONAPPlICATION=getAppContext();
        Stetho.initializeWithDefaults(this);
    }

    public static Application getAppContext(){
        return MonApplication.MONAPPlICATION;
    }
}
