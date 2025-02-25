package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.creation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.R;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.base.BaseFragment;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.design.DesignDatabase;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.design.DesignModel;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.icon.IconModel;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.FragmentCreationBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.DeleteDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.dialog.DetailDialog;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.HomeActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.crop.CropActivity;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.EventTracking;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.ImageUtils;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.PermissionManager;

public class CreationFragment extends BaseFragment<FragmentCreationBinding> {
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    List<DesignModel> list = new ArrayList<>();
    DesignAdapter designAdapter;

    @Override
    public FragmentCreationBinding setBinding(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return FragmentCreationBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        list.clear();
        list.addAll(DesignDatabase.getInstance(requireContext()).designDAO().getDesigns());
        designAdapter = new DesignAdapter(requireContext(), list, new ClickDesignCallBack() {
            @Override
            public void select(DesignModel designModel) {
                EventTracking.logEvent(requireContext(), "album_my_design_item_click");
                Intent intent = new Intent(requireContext(), DesignDetailActivity.class);
                intent.putExtra("DESIGN_MODEL", designModel);
                startArc(intent);
            }

            @Override
            public void detail(DesignModel designModel) {
                showDetailDialog(designModel);
            }
        });
        if (list.isEmpty()) binding.llNoData.setVisibility(View.VISIBLE);
        else binding.llNoData.setVisibility(View.GONE);
        binding.rcvDesign.setAdapter(designAdapter);
    }

    public void startArc(Intent intent) {
        if (getContext() instanceof HomeActivity) {
            HomeActivity main = (HomeActivity) getContext();
            main.resultLauncher.launch(intent);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void showDeleteDialog(DesignModel iconModel) {
        DeleteDialog dialog = new DeleteDialog(requireContext(), false);
        dialog.binding.btnDeny.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.binding.btnAllow.setOnClickListener(view -> {
            DesignDatabase.getInstance(requireContext()).designDAO().delete(iconModel.getId());
            list.remove(iconModel);
            if (list.isEmpty()) binding.llNoData.setVisibility(View.VISIBLE);
            else binding.llNoData.setVisibility(View.GONE);
            designAdapter.notifyDataSetChanged();
            File file = new File(iconModel.getPath());
            boolean deleted = file.delete();
            if (deleted) {
                Log.d("CacheCleanup", "File cache đã được xóa: " + iconModel.getPath());
            } else {
                Log.e("CacheCleanup", "Xóa file cache thất bại");
            }
            dialog.dismiss();
        });
        dialog.show();
    }

    private void showDetailDialog(DesignModel designModel) {
        DetailDialog dialog = new DetailDialog(requireContext());
        dialog.binding.llDelete.setOnClickListener(view -> {
            showDeleteDialog(designModel);
            dialog.dismiss();
        });
        dialog.binding.llShare.setOnClickListener(view -> {
            try {
                File file = new File(designModel.getPath());
                Uri fileUri = FileProvider.getUriForFile(requireContext(), requireContext().getPackageName() + ".provider", file);
                shareImage(fileUri);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("uricheck", "e:", e);
                Log.e("uricheck", "path:" + designModel.getPath());
                Toast.makeText(requireContext(), R.string.failed_to_get_image, Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        });
        dialog.binding.llDownload.setOnClickListener(view -> {
            EventTracking.logEvent(requireContext(), "success_download_click");
            Bitmap bitmap = null;
            File imgFile = new File(designModel.getPath());
            if (imgFile.exists()) {
                bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    ImageUtils.saveImageToMediaStore(requireContext(), bitmap);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (!PermissionManager.checkImageStoragePermission(requireContext())) {
                            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE_PERMISSION);
                        } else {
                            ImageUtils.saveBitmap(requireContext(), bitmap);
                        }
                    } else {
                        ImageUtils.saveBitmap(requireContext(), bitmap);
                    }
                }
            } else {
                Log.e("img_check", "Image file does not exist at path: " + designModel.getPath());
                Toast.makeText(requireContext(), R.string.error, Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        });
        dialog.show();
    }

    private void shareImage(Uri uri) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/png");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        AppOpenManager.getInstance().disableAppResumeWithActivity(DesignSuccessActivity.class);
        Intent chooser = Intent.createChooser(shareIntent, "Share Image via");
        startArc(chooser);
    }

    @Override
    public void bindView() {
        binding.llCreateNew.setOnClickListener(v -> {
            startArc(new Intent(requireActivity(), CropActivity.class));
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {
        super.onResume();
        if (designAdapter != null) {
            list.clear();
            list.addAll(DesignDatabase.getInstance(requireContext()).designDAO().getDesigns());
            designAdapter.notifyDataSetChanged();
            if (list.isEmpty()) binding.llNoData.setVisibility(View.VISIBLE);
            else binding.llNoData.setVisibility(View.GONE);
        }
    }
}