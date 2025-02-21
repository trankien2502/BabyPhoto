package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.icon;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "icon")
public class IconModel implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private int stt;
    private String category;
    private String url;
    private boolean isSelect;
    private String imagename;
    private int sortasc;

    public IconModel() {
        this.isSelect = false;
    }
    public IconModel(String url) {
        this.url = url;
        this.isSelect = false;
    }

    public IconModel(String url, String category) {
        this.url = url;
        this.isSelect = false;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public int getSortasc() {
        return sortasc;
    }

    public void setSortasc(int sortasc) {
        this.sortasc = sortasc;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}