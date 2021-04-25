package com.example.teamchfirebase.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamchfirebase.R;
import com.example.teamchfirebase.bean.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleAdapterClass extends RecyclerView.Adapter<RecycleAdapterClass.MyViewHolder> {

    private List<Post> Postlist;
    private Context activity;

    //Constructeur
    public RecycleAdapterClass(List<Post> postlist, Context activity) {
        Postlist = postlist;
        this.activity = activity;
    }

    @NonNull
    @Override
    //View holder to set recycleViewat
    //View holder c'est l'objet ou l'elem qui contient (Image , titre , description)
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // specifier l'emplacement de layout utilise
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //position actuelle de notre eleme holder
       Post mPost=Postlist.get(position);
       String titre = mPost.getTitre();
       String desc = mPost.getDescription();
       String image = mPost.getImage();
       //replir le holder
       holder.title_holder.setText(titre);
       holder.Desc_holder.setText(desc);

       if(image == null){
           // cacher le champ image s'il n y a pas d'image
           holder.imagePost.setVisibility(View.GONE);
       }else{
           //Afficher l'image
           holder.imagePost.setVisibility(View.VISIBLE);
           Picasso.get().load(image).into(holder.imagePost);
       }
    }

    @Override
    public int getItemCount() {
        return Postlist.size();
    }

    ///Cree la classe des items View Holder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title_holder , Desc_holder;
        public ImageView imagePost;
        View mview;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mview = itemView;
            //recuperation des elements a partie des champs
            title_holder = mview.findViewById(R.id.titree_recycleView);
            Desc_holder = mview.findViewById(R.id.description_recycleView);
            imagePost = mview.findViewById(R.id.post_image);
        }





        @Override
        public void onClick(View v) {

        }

}









}
