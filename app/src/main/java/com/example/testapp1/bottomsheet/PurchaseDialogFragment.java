package com.example.testapp1.bottomsheet;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.example.testapp1.R;
import com.example.testapp1.databinding.FragmentPurchaseEditDialogBinding;
import com.example.testapp1.databinding.PurchaseListItemBinding;
import com.example.testapp1.model.Purchase;
import com.example.testapp1.viewmodel.MainActivityViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     PurchaseDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 * <p>You activity (or fragment) needs to implement {@link PurchaseDialogFragment.Listener}.</p>
 */
public class PurchaseDialogFragment extends BottomSheetDialogFragment {

    private FragmentPurchaseEditDialogBinding binding;
    MainActivityViewModel mainActivityViewModel;
    Purchase purchase;

    public static PurchaseDialogFragment newInstance(Purchase purchase) {
        final PurchaseDialogFragment fragment = new PurchaseDialogFragment();
        final Bundle bundle = new Bundle();
        bundle.putInt("id", purchase.getId());
        fragment.setArguments(bundle);

        purchase = purchase;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        FragmentPurchaseEditDialogBinding binding = FragmentPurchaseEditDialogBinding.inflate(inflater, container, false);
        return inflater.inflate(R.layout.fragment_purchase_edit_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getPurchases().observe(this, new Observer<List<Purchase>>() {
            @Override
            public void onChanged(List<Purchase> purchasesList) {
//                binding.setPurchase(purchasesList);
//                purchases = purchasesList;
//                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                recyclerView.setHasFixedSize(true);
//                adapter = new PurchaseAdapter(purchases, MainActivity.this);
//                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface Listener {
        void onPurchaseClicked(int position);
    }
}
