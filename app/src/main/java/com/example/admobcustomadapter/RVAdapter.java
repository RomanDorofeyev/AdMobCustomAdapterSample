package com.example.admobcustomadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final byte AD_VIEW_TYPE = 0;
    private static final byte SIMPLE_VIEW_TYPE = 2;

    private List<String > items;
    private AdView adView;

    RVAdapter(List<String > items, int adPosition, AdView adView) {
        this.items = items;
        items.add(adPosition, null);
        this.adView = adView;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case AD_VIEW_TYPE:
                viewHolder = new AdHolder(adView);
                break;

            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
                viewHolder = new ViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) == null) {
            return AD_VIEW_TYPE;

        } else {
            return SIMPLE_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (holder.getItemViewType() != 0 && holder instanceof ViewHolder) {
            ((ViewHolder) holder).name.setText(items.get(position));
            ((ViewHolder) holder).description.setText(items.get(position) + "  content");
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    static class AdHolder extends RecyclerView.ViewHolder {
        AdHolder(View itemView) {
            super(itemView);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView description;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }
    }
}
