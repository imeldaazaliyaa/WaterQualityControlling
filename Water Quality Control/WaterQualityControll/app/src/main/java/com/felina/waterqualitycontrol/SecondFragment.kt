package com.felina.waterqualitycontrol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.felina.waterqualitycontrol.data.adapter.setAmoniaDangerType
import com.felina.waterqualitycontrol.data.adapter.setTempDangerType
import com.felina.waterqualitycontrol.data.adapter.setDataUpdate
import com.felina.waterqualitycontrol.data.adapter.setOxigenDangerType
import com.felina.waterqualitycontrol.data.adapter.setPhDangerType
import com.felina.waterqualitycontrol.data.adapter.setSalinityDangerType
import com.felina.waterqualitycontrol.data.adapter.setTdsDangerType
import com.felina.waterqualitycontrol.databinding.FragmentSecondBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val sharedViewModel: MainViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLivemonitoring.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            sharedViewModel.Data.observe(viewLifecycleOwner) {
                binding.tvDataTemp.setDataUpdate(it.temp.toString(), "temp")
                binding.tvDataPh.setDataUpdate(it.ph.toString(), "ph")
                binding.tvDataOxigen.setDataUpdate(it.oxigen.toString(), "oxigen")
                binding.tvDataTds.setDataUpdate(it.tds.toString(), "tds")
                binding.tvDataSalinity.setDataUpdate(it.salinity.toString(), "salinity")
                binding.tvDataAmonia.setDataUpdate(it.amonia.toString(), "amonia")

                binding.cvTemp.setTempDangerType(it.temp.toString())
                binding.cvPh.setPhDangerType(it.ph.toString())
                binding.cvOxigen.setOxigenDangerType(it.oxigen.toString())
                binding.cvTds.setTdsDangerType(it.tds.toString())
                binding.cvSalinity.setSalinityDangerType(it.salinity.toString())
                binding.cvAmonia.setAmoniaDangerType(it.amonia.toString())
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