package com.akane.scarletserenity.controller.character

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.akane.scarletserenity.R
import com.akane.scarletserenity.controller.BaseActivity
import com.akane.scarletserenity.controller.MainActivity
import com.akane.scarletserenity.controller.once.CreateCharacterActivity
import com.akane.scarletserenity.model.webservice.CharacterGame
import com.akane.scarletserenity.service.ApiCharacterGameService
import com.akane.scarletserenity.service.ApiUserService
import com.akane.scarletserenity.service.BasicAuthClient
import com.firebase.ui.auth.AuthUI
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_character.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class CharacterActivity : BaseActivity()  {
    //val email = user?.email
    //val photoUrl = user?.photoUrl
//    var modelCurrentUser: User? =
//        User("", "")
    lateinit var modelCharacter: CharacterGame
    val TAG = "chaud"


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        //createUserInFirestore()
        val mEmail = findViewById<TextView>(R.id.user_mail) as TextView
        mEmail.text = currentUsername
        val mlogout = findViewById<Button>(R.id.bt_logout) as Button
//        getDocument()
//        checkCharacter()

        speech(getString(R.string.characterSpeech))

        runBlocking {
            launch {
                loadProfileUser(currentUsername, currentPassword)
                loadProfile(currentUsername, currentPassword)
            }
        }


//        Glide.with(this)
//            .load(photoUrl)
//            .apply(RequestOptions.circleCropTransform())
//            .into(findViewById<ImageView>(R.id.photo_url) as ImageView)

        mlogout.setOnClickListener(){
            signOutUserFromFirebase()
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

    private fun signOutUserFromFirebase() {
        AuthUI.getInstance().signOut(this)
        startMainActivity()
    }
    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

//    @SuppressLint("ResourceType")
//    private fun checkCharacter() {
//        val docRef = CharacterHelper.getCharacter(user?.uid)
//        val mAvatar = findViewById<ImageView>(R.id.iv_character) as ImageView
//        docRef
//            .addOnSuccessListener { document ->
//
//                if (document.data.isNullOrEmpty()){
//                    // go create character activity
//                    mAvatar.setImageResource(R.drawable.add_character)
//                    var mContent = findViewById<TextView>(R.id.tv_content) as TextView
//                    mContent.setText(R.string.content_createcharacter)
//                    Log.d(TAG, "Y a pas de personnage encore $document")
//                    mAvatar.setOnClickListener {
//                        startCreateCharacterActivity()
//                    }
//
//                } else {
//                    // go Menu character Activity
//                    val ava = R.drawable.female_1
//                    mAvatar.setImageResource(ava)
//                    getCharacter()
//                    mAvatar.setOnClickListener {
//                        startMainCharacterActivity()
//                    }
//
//                    Log.d(TAG, "Y a un personnage")
//
//
//                }
//            }
//    }

    suspend fun loadProfile(username:String, password:String) {

        val mAvatar = findViewById<ImageView>(R.id.iv_character) as ImageView

        try {
            val response = BasicAuthClient<ApiCharacterGameService>(username, password).create(ApiCharacterGameService::class.java).getCharacterGameByUser(username)


            if (response.isSuccessful && response.body() != null) {
                val content = response.body()
                Log.e("test", content?.pseudo)

                currentPseudo = content?.pseudo!!

                modelCharacter = content!!

                val response2 = BasicAuthClient<ApiCharacterGameService>(username, password).create(ApiCharacterGameService::class.java).incrementHpMaxCharacter(modelCharacter.pseudo!!, 2)

                Log.e("test", response2?.message())

                // go Menu character Activity
                val ava = R.drawable.female_1
                mAvatar.setImageResource(ava)
                attributesCharacter()
              //  getCharacter()
                mAvatar.setOnClickListener {
                    startMainCharacterActivity()
                }

                Log.d(TAG, "Y a un personnage")

                //do something
            } else {

                mAvatar.setImageResource(R.drawable.add_character)
                var mContent = findViewById<TextView>(R.id.tv_content) as TextView
                mContent.setText(R.string.content_createcharacter)
                Log.d(TAG, "Y a pas de personnage encore")
                mAvatar.setOnClickListener {
                    startCreateCharacterActivity()
                }

                Log.e("error", response.message())
                Toast.makeText(
                    this@CharacterActivity,
                    "Pas de personnage actuellement, veuillez en créez un",
                    Toast.LENGTH_LONG
                ).show()
            }

        } catch (e: Exception) {
            Log.e("error", e.message)

            mAvatar.setImageResource(R.drawable.add_character)
            var mContent = findViewById<TextView>(R.id.tv_content) as TextView
            mContent.setText(R.string.content_createcharacter)
            Log.d(TAG, "Y a pas de personnage encore")
            mAvatar.setOnClickListener {
                startCreateCharacterActivity()
            }

            Toast.makeText(
                this@CharacterActivity,
                "Pas de personnage actuellement, veuillez en créez un",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    suspend fun loadProfileUser(username:String, password:String) {

        try {
            val response = BasicAuthClient<ApiUserService>(username, password).create(ApiUserService::class.java).getUserByUsername(username)

            if (response.isSuccessful && response.body() != null) {
                val content = response.body()
                Log.e("test", content?.username)
                currentUser = content!!

                //do something
            } else {
                Log.e("error", response.message())
                Toast.makeText(
                    this@CharacterActivity,
                    "Erreur, identifiants incorrectes",
                    Toast.LENGTH_LONG
                ).show()
            }

        } catch (e: Exception) {
            Log.e("error", e.message + e.cause)
            Toast.makeText(
                this@CharacterActivity,
                "Error Occurred: ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }

    }

//    private fun getDocument() {
//        UserHelper.getUser(user?.uid)
//            .addOnSuccessListener { document ->
//                if (document != null) {
//                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
//                    modelCurrentUser = document.toObject(User::class.java)
//                    Log.d(TAG, "user: $modelCurrentUser")
//                    var mName = findViewById<TextView>(R.id.user_name) as TextView
//                    mName.text = modelCurrentUser?.username
//                } else {
//                    Log.d(TAG, "No such document")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(TAG, "get failed with ", exception)
//            }
//    }

    @SuppressLint("SetTextI18n")
    private fun attributesCharacter(){
        val mContent = findViewById<TextView>(R.id.tv_content) as TextView
        mContent.setText(R.string.content_character)
        val mCharacterName = findViewById<TextView>(R.id.tv_characterName) as TextView
        mCharacterName.text = modelCharacter.pseudo

        val mStrength = findViewById<TextView>(R.id.tv_strength) as TextView
        val mIntelligence = findViewById<TextView>(R.id.tv_intelligence) as TextView
        val mAgility = findViewById<TextView>(R.id.tv_agility) as TextView
        val mLuck = findViewById<TextView>(R.id.tv_luck) as TextView

        mStrength.text =
            "${getString(R.string.strength)}: ${modelCharacter.strength}"
        mIntelligence.text =
            "${getString(R.string.intelligence)}: ${modelCharacter.intelligence}"
        mAgility.text =
            "${getString(R.string.agility)}: ${modelCharacter.agility}"
        mLuck.text =
            "${getString(R.string.luck)}: ${modelCharacter.luck}"

        val gender= modelCharacter?.gender

        if (gender == "FEMALE"){
            iv_character.setImageResource(R.drawable.female_1)
        } else {
            iv_character.setImageResource(R.drawable.male_1)
        }
    }

//    private fun getCharacter() {
//        CharacterHelper.getCharacter(user?.uid)
//            .addOnSuccessListener { documentSnapshot ->
//                if (documentSnapshot != null) {
//                    modelCharacter = documentSnapshot.toObject(Character::class.java)!!
//                    attributesCharacter()
//
//                } else {
//                    Log.d(TAG, "No such document")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(TAG, "get failed with ", exception)
//            }
//    }

    private fun startCreateCharacterActivity() {
        val intent = Intent(this, CreateCharacterActivity::class.java)
        startActivity(intent)
    }

}

