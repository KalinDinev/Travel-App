package com.example.travelapp.views

import com.example.travelapp.viewModel.CityViewModel
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.navArgs
import com.example.travelapp.model.City
import com.example.travelapp.databinding.CityFragmentBinding
import com.example.travelapp.customAdapter.CityAdapter as CityAdapter


class CityFragment() : Fragment(), CityAdapter.OnLongClickListener {

    private val args by navArgs<CityFragmentArgs>()
    private lateinit var myCityViewModel: CityViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding:CityFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = CityFragmentBinding.inflate(inflater,container,false)
        val root =binding.root

        // Inflate the layout for this fragment
//        val view = inflater.inflate(city_fragment, container, false)
        recyclerView = binding.cityFragmentView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        myCityViewModel = ViewModelProvider(this).get(CityViewModel::class.java)
        myCityViewModel.readAllData.observe(viewLifecycleOwner, Observer { city ->
            val adapter = CityAdapter(city)
            adapter.setOnLongClickListener(this)
            recyclerView.adapter = adapter
        })

        val floatingAddButton = binding.cityAddButton

        floatingAddButton.setOnClickListener {
            val action = CityFragmentDirections.navigateToUserInputFragment()
            findNavController().navigate(action)
        }

        return root
    }

    override fun onItemLongClicked(city: City) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.apply {
            setTitle("Delete City")
            setMessage("Are you sure you want to delete this city?")
            setPositiveButton("Yes") { _, _ ->
                // Check if the city has landmarks
                myCityViewModel.deleteCity(city)
            }
            setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
        }.create().show()
    }

}