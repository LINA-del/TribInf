package com.example.teamchfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.example.teamchfirebase.Adapter.RecycleAdapterClass;
import com.example.teamchfirebase.bean.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    // recycler viewer pour afficher des données sous forme d'une liste
    private RecyclerView ensemble_Post ;
    private RecycleAdapterClass adapter;
    private static final int ADD_CODE = 1;
    private DatabaseReference requete;
    private List<Post> postList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    ensemble_Post = (RecyclerView)findViewById(R.id.ensem_post);
    ensemble_Post.setHasFixedSize(true);
    requete = FirebaseDatabase.getInstance().getReference().child("posts");
    //linear Layout pour faire un element par ligne
        // linear layout manager pour affichage uniderictionnel soit vertical soit horizentale

    //
    getPosts();
    ensemble_Post.setLayoutManager(new LinearLayoutManager(this));


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
            Intent intent = new Intent(MainActivity.this,AjouterPost.class);
            // ça veut dire on attends un resultat lors de lancement de resultat
            startActivityForResult(intent,ADD_CODE);
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //adapter.notifyDataSetChanged();
    }

    private void getPosts() {

        requete.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                postList.clear();// vider la liste avant la remplire

                for(DataSnapshot data : snapshot.getChildren()){
                    // recupere les infos depuis realtimedatabase depuis realtime database
                    Post mPost = data.getValue(Post.class);
                    postList.add(mPost);
                }
                // remplire la class recycleAdapterClass
                adapter = new RecycleAdapterClass(postList,getApplicationContext());
                // set recycleView avec le contenu de l'adapter
                ensemble_Post.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}