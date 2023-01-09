package com.mvvm.weather.presentation

import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.weather.presentation.listeners.ActionListener
import com.mvvm.weather.R
import com.mvvm.weather.data.utils.Constants
import com.mvvm.weather.data.utils.InjectorUtils
import com.mvvm.weather.databinding.WeatherFragmentBinding
import com.mvvm.weather.presentation.adapters.WeatherAdapter
import com.mvvm.weather.presentation.viewmodel.WeatherViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
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
        val userLocation: Location = Constants.NEW_YORK_LOCATION
        viewModel.getWeatherData(userLocation).observe(viewLifecycleOwner, Observer { data ->
            adapter?.apply {
                records = data.daily.data ?: ArrayList()
                notifyDataSetChanged()
            }
        })
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        setUpView()
    }

    override fun onViewClicked(parentId: String) {

    }

    private fun setUpView() {
        adapter = WeatherAdapter(requireContext(), this)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
    }
}