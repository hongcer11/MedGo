package com.group4.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.group4.models.Partner;
import com.group4.medgo.R;

import java.util.List;

public class PartnerAdapter extends RecyclerView.Adapter<PartnerAdapter.PartnerViewHolder> {
    Context context;
    List<Partner> partners;

    public PartnerAdapter(Context context, List<Partner> partners) {
        this.context = context;
        this.partners = partners;
    }

    @NonNull
    @Override
    public PartnerAdapter.PartnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_partner, parent, false);
        return new PartnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartnerAdapter.PartnerViewHolder holder, int position) {
        Partner partner = partners.get(position);
        holder.partnerLogo.setImageResource(partner.getPartnerLogo());
        holder.partnerName.setText(partner.getPartnerName());
    }

    @Override
    public int getItemCount() {
        return partners.size();
    }

    public static class PartnerViewHolder extends RecyclerView.ViewHolder {
        ImageView partnerLogo;
        TextView partnerName;

        public PartnerViewHolder(@NonNull View itemView) {
            super(itemView);
            partnerLogo = itemView.findViewById(R.id.partnerLogo);
            partnerName = itemView.findViewById(R.id.partnerName);
        }
    }

}
