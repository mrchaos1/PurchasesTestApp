package com.example.testapp1.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "purchase_groups")
public class PurchaseGroup extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;


    @ColumnInfo(name = "description")
    private String description;

    public PurchaseGroup(String description) {
        this.description = description;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }
}
