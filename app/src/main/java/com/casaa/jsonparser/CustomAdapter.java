package com.casaa.jsonparser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<Imges> localDataSet;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private  TextView IdView;
         private ImageView Im;

        public ImageView getIm() {
            return Im;
        }

        public ViewHolder(View view) {
            super(view);
            IdView = (TextView) view.findViewById(R.id.IDText);
            Im = (ImageView) view.findViewById(R.id.IDimage);
        }


        public TextView getIdView() {
            return IdView;
        }

    }
    public CustomAdapter(List<Imges> dataSet) {
        localDataSet = dataSet;
    }

    public CustomAdapter(List<Imges> localDataSet, RecyclerView layout) {
        this.localDataSet = localDataSet;
        layout.setAdapter(this);
    }


    public List<Imges> getLocalDataSet() {
        return localDataSet;
    }

    public void setLocalDataSet(List<Imges> localDataSet) {
        this.localDataSet = localDataSet;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cel, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.getIdView().setText(localDataSet.get(position).getTitle());
        Picasso.get().load(localDataSet.get(position).getImage()).into(viewHolder.getIm());
    }
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}