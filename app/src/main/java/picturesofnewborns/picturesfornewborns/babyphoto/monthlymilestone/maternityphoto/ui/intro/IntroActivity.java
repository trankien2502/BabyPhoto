package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.intro;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.viewpager2.widget.ViewPager2;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.R;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ads.IsNetWork;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ActivityIntroBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.permission.PermissionActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.EventTracking;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends BaseActivity<ActivityIntroBinding> {

    ImageView[] dots = null;
    int positionPage = 0;
    String[] title;
    private boolean isStartActivity = true;
    List<Integer> listImage;
    SlideAdapter adapter;
    int height;

    @Override
    public ActivityIntroBinding getBinding() {
        return ActivityIntroBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        EventTracking.logEvent(IntroActivity.this, "Intro1_view");
        listImage = new ArrayList<>();
        binding.circle4.setVisibility(View.GONE);
        listImage.add(R.drawable.img_intro_1);
        listImage.add(R.drawable.img_intro_2);
        listImage.add(R.drawable.img_intro_3);
        dots = new ImageView[]{findViewById(R.id.circle1), findViewById(R.id.circle2), findViewById(R.id.circle3)};
        title = new String[]{getResources().getString(R.string.intro_1), getResources().getString(R.string.intro_2), getResources().getString(R.string.intro_3)};
        binding.viewHeight.post(() -> {
            height = binding.viewHeight.getHeight();
            Log.d("intro_check", "Chiều cao của viewHeight: " + height);
            adapter = new SlideAdapter(this, this, listImage, false
                    , height, () -> {
                binding.viewPager.setCurrentItem(3);
            });
            binding.viewPager.setAdapter(adapter);
            binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    positionPage = position;
                    if (!IsNetWork.haveNetworkConnection(IntroActivity.this)) {
                        if (IntroActivity.this.listImage.size() > 3) {
                            listImage.remove(3);
                            adapter.setList(listImage);
                        }
                    }
                }

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    changeContentInit(position);
                    if (position == 0) {
                        EventTracking.logEvent(IntroActivity.this, "Intro1_view");
                    } else if (position == 1) {
                        EventTracking.logEvent(IntroActivity.this, "Intro2_view");
                    } else {
                        EventTracking.logEvent(IntroActivity.this, "Intro3_view");
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    super.onPageScrollStateChanged(state);
                }
            });
        });


    }

    @Override
    public void bindView() {
        binding.btnNext2.setOnClickListener(v -> {
            if (binding.viewPager.getCurrentItem() == 0) {
                EventTracking.logEvent(IntroActivity.this, "Intro1_next_click");
                binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
            } else if (binding.viewPager.getCurrentItem() == 1) {
                EventTracking.logEvent(IntroActivity.this, "Intro2_next_click");
                binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
            } else {
                EventTracking.logEvent(IntroActivity.this, "Intro3_next_click");
                if (isStartActivity) {
                    isStartActivity = false;
                    goToHome();
                }
            }
        });
    }

    @Override
    public void onBack() {
        finishAffinity();
    }

    public void goToHome() {
        startNextActivity(PermissionActivity.class, null);
//        if (SharePrefUtils.getCountOpenApp(IntroActivity.this) == 1) {
//            startNextActivity(PermissionActivity.class, null);
//        } else {
//            if (!PermissionManager.checkNotificationPermission(this) || !PermissionManager.checkCameraPermission(this) || !PermissionManager.checkStoragePermissionSplash(this)) {
//                startNextActivity(PermissionActivity.class, null);
//            } else {
//                if (ConstantRemote.show_permission.contains(String.valueOf(SharePrefUtils.getCountOpenApp(IntroActivity.this))))
//                    startNextActivity(PermissionActivity.class, null);
//                else startNextActivity(HomeActivity.class, null);
//            }
//        }
        finish();
    }

    private void changeContentInit(int position) {
        binding.tvIntro.setText(title[position]);
        if (binding.circle4.getVisibility() == View.VISIBLE) {
            binding.circle4.setVisibility(View.GONE);
        }
        for (int i = 0; i < 3; i++) {
            if (i == position) {
                dots[i].setImageResource(R.drawable.ic_intro_s);
            } else dots[i].setImageResource(R.drawable.ic_intro_sn);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        changeContentInit(binding.viewPager.getCurrentItem());
    }


}
