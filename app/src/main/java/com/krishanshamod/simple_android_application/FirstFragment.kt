package com.krishanshamod.simple_android_application

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.krishanshamod.simple_android_application.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // To pass the ID from here to the fragment two easily
    companion object {
        public var userID: Int = 0
            get() = field
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            binding.apply {
                // Handled the error if user doesn't input anything
                try {
                    // Get the input from the user
                    userID = editTextNumber.text.toString().toInt()

                    // Navigate to the fragment two if user entered any value
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

                } catch (e: Exception) {
                    // will update in the future
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}