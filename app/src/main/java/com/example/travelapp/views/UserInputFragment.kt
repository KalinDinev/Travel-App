package com.example.travelapp.views

import com.example.travelapp.viewModel.CityViewModel
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.travelapp.model.City
import com.example.travelapp.databinding.FragmentUserInputBinding


class UserInputFragment : Fragment() {

    private lateinit var myCityViewModel: CityViewModel
    private lateinit var binding: FragmentUserInputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUserInputBinding.inflate(inflater, container, false)
        val root = binding.root
//        val view = inflater.inflate(R.layout.fragment_user_input, container, false)
        val btnCreate = binding.createCityButton
        val btnBack = binding.backFromUserInput

        myCityViewModel = ViewModelProvider(this)[CityViewModel::class.java]


        btnBack.setOnClickListener {

            val action = UserInputFragmentDirections.fromUserInputToCityFragment()
            findNavController().navigate(action)
        }

        btnCreate.setOnClickListener {
            insertDataToDatabase()

        }

        return root
    }

    private fun insertDataToDatabase() {
        val userCityInput = binding.cityNameInput.text.toString()
        val userDescriptionInput = binding.decriptionNameInput.text.toString()

        if (checkInputs(userCityInput, userDescriptionInput)) {
            val userInputs = City(0, userCityInput, userDescriptionInput)
            myCityViewModel.addCity(userInputs)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

            val action = UserInputFragmentDirections.fromUserInputToCityFragment()

            findNavController().navigate(action)
        } else {
            binding.cityNameInput.error ="This field cannot be empty!"
            binding.decriptionNameInput.error ="This field cannot be empty!"
        }

    }

    private fun checkInputs(cityName: String, cityDescription: String): Boolean {
        return !(TextUtils.isEmpty(cityName) && TextUtils.isEmpty(cityDescription))
    }
}