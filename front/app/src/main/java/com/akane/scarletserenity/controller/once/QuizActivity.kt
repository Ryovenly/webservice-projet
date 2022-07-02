package com.akane.scarletserenity.controller.once

import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.akane.scarletserenity.R
import com.akane.scarletserenity.controller.BaseActivity
import com.akane.scarletserenity.model.character.CharacterHelper
import com.akane.scarletserenity.model.quiz.Question
import com.akane.scarletserenity.model.quiz.QuestionBank
import com.akane.scarletserenity.service.ApiCharacterGameService
import com.akane.scarletserenity.service.BasicAuthClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.util.*


class QuizActivity : BaseActivity(), View.OnClickListener {
    private var mp: MediaPlayer? = null
    private var current: LocalDateTime? = null
    private var seconde = 0
    var strength: Long? = null
    var intelligence: Long? = null
    var agility: Long? = null
    var luck: Long? = null
    var hpmax: Long? = null
    var manamax: Long? = null
    private var gQuestionTextView: TextView? = null
    private var gAnswer1Button: Button? = null
    private var gAnswer2Button: Button? = null
    private var gAnswer3Button: Button? = null
    private var gAnswer4Button: Button? = null
    private var questionBank: QuestionBank? = null
    private var currentQuestion: Question? = null
    private var numberOfQueestions = 0
    private var enableTouchEvents = false
    //var user = FirebaseAuth.getInstance().currentUser

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        //music
        var mp = MediaPlayer.create(this, R.raw.rereation)
        mp.setLooping(true)
        mp.setVolume(0.7f, 0.7f)
        mp.start()
        var current = LocalDateTime.now()
        seconde = current.getSecond()
        Log.d("time", "time $current")
        questionBank = generateQuestions()
        numberOfQueestions = 10
        enableTouchEvents = true

        // Widgets
        gQuestionTextView = findViewById(R.id.activity_game_question_text)
        randomAnswer(seconde)
        currentQuestion = questionBank!!.question
        displayQuestion(currentQuestion)
        character()
        if (savedInstanceState != null) {
            numberOfQueestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION)
        } else {
            Toast.makeText(this, "Bug exploitable !", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayQuestion(question: Question?) {
        gQuestionTextView!!.text = question!!.question
        gAnswer1Button!!.text = question.choiceList[0]
        gAnswer2Button!!.text = question.choiceList[1]
        gAnswer3Button!!.text = question.choiceList[2]
        gAnswer4Button!!.text = question.choiceList[3]
    }

    private fun generateQuestions(): QuestionBank {
        val question1 = Question(
            getString(R.string.question1),
            Arrays.asList(
                getString(R.string.a1question1),
                getString(R.string.a2question1),
                getString(R.string.a3question1),
                getString(R.string.a4question1)
            )
        )
        val question2 = Question(
            getString(R.string.question2),
            Arrays.asList(
                getString(R.string.a1question2),
                getString(R.string.a2question2),
                getString(R.string.a3question2),
                getString(R.string.a4question2)
            )
        )
        val question3 = Question(
            getString(R.string.question3),
            Arrays.asList(
                getString(R.string.a1question3),
                getString(R.string.a2question3),
                getString(R.string.a3question3),
                getString(R.string.a4question3)
            )
        )
        val question4 = Question(
            getString(R.string.question4),
            Arrays.asList(
                getString(R.string.a1question4),
                getString(R.string.a2question4),
                getString(R.string.a3question4),
                getString(R.string.a4question4)
            )
        )
        val question5 = Question(
            getString(R.string.question5),
            Arrays.asList(
                getString(R.string.a1question5),
                getString(R.string.a2question5),
                getString(R.string.a3question5),
                getString(R.string.a4question5)
            )
        )
        val question6 = Question(
            getString(R.string.question6),
            Arrays.asList(
                getString(R.string.a1question6),
                getString(R.string.a2question6),
                getString(R.string.a3question6),
                getString(R.string.a4question6)
            )
        )
        val question7 = Question(
            getString(R.string.question7),
            Arrays.asList(
                getString(R.string.a1question7),
                getString(R.string.a2question7),
                getString(R.string.a3question7),
                getString(R.string.a4question7)
            )
        )
        val question8 = Question(
            getString(R.string.question8),
            Arrays.asList(
                getString(R.string.a1question8),
                getString(R.string.a2question8),
                getString(R.string.a3question8),
                getString(R.string.a4question8)
            )
        )
        val question9 = Question(
            getString(R.string.question9),
            Arrays.asList(
                getString(R.string.a1question9),
                getString(R.string.a2question9),
                getString(R.string.a3question9),
                getString(R.string.a4question9)
            )
        )
        val question10 = Question(
            getString(R.string.question10),
            Arrays.asList(
                getString(R.string.a1question10),
                getString(R.string.a2question10),
                getString(R.string.a3question10),
                getString(R.string.a4question10)
            )
        )
        return QuestionBank(
            Arrays.asList(
                question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9,
                question10
            )
        )
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return enableTouchEvents && super.dispatchTouchEvent(ev)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onClick(v: View) {
        val responseIndex = v.tag as Int
        if (responseIndex == 0) {
            // strenght
            Toast.makeText(this, "Power !", Toast.LENGTH_SHORT).show()
            character()
            runBlocking {
                launch {
                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementHpMaxCharacter(currentPseudo,
                        5
                    )

                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementStrengthCharacter(currentPseudo,
                        20
                    )
                }
            }
//            CharacterHelper.updateStrength(user!!.uid, strength!! + 5)
//            CharacterHelper.updateHpMax(user!!.uid, strength!!)
        } else if (responseIndex == 1) {
            // intelligence
            Toast.makeText(this, "Intelligence !", Toast.LENGTH_SHORT).show()
            character()

            runBlocking {
                launch {
                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementIntelligenceCharacter(currentPseudo,
                        (5).toInt()
                    )

                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementManaMaxCharacter(currentPseudo,
                        (20).toInt()
                    )
                }
            }

//            CharacterHelper.updateIntelligence(user!!.uid, intelligence!! + 5)
//            CharacterHelper.updateManaMax(user!!.uid, intelligence!!)
        } else if (responseIndex == 2) {
            // agility
            Toast.makeText(this, "Agility  !", Toast.LENGTH_SHORT).show()
            character()

            runBlocking {
                launch {
                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementAgilityCharacter(currentPseudo,
                        5
                    )
                }
            }

           // CharacterHelper.updateAgility(user!!.uid, agility!! + 5)
        } else if (responseIndex == 3) {
            // luck
            Toast.makeText(this, "Lucky  !", Toast.LENGTH_SHORT).show()
            character()

            runBlocking {
                launch {
                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementLuckCharacter(currentPseudo,
                        5
                    )
                }
            }

         //   CharacterHelper.updateLuck(user!!.uid, luck!! + 5)
        }
        enableTouchEvents = false
        Handler().postDelayed({
            enableTouchEvents = true
            if (--numberOfQueestions == 0) {
                // Pas de questions restantes donc terminer le quiz
                endGame()
            } else {
                currentQuestion = questionBank!!.question
                displayQuestion(currentQuestion)
                var current = LocalDateTime.now()
                seconde = current.getSecond()
                //    Log.d("time", "time "+ second);
                //randomAnswer(seconde);
            }
        }, 2000) // delay
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(BUNDLE_STATE_QUESTION, numberOfQueestions)
        super.onSaveInstanceState(outState)
    }

    fun character() {
        runBlocking {
            launch {
                val response = BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                    ApiCharacterGameService::class.java).getCharacterGameByPseudo(currentPseudo)

                val content = response.body()

                strength = content!!.strength
                intelligence = content!!.intelligence
                agility = content!!.agility
                luck = content!!.luck
                hpmax = content!!.hpMax
                manamax = content!!.manaMax
            }
        }
    }




    private fun endGame() {
        val builder = AlertDialog.Builder(this)
        character()
        if (strength!! > intelligence!! && strength!! > agility!! && strength!! > luck!!) {

            runBlocking {
                launch {
                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementHpMaxCharacter(currentPseudo,
                        20
                    )

                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementStrengthCharacter(currentPseudo,
                        100
                    )
                }
            }

//            CharacterHelper.updateStrength(user!!.uid, strength!! + 20)
//            CharacterHelper.updateHpMax(user!!.uid, strength!!)

            builder.setTitle(getString(R.string.quiz_strenght_title))
                .setMessage(getString(R.string.quiz_strenght_description))
                .setPositiveButton(
                    getString(R.string.quiz_end)
                ) { dialog, which ->
                    startSynopsisActivity()
                    finish()
                }
                .create()
                .show()
        } else if (intelligence!! > strength!! && intelligence!! > agility!! && intelligence!! > luck!!) {
            runBlocking {
                launch {
                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementIntelligenceCharacter(currentPseudo,
                        20
                    )

                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementManaMaxCharacter(currentPseudo,
                        100
                    )
                }
            }

//            CharacterHelper.updateIntelligence(user!!.uid, intelligence!! + 20)
//            CharacterHelper.updateManaMax(user!!.uid, intelligence!!)

            builder.setTitle(getString(R.string.quiz_int_title))
                .setMessage(getString(R.string.quiz_int_description))
                .setPositiveButton(
                    getString(R.string.quiz_end)
                ) { dialog, which ->
                    startSynopsisActivity()
                    finish()
                }
                .create()
                .show()
        } else if (agility!! > strength!! && agility!! > intelligence!! && agility!! > luck!!) {
            runBlocking {
                launch {
                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementAgilityCharacter(currentPseudo,
                        20
                    )
                }
            }
            //CharacterHelper.updateAgility(user!!.uid, agility!! + 20)

            builder.setTitle(getString(R.string.quiz_agility_title))
                .setMessage(getString(R.string.quiz_agility_description))
                .setPositiveButton(
                    getString(R.string.quiz_end)
                ) { dialog, which ->
                    startSynopsisActivity()
                    finish()
                }
                .create()
                .show()
        } else if (luck!! > strength!! && luck!! > agility!! && luck!! > intelligence!!) {
            runBlocking {
                launch {
                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementLuckCharacter(currentPseudo,
                        20
                    )
                }
            }
           // CharacterHelper.updateLuck(user!!.uid, luck!! + 20)

            builder.setTitle(getString(R.string.quiz_luck_title))
                .setMessage(getString(R.string.quiz_luck_description))
                .setPositiveButton(
                    getString(R.string.quiz_end)
                ) { dialog, which -> startSynopsisActivity() }
                .create()
                .show()
        } else { // stats egaux

            runBlocking {
                launch {
                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementHpMaxCharacter(currentPseudo,
                        7
                    )

                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementStrengthCharacter(currentPseudo,
                        25
                    )

                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementIntelligenceCharacter(currentPseudo,
                        7
                    )

                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementManaMaxCharacter(currentPseudo,
                        25
                    )

                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementAgilityCharacter(currentPseudo,
                        7
                    )

                    BasicAuthClient<ApiCharacterGameService>(currentUsername, currentPassword).create(
                        ApiCharacterGameService::class.java).incrementLuckCharacter(currentPseudo,
                        7
                    )
                }
            }

//            CharacterHelper.updateStrength(user!!.uid, strength!! + 7)
//            CharacterHelper.updateHpMax(user!!.uid, strength!!)
//            CharacterHelper.updateIntelligence(user!!.uid, intelligence!! + 7)
//            CharacterHelper.updateManaMax(user!!.uid, intelligence!!)
//            CharacterHelper.updateAgility(user!!.uid, agility!! + 7)
//            CharacterHelper.updateLuck(user!!.uid, luck!! + 7)
            builder.setTitle(getString(R.string.quiz_all_title))
                .setMessage(getString(R.string.quiz_all_description))
                .setPositiveButton(
                    getString(R.string.quiz_end)
                ) { dialog, which -> startSynopsisActivity() }
                .create()
                .show()
        }
    }

    private fun startSynopsisActivity() {
//        mp!!.stop()
//        mp!!.release()
        val intent = Intent(this@QuizActivity, SynopsisActivity::class.java)
        startActivity(intent)
        setResult(RESULT_OK, intent)
    }

    private fun getLastSecond(second: Int): Int {
        var lastSecond = second
        if (lastSecond > 50) {
            lastSecond -= 50
        } else if (lastSecond > 40) {
            lastSecond -= 40
        } else if (lastSecond > 30) {
            lastSecond -= 30
        } else if (lastSecond > 20) {
            lastSecond -= 20
        } else if (lastSecond > 10) {
            lastSecond -= 10
        }
        return lastSecond
    }

    private fun randomAnswer(second: Int) {
        if (getLastSecond(second) >= 8) {
            gAnswer1Button = findViewById(R.id.activity_game_answer1_btn)
            gAnswer2Button = findViewById(R.id.activity_game_answer2_btn)
            gAnswer3Button = findViewById(R.id.activity_game_answer3_btn)
            gAnswer4Button = findViewById(R.id.activity_game_answer4_btn)
            seconde = 6
            Log.d("time", "time $second")
        } else if (getLastSecond(second) >= 6) {
            gAnswer1Button = findViewById(R.id.activity_game_answer2_btn)
            gAnswer2Button = findViewById(R.id.activity_game_answer1_btn)
            gAnswer3Button = findViewById(R.id.activity_game_answer4_btn)
            gAnswer4Button = findViewById(R.id.activity_game_answer3_btn)
            seconde = 4
        } else if (getLastSecond(second) >= 4) {
            gAnswer1Button = findViewById(R.id.activity_game_answer4_btn)
            gAnswer2Button = findViewById(R.id.activity_game_answer3_btn)
            gAnswer3Button = findViewById(R.id.activity_game_answer1_btn)
            gAnswer4Button = findViewById(R.id.activity_game_answer2_btn)
            seconde = 2
        } else if (getLastSecond(second) >= 2) {
            gAnswer1Button = findViewById(R.id.activity_game_answer3_btn)
            gAnswer2Button = findViewById(R.id.activity_game_answer1_btn)
            gAnswer3Button = findViewById(R.id.activity_game_answer4_btn)
            gAnswer4Button = findViewById(R.id.activity_game_answer2_btn)
            seconde = 0
        } else if (getLastSecond(second) >= 0) {
            gAnswer1Button = findViewById(R.id.activity_game_answer3_btn)
            gAnswer2Button = findViewById(R.id.activity_game_answer1_btn)
            gAnswer3Button = findViewById(R.id.activity_game_answer4_btn)
            gAnswer4Button = findViewById(R.id.activity_game_answer2_btn)
            seconde = 8
        }
        gAnswer1Button!!.tag = 0
        gAnswer2Button!!.tag = 1
        gAnswer3Button!!.tag = 2
        gAnswer4Button!!.tag = 3
        gAnswer1Button!!.setOnClickListener(this)
        gAnswer2Button!!.setOnClickListener(this)
        gAnswer3Button!!.setOnClickListener(this)
        gAnswer4Button!!.setOnClickListener(this)
    }

    companion object {
        const val BUNDLE_STATE_QUESTION = "currentQuestion"
    }
}