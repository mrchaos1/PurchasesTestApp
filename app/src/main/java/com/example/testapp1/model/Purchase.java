package com.example.testapp1.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "purchases", foreignKeys = @ForeignKey(entity = PurchaseGroup.class, parentColumns = "id", childColumns = "group_id", onDelete = ForeignKey.CASCADE))
public class Purchase extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "price")
    private int price;

    @ColumnInfo(name = "quantity")
    private float quantity;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "group_id")
    private Integer groupId;

    public Purchase(int price, float quantity, String text) {
        this.price = price;
        this.quantity = quantity;
        this.text = text;
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
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
        notifyPropertyChanged(BR.quantity);
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }

    @Bindable
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
        notifyPropertyChanged(BR.groupId);
    }
}
