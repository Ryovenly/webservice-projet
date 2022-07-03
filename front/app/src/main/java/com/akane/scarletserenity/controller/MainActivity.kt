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
import com.akane.scarletserenity.model.webservice.User
import com.akane.scarletserenity.service.ApiClient
import com.akane.scarletserenity.service.ApiUserService
import com.akane.scarletserenity.service.BasicAuthClient
import com.akane.scarletserenity.service.BasicAuthInterceptor
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.ads.doubleclick.PublisherAdView
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : BaseActivity() {


    private lateinit var mp: MediaPlayer
    private var totalTime: Int = 0

    lateinit var mPublisherAdView : PublisherAdView



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


// Create and launch sign-in intent

        mBtLogin.setOnClickListener {
            runBlocking {
                launch {
                    loadProfile(editTextTextPseudo.text.toString(),editTextTextPassword.text.toString())
                }
            }
        }

        mBtInscription.setOnClickListener {
            showdialogInscription()
        }



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

// Set up the buttons
        builder.setPositiveButton("Créer", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            var m_Text = newPseudo.text.toString()
            runBlocking {
                launch {
                    createCharacter(newPseudo.text.toString(), newPassword.text.toString())
                }
            }

        })
        builder.setNegativeButton("Annuler", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }

    suspend fun createCharacter(username:String, password:String) {

        try {
            var user = User(username,password,"USER")

            if (editTextUrl.text.toString() != ""){
                url = "http://" + editTextUrl.text.toString()
            }

            val response = ApiClient.apiService.createUser(user)

            if (response.isSuccessful && response.body() != null) {

                Toast.makeText(
                    this@MainActivity,
                    "Personnage bien crée !",
                    Toast.LENGTH_LONG
                ).show()

                currentUsername = username
                currentPassword = password

                this.startCharacterActivity()
                //do something
            } else {
                Log.e("error", response.message())
                Toast.makeText(
                    this@MainActivity,
                    "Error Occurred: ${response.message()}",
                    Toast.LENGTH_LONG
                ).show()
            }

        } catch (e: Exception) {
            Log.e("error", e.message)
            Toast.makeText(
                this@MainActivity,
                "Error Occurred: ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    suspend fun loadProfile(username:String, password:String) {

        try {

            if (editTextUrl.text.toString() != ""){
                url = "http://" + editTextUrl.text.toString()
            }
            
            val response = BasicAuthClient<ApiUserService>(username, password).create(ApiUserService::class.java).getUserByUsername(username)

            if (response.isSuccessful && response.body() != null) {
                val content = response.body()
                Log.e("test", content?.username)

                currentUsername = username
                currentPassword = password

                currentUser = content!!

                this.startCharacterActivity()
                mp.stop()

                //do something
            } else {
                Log.e("error", response.message())
                Toast.makeText(
                    this@MainActivity,
                    "Erreur, identifiants incorrectes",
                    Toast.LENGTH_LONG
                ).show()
            }

        } catch (e: Exception) {
            Log.e("error", e.message + e.cause)
            Toast.makeText(
                this@MainActivity,
                "Error Occurred: ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }

    }




}
