package com.krishanshamod.simple_android_application

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.krishanshamod.simple_android_application.api.UserAPIService
import com.krishanshamod.simple_android_application.databinding.FragmentSecondBinding
import com.krishanshamod.simple_android_application.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val userAPIService = UserAPIService.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get user details from the server
        val user = userAPIService.getUser(FirstFragment.userID.toString())

        user.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val body = response.body()

                body.let {
                    // Show user details or error if ID is not valid
                    if (it != null) {
                        binding.textViewName.text = it.name
                        binding.textViewEmail.text = it.email
                        binding.textViewWebsite.text = it.website
                    } else {
                        binding.textViewError.text = "Please enter valid user ID"
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // will update in the future
            }
        })

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}