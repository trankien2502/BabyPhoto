package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.setting;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.BuildConfig;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.R;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ActivityAboutBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.policy.PolicyActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.EventTracking;

public class AboutActivity extends BaseActivity<ActivityAboutBinding> {

    @Override
    public ActivityAboutBinding getBinding() {
        return ActivityAboutBinding.inflate(getLayoutInflater());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initView() {
        EventTracking.logEvent(AboutActivity.this, "view_about_app");
        String text = getString(R.string.privacy_policy);


        SpannableString spannableString = new SpannableString(text);

        spannableString.setSpan(new UnderlineSpan(), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        int color = Color.parseColor("#0256FF");
        spannableString.setSpan(new ForegroundColorSpan(color), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.privacy.setText(spannableString);
        binding.ver.setText(getString(R.string.version) + " " + BuildConfig.VERSION_NAME);
    }

    @Override
    public void bindView() {
        binding.privacy.setOnClickListener(view -> {
            startNextActivity(PolicyActivity.class, null);
        });
        binding.ivGone.setOnClickListener(view -> onBack());
    }

    @Override
    public void onBack() {
        finish();
    }
}