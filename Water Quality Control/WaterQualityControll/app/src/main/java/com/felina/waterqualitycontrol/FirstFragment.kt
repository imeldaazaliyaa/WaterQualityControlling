package com.felina.waterqualitycontrol

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.felina.waterqualitycontrol.data.adapter.setDataUpdate
import com.felina.waterqualitycontrol.data.adapter.showCustomToast
import com.felina.waterqualitycontrol.databinding.FragmentFirstBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var notif: Boolean = true

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvDate.text = SimpleDateFormat("EEE, dd MMM").format(Date())

        binding.btnController.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                sharedViewModel.Data.observe(viewLifecycleOwner) {
                    Log.e("dalam fragment",it.toString())
                    binding.tvDatatemp.setDataUpdate(it.temp.toString(), "temp")
                    binding.tvDataph.setDataUpdate(it.ph.toString(), "ph")
                    binding.tvDataoxigen.setDataUpdate(it.oxigen.toString(), "oxigen")
                    binding.tvDatatds.setDataUpdate(it.tds.toString(), "tds")
                    binding.tvDatasalinity.setDataUpdate(it.salinity.toString(), "salinity")
                    binding.tvDataamonia.setDataUpdate(it.amonia.toString(), "amonia")

                    if (notif){
                        when (it.temp){
                            in 0..19 -> {
                                Toast(context?.applicationContext).showCustomToast("Temperature is Low",(activity as MainActivity))
                            }
                            in 31..50 -> {
                                Toast(context?.applicationContext).showCustomToast("Temperature is too High",(activity as MainActivity))
                            }
                        }

                        when (it.ph){
                            in 0.0..7.4 -> {
                                Toast(context?.applicationContext).showCustomToast("pH is Low",(activity as MainActivity))
                            }
                            in 8.9..50.0 -> {
                                Toast(context?.applicationContext).showCustomToast("pH is too High",(activity as MainActivity))
                            }
                        }

                        when (it.oxigen){
                            in 0..3 -> {
                                Toast(context?.applicationContext).showCustomToast("Oxigen is Low",(activity as MainActivity))
                            }
                            in 6..50 -> {
                                Toast(context?.applicationContext).showCustomToast("Oxigen is too High",(activity as MainActivity))
                            }
                        }

                        when (it.tds){
                            in 0..299 -> {
                                Toast(context?.applicationContext).showCustomToast("TDS is Low",(activity as MainActivity))
                            }
                            in 601..1000 -> {
                                Toast(context?.applicationContext).showCustomToast("TDS is too High",(activity as MainActivity))
                            }
                        }

                        when (it.salinity) {
                            in 0.0..0.4 -> {
                                Toast(context?.applicationContext).showCustomToast("salinity is Low",(activity as MainActivity))
                            }

                            in 46.0..100.0 -> {
                                Toast(context?.applicationContext).showCustomToast("salinity is too High",(activity as MainActivity))
                            }
                        }

                        when (it.amonia){
                            in 0.5..100.0 -> {
                                Toast(context?.applicationContext).showCustomToast("Amonia is too High",(activity as MainActivity))
                            }
                        }
                        notif = false
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        viewLifecycleOwner.lifecycleScope.launch {
            sharedViewModel.getRealTimeData()
        }
    }
}