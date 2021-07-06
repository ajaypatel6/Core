package ca.ajaypatel.core

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.supportActionBar?.hide();

        loginBtn.setOnClickListener() {
            vibrateDevice(this)
            startActivity(Intent(this, Login::class.java))
        }

        signupBtn.setOnClickListener() {
            vibrateDevice(this)
            startActivity(Intent(this, Signup::class.java))
        }
    }

    private fun vibrateDevice(mainActivity: MainActivity) {

    }

    public override fun onStart() {
        super.onStart()

//        val user = Firebase.auth.currentUser
//
//        if(user!=null){
//            // there is a user
//            startActivity(Intent(this, DashboardActivity::class.java))
//            finish()
//        }
//
    }
}