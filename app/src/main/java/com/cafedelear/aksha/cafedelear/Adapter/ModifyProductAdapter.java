package com.cafedelear.aksha.cafedelear.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cafedelear.aksha.cafedelear.Model.Menu_model;
import com.cafedelear.aksha.cafedelear.R;

import java.util.List;

public class ModifyProductAdapter extends RecyclerView.Adapter<ModifyProductAdapter.ViewHolder> {

    private Context context;
    private List<Menu_model> menu_models;

    public ModifyProductAdapter(Context context, List<Menu_model> menu_models) {
        this.context = context;
        this.menu_models = menu_models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_delete_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.menu_name.setText(menu_models.get(position).getMenu_name());
        Glide.with(context).load(menu_models.get(position).getMenu_url()).into(holder.menu_image);

    }

    @Override
    public int getItemCount() {
        return menu_models.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView menu_image;
        TextView menu_name,edit,delete;
        LinearLayout linear_modify;


        public ViewHolder(View itemView) {
            super(itemView);

        menu_image = itemView.findViewById(R.id.menu_image);
        menu_name = itemView.findViewById(R.id.menu_name);
        edit = itemView.findViewById(R.id.edit);
        delete = itemView.findViewById(R.id.delete);

        }


    }
}
