package mobile.android.mentawaitour.other;

import android.app.Application;

import com.doku.sdkocov2.DirectSDK;
import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.Realm;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        Realm.init(this);
    }
}
