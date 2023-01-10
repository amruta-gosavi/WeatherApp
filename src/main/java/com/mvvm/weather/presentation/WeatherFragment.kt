package com.mvvm.weather.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.weather.R
import com.mvvm.weather.data.utils.ConnectionUtils
import com.mvvm.weather.data.utils.Constants
import com.mvvm.weather.data.utils.InjectorUtils
import com.mvvm.weather.databinding.WeatherFragmentBinding
import com.mvvm.weather.presentation.adapters.WeatherAdapter
import com.mvvm.weather.presentation.listeners.ActionListener
import com.mvvm.weather.presentation.viewmodel.WeatherViewModel

/**
 * This is the dashboard fragment which shows weather data for current week.
 */
class WeatherFragment : Fragment(), ActionListener {

    private lateinit var _binding: WeatherFragmentBinding
    private val binding get() = _binding
    private lateinit var viewModel: WeatherViewModel
    private var adapter: WeatherAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = WeatherFragmentBinding.inflate(inflater, container, false)
        _binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationFactory = InjectorUtils.provideWeatherViewModelFactory(requireContext())
        viewModel = ViewModelProvider(
            this,
            locationFactory
        )[WeatherViewModel::class.java]
        binding.viewModel = viewModel
        viewModel.userLocation.value = Constants.NEW_YORK_LOCATION
        viewModel.weatherData.observe(viewLifecycleOwner, Observer { data ->
            adapter?.apply {
                records = data
                notifyDataSetChanged()
            }
        })
        binding.buttonFirst.setOnClickListener {
        }
        setUpView()
    }

    override fun onViewClicked(pos: Int) {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    private fun setUpView() {
        adapter = WeatherAdapter(this)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        if (!ConnectionUtils().isOnline(requireContext())) {
            showError(requireContext().resources.getString(R.string.alert_no_internet))
        }
    }

    private fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }
}