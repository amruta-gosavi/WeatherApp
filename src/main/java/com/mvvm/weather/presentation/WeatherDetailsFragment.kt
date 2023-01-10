package com.mvvm.weather.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mvvm.weather.R
import com.mvvm.weather.data.utils.ConnectionUtils
import com.mvvm.weather.data.utils.Constants
import com.mvvm.weather.data.utils.InjectorUtils
import com.mvvm.weather.databinding.WeatherDetailsFragmentBinding
import com.mvvm.weather.presentation.viewmodel.WeatherViewModel

/**
 * This fragment shows weather details
 */
class WeatherDetailsFragment : Fragment() {

    private lateinit var _binding: WeatherDetailsFragmentBinding
    private val binding get() = _binding
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = WeatherDetailsFragmentBinding.inflate(inflater, container, false)
        _binding.lifecycleOwner = viewLifecycleOwner
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = InjectorUtils.provideWeatherViewModelFactory(requireContext())
        viewModel = ViewModelProvider(
            this,
            factory
        )[WeatherViewModel::class.java]
        viewModel.userLocation.value = Constants.NEW_YORK_LOCATION
        viewModel.weatherData.observe(viewLifecycleOwner, Observer { data ->
            binding.weather = data.get(0)
        })
        if (!ConnectionUtils().isOnline(requireContext())) {
            showError(requireContext().resources.getString(R.string.alert_no_internet))
        }
    }

    private fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }
}