package com.example.ss10.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ss10.R;
import com.example.ss10.database.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter {
    Activity activity;
    List<User> listUser;

    public UserAdapter(Activity activity, List<User> listUser) {
        this.activity = activity;
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_user, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserHolder vh = (UserHolder) holder;
        User user = listUser.get(position);
        vh.tvID.setText(user.getId() + "");
        vh.tvUser.setText(user.getUsername());
        vh.tvGender.setText(user.getGender());
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        TextView tvUser, tvID, tvGender;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUserName);
            tvID = itemView.findViewById(R.id.tvID);
            tvGender = itemView.findViewById(R.id.tvGender);
        }
    }
}
