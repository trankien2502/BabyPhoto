package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.icon;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IconDAO {
    @Insert()
    void insert(IconModel iconModel);

    @Query("DELETE FROM icon")
    void deleteAll();

    @Query("SELECT * FROM icon")
    List<IconModel> getIconModels();

    @Query("SELECT * FROM icon where category = :category")
    List<IconModel> getIconByCategory(String category);

    @Query("SELECT * FROM icon where id = :designId")
    IconModel getDesignById(int designId);

    @Query("SELECT * FROM icon where category = :category and color = :color")
    List<IconModel> getIconByCategoryAndColor(String category, int color);

    @Update
    void update(IconModel iconModel);

    @Query("Delete from icon where id = :designId")
    void delete(int designId);

    @Query("Delete from icon where category = :category")
    void delete(String category);

}
