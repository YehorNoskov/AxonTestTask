package app.axon.test.ui.userlist;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.imagepipeline.request.ImageRequest;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import app.axon.test.R;
import app.axon.test.databinding.UserItemBinding;

public final class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<UserModel> items = new ArrayList<>();
    private UsersAdapter.ClickListener clickListener;


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        UserItemBinding binding = UserItemBinding.inflate(inflater, parent, false);

        return new UserViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        if (position != holder.getAdapterPosition()) return;

        UserModel model = items.get(position);
        holder.layout.tvUserName.setText(model.getUserName());
        if (!TextUtils.isEmpty(model.getUserAvatarMedium())) {
            holder.layout.ivAvatar.setImageRequest(ImageRequest.fromUri(model.getUserAvatarMedium()));
        } else {
            holder.layout.ivAvatar.setImageResource(R.drawable.ic_avatar_placeholder);
        }
        holder.layout.tvUserEmail.setText(model.getUserEmail());

        holder.layout.linearLayout.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onItemClicked(model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<UserModel> items) {
        this.items.clear();
        if (items != null) {
            this.items.addAll(items);
        }
        notifyDataSetChanged();
    }

    public void appendItems(List<UserModel> items) {
        if (items != null) {
            this.items.addAll(items);
        }
        notifyDataSetChanged();
    }

    interface ClickListener {
        void onItemClicked(UserModel userModel);
    }

    public void setClickListener(UsersAdapter.ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        UserItemBinding layout;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = DataBindingUtil.bind(itemView);
        }
    }
}
