package ca.ajaypatel.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//new
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.activity_dashboard.*

// need to add     id 'kotlin-android-extensions' to plugins, error
import kotlinx.android.synthetic.main.activity_main.*

class dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        this.supportActionBar?.hide();

        //BUTTON.setVisibility(View.VISIBLE);
        levelTwoButton.visibility=View.GONE;
        levelThreeButton.visibility=View.GONE;
        levelFourButton.visibility=View.GONE;

        levelOneButton.setOnClickListener(){
            startActivity(Intent(this, LevelOne::class.java))
        }
    }
}