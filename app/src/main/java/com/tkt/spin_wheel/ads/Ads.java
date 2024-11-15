//package voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.ads;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Handler;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.recyclerview.widget.GridLayoutManager;
//
//import com.ads.sapp.admob.Admob;
//import com.ads.sapp.ads.CommonAd;
//import com.ads.sapp.ads.CommonAdCallback;
//import com.ads.sapp.funtion.AdCallback;
//import com.ads.sapp.util.CheckAds;
//import com.google.android.gms.ads.LoadAdError;
//import com.google.android.gms.ads.nativead.NativeAd;
//import com.google.android.gms.ads.nativead.NativeAdView;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.R;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.ads.ConstantIdAds;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.ads.ConstantRemote;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.ads.IsNetWork;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.base.BaseActivity;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.databinding.ActivityLanguageStartBinding;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.ui.home.HomeActivity;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.ui.home.lock_type.pattern_lock.SetPatternLockActivity;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.ui.home.lock_type.pin_lock.SetPinLockActivity;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.ui.home.lock_type.voice_lock.SetVoiceLockActivity;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.ui.intro.IntroActivity;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.ui.intro.WelcomeActivity;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.ui.language.adapter.LanguageStartAdapter;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.ui.language.model.LanguageModel;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.ui.permission.PermissionActivity;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.util.EventTracking;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.util.PermissionManager;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.util.SPUtils;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.util.SharePrefUtils;
//import voicescreenlock.unlockphonewithvoice.voicelock.screenfingerlock.patternpassword.util.SystemUtil;
//
//public class Ads extends BaseActivity<ActivityLanguageStartBinding> {
//
//    List<LanguageModel> listLanguage;
//    String codeLang;
//    String nameLang;
//
//    @Override
//    public ActivityLanguageStartBinding getBinding() {
//        return ActivityLanguageStartBinding.inflate(getLayoutInflater());
//    }
//
//    @Override
//    public void initView() {
//        loadNativeLanguageAds();
//        loadNativePermissionAds();
//
//        loadInterPermission();
//        showInterPermission();
//
//        loadNativeIntro();
//        loadNativeFullScreenIntro();
//
//        loadInterIntro();
//        showInterIntro();
//
//        loadBanner();
//        loadBannerCollapsibleHome();
//
//        loadInterAll();
//        showInterHome(1,true);
//    }
//
//    @Override
//    public void bindView() {
//
//    }
//
//
//
//    @Override
//    public void onBack() {
//
//    }
//    private void loadBannerCollapsibleHome() {
////        if (IsNetWork.haveNetworkConnectionUMP(this) && ConstantIdAds.listIDAdsBannerCollapsible.size() != 0 && ConstantRemote.banner_collapsible_home) {
////            new Thread(() -> {
////                runOnUiThread(() -> {
////                    binding.rlBanner.setVisibility(View.VISIBLE);
////                    binding.rlBanner.removeAllViews();
////                    RelativeLayout layout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.layout_banner_control, null, false);
////                    binding.rlBanner.addView(layout);
////                    CommonAd.getInstance().loadCollapsibleBannerFloor(this, ConstantIdAds.listIDAdsBannerCollapsible, "bottom");
////                });
////            }).start();
////        } else {
////            binding.rlBanner.setVisibility(View.GONE);
////        }
//    }
//    public void loadNativeLanguageAds() {
//        try {
//            if (IsNetWork.haveNetworkConnectionUMP(this) && ConstantIdAds.listIDAdsNativeLanguage.size() != 0 && ConstantRemote.native_language) {
//                @SuppressLint("InflateParams") NativeAdView adViewLoad = (NativeAdView) LayoutInflater.from(this).inflate(R.layout.layout_native_load_large, null);
//                binding.nativeLanguage.removeAllViews();
//                binding.nativeLanguage.addView(adViewLoad);
//                binding.nativeLanguage.setVisibility(View.VISIBLE);
//                new Thread(()->{
//                    Admob.getInstance().loadNativeAd(this, ConstantIdAds.listIDAdsNativeLanguage, new AdCallback() {
//                        @Override
//                        public void onUnifiedNativeAdLoaded(@NonNull NativeAd unifiedNativeAd) {
//                            runOnUiThread(()->{
//                                @SuppressLint("InflateParams") NativeAdView adView = (NativeAdView) LayoutInflater.from(getBaseContext()).inflate(R.layout.layout_native_show_large, null);
//                                binding.nativeLanguage.removeAllViews();
//                                binding.nativeLanguage.addView(adView);
//                                Admob.getInstance().populateUnifiedNativeAdView(unifiedNativeAd, adView);
//                            });
//                        }
//
//                        @Override
//                        public void onAdFailedToLoad(@Nullable LoadAdError i) {
//                            runOnUiThread(()->{
//                                binding.nativeLanguage.setVisibility(View.GONE);
//                            });
//
//                        }
//                    });
//                }).start();
//            } else {
//                binding.nativeLanguage.setVisibility(View.GONE);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            binding.nativeLanguage.setVisibility(View.GONE);
//        }
//    }
//    public void showInterPermission() {
////        if (IsNetWork.haveNetworkConnection(this) && !ConstantIdAds.listIDAdsInterPermission.isEmpty() && ConstantRemote.inter_permission && CheckAds.getInstance().isShowAds(this)) {
////            if (System.currentTimeMillis() - ConstantRemote.interval_interstitial_from_start_old > ConstantRemote.interval_interstitial_from_start * 1000) {
////                if (System.currentTimeMillis() - ConstantRemote.time_interval_old > ConstantRemote.interval_between_interstitial * 1000) {
////                    try {
////                        if (ConstantIdAds.mInterPermission != null) {
////                            CommonAd.getInstance().forceShowInterstitialByTime(this, ConstantIdAds.mInterPermission, new CommonAdCallback() {
////                                @Override
////                                public void onAdClosed() {
////                                    super.onAdClosed();
////                                    startNextActivity();
////                                }
////
////                                @Override
////                                public void onAdClosedByTime() {
////                                    super.onAdClosedByTime();
////                                    ConstantRemote.time_interval_old = System.currentTimeMillis();
////                                    ConstantIdAds.mInterPermission = null;
////                                    loadInterPermission();
////                                }
////                            }, true);
////                        } else {
////                            startNextActivity();
////                            ConstantIdAds.mInterPermission = null;
////                            loadInterPermission();
////                        }
////                    } catch (Exception e) {
////                        startNextActivity();
////                    }
////                } else {
////                    startNextActivity();
////                }
////            } else {
////                startNextActivity();
////            }
////        } else {
////            startNextActivity();
////        }
//    }
//
//    public void loadNativePermissionAds() {
////        try {
////            if (IsNetWork.haveNetworkConnection(this) && ConstantIdAds.listIDAdsNativePermission.size() != 0 && ConstantRemote.native_permission && CheckAds.getInstance().isShowAds(this)) {
////                @SuppressLint("InflateParams") NativeAdView adViewLoad = (NativeAdView) LayoutInflater.from(PermissionActivity.this).inflate(R.layout.layout_native_load_large, null);
////                binding.nativePermission.removeAllViews();
////                binding.nativePermission.addView(adViewLoad);
////                binding.nativePermission.setVisibility(View.VISIBLE);
////                new Thread(() -> {
////                    Admob.getInstance().loadNativeAd(this, ConstantIdAds.listIDAdsNativePermission, new AdCallback() {
////                        @Override
////                        public void onUnifiedNativeAdLoaded(@NonNull NativeAd unifiedNativeAd) {
////                            runOnUiThread(() -> {
////                                @SuppressLint("InflateParams") NativeAdView adView = (NativeAdView) LayoutInflater.from(PermissionActivity.this).inflate(R.layout.layout_native_show_large, null);
////                                binding.nativePermission.removeAllViews();
////                                binding.nativePermission.addView(adView);
////                                Admob.getInstance().populateUnifiedNativeAdView(unifiedNativeAd, adView);
////                                CheckAds.getInstance().checkAds(adView, CheckAds.PE);
////                            });
////                        }
////
////                        @Override
////                        public void onAdFailedToLoad(@org.jetbrains.annotations.Nullable LoadAdError i) {
////                            runOnUiThread(() -> {
////                                binding.nativePermission.setVisibility(View.GONE);
////                            });
////                        }
////                    });
////                }).start();
////
////            } else {
////                binding.nativePermission.setVisibility(View.GONE);
////            }
////
////        } catch (Exception e) {
////            e.printStackTrace();
////            binding.nativePermission.setVisibility(View.GONE);
////        }
//    }
//
//    private void loadInterPermission() {
//        if (IsNetWork.haveNetworkConnection(this) && ConstantIdAds.mInterPermission == null && ConstantIdAds.listIDAdsInterPermission.size() != 0 && CheckAds.getInstance().isShowAds(this)) {
//            ConstantIdAds.mInterPermission = CommonAd.getInstance().getInterstitialAds(this, ConstantIdAds.listIDAdsInterPermission);
//        }
//    }
//    public void loadNativeIntro() {
////        try {
////            if (IsNetWork.haveNetworkConnection(IntroActivity.this) && ConstantIdAds.listIDAdsNativeIntro.size() != 0 && ConstantRemote.native_intro  && CheckAds.getInstance().isShowAds(this)) {
////                Admob.getInstance().loadNativeAd(IntroActivity.this, ConstantIdAds.listIDAdsNativeIntro, new AdCallback() {
////                    @Override
////                    public void onUnifiedNativeAdLoaded(@NonNull NativeAd unifiedNativeAd) {
////                        NativeAdView adView = (NativeAdView) LayoutInflater.from(IntroActivity.this).inflate(R.layout.layout_native_show_small, null);
////                        binding.nativeIntro.removeAllViews();
////                        binding.nativeIntro.addView(adView);
////                        Admob.getInstance().populateUnifiedNativeAdView(unifiedNativeAd, adView);
////                        CheckAds.getInstance().checkAds(adView, CheckAds.IN);
////                    }
////
////                    @Override
////                    public void onAdFailedToLoad(@org.jetbrains.annotations.Nullable LoadAdError i) {
////                        binding.nativeIntro.setVisibility(View.GONE);
////                    }
////                });
////            } else {
////                binding.nativeIntro.setVisibility(View.GONE);
////            }
////
////        } catch (Exception e) {
////            binding.nativeIntro.setVisibility(View.GONE);
////        }
//    }
//    private void loadInterIntro() {
//        if (ConstantIdAds.mInterIntro == null && IsNetWork.haveNetworkConnection(this) && ConstantIdAds.listIDAdsInterIntro.size() != 0 && ConstantRemote.inter_intro  && CheckAds.getInstance().isShowAds(this)) {
//            ConstantIdAds.mInterIntro = CommonAd.getInstance().getInterstitialAds(this, ConstantIdAds.listIDAdsInterIntro);
//        }
//    }
//    public void loadNativeFullScreenIntro() {
////        try {
////            if (IsNetWork.haveNetworkConnection(IntroActivity.this) && ConstantIdAds.listIDAdsNativeIntro.size() != 0 && ConstantRemote.native_intro_fullscreen  && CheckAds.getInstance().isShowAds(this)) {
////                new Thread(() -> {
////                    Admob.getInstance().loadNativeAd(this, ConstantIdAds.listIDAdsNativeIntro, new AdCallback() {
////                        @Override
////                        public void onUnifiedNativeAdLoaded(@NonNull NativeAd unifiedNativeAd) {
////                            runOnUiThread(() -> {
////                                @SuppressLint("InflateParams") NativeAdView adView = (NativeAdView) LayoutInflater.from(IntroActivity.this).inflate(R.layout.layout_native_show_all_screen, null);
////                                binding.nativeFullScreen.removeAllViews();
////                                binding.nativeFullScreen.addView(adView);
////                                Admob.getInstance().populateUnifiedNativeAdView(unifiedNativeAd, adView);
////                                CheckAds.getInstance().checkAds(adView, CheckAds.IN);
////                                findViewById(R.id.btnCloseShow).setOnClickListener(view -> {
////                                    binding.nativeFullScreenLoad.setVisibility(View.GONE);
////                                    binding.nativeFullScreen.setVisibility(View.GONE);
////                                    if (currentPosition==2){
////                                        if (IsNetWork.haveNetworkConnection(IntroActivity.this) && ConstantIdAds.listIDAdsNativeIntro.size() != 0 && ConstantRemote.native_intro && CheckAds.getInstance().isShowAds(getBaseContext())) {
////                                            binding.nativeLoad.setVisibility(View.VISIBLE);
////                                            new Handler().postDelayed(() -> {
////                                                binding.nativeLoad.setVisibility(View.GONE);
////                                                binding.nativeIntro.setVisibility(View.VISIBLE);
////                                            }, 500);
////                                        }
////                                    }
////                                });
////                            });
////                        }
////
////                        @Override
////                        public void onAdFailedToLoad(@org.jetbrains.annotations.Nullable LoadAdError i) {
////                            runOnUiThread(() -> {
////                                binding.nativeFullScreen.setVisibility(View.GONE);
////                            });
////                        }
////                    });
////                }).start();
////            } else {
////                binding.nativeFullScreen.setVisibility(View.GONE);
////            }
////
////        } catch (Exception e) {
////            binding.nativeFullScreen.setVisibility(View.GONE);
////        }
//    }
//    private void showInterIntro() {
////        if (IsNetWork.haveNetworkConnectionUMP(IntroActivity.this) && ConstantIdAds.listIDAdsInterIntro.size() != 0 && ConstantRemote.inter_intro  && CheckAds.getInstance().isShowAds(this)) {
////            if (System.currentTimeMillis() - ConstantRemote.interval_interstitial_from_start_old > ConstantRemote.interval_interstitial_from_start * 1000) {
////                if (System.currentTimeMillis() - ConstantRemote.time_interval_old > ConstantRemote.interval_between_interstitial * 1000) {
////                    try {
////                        if (ConstantIdAds.mInterIntro != null) {
////                            CommonAd.getInstance().forceShowInterstitialByTime(this, ConstantIdAds.mInterIntro, new CommonAdCallback() {
////                                @Override
////                                public void onAdClosed() {
////                                    super.onAdClosed();
////                                    startNextActivity();
////                                }
////
////                                @Override
////                                public void onAdClosedByTime() {
////                                    super.onAdClosedByTime();
////                                    startNextActivity();
////                                    ConstantRemote.time_interval_old = System.currentTimeMillis();
////                                    ConstantIdAds.mInterIntro = null;
////                                    loadInterIntro();
////                                }
////                            }, true);
////                        } else {
////                            startNextActivity();
////                            ConstantIdAds.mInterIntro = null;
////                            loadInterIntro();
////                        }
////                    } catch (Exception e) {
////                        startNextActivity();
////                    }
////                } else {
////                    startNextActivity();
////                }
////            }else {
////                startNextActivity();
////            }
////        }else {
////            startNextActivity();
////        }
//    }
//    private void loadBanner() {
////        if (IsNetWork.haveNetworkConnectionUMP(this) && ConstantIdAds.listIDAdsBanner.size() != 0 && ConstantRemote.banner) {
////            new Thread(()->{
////                runOnUiThread(()->{
////                    binding.rlBanner.setVisibility(View.VISIBLE);
////                    Admob.getInstance().loadBannerFloor(this, ConstantIdAds.listIDAdsBanner);
////                });
////            }).start();
////        } else {
////            binding.rlBanner.setVisibility(View.GONE);
////        }
//    }
//    private void loadInterAll() {
//        if (ConstantIdAds.mInterAll == null && IsNetWork.haveNetworkConnection(this) && ConstantIdAds.listIDAdsInterAll.size() != 0) {
//            ConstantIdAds.mInterAll = CommonAd.getInstance().getInterstitialAds(this, ConstantIdAds.listIDAdsInterAll);
//        }
//    }
//
//    private void showInterHome(int idActivity, boolean checkRemote) {
//        if (IsNetWork.haveNetworkConnectionUMP(this) && ConstantIdAds.listIDAdsInterAll.size() != 0 && checkRemote) {
//            if (System.currentTimeMillis() - ConstantRemote.interval_interstitial_from_start_old > ConstantRemote.interval_interstitial_from_start * 1000) {
//                if (System.currentTimeMillis() - ConstantRemote.time_interval_old > ConstantRemote.interval_between_interstitial * 1000) {
//                    try {
//                        if (ConstantIdAds.mInterAll != null) {
//                            CommonAd.getInstance().forceShowInterstitialByTime(this, ConstantIdAds.mInterAll, new CommonAdCallback() {
//                                @Override
//                                public void onAdClosed() {
//                                    super.onAdClosed();
//                                    startNextActivity(idActivity);
//                                }
//
//                                @Override
//                                public void onAdClosedByTime() {
//                                    super.onAdClosedByTime();
//                                    ConstantRemote.time_interval_old = System.currentTimeMillis();
//                                    ConstantIdAds.mInterAll = null;
//                                    loadInterAll();
//                                }
//                            }, true);
//                        } else {
//                            startNextActivity(idActivity);
//                            loadInterAll();
//                        }
//                    } catch (Exception e) {
//                        startNextActivity(idActivity);
//                    }
//                } else {
//                    startNextActivity(idActivity);
//                }
//            } else {
//                startNextActivity(idActivity);
//            }
//        } else {
//            startNextActivity(idActivity);
//        }
//    }
//
//    private void startNextActivity(int idActivity) {
////        switch (idActivity) {
////            case ACTIVITY_PIN:
////                startArc(new Intent(getContext(), SetPinLockActivity.class));
////                break;
////            case ACTIVITY_PATTERN:
////                startArc(new Intent(getContext(), SetPatternLockActivity.class));
////                break;
////            default:
////                startArc(new Intent(requireContext(), SetVoiceLockActivity.class));
////                break;
////        }
//    }
//}
