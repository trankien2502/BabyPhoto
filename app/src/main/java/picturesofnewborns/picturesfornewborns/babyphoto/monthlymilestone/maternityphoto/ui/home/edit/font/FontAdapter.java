package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.font;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.R;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ItemFontBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.color.ColorModel;

public class FontAdapter extends RecyclerView.Adapter<FontAdapter.IconViewHolder> {

    List<FontModel> listFont;
    Context context;
    FontClickCallBack iconClickCallBack;

    public FontAdapter(Context context, List<FontModel> listFont, FontClickCallBack iconClickCallBack) {
        this.listFont = listFont;
        this.context = context;
        this.iconClickCallBack = iconClickCallBack;
    }

    public List<FontModel> getStringList() {
        return listFont;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setStringList(List<FontModel> listFont) {
        this.listFont = listFont;
        notifyDataSetChanged();
    }

    public FontClickCallBack getColorClickCallBack() {
        return iconClickCallBack;
    }

    public void setColorClickCallBack(FontClickCallBack iconClickCallBack) {
        this.iconClickCallBack = iconClickCallBack;
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFontBinding itemFontBinding = ItemFontBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new IconViewHolder(itemFontBinding);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, int position) {
        FontModel fontModel = listFont.get(position);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontModel.getFontPath());
        if (fontModel.isSelect()) {
            holder.binding.layoutItem.setBackgroundResource(R.color.font_select);
            holder.binding.ivSelect.setVisibility(VISIBLE);
        } else {
            holder.binding.layoutItem.setBackgroundResource(0);
            holder.binding.ivSelect.setVisibility(INVISIBLE);
        }
        holder.binding.tvFont.setTypeface(typeface);
        holder.binding.layoutItem.setOnClickListener(view -> {
            setCheck(fontModel);
            iconClickCallBack.select(fontModel);
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCheck(FontModel iconModel) {
        for (FontModel iconModel1 : listFont) {
            iconModel1.setSelect(Objects.equals(iconModel1.getFontPath(), iconModel.getFontPath()));
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listFont.size();
    }

    public static class IconViewHolder extends RecyclerView.ViewHolder {
        ItemFontBinding binding;

        public IconViewHolder(ItemFontBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
