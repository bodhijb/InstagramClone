package com.jf2mc1.a015004vinstagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("r1cTNYKpcCpy8ZYLJKG85rwL9lPlDa0bhP6nEdxw")
                // if defined
                .clientKey("jWTU13MlYQaegGMF2ASBGjUuWY0UqNYwlRNp6XdK")
                .server("https://parseapi.back4app.com/")
                .build()
        );




    }
}
