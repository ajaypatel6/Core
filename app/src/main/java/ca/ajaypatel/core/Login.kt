package ca.ajaypatel.core

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.supportActionBar?.hide();

        // init
        auth = FirebaseAuth.getInstance()
        val user = Firebase.auth.currentUser

        if(user!=null){
            startActivity(Intent(this, dashboard::class.java))
        }

        loginSignupButton.setOnClickListener(){
            //vibrate
            startActivity(Intent(this, Signup::class.java))
        }
    }
}