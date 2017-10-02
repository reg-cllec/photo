package com.example.photogallery.photogallery.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.photogallery.photogallery.activity.FullImageActivity;
import com.example.photogallery.photogallery.entity.Images;
import com.example.photogallery.photogallery.R;

import java.util.ArrayList;

/**
 * Created by bafeng on 10/1/17.
 */

//data adapter for recyclerview to hold photo in grid
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.CustomViewHolder> {

    public ArrayList<Images> mPhotos = new ArrayList<>();
    private final Activity mActivity;

    public void update(ArrayList<Images> images){
        mPhotos.clear();
        for (Images photo: images) {
            mPhotos.add(photo);
        }
        notifyDataSetChanged();
    }
    public GalleryAdapter(Activity activity, ArrayList<Images> list) {
        mActivity = activity;
        mPhotos = list;

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new GalleryAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final String url = mPhotos.get(position).getURL();
        final String title = mPhotos.get(position).getTitle();
        Glide
                .with(mActivity)
                .load(url)
                .override(200, 200)
                .centerCrop()
                .into(holder.imageResource);

        final int itemPosition = holder.getAdapterPosition();
        holder.imageResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, FullImageActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", title);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageResource;
        CustomViewHolder(View itemView) {
            super(itemView);
            this.imageResource = (ImageView) itemView.findViewById(R.id.recycler_single_item);
        }
    }
}

