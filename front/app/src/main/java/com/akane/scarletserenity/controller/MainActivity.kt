package com.akane.scarletserenity.controller

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.akane.scarletserenity.R
import com.akane.scarletserenity.controller.character.CharacterActivity
import com.akane.scarletserenity.service.BasicAuthInterceptor
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.ads.doubleclick.PublisherAdView
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : BaseActivity() {


    private lateinit var mp: MediaPlayer
    private var totalTime: Int = 0

    lateinit var mPublisherAdView : PublisherAdView


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        handleResponseAfterSignIn(requestCode, resultCode, data!!)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Oncreate", "test")

        speech(getString(R.string.welcomeSpeech))

        // variables

        val mBtLogin = findViewById<Button>(R.id.btLogin) as Button
        mBtLogin.setText(R.string.bt_login)

        val mBtInscription = findViewById<Button>(R.id.btInscription) as Button

        // music
        mp = MediaPlayer.create(this, R.raw.airtone)
        mp.isLooping = true
        mp.setVolume(0.7f, 0.7f)
        totalTime = mp.duration

        mp.start()
        // reNovation by airtone (c) copyright 2019 Licensed under a Creative Commons Attribution (3.0) license. http://dig.ccmixter.org/files/airtone/60674


        // crash test
//        val crashButton = Button(this)
//        crashButton.text = "Crash!"
//        crashButton.setOnClickListener {
//            throw RuntimeException("Test Crash") // Force a crash
//        }


//        addContentView(crashButton, ViewGroup.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT))


// Create and launch sign-in intent

        mBtLogin.setOnClickListener {
            if (this.checkCurrentUser()) {
                Log.d("Log", "Changement d'activity")
                mBtLogin.setText(R.string.bt_login2)
               // createUserInFirestore()
                mp.stop()
                this.startCharacterActivity()
            } else {
                this.startSignInActivity()
            }
        }

        mBtInscription.setOnClickListener {
            showdialogInscription()
        }



    }



//    private fun createUserInFirestore(){
//
//        val uid = this.user?.uid
//        val username = this.user?.displayName
//
//        UserHelper.createUser(uid, username)
//        Log.d("ça marche", "Utilisateur créé")
//
//    }

    private fun startSignInActivity(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN)
    }

        companion object {

            private const val RC_SIGN_IN = 123
        }

    private fun checkCurrentUser(): Boolean {
        // [START check_current_user]
        val user = Firebase.auth.currentUser
        return user != null
        // [END check_current_user]
    }

    private fun startCharacterActivity() {
        val intent = Intent(this, CharacterActivity::class.java)
        startActivity(intent)
    }

    fun showdialogInscription(){


        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Création d'un nouvel utilisateur")

        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL

        val newPseudo = EditText(this)

        newPseudo.setHint("Entrez votre pseudo")
        newPseudo.inputType = InputType.TYPE_CLASS_TEXT

        val newPassword = EditText(this)

        newPassword.setHint("Entrez votre mot de passe")
        newPassword.inputType = InputType.TYPE_CLASS_TEXT

        linearLayout.addView(newPseudo);
        linearLayout.addView(newPassword)

        builder.setView(linearLayout)

        val client =  OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor(newPseudo.text.toString(), newPassword.text.toString()))
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create();

        val retrofit = Retrofit.Builder()
            .baseUrl("10.69.0.111")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

// Set up the buttons
        builder.setPositiveButton("Créer", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            var m_Text = newPseudo.text.toString()
        })
        builder.setNegativeButton("Annuler", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }

    protected fun onFailureListener(): OnFailureListener? {
        return OnFailureListener {
            Toast.makeText(
                applicationContext,
                getString(R.string.error_unknown_error),
                Toast.LENGTH_LONG
            ).show()
        }
    }


    private fun handleResponseAfterSignIn(
        requestCode: Int,
        resultCode: Int,
        data: Intent
    ) {
        val response = IdpResponse.fromResultIntent(data)
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) { // SUCCESS
                //this.createUserInFirestore()
                Toast.makeText(getApplicationContext(), getString(R.string.connection_succeed), Toast.LENGTH_LONG).show();

            }
            else { // ERRORS
                if (response == null) {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_authentication_canceled), Toast.LENGTH_LONG).show();
                } else if (response.error?.errorCode == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_no_internet), Toast.LENGTH_LONG).show();
                } else if (response.error?.errorCode == ErrorCodes.UNKNOWN_ERROR) {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_unknown_error), Toast.LENGTH_LONG).show();
                }
            }
        }
    }



}
