package ca.ajaypatel.core.database

import android.util.Log
import ca.ajaypatel.core.Signup
import ca.ajaypatel.core.models.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import android.content.ContentValues.TAG


class Firestore  {

    private val db = FirebaseFirestore.getInstance()

    fun addUser(activity: Signup, userInfo: User) {
        //users collection name, create if not there
        db.collection("users")
            //document is uid
            .document(userInfo.id)
            //user object into merge SET
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                //TOAST
                Log.d(TAG, "DocumentSnapshot added with ID: ${userInfo.id}")

            }
            .addOnFailureListener() {
                //FAIL
                Log.w(TAG, "Error adding document")

            }
    }
}