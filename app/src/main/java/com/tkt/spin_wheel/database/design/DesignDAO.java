package com.tkt.spin_wheel.database.design;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DesignDAO {
    @Insert()
    void insert(DesignModel designModel);

    @Query("DELETE FROM design")
    void deleteAll();

    @Query("SELECT * FROM design ORDER BY id DESC")
    List<DesignModel> getDesigns();

    @Query("SELECT * FROM design where id = :designId")
    DesignModel getDesignById(int designId);

    @Update
    void update(DesignModel designModel);

    @Query("Delete from design where id = :designId")
    void delete(int designId);

}
