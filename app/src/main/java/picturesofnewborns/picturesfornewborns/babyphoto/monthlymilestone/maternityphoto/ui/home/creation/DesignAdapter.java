package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.creation;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.design.DesignModel;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.databinding.ItemDesignBinding;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.ImageUtils;

public class DesignAdapter extends RecyclerView.Adapter<DesignAdapter.DesignViewHolder> {

    List<DesignModel> list;
    Context context;
    ClickDesignCallBack clickDesignCallBack;

    public DesignAdapter(Context context, List<DesignModel> list, ClickDesignCallBack clickDesignCallBack) {
        this.list = list;
        this.context = context;
        this.clickDesignCallBack = clickDesignCallBack;
    }

    @NonNull
    @Override
    public DesignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDesignBinding itemCatBinding = ItemDesignBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DesignViewHolder(itemCatBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DesignViewHolder holder, int position) {
        DesignModel designModel = list.get(position);
        Bitmap bitmap = ImageUtils.loadBitmapImageFromInternalStorage(designModel.getPath());
        if (bitmap != null)
            holder.binding.ivCat.setImageBitmap(bitmap);
        holder.binding.layoutItem.setOnClickListener(view -> clickDesignCallBack.select(designModel));
        holder.binding.ivMore.setOnClickListener(v -> clickDesignCallBack.detail(designModel));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class DesignViewHolder extends RecyclerView.ViewHolder {
        ItemDesignBinding binding;

        public DesignViewHolder(ItemDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
