package com.example.travelapp.CustomAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.Model.Landmark
import com.example.travelapp.R

class LandmarkAdapter(private var landmarkList: List<Landmark>) :
    RecyclerView.Adapter<LandmarkAdapter.ViewHolder>() {


    interface OnLongClickListener {
        fun onItemLongClicked(landmark: Landmark)
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
        val landmarkNameView = itemView.findViewById<TextView>(R.id.cardCity)
        val landmarkDescription = itemView.findViewById<TextView>(R.id.cardDescription)
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val landmarkView = inflater.inflate(R.layout.card_view, parent, false)
        // Return a new holder instance
        return ViewHolder(landmarkView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: LandmarkAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val currentItem: Landmark = landmarkList.get(position)
        // Set item views based on your views and data model
        val landmarkView = viewHolder.landmarkNameView
        landmarkView.setText(currentItem.landmarkName)

        val descriptionView = viewHolder.landmarkDescription
        descriptionView.text = currentItem.landmarkDescription


        viewHolder.itemView.setOnLongClickListener {
            longClickListener?.onItemLongClicked(currentItem)
            true
        }

    }

    fun setData(landmark: List<Landmark>) {
        this.landmarkList = landmark
        notifyDataSetChanged()
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return landmarkList.size
    }
}
