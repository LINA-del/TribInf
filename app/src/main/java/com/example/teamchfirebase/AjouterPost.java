package com.example.teamchfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.teamchfirebase.bean.Post;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AjouterPost extends AppCompatActivity  {
    private ImageButton addPost;
    private static final int reponse_Gallerie =1 ;
    private Uri ImageUri = null ;
    private Intent ajouterIntent;
    private EditText title;
    private  EditText description;
    private EditText comment;
    private Button submit;
    private ImageView sendComment;
    private StorageReference myFirebaseStorage ;
    private ProgressDialog Button_attente;
    private DatabaseReference database;

    public String random_value = null;
    public Post mPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_post);
        database = FirebaseDatabase.getInstance().getReference("posts");
        random_value= database.push().getKey();

      mPost = new Post();

        Button_attente=new ProgressDialog(this);

        title=(EditText)findViewById(R.id.editTextTextPersonName);
        description=(EditText)findViewById(R.id.editTextTextPersonName2);
        submit = (Button)findViewById(R.id.button);
        LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.recycleview, null );
       sendComment=(ImageView)view.findViewById(R.id.imageButton2);
        comment=(EditText)view.findViewById(R.id.comment);
        //comment=(EditText)findViewById(R.id.comment);
        //sendComment=(ImageView) findViewById(R.id.imageButton2);

        ajouterIntent = getIntent();
        // acces au stockage dans firebase
        myFirebaseStorage= FirebaseStorage.getInstance().getReference();
        addPost=(ImageButton)findViewById(R.id.imageView3);
   

    //pour accéder à la bd il faut crée une instance , get reference (crée reference /dossier)
    // ici "Blog " c'est le dossier ou on va mettre les infos

    //
   sendComment.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           SaveComment();
       }


   });


    //Add Post Button View
        addPost.setOnClickListener(new View.OnClickListener(){

        public void onClick(View view){
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.setType("image/*");
            //démarer selon le resultat de On ActivityResult
            startActivityForResult(i,reponse_Gallerie);
        }
    });

    //Submit Button
        submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Poster();
        }

    });


}
    public void SaveComment() {
        String contenu_comment=comment.getText().toString().trim();

        mPost.setComment(contenu_comment);
        database.child(random_value).setValue(mPost);


    }

    public void Poster(){
        //Affichage de progress
        Button_attente.setMessage("Start Posting");


        // recupere les valeurs depuis les deux champs d'edit text
        String contenu_title = title.getText().toString().trim();
        String contenu_descrip= description.getText().toString().trim();


        //Content cnt = new Content(contenu_title, contenu_descrip);

        if(!TextUtils.isEmpty(contenu_title)&& !TextUtils.isEmpty(contenu_descrip)){
            Button_attente.show();
            //PostImage : nom de dossier , getLastPathSegment(): retourne le nom de l'image
            // mettre un nom aléatoire pour chaque nouvelle image pour ne pea sles confondre


            if(ImageUri != null){

                StorageReference  Emplecement_fichier = myFirebaseStorage.child("PostImage").child(ImageUri.getLastPathSegment()+random_value);
                Emplecement_fichier.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //here
                        Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!urlTask.isSuccessful());
                        Uri downloadUrl = urlTask.getResult();
                        final String sdownload_url = String.valueOf(downloadUrl);

                        mPost.setImage(sdownload_url);
                        mPost.setDescription(contenu_descrip);
                        mPost.setTitre(contenu_title);
                        database.child(random_value).setValue(mPost);


                    }
                });

            }else{
                Post mPost = new Post();
                mPost.setDescription(contenu_descrip);
                mPost.setTitre(contenu_title);

                database.child(random_value).setValue(mPost);
            }



            //d.child("description").setValue(contenu_descrip);

            //DatabaseReference dd=aa.child("Title").child(random_value);
            //database.child("post").child(random_value).setValue(cnt);



            Button_attente.dismiss();

            setResult(RESULT_OK, ajouterIntent);
            finish();


            //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }





    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==reponse_Gallerie && resultCode==RESULT_OK){
            //puisque la donnée est retourne en format URI
            ImageUri = data.getData();
            //poster l'image dans l'espace réservé
            addPost.setImageURI(ImageUri);
        }
    }

















}