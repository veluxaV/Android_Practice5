package com.example.pr55;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ServicesAdapter extends RecyclerView.Adapter< ServicesAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final String[] items;
    private OnItemClickListener onItemClickListener;

    ServicesAdapter(Context context, String[] items, OnItemClickListener onItemClickListener) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_layout, parent, false);

        return new ViewHolder(view, onItemClickListener);
    }
    @Override
    public void onBindViewHolder(ServicesAdapter.ViewHolder holder, int position) {
        //Item item = items.get(position);
        holder.textView.setText(items[position]);
    }
    @Override
    public int getItemCount() {
        return items.length;
    }
    public String getName(int p) {
        return items[p];
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView textView;
        OnItemClickListener onItemClickListener;
        ViewHolder(View view, OnItemClickListener onItemClickListener){
            super(view);
            textView = view.findViewById(R.id.service);
            //ImageView imageView = view.findViewById(R.id.image_car);
            //imageView.setImageResource(R.drawable.not_painted);
            this.onItemClickListener = onItemClickListener;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
