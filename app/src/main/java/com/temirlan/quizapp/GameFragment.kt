package com.temirlan.quizapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.temirlan.quizapp.databinding.FragmentGameBinding
import kotlinx.android.synthetic.main.activity_quiz.*

class GameFragment : Fragment() {

    private var mCurrentPosition:Int = 1
    private var mQuestionList:ArrayList<Question>? = null
    private var mSelectedOptionPosition:Int = 0
    private var mCorrectAnswers:Int = 0
    private var mUsername: String? = null
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false )

        mQuestionList = Constants.getQuestion()
        //adding username to store it

        setQuestion()
        val bundle = arguments

        mUsername = bundle!!.getSerializable(Constants.USER_NAME) as String?




        binding.submit.setOnClickListener { view : View ->

                binding.a1.setOnClickListener {
                    selectedOptionView(binding.a1, 1)
                }
                binding.a2.setOnClickListener {
                    selectedOptionView(binding.a2, 2)
                }
                binding.a3.setOnClickListener {
                    selectedOptionView(binding.a3, 3)
                }
                binding.a4.setOnClickListener {
                    selectedOptionView(binding.a4, 4)
                }
                binding.submit.setOnClickListener {
                    if (mSelectedOptionPosition == 0) {
                        mCurrentPosition++

                        when {
                            mCurrentPosition <= mQuestionList!!.size -> {
                                setQuestion()
                            }
                            else -> {
//
                                val bundle = Bundle()
                                //instead of putExtra we put putSerializable
                                bundle.putSerializable(Constants.USER_NAME, mUsername)
                                bundle.putSerializable(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                                bundle.putSerializable(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                                view?.findNavController()
                                    ?.navigate(R.id.action_gameFragment_to_resultFragment, bundle)
                            }
                        }
                    } else {
                        val question = mQuestionList?.get(mCurrentPosition - 1)
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
        return binding.root
    }

    private fun setQuestion() {

        val question = mQuestionList!!.get(mCurrentPosition - 1)

        defaultOptionsView()

        if (mCurrentPosition == mQuestionList!!.size) {
            binding.submit.text = "FINISH"
        } else {
            binding.submit.text = "SUBMIT"
        }

        binding.pBar.progress = mCurrentPosition
        binding.progress.text = "$mCurrentPosition" + "/" + binding.pBar.max

        binding.tvQuestion.text = question!!.question
        binding.image.setImageResource(question.image)

        binding.a1.text = question.optionOne
        binding.a2.text = question.optionTwo
        binding.a3.text = question.optionThree
        binding.a4.text = question.optionFour

    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView> ()
        options.add(0, binding.a1)
        options.add(1, binding.a2)
        options.add(2, binding.a3)
        options.add(3, binding.a4)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = activity?.let {
                ContextCompat.getDrawable(
                    it,
                    R.drawable.border
                )
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#2196F5"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = activity?.let {
            ContextCompat.getDrawable(
                it,
                R.drawable.selected_border
            )
        }
    }
    private fun answerView(answer: Int, drawableView: Int) {
        when(answer) {
            1 -> {
                binding.a1.background = activity?.let {
                    ContextCompat.getDrawable(
                        it, drawableView
                    )
                }
            }
            2 -> {
                binding.a2.background = activity?.let {
                    ContextCompat.getDrawable(
                        it, drawableView
                    )
                }
            }
            3 -> {
                binding.a3.background = activity?.let {
                    ContextCompat.getDrawable(
                        it, drawableView
                    )
                }
            }
            4 -> {
                binding.a4.background = activity?.let {
                    ContextCompat.getDrawable(
                        it, drawableView
                    )
                }
            }
        }
    }
}