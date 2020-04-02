package com.example.testapp1.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import com.example.testapp1.dao.PurchaseDao;
import com.example.testapp1.database.AppDatabase;
import com.example.testapp1.model.Purchase;
import java.util.List;

public class PurchaseRepository {
    private PurchaseDao purchaseDao;
    private LiveData<List<Purchase>> purchases;

    public PurchaseRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        purchaseDao = database.purchaseDao();
    }

    public LiveData<List<Purchase>> getPurchases() {
        return purchaseDao.all();
    }

    public void insert(Purchase purchase) {
        new InsertAsyncTask(purchaseDao).execute(purchase);
    }

    public void update(Purchase purchase) {
        new UpdateAsyncTask(purchaseDao).execute(purchase);
    }

    public void delete(Purchase purchase) {
        new DeleteAsyncTask(purchaseDao).execute(purchase);
    }

    private static class InsertAsyncTask extends AsyncTask<Purchase, Void, Void> {

        private PurchaseDao purchaseDao;

        public InsertAsyncTask(PurchaseDao purchaseDao) {
            this.purchaseDao = purchaseDao;
        }

        @Override
        protected Void doInBackground(Purchase... purchases) {
            purchaseDao.insert(purchases[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Purchase, Void, Void> {

        private PurchaseDao purchaseDao;

        public UpdateAsyncTask(PurchaseDao purchaseDao) {
            this.purchaseDao = purchaseDao;
        }

        @Override
        protected Void doInBackground(Purchase... purchases) {
            purchaseDao.update(purchases[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Purchase, Void, Void> {

        private PurchaseDao purchaseDao;

        public DeleteAsyncTask(PurchaseDao purchaseDao) {
            this.purchaseDao = purchaseDao;
        }

        @Override
        protected Void doInBackground(Purchase... purchases) {
            purchaseDao.delete(purchases[0]);
            return null;
        }
    }
}
