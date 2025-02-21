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

    @Query("SELECT * FROM icon where category = :category Order by sortasc asc ")
    List<IconModel> getIconByCategory(String category);
    @Query("SELECT * FROM icon where category = :category and sortasc= :sortasc ")
    IconModel getIconByCategoryAndSortASC(String category, int sortasc);

    @Query("SELECT * FROM icon where id = :id")
    IconModel getIconById(int id);

    @Update
    void update(IconModel iconModel);

    @Query("Delete from icon where id = :id")
    void delete(int id);

    @Query("Delete from icon where category = :category")
    void delete(String category);

}
