package com.example.testapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.testapp1.adapter.PurchaseAdapter;
import com.example.testapp1.bottomsheet.PurchaseDialogFragment;
import com.example.testapp1.database.AppDatabase;
import com.example.testapp1.model.Purchase;
import com.example.testapp1.viewmodel.MainActivityViewModel;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PurchaseDialogFragment.Listener {
    MainActivityViewModel mainActivityViewModel;
    AppDatabase database;
    private List<Purchase> purchases;
    private PurchaseAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.purchaseRecyclerView);
        recyclerView.setHasFixedSize(true);

        mainActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModel.class);

        mainActivityViewModel.getPurchases().observe(this, new Observer<List<Purchase>>() {
            @Override
            public void onChanged(List<Purchase> purchasesList) {
                purchases = purchasesList;
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setHasFixedSize(true);
                adapter = new PurchaseAdapter(purchases, MainActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    public void showBottomSheet(View view) {
        final Purchase purchase = new Purchase(666, 12, "Lol kek");
        PurchaseDialogFragment.newInstance(purchase).show(getSupportFragmentManager(), "dialog");
    }

//    public void editPurchase(final Purchase purchase, final int position) {
//        Toast.makeText(MainActivity.this, "Press on position: " + position, Toast.LENGTH_LONG).show();
//        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
//        View view = layoutInflater.inflate(R.layout.content_edit_purchase, null);
//        final EditText purchaseDescriptionEditText = view.findViewById(R.id.purchaseDescriptionEditText);
//        purchaseDescriptionEditText.setText("111111111");
//
//
//        BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this);
//        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
//            @Override
//            public void onShow(DialogInterface dialog) {
//                BottomSheetDialog d = (BottomSheetDialog) dialog;
//                View bottomSheetInternal = d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
////                TextView purchaseTitleView = d.findViewById(R.id.purchaseTitleView);
////                purchaseTitleView.setText("АААААААААА!!!");
//
//
//
//                BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
//            }
//        });
//        dialog.setContentView(view);
//        dialog.show();
//    }

    @Override
    public void onPurchaseClicked(int position) {
        final Purchase purchase = purchases.get(position);
        PurchaseDialogFragment.newInstance(purchase).show(getSupportFragmentManager(), "dialog");
    }

    public class MainActivityClickHandlers {
        public void onFloatingActionButtonClock(View view) {
            Toast.makeText(MainActivity.this, "Click!", Toast.LENGTH_LONG).show();
        }
    }
}
