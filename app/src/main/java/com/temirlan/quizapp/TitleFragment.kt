package com.temirlan.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.temirlan.quizapp.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTitleBinding.inflate(inflater, container, false)
        val fragment = GameFragment();

        binding.titleBtn.setOnClickListener{
            if (binding.etName.text.toString().isEmpty()) {

                Toast.makeText(context  ,"Please enter your name", Toast.LENGTH_SHORT).show()

            } else {
                val bundle = Bundle()
                bundle.putSerializable(Constants.USER_NAME, binding.etName.text.toString())
                view?.findNavController()?.navigate(R.id.action_titleFragment_to_gameFragment, bundle)
            }
        }

        return binding.root
    }
}