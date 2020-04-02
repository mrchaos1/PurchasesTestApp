package com.example.testapp1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.testapp1.MainActivity;
import com.example.testapp1.R;
import com.example.testapp1.databinding.PurchaseListItemBinding;
import com.example.testapp1.model.Purchase;
import java.util.List;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder> {

    private List<Purchase> purchases;
    private MainActivity mainActivity;

    public PurchaseAdapter(List<Purchase> purchases, MainActivity mainActivity) {
        this.purchases = purchases;
        this.mainActivity = mainActivity;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PurchaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PurchaseListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.purchase_list_item, parent, false);
        return new PurchaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseViewHolder holder, final int position) {
        final Purchase purchase = purchases.get(position);
        holder.purchaseListItemBinding.setPurchase(purchase);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onPurchaseClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return purchases.size();
    }

    class PurchaseViewHolder extends RecyclerView.ViewHolder {

        private PurchaseListItemBinding purchaseListItemBinding;
        private TextView titleView;

        public PurchaseViewHolder(@NonNull PurchaseListItemBinding purchaseListItemBinding) {
            super(purchaseListItemBinding.getRoot());
            this.purchaseListItemBinding = purchaseListItemBinding;
        }
    }
}