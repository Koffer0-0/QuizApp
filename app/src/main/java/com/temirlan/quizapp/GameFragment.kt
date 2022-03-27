package com.temirlan.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.temirlan.quizapp.databinding.FragmentGameBinding
import kotlinx.android.synthetic.main.activity_quiz.*

class GameFragment : Fragment() {

    private var mCurrentPosition:Int = 1
    private var mQuestionList:ArrayList<Question>? = null
    private var mSelectedOptionPosition:Int = 0
    private var mCorrectAnswers:Int = 0
    private var mUsername: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater,
            R.layout.fragment_game,container,false)

        //adding username to store it
        mUsername = binding.getStringExtra(Constants.USER_NAME)

        mQuestionList = Constants.getQuestion()
        setQuestion()

        a1.setOnClickListener(this)
        a2.setOnClickListener(this)
        a3.setOnClickListener(this)
        a4.setOnClickListener(this)

        binding.submit.setOnClickListener { view : View ->
            view.findNavController()
                .navigate(R.id.action_gameFragment_to_resultFragment)
        }

        return binding.root
    }

    private fun setQuestion() {

        val question = mQuestionList!!.get(mCurrentPosition - 1)

        defaultOptionsView()

        if (mCurrentPosition == mQuestionList!!.size) {
            submit.text = "FINISH"
        } else {
            submit.text = "SUBMIT"
        }

        pBar.progress = mCurrentPosition
        progress.text = "$mCurrentPosition" + "/" + pBar.max

        tv_question.text = question!!.question
        image.setImageResource(question.image)
        a1.text = question.optionOne
        a2.text = question.optionTwo
        a3.text = question.optionThree
        a4.text = question.optionFour

    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView> ()
        options.add(0, a1)
        options.add(1, a2)
        options.add(2, a3)
        options.add(3, a4)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.border
            )
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#2196F5"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_border
        )
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when(answer) {
            1 -> {
                a1.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                a2.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                a3.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                a4.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.a1 -> {
                selectedOptionView(a1, 1)
            }
            R.id.a2 -> {
                selectedOptionView(a2, 2)
            }
            R.id.a3 -> {
                selectedOptionView(a3, 3)
            }
            R.id.a4 -> {
                selectedOptionView(a4, 4)
            }
            R.id.submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        } else -> {
//                            Toast
//                                .makeText(this, "Congrats", Toast.LENGTH_SHORT)
//                                .show()
                        val intent = Intent(
                            this,
                            ResultActivity::class.java
                        )
                        intent.putExtra(Constants.USER_NAME, mUsername)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                        startActivity(intent)
                    }
                    }
                } else {
                    val question = mQuestionList?.get(mCurrentPosition -1)
                    if (question!!.optionAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.incorrect)
                    } else {
                        mCorrectAnswers++
                    }
                    answerView(mSelectedOptionPosition, R.drawable.correct)

                    if (mCurrentPosition == mQuestionList!!.size) {
                        submit.text = "FINISH"
                    } else {
                        submit.text = "NEXT"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }
}