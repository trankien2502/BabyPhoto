package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.frame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;
import java.util.Objects;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.icon.IconModel;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ItemFrameListBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ItemFrameListBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.start.IconClickCallBack;

public class FrameAdapter extends RecyclerView.Adapter<FrameAdapter.IconViewHolder> {

    List<IconModel> iconModelList;
    Context context;
    IconClickCallBack iconClickCallBack;

    public FrameAdapter(Context context, List<IconModel> iconModelList, IconClickCallBack iconClickCallBack) {
        this.iconModelList = iconModelList;
        this.context = context;
        this.iconClickCallBack = iconClickCallBack;
    }

    public List<IconModel> getIconModelList() {
        return iconModelList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setIconModelList(List<IconModel> iconModelList) {
        this.iconModelList = iconModelList;
        notifyDataSetChanged();
    }

    public IconClickCallBack getIconClickCallBack() {
        return iconClickCallBack;
    }

    public void setIconClickCallBack(IconClickCallBack iconClickCallBack) {
        this.iconClickCallBack = iconClickCallBack;
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFrameListBinding itemIconModelBinding = ItemFrameListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new IconViewHolder(itemIconModelBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, int position) {
        IconModel iconModel = iconModelList.get(position);
//        if (iconModel.isSelect())
//            holder.binding.layoutItem.setBackgroundResource(R.drawable.bg_item_model_s);
//        else holder.binding.layoutItem.setBackgroundResource(R.drawable.bg_item_model_sn);
        if (!Objects.equals(iconModel.getUrl(), "") && iconModel.getUrl() != null)
            Glide.with(context).load(iconModel.getUrl()).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    holder.binding.progress.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    holder.binding.progress.setVisibility(View.GONE);
                    return false;
                }
            }).into(holder.binding.ivItemModel);
        else holder.binding.progress.setVisibility(View.GONE);
        holder.binding.layoutItem.setOnClickListener(view -> {
//            setCheck(iconModel);
            iconClickCallBack.select(iconModel);
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCheck(IconModel iconModel) {
        for (IconModel iconModel1 : iconModelList) {
            iconModel1.setSelect(Objects.equals(iconModel1.getUrl(), iconModel.getUrl()));
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return iconModelList.size();
    }

    public static class IconViewHolder extends RecyclerView.ViewHolder {
        ItemFrameListBinding binding;

        public IconViewHolder(ItemFrameListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
