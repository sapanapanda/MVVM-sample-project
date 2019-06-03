package com.sapana.androidapps.spacexrocket.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.sapana.androidapps.spacexrocket.utils.MarginItemDecoration
import com.sapana.androidapps.spacexrocket.R
import com.sapana.androidapps.spacexrocket.di.factory.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
	
	private lateinit var binding: com.sapana.androidapps.spacexrocket.databinding.ActivityMainBinding
	private lateinit var viewModel: RocketListViewModel
	private var errorSnackbar: Snackbar? = null
	
	
	@Inject
	internal lateinit var viewModelFactory: ViewModelFactory
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		AndroidInjection.inject(this)
		
		val adapter = RocketListAdapter()
		
		rocket_list.layoutManager = LinearLayoutManager(this).apply {
			orientation = LinearLayoutManager.VERTICAL
			
		}
		rocket_list.addItemDecoration(
			MarginItemDecoration(
				resources.getDimension(R.dimen.default_padding).toInt()
			)
		)
		rocket_list.adapter = adapter
		
		viewModel = ViewModelProviders.of(this, viewModelFactory).get(RocketListViewModel::class.java)
		
		viewModel.fetchRocketList()
		viewModel.getError().observe(this, Observer { errorMessage ->
			if (errorMessage != null) showError(errorMessage) else hideError()
		})
		
		viewModel.getResponseData().observe(this, Observer {
			adapter.updateRocketList(it ?: emptyList())
		})
		binding.viewModel = viewModel
	}
	
	private fun showError(@StringRes errorMessage: Int) {
		errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
		errorSnackbar?.setAction(R.string.retry, viewModel.getErrorClick())
		errorSnackbar?.show()
	}
	
	private fun hideError() {
		errorSnackbar?.dismiss()
	}
}
