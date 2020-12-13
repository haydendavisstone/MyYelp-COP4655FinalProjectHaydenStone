package com.example.cop4655finalprojecthaydenstone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class favoriteAdapter extends FirebaseRecyclerAdapter<FavoriteDataGet, favoriteAdapter.favoritesViewholder> { public favoriteAdapter(@NonNull FirebaseRecyclerOptions<FavoriteDataGet> options) { super(options); }
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    protected void
    onBindViewHolder(@NonNull favoritesViewholder holder, int position, @NonNull FavoriteDataGet model)
    {
        holder.favoriteName.setText(model.getFavoriteName());

        holder.favoriteAddress.setText(model.getFavoriteAddress());

        holder.favoritePhone.setText(model.getFavoritePhone());
    }

    // Function to tell the class about the Card view (here "person.xml")in which the data will be shown
    @NonNull
    @Override
    public favoritesViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite, parent, false);
        return new favoriteAdapter.favoritesViewholder(view, mListener);
    }

    // Sub Class to create references of the views in Card view (here "favorite.xml")
    class favoritesViewholder extends RecyclerView.ViewHolder {
    ImageView DeleteImage;
    TextView favoriteAddress, favoriteName, favoritePhone;

        public favoritesViewholder(@NonNull View itemView, final OnItemClickListener listener)
        {
            super(itemView);
            DeleteImage = itemView.findViewById(R.id.delete_image);
            favoriteName = itemView.findViewById(R.id.name);
            favoriteAddress = itemView.findViewById(R.id.address);
            favoritePhone = itemView.findViewById(R.id.phone);

            DeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
}