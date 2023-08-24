package com.example.travelapp.views

import com.example.travelapp.viewModel.LandmarkViewModel
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.customAdapter.LandmarkAdapter
import com.example.travelapp.model.Landmark
import com.example.travelapp.databinding.LandmarkFragmentBinding


class LandmarkFragment : Fragment(), LandmarkAdapter.OnLongClickListener {

    private val args by navArgs<LandmarkFragmentArgs>()
    private lateinit var viewModel: LandmarkViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: LandmarkFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = LandmarkFragmentBinding.inflate(inflater, container, false)
        val root = binding.root
        recyclerView = binding.landmarkFragmentView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val cityId = args.currentCityId

        val cityNameInput = binding.cityNameInput
        val btnBack = binding.backFromLandmark
        cityNameInput.text = args.cityName

        viewModel = ViewModelProvider(this).get(LandmarkViewModel::class.java)
        viewModel.getLandmarksForCity(cityId).observe(viewLifecycleOwner, Observer { landmarks ->
            val adapter = LandmarkAdapter(landmarks)
            adapter.setOnLongClickListener(this)
            recyclerView.adapter = adapter


        })


        val createLandmarkButton = binding.landmarkAddButton
        createLandmarkButton.setOnClickListener {
// maria add arguments

            val action = LandmarkFragmentDirections.fromLandmarkFragmentToLandmarkUserInput(
                cityId, args.cityName
            )
            findNavController().navigate(action)
        }


        btnBack.setOnClickListener {
            val action = LandmarkFragmentDirections.fromLandmarkFragmentToCityFragment()

            findNavController().navigate(action)
        }

        return root
    }

    override fun onItemLongClicked(landmark: Landmark) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.apply {
            setTitle("Delete Landmark")
            setMessage("Are you sure you want to delete this landmark?")
            setPositiveButton("Yes") { _, _ ->
                // Delete the landmark here
                viewModel.deleteLandmark(landmark)
                Toast.makeText(
                    context, "Landmark deleted!", Toast.LENGTH_SHORT
                ).show()
                // Call the deleteLandmark function in your LandmarkViewModel passing the 'landmark' object
                // viewModel.deleteLandmark(landmark)
            }
            setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
        }.create().show()
    }


}