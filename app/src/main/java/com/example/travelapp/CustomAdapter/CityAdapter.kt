package com.example.travelapp.CustomAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.Model.City
import com.example.travelapp.R
import com.example.travelapp.UI.CityFragmentDirections

class CityAdapter(private var cityList: List<City>) :
    RecyclerView.Adapter<CityAdapter.ViewHolder>()
{
//     private val onClickEvent: CityListener
//    private var cityList = emptyList<City>()


    interface OnLongClickListener {
        fun onItemLongClicked(city: City)
    }

    private var longClickListener: OnLongClickListener? = null

    fun setOnLongClickListener(listener: OnLongClickListener) {
        this.longClickListener = listener
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val cityNameView = itemView.findViewById<TextView>(R.id.cardCity)
        val descriptionView =itemView.findViewById<TextView>(R.id.cardDescription)
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val cardView = inflater.inflate(R.layout.card_view, parent, false)
        // Return a new holder instance
        return ViewHolder(cardView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get the data model based on position
        val currentItem = cityList[position]

        viewHolder.cityNameView.text =currentItem.cityName
        viewHolder.descriptionView.text =currentItem.cityDescription
        // Set item views based on your views and data model

        viewHolder.itemView.setOnClickListener {
            val currentCityName =currentItem.cityName.toString()
            val currentCityId =currentItem.id
            val action =CityFragmentDirections.navigateToLandmark(currentCityName,currentCityId)
            viewHolder.itemView.findNavController().navigate(action)
        }

        viewHolder.itemView.setOnLongClickListener {
            longClickListener?.onItemLongClicked(currentItem)
            true
        }



    }

    fun setData(city:List<City>) {
        this.cityList =city
        notifyDataSetChanged()
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return cityList.size
    }
}