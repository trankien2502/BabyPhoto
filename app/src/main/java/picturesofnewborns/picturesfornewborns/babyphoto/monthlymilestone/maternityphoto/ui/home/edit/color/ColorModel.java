package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.color;

public class ColorModel {
    private int color;
    private boolean isSelect;

    public ColorModel() {
    }

    public ColorModel(int color) {
        this.color = color;
        this.isSelect = false;
    }
    public ColorModel(int color, boolean isSelect) {
        this.color = color;
        this.isSelect = isSelect;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
