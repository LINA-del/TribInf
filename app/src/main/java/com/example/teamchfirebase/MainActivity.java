package com.example.teamchfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.teamchfirebase.bean.Post;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity  {
    // recycler viewer pour afficher des données sous forme d'une liste
    private RecyclerView ensemble_Post ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    ensemble_Post = (RecyclerView)findViewById(R.id.ensem_post);
    ensemble_Post.setHasFixedSize(true);
    //linear Layout pour faire un element par ligne
        // linear layout manager pour affichage uniderictionnel soit vertical soit horizentale
    ensemble_Post.setLayoutManager(new LinearLayoutManager(this));

    }
    public void onStart(){
        super.onStart();
    }










    //View holder to set recycleView
    //View holder c'est l'objet ou l'elem qui contient (Image , titre , description)
    public static class PostHolder extends RecyclerView.ViewHolder{
          View mview;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            mview = itemView;
        }
        public void setTitle(String titlee){
            // on récupere le texte à partir du  designe recycleView
            TextView title_holder = (TextView)mview.findViewById(R.id.titree_recycleView);
            title_holder.setText(titlee);
        }

        public void setDescription(String desc){
            TextView Desc_holder = (TextView)mview.findViewById(R.id.description_recycleView);
            Desc_holder.setText(desc);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //récuperer le bouton add et setting depuis le fichier menuu.xml
        getMenuInflater().inflate(R.menu.menuu,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // si on clique sur le bouton add on demarre une nouvelle activité

        if(item.getItemId() == R.id.action_add){
            Intent i = new Intent(MainActivity.this,AjouterPost.class);

            startActivity(i);}

        return super.onOptionsItemSelected(item);

    }

}