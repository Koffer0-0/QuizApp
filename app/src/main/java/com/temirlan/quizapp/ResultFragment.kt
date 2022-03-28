package com.temirlan.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.temirlan.quizapp.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val bundle = arguments

        val username = bundle!!.getSerializable(Constants.USER_NAME) as String?
        binding.usr.text = username

        val correctAnswers = bundle!!.getSerializable(Constants.CORRECT_ANSWERS) as Int?
        val total = bundle!!.getSerializable(Constants.TOTAL_QUESTIONS) as Int?

        binding.score.text = "Your score is $correctAnswers out of $total"

        binding.btnFinish.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_resultFragment_to_titleFragment)
        }

        return binding.root
    }

}