package com.tkt.spin_wheel.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.tkt.spin_wheel.base.BaseActivity;
import com.tkt.spin_wheel.dialog.exit.ExitAppDialog;
import com.tkt.spin_wheel.dialog.exit.IClickDialogExit;
import com.tkt.spin_wheel.dialog.rate.IClickDialogRate;
import com.tkt.spin_wheel.dialog.rate.RatingDialog;
import com.tkt.spin_wheel.ui.home.creation.CreationFragment;
import com.tkt.spin_wheel.ui.home.start.HomeFragment;
import com.tkt.spin_wheel.ui.setting.SettingActivity;
import com.tkt.spin_wheel.util.EventTracking;
import com.tkt.spin_wheel.util.SPUtils;
import com.tkt.spin_wheel.util.SharePrefUtils;
import com.tkt.spin_wheel.R;
import com.tkt.spin_wheel.databinding.ActivityHomeBinding;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
//import com.google.android.gms.tasks.Task;
//import com.google.android.play.core.review.ReviewInfo;
//import com.google.android.play.core.review.ReviewManager;
//import com.google.android.play.core.review.ReviewManagerFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeActivity extends BaseActivity<ActivityHomeBinding> {

    private static final int STATE_HOME = 1;
    private static final int STATE_CREATION = 2;
    int state = 1;

    ArrayList<String> exitRate = new ArrayList<String>(Arrays.asList("2", "4", "6", "8", "10"));


    @Override
    public ActivityHomeBinding getBinding() {
        return ActivityHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        binding.clView.setPadding(
                binding.clView.getPaddingLeft(),
                binding.clView.getPaddingTop() + getStatusBarHeight(),
                binding.clView.getPaddingRight(),
                binding.clView.getPaddingBottom()
        );
        EventTracking.logEvent(this, "home_view");
        changeState();
    }

    @Override
    public void bindView() {
        binding.ivSetting.setOnClickListener(view -> {
            startNextActivity(SettingActivity.class, null);
        });
        binding.llHome.setOnClickListener(view -> {
            state = STATE_HOME;
            changeState();
        });
        binding.llCreation.setOnClickListener(view -> {
            state = STATE_CREATION;
            changeState();
        });
    }

    private void changeState() {
        if (state == STATE_HOME) {
            binding.ivHome.setImageResource(R.drawable.buildings_s);
            binding.tvHome.setTextColor(Color.parseColor("#064380"));
            binding.ivCreation.setImageResource(R.drawable.filled_sn);
            binding.tvCreation.setTextColor(Color.parseColor("#73808C"));
            replaceFragment(new HomeFragment());
        } else {
            binding.ivHome.setImageResource(R.drawable.buildings_sn);
            binding.tvHome.setTextColor(Color.parseColor("#73808C"));
            binding.ivCreation.setImageResource(R.drawable.filled_s);
            binding.tvCreation.setTextColor(Color.parseColor("#064380"));
            replaceFragment(new CreationFragment());
        }
    }

    @Override
    public void onBack() {
        if (!SharePrefUtils.isRated(this)) {
            if (exitRate.contains(String.valueOf(SharePrefUtils.getCountOpenApp(this)))) {
                rateApp();
            } else {
                exitApp();
            }
        } else {
            exitApp();
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameContent, fragment)
                .commit();
    }

    public ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            //ads
            Log.d("activity_check", "home");
        }
    });

    private void rateApp() {
        RatingDialog ratingDialog = new RatingDialog(HomeActivity.this, true);
        ratingDialog.init(new IClickDialogRate() {
            @Override
            public void send() {
                //binding.rlRate.setVisibility(View.GONE);
                ratingDialog.dismiss();
                String uriText = "mailto:" + SharePrefUtils.email + "?subject=" + "Review for " + SharePrefUtils.subject + "&body=" + SharePrefUtils.subject + "\nRate : " + ratingDialog.getRating() + "\nContent: ";
                Uri uri = Uri.parse(uriText);
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(uri);
                try {
                    finishAffinity();
                    startActivity(Intent.createChooser(sendIntent, getString(R.string.Send_Email)));
                    SharePrefUtils.forceRated(HomeActivity.this);
                    int star = SPUtils.getInt(HomeActivity.this, SPUtils.RATE_STAR, 0);
                    EventTracking.logEvent(HomeActivity.this, "rate_submit", "rate_star" + star, String.valueOf(star));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(HomeActivity.this, getString(R.string.There_is_no), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void rate() {
                ReviewManager manager = ReviewManagerFactory.create(HomeActivity.this);
                Task<ReviewInfo> request = manager.requestReviewFlow();
                request.addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ReviewInfo reviewInfo = task.getResult();
                        Task<Void> flow = manager.launchReviewFlow(HomeActivity.this, reviewInfo);
                        flow.addOnSuccessListener(result -> {
                            //binding.rlRate.setVisibility(View.GONE);
                            int star = SPUtils.getInt(HomeActivity.this, SPUtils.RATE_STAR, 0);
                            EventTracking.logEvent(HomeActivity.this, "rate_submit", "rate_star" + star, String.valueOf(star));
                            SharePrefUtils.forceRated(HomeActivity.this);
                            ratingDialog.dismiss();
                            finishAffinity();
                        });
                    } else {
                        ratingDialog.dismiss();
                    }
                });
            }

            @Override
            public void later() {
                EventTracking.logEvent(HomeActivity.this, "rate_not_now");
                ratingDialog.dismiss();
                finishAffinity();
            }

        });
        ratingDialog.show();
        EventTracking.logEvent(this, "rate_show");
    }


    private void exitApp() {
        ExitAppDialog exitAppDialog = new ExitAppDialog(this, true);
        exitAppDialog.init(new IClickDialogExit() {
            @Override
            public void cancel() {
                exitAppDialog.dismiss();
            }

            @Override
            public void quit() {
                exitAppDialog.dismiss();
                finishAffinity();
            }
        });

        try {
            exitAppDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
