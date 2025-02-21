package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.color;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

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

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ItemColorBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ItemColorBinding;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.IconViewHolder> {

    List<ColorModel> iconModelList;
    Context context;
    ColorClickCallBack iconClickCallBack;

    public ColorAdapter(Context context, List<ColorModel> iconModelList, ColorClickCallBack iconClickCallBack) {
        this.iconModelList = iconModelList;
        this.context = context;
        this.iconClickCallBack = iconClickCallBack;
    }

    public List<ColorModel> getColorModelList() {
        return iconModelList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setColorModelList(List<ColorModel> iconModelList) {
        this.iconModelList = iconModelList;
        notifyDataSetChanged();
    }

    public ColorClickCallBack getColorClickCallBack() {
        return iconClickCallBack;
    }

    public void setColorClickCallBack(ColorClickCallBack iconClickCallBack) {
        this.iconClickCallBack = iconClickCallBack;
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemColorBinding itemColorModelBinding = ItemColorBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new IconViewHolder(itemColorModelBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, int position) {
        ColorModel iconModel = iconModelList.get(position);
        if (iconModel.isSelect()) holder.binding.ivSelect.setVisibility(VISIBLE);
        else holder.binding.ivSelect.setVisibility(GONE);
        holder.binding.layoutItem.setOnClickListener(view -> {
            setCheck(iconModel);
            iconClickCallBack.select(iconModel);
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCheck(ColorModel iconModel) {
        for (ColorModel iconModel1 : iconModelList) {
            iconModel1.setSelect(iconModel1.getColor() == iconModel.getColor());
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return iconModelList.size();
    }

    public static class IconViewHolder extends RecyclerView.ViewHolder {
        ItemColorBinding binding;

        public IconViewHolder(ItemColorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
