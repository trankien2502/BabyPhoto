package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.filter;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.R;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ItemFilterBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ItemFilterBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.color.ColorClickCallBack;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.color.ColorModel;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.IconViewHolder> {

    List<FilterModel> iconModelList;
    Context context;
    FilterClickCallBack iconClickCallBack;

    public FilterAdapter(Context context, List<FilterModel> iconModelList, FilterClickCallBack iconClickCallBack) {
        this.iconModelList = iconModelList;
        this.context = context;
        this.iconClickCallBack = iconClickCallBack;
    }

    public List<FilterModel> getColorModelList() {
        return iconModelList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setColorModelList(List<FilterModel> iconModelList) {
        this.iconModelList = iconModelList;
        notifyDataSetChanged();
    }

    public FilterClickCallBack getColorClickCallBack() {
        return iconClickCallBack;
    }

    public void setColorClickCallBack(FilterClickCallBack iconClickCallBack) {
        this.iconClickCallBack = iconClickCallBack;
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilterBinding itemColorModelBinding = ItemFilterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new IconViewHolder(itemColorModelBinding);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, int position) {
        FilterModel iconModel = iconModelList.get(position);
        if (position == 0) {
            if (iconModel.isSelect()) {
                holder.binding.ivNone.setVisibility(VISIBLE);
                holder.binding.clFilter.setVisibility(GONE);
                holder.binding.ivNone.setImageResource(R.drawable.no_filter_s);
            } else {
                holder.binding.ivNone.setVisibility(VISIBLE);
                holder.binding.clFilter.setVisibility(GONE);
                holder.binding.ivNone.setImageResource(R.drawable.no_filter_sn);
            }
        } else {
            holder.binding.ivNone.setVisibility(GONE);
            holder.binding.clFilter.setVisibility(VISIBLE);
            if (iconModel.isSelect()) holder.binding.ivSelect.setVisibility(VISIBLE);
            else holder.binding.ivSelect.setVisibility(GONE);
            holder.binding.ivColor.setColorFilter(iconModel.getFilter());
        }
        holder.binding.layoutItem.setOnClickListener(view -> {
            setCheck(iconModel);
            iconClickCallBack.select(iconModel);
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCheck(FilterModel iconModel) {
        for (FilterModel iconModel1 : iconModelList) {
            iconModel1.setSelect(iconModel1.getFilter() == iconModel.getFilter());
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return iconModelList.size();
    }

    public static class IconViewHolder extends RecyclerView.ViewHolder {
        ItemFilterBinding binding;

        public IconViewHolder(ItemFilterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
