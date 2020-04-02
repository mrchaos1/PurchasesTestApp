package com.example.testapp1.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.testapp1.model.Purchase;
import com.example.testapp1.repository.PurchaseRepository;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    PurchaseRepository purchaseRepository;
    private LiveData<List<Purchase>> purchases;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        purchaseRepository = new PurchaseRepository(application);
    }

    public LiveData<List<Purchase>> getPurchases() {
        purchases = purchaseRepository.getPurchases();
        return purchases;
    }

    public void create(Purchase purchase) {
        purchaseRepository.insert(purchase);
    }

    public void update(Purchase purchase) {
        purchaseRepository.update(purchase);
    }

    public void delete(Purchase purchase) {
        purchaseRepository.delete(purchase);
    }
}
