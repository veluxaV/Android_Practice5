package com.example.pr55.UI.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr55.R;
import com.example.pr55.domain.model.ServiceModel;
import com.example.pr55.domain.viewModel.ServiceViewModel;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter< ServicesAdapter.ViewHolder> {
    private ServiceViewModel serviceViewModel = new ServiceViewModel();
    private final LayoutInflater inflater;
    private final LiveData<List<ServiceModel>> services;
    //private OnItemClickListener onItemClickListener;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ServiceModel service);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ServicesAdapter(Context context, LiveData<List<ServiceModel>> services) {
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
        ServiceModel serviceModel = services.getValue().get(position);
        holder.serviceName.setText(serviceModel.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //нужный код
                int position = holder.getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(services.getValue().get(position));
                }
                /*
                String text = serviceModel.getName();
                Log.d("12345678", text);
                serviceViewModel.selectService(serviceModel);
                */

                Navigation.findNavController(v).navigate(R.id.action_services_to_fragment_info);
            }
        });
    }
    @Override
    public int getItemCount() {
        return services.getValue().size();
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
