package com.example.travelapp.UI

import ViewModel.CityViewModel
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.travelapp.Model.City
import com.example.travelapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class UserInputFragment : Fragment() {

    private lateinit var myCityViewModel: CityViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_input, container, false)
        val btnCreate = view.findViewById<Button>(R.id.createCityButton)
        val btnBack = view.findViewById<FloatingActionButton>(R.id.backFromUserInput)

        myCityViewModel = ViewModelProvider(this)[CityViewModel::class.java]


        btnBack.setOnClickListener {
            findNavController().navigate(R.id.cityFragment)
        }

        btnCreate.setOnClickListener {
            insertDataToDatabase()

        }

        return view
    }

    private fun insertDataToDatabase() {
        val userCityInput = view?.findViewById<EditText>(R.id.cityNameInput)?.text.toString()
        val userDecriptionInput =
            view?.findViewById<EditText>(R.id.decriptionNameInput)?.text.toString()

        if (checkInputs(userCityInput, userDecriptionInput)) {
            val userInputs = City(0, userCityInput, userDecriptionInput)
            myCityViewModel.addCity(userInputs)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.cityFragment)
        } else {
            Toast.makeText(requireContext(), "All fields required!", Toast.LENGTH_LONG).show()
        }

    }

    private fun checkInputs(cityName: String, cityDescription: String): Boolean {
        return !(TextUtils.isEmpty(cityName) && TextUtils.isEmpty(cityDescription))
    }
}