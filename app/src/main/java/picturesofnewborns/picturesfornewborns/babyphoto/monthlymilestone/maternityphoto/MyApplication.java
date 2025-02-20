package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto;

import android.app.Application;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.SharePrefUtils;


public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        SharePrefUtils.init(this);

    }

}

