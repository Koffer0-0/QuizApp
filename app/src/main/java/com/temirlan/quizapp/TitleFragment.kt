package com.temirlan.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.temirlan.quizapp.databinding.FragmentTitleBinding
import kotlinx.android.synthetic.main.activity_main.*

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
            R.layout.fragment_title,container,false)

        binding.titleBtn.setOnClickListener{
                view : View ->
            if (et_name.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
                intent.putExtra(Constants.USER_NAME, et_name.text.toString())
            }
        }

        return binding.root

    }
}