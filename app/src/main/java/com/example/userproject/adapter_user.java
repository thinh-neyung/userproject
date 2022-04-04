package com.example.userproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter_user extends RecyclerView.Adapter<adapter_user.ViewHolder> {
    //Dữ liệu hiện thị là danh sách sinh viên
    private ArrayList<user> user_list;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;

    public adapter_user(ArrayList<user> user_list, Context mContext) {
        this.user_list = user_list;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View user_view =
                inflater.inflate(R.layout.layout_user, parent, false);

        ViewHolder viewHolder = new ViewHolder(user_view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        user user = user_list.get(position);

        holder.user_name.setText(user.getName());


    }

    @Override
    public int getItemCount() {
        return user_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;
        public TextView user_name;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            user_name = itemView.findViewById(R.id.user_name);
        }
    }
}
