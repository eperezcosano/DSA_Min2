package edu.upc.dsa.examenminimo2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private Museums museumsList;

    public MyAdapter(Context context, Museums museumsList) {
        this.context = context;
        this.museumsList = museumsList;
    }

    public void setMuseumsList(Museums museumsList) {
        this.museumsList = museumsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        if (museumsList.getElements().get(i).getAdrecaNom() != null)
            myViewHolder.adreca.setText(museumsList.getElements().get(i).getAdrecaNom());
        if (museumsList.getElements().get(i).getImatge().get(0) != null)
            Picasso.get().load(museumsList.getElements().get(i).getImatge().get(0)).into(myViewHolder.imatge);
    }

    @Override
    public int getItemCount() {
        try {
            return museumsList.getElements().size();
        } catch (Exception e) {
            return 0;
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imatge;
        private TextView adreca;

        public MyViewHolder(View itemView) {
            super(itemView);
            imatge = itemView.findViewById(R.id.imgMuseu);
            adreca = itemView.findViewById(R.id.txtNom);
        }
    }
}