package com.example.photoalbum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var picButton: Button
    private lateinit var createButton: Button
    private lateinit var logoutButton: Button
    private lateinit var userListButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        picButton = btn_camera
        createButton = btn_creator
        logoutButton = btn_logout
        userListButton = btn_userlist

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
//            Toast.makeText(this, "Already logged in", Toast.LENGTH_LONG).show()
        }

        picButton.setOnClickListener(){
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
        createButton.setOnClickListener(){
            val intent = Intent(this, AlbumCreatorActivity::class.java)
            startActivity(intent)
        }

        btn_logout.setOnClickListener() {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        userListButton.setOnClickListener() {
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}
