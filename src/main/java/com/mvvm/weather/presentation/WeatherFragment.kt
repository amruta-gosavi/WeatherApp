package com.mvvm.weather.presentation

import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnSuccessListener
import com.mvvm.weather.R
import com.mvvm.weather.data.utils.Constants
import com.mvvm.weather.data.utils.InjectorUtils
import com.mvvm.weather.databinding.WeatherFragmentBinding
import com.mvvm.weather.presentation.adapters.WeatherAdapter
import com.mvvm.weather.presentation.common.PermissionUtils
import com.mvvm.weather.presentation.listeners.ActionListener
import com.mvvm.weather.presentation.viewmodel.WeatherViewModel
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class WeatherFragment : Fragment(), ActionListener, OnSuccessListener<Location> {

    private lateinit var _binding: WeatherFragmentBinding
    private val binding get() = _binding
    private lateinit var viewModel: WeatherViewModel
    private var adapter: WeatherAdapter? = null
    lateinit var fusedLocationClient: FusedLocationProviderClient
    private var userLocation:Location? = null

    val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            if (locationResult == null) {
                userLocation = Constants.NEW_YORK_LOCATION
            }
            //refresh location
            val location = locationResult.lastLocation ?: return
            viewModel.userLocation.value = location
        }
    }
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
                    records = data.daily.data ?: ArrayList()
                    notifyDataSetChanged()
                }
            })
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        setUpView()
    }

    override fun onStart() {
        super.onStart()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        fun createLocationRequest() = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, TimeUnit.MINUTES.toMillis(5)
        ).apply {
            setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
            setDurationMillis(TimeUnit.MINUTES.toMillis(5))
            setWaitForAccurateLocation(true)
            setMaxUpdates(1)
        }.build()
        if (PermissionUtils.checkUserLocationPermission(requireContext())) {
            fusedLocationClient.requestLocationUpdates(
                createLocationRequest(),
                locationCallback,
                Looper.myLooper()
            )
        }else {
            //Permission denied!! Displaying default location data.
            Toast.makeText(
                context,
                requireContext().resources.getString(R.string.alert_permission_denied),
                Toast.LENGTH_SHORT
            ).show();
        }
    }

    override fun onViewClicked(parentId: String) {

    }

    private fun setUpView() {
        adapter = WeatherAdapter(requireContext(), this)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onSuccess(location: Location) {
        viewModel.userLocation.value = location
    }
}