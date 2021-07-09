package ca.ajaypatel.core

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_signup.*
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import ca.ajaypatel.core.database.Firestore
import ca.ajaypatel.core.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Signup : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        this.supportActionBar?.hide();
        auth = FirebaseAuth.getInstance()

        signupPageButton.setOnClickListener() {
//            vibrateDevice(this)
            registerUser()

        }
    }

    private fun registerUser() {
        if (editTextPersonName.text.toString().isEmpty()) {
            editTextPersonName.error = "Enter username"
            editTextPersonName.requestFocus()
            return
        }

        if (editTextEmailAddress.text.toString().isEmpty()) {
            editTextEmailAddress.error = "Enter Email"
            editTextEmailAddress.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(editTextEmailAddress.text.toString()).matches()) {
            editTextEmailAddress.error = "Enter valid email"
            editTextEmailAddress.requestFocus()
            return
        }

        if (editTextPassword.text.toString().isEmpty()) {
            editTextPassword.error = "Enter password"
            editTextPassword.requestFocus()
            return
        }

        if (editTextPassword2.text.toString().isEmpty()) {
            editTextPassword2.error = "Re-enter password"
            editTextPassword2.requestFocus()
            return
        }

        if (!editTextPassword.text.toString().equals(editTextPassword2.text.toString())) {
            editTextPassword2.error = "Passwords do not match"
            editTextPassword2.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(
            editTextEmailAddress.text.toString(),
            editTextPassword.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "createUserWithEmail:success")
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val randy = (0..100000).random()

                    val user = User(
                        firebaseUser.uid,
                        editTextEmailAddress.text.toString(),
                        editTextPersonName.text.toString(),
                        random = randy
                    )

                    //got info, now need to store in cloud
                    Firestore().addUser(this, user)

                    //Firestore().addUserExample(this)

                    Toast.makeText(
                        baseContext, "Registration completed, please sign in.",
                        Toast.LENGTH_SHORT
                    ).show()

                    //contains info

                    startActivity(Intent(this, Login::class.java))
                    finish()
                    //val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("tag", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Registration failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //updateUI(null)
                }

                // ...
            }

    }

    private fun vibrateDevice(context: Context) {
        val vibrator = ContextCompat.getSystemService(context, Vibrator::class.java)
        vibrator?.let {
            if (Build.VERSION.SDK_INT >= 26) {
                it.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                it.vibrate(100)
            }
        }
    }
}