package com.example.travelapp.UI

import ViewModel.LandmarkViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.travelapp.Model.Landmark

import com.example.travelapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class LandmarkUserInputs : Fragment() {


    private lateinit var viewModel: LandmarkViewModel
    private val args by navArgs<LandmarkUserInputsArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_landmark_user_inputs, container, false)
        val backButton = view.findViewById<FloatingActionButton>(R.id.backFromLandmarkInputs)
        val btnCreate = view.findViewById<Button>(R.id.createLandmarkButton)

        var cityId = args.currentCityId


        val landmarkNameInput = view.findViewById<EditText>(R.id.landmarkNameInput)
        val landmarkDescriptionInput = view.findViewById<EditText>(R.id.landmarkDescriptionInput)

        // Initialize the LandmarkViewModel
        viewModel = ViewModelProvider(this).get(LandmarkViewModel::class.java)

        btnCreate.setOnClickListener {
            // Get the user input data
            val landmarkName = landmarkNameInput.text.toString().trim()
            val landmarkDescription = landmarkDescriptionInput.text.toString().trim()

            // Perform input validation, if necessary
            if (landmarkName.isNotEmpty() && landmarkDescription.isNotEmpty()) {
                // Create a new Landmark object with the user input and cityId
                val newLandmark =
                    Landmark(landmarkId = 0, cityId = cityId, landmarkName, landmarkDescription)

                viewModel.insertLandmark(newLandmark)
                Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

                // After inserting, navigate back to the LandmarkFragment
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "All fields required!", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }

}