package com.example.testapp1.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.testapp1.model.Purchase;
import java.util.List;

@Dao
public interface PurchaseDao {
    @Insert
    public void insert(Purchase purchase);

    @Update
    public void update(Purchase purchase);

    @Delete
    public void delete(Purchase purchase);

    @Query("SELECT * FROM purchases")
    public LiveData<List<Purchase>> all();

    @Query("SELECT * FROM purchases WHERE id = :id")
    public Purchase get(int id);
}
