package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.splash;

import android.os.Handler;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ads.IsNetWork;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.api_data.CallApiUtils;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.api_data.ConstantApiData;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.language.LanguageStartActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.SPUtils;
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
        if (IsNetWork.haveNetworkConnection(this)) {
            if (!SPUtils.getBoolean(this, SPUtils.CALL_API_SUCCESS, false) || (SPUtils.getBoolean(this, SPUtils.CALL_API_SUCCESS, false) && System.currentTimeMillis() - SPUtils.getLong(this, SPUtils.CALL_API_TIME_DONE, System.currentTimeMillis()) > ConstantApiData.check_api * 86400000L)) {
                CallApiUtils.callApi(this);
            }
        }
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
