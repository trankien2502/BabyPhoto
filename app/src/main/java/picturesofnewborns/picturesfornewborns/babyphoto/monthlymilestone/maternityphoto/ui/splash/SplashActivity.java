package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.splash;

import android.os.Handler;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.language.LanguageStartActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.SharePrefUtils;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ActivitySplashBinding;


public class SplashActivity extends BaseActivity<ActivitySplashBinding> {


    @Override
    public ActivitySplashBinding getBinding() {
        return ActivitySplashBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        SharePrefUtils.increaseCountOpenApp(this);
        new Handler().postDelayed(() -> {
            startNextActivity(LanguageStartActivity.class, null);
            finishAffinity();
        }, 3000);

    }

    @Override
    public void bindView() {

    }

    @Override
    public void onBack() {

    }
}
