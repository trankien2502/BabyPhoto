package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.policy;

import android.annotation.SuppressLint;
import android.view.View;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ads.IsNetWork;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.R;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ActivityPolicyBinding;

public class PolicyActivity extends BaseActivity<ActivityPolicyBinding> {

    String linkPolicy = "";

    @Override
    public ActivityPolicyBinding getBinding() {
        return ActivityPolicyBinding.inflate(getLayoutInflater());
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initView() {
        binding.tvTitle.setText(getString(R.string.privacy_policy));

        if (!linkPolicy.isEmpty() && IsNetWork.haveNetworkConnection(this)) {
            binding.webView.setVisibility(View.VISIBLE);
            binding.lnNoInternet.setVisibility(View.GONE);

            binding.webView.getSettings().setJavaScriptEnabled(true);
            binding.webView.loadUrl(linkPolicy);
        } else {
            binding.webView.setVisibility(View.GONE);
            binding.lnNoInternet.setVisibility(View.VISIBLE);
        }

        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.loadUrl(linkPolicy);
    }

    @Override
    public void bindView() {
        binding.ivGone.setOnClickListener(v -> onBack());
    }

    @Override
    public void onBack() {
        finish();
    }

}
