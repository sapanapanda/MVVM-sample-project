package com.sapana.androidapps.spacexrocket.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sapana.androidapps.spacexrocket.R
import com.sapana.androidapps.spacexrocket.data.model.Rocket


class RocketListAdapter : RecyclerView.Adapter<RocketListAdapter.ViewHolder>() {
	private lateinit var rocketList: List<Rocket>
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val binding: com.sapana.androidapps.spacexrocket.databinding.ItemRocketBinding =
			DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_rocket, parent, false)
		return ViewHolder(binding)
	}
	
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(rocketList[position])
	}
	
	override fun getItemCount(): Int {
		return if (::rocketList.isInitialized) rocketList.size else 0
	}
	
	fun updateRocketList(rocketList: List<Rocket>) {
		this.rocketList = rocketList
		notifyDataSetChanged()
	}
	
	class ViewHolder(private val binding: com.sapana.androidapps.spacexrocket.databinding.ItemRocketBinding) :
		RecyclerView.ViewHolder(binding.root) {
		
		var viewModel: RocketViewModel = RocketViewModel()
		
		fun bind(rocket: Rocket) {
			viewModel.bind(rocket)
			binding.viewModel = viewModel
		}
	}
}