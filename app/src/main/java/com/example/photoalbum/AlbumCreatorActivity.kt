package com.example.photoalbum

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.photoalbum.Data.Album
import com.example.photoalbum.Data.Comments
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.album_creator_layout.*

class AlbumCreatorActivity: AppCompatActivity() {

    lateinit var albumName: EditText
    lateinit var albumDescription: EditText
    lateinit var albumType: Switch
    lateinit var createAlbumButton: Button
    lateinit var type:String
    //creat instance of FirebaseFirestore
    lateinit var db : FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_creator_layout)
        auth = FirebaseAuth.getInstance()
        //set instance of firestore
        db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build()
        db.setFirestoreSettings(settings)

        albumName = album_name
        albumDescription = album_description
        albumType = switch1
        createAlbumButton = createButton

        var typePublic = true


        albumType.setOnCheckedChangeListener({ _ , isChecked ->
            typePublic = false
            if (isChecked!=true){
                typePublic = true
            }

        })



        createAlbumButton.setOnClickListener(){
            var name = albumName.text.toString()
            var description = albumDescription.text.toString()

            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(description)) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show()
            }
            else{
                val userId:String = FirebaseAuth.getInstance().currentUser!!.uid


                var albumName = name
                var albumDesc = description
                var albumType = type
                var owner = auth.currentUser!!.email
                val pictures = ArrayList<String>()
                //Stores userids or usernames that can view album
                val allowedUserList=  ArrayList<String>()
                //Stores arraylist of userids or usernames that are mods
                val isModList = ArrayList<String>()
                val comments = ArrayList<Comments>()
                var newAlbum = Album(owner, albumName, null, albumDesc, typePublic, pictures, allowedUserList, isModList,comments)
                db.collection("Albums").document(albumName)
                    .set(newAlbum)

                    .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                       Toast.makeText(this,"Album Created!",Toast.LENGTH_SHORT).show()
                        }
                    .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e) }

                    .addOnFailureListener(OnFailureListener { e ->
                        Log.d("AlbumCreatorActivity", "Failed to insert data!")
                    })
            }
        }









    }
}