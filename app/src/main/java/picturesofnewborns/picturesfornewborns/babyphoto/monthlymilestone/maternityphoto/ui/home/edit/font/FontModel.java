package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ui.home.edit.font;

public class FontModel {
    private String fontPath;
    private boolean isSelect;

    public String getFontPath() {
        return fontPath;
    }

    public void setFontPath(String fontPath) {
        this.fontPath = fontPath;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public FontModel(String fontPath) {
        this.fontPath = fontPath;
        this.isSelect = false;
    }
}
