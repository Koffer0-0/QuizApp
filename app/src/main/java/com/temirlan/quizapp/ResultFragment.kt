package com.temirlan.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.temirlan.quizapp.databinding.FragmentResultBinding
import kotlinx.android.synthetic.main.activity_result.*

class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentResultBinding>(inflater,
            R.layout.fragment_result,container,false)

        val username = intent.getStringExtra(Constants.USER_NAME)
        usr.text = username

        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        val total = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        score.text = "Your score is $correctAnswers out of $total"

        binding.btnFinish.setOnClickListener{ view: View->
            view.findNavController()
                .navigate(R.id.action_resultFragment_to_titleFragment)}


        return binding.root
    }

}