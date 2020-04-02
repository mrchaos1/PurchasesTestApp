package com.example.testapp1.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.testapp1.model.Purchase;
import com.example.testapp1.dao.PurchaseDao;
import com.example.testapp1.model.PurchaseGroup;

@Database(entities = {Purchase.class, PurchaseGroup.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PurchaseDao purchaseDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "data.db")
                .fallbackToDestructiveMigration()
                .addCallback(callback)
                .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            System.out.println("--- onOpen Callback ---");
            new InitialDataAsyncTask(instance).execute();
        }
    };

    private static class InitialDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private PurchaseDao purchaseDao;

        public InitialDataAsyncTask(AppDatabase database) {
            System.out.println("--- InitialDataAsyncTask ---");
            purchaseDao = database.purchaseDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            purchaseDao.insert(new Purchase(666000, 1, "Гавно обычное"));
            purchaseDao.insert(new Purchase(120000, 2, "Гавно на палке"));
            purchaseDao.insert(new Purchase(10000, 3, "Салат с гавном"));
            purchaseDao.insert(new Purchase(340000, 3, "Кофе с гавном"));
            purchaseDao.insert(new Purchase(140000, 1, "Мороженное из гавна"));
            return null;
        }
    }
}
