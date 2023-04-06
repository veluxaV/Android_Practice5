package com.example.pr55.UI;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr55.R;
import com.example.pr55.data.ServiceItem;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter< ServicesAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<ServiceItem> services;
    //private OnItemClickListener onItemClickListener;

    ServicesAdapter(Context context, List<ServiceItem> services) {
        this.services = services;
        this.inflater = LayoutInflater.from(context);
        //this.onItemClickListener = onItemClickListener;
    }
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_layout, parent, false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ServicesAdapter.ViewHolder holder, int position) {
        ServiceItem serviceItem = services.get(position);
        holder.serviceName.setText(serviceItem.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //нужный код
                Log.d("12345678", "holder click");
                Navigation.findNavController(v).navigate(R.id.action_services_to_fragment_info);
                String text = serviceItem.getName();
                Log.d("12345678", text);
            }
        });
    }
    @Override
    public int getItemCount() {
        return services.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView serviceName;
        //OnItemClickListener onItemClickListener;
        ViewHolder(View view){
            super(view);
            serviceName = view.findViewById(R.id.service_name);
            ImageView imageView = view.findViewById(R.id.image_car);
            imageView.setImageResource(R.drawable.not_painted);
            //this.onItemClickListener = onItemClickListener;
            //itemView.setOnClickListener(this);
        }

    }
}
