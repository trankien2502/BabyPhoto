package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.filter;

import android.graphics.ColorMatrixColorFilter;

public class FilterModel {
    ColorMatrixColorFilter filter;
    boolean isSelect;

    public ColorMatrixColorFilter getFilter() {
        return filter;
    }

    public void setFilter(ColorMatrixColorFilter filter) {
        this.filter = filter;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public FilterModel(ColorMatrixColorFilter filter) {
        this.filter = filter;
    }
}
