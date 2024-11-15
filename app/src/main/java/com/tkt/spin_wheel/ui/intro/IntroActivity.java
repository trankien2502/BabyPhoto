package com.tkt.spin_wheel.ui.intro;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.tkt.spin_wheel.R;
import com.tkt.spin_wheel.base.BaseActivity;
import com.tkt.spin_wheel.databinding.ActivityIntroBinding;
import com.tkt.spin_wheel.ui.home.HomeActivity;
import com.tkt.spin_wheel.ui.permission.PermissionActivity;
import com.tkt.spin_wheel.util.EventTracking;
import com.tkt.spin_wheel.util.SharePrefUtils;


public class IntroActivity extends BaseActivity<ActivityIntroBinding> {
    ImageView[] dots = null;
    int[] listIntroTitle = null, listIntroContent = null;
    IntroAdapter introAdapter;

    @Override
    public ActivityIntroBinding getBinding() {
        return ActivityIntroBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        dots = new ImageView[]{binding.circle1, binding.circle2, binding.circle3};
        listIntroTitle = new int[]{R.string.intro_1, R.string.intro_2, R.string.intro_3};
        listIntroContent = new int[]{R.string.content_intro_1, R.string.content_intro_2, R.string.content_intro_3};
        introAdapter = new IntroAdapter(this);

        binding.viewPager2.setAdapter(introAdapter);

        binding.viewPager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                changeContentInit(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void bindView() {
        binding.clNext.setOnClickListener(view -> {
            switch (binding.viewPager2.getCurrentItem()) {
                case 0:
                    EventTracking.logEvent(this, "onboarding1_next_click");
                    break;
                case 1:
                    EventTracking.logEvent(this, "onboarding2_next_click");
                    break;
                case 2:
                    EventTracking.logEvent(this, "onboarding3_next_click");
                    break;

            }
            if (binding.viewPager2.getCurrentItem() < 2) {
                binding.viewPager2.setCurrentItem(binding.viewPager2.getCurrentItem() + 1);
            } else {
                startNextActivity();
            }
        });
        binding.btnNext2.setOnClickListener(v -> {
            EventTracking.logEvent(this, "onboarding3_next_click");
            startNextActivity();
        });

    }

    @Override
    public void onBack() {
        finishAffinity();
    }

    private void changeContentInit(int position) {
        binding.tvIntro.setText(listIntroTitle[position]);
        binding.tvIntroContent.setText(listIntroContent[position]);
        for (int i = 0; i < 3; i++) {
            if (i == position) {
                dots[i].setImageResource(R.drawable.ic_intro_s);

            } else dots[i].setImageResource(R.drawable.ic_intro_sn);
        }
        switch (position) {
            case 0:
                binding.clNext.setVisibility(View.VISIBLE);
                binding.btnNext2.setVisibility(View.GONE);
                EventTracking.logEvent(this, "Onboarding1_view");
                break;
            case 1:
                binding.clNext.setVisibility(View.VISIBLE);
                binding.btnNext2.setVisibility(View.GONE);
                EventTracking.logEvent(this, "Onboarding2_view");
                break;
            case 2:
                binding.clNext.setVisibility(View.GONE);
                binding.btnNext2.setVisibility(View.VISIBLE);
                EventTracking.logEvent(this, "Onboarding3_view");
                break;

        }
    }

    public void startNextActivity() {
        if (SharePrefUtils.getCountOpenApp(IntroActivity.this) == 1) {
            startNextActivity(PermissionActivity.class, null);
        } else {
            if (!checkStoragePermission()) {
                startNextActivity(PermissionActivity.class, null);
            } else
                startNextActivity(HomeActivity.class, null);
        }
    }

    private boolean checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        changeContentInit(binding.viewPager2.getCurrentItem());
    }
}