package com.kocci.healtikuy.ui.temp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.util.helper.DateHelper
import com.kocci.healtikuy.databinding.FragmentDebugBinding
import com.kocci.healtikuy.util.extension.gone
import com.kocci.healtikuy.util.extension.showToast
import com.kocci.healtikuy.util.extension.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DebugFragment : Fragment() {
    private var _binding: FragmentDebugBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DebugViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDebugBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarDebug.setupWithNavController(findNavController())

        binding.btnLastLoginToYesterday.setOnClickListener{
            viewModel.regularSync().observe(viewLifecycleOwner){
                when(it){
                    Async.Loading -> binding.progressbarDebug.visible()
                    is Async.Error -> {
                        binding.progressbarDebug.gone()
                        showToast("Failed! error with msg ${it.msg}")
                    }
                    is Async.Success -> {
                        binding.progressbarDebug.gone()
                        showToast("Success!")
                    }
                }
            }
        }
        binding.btnLastLoginTo1Week.setOnClickListener{
            viewModel.regularSync(DateHelper.weekInMill).observe(viewLifecycleOwner){
                when(it){
                    Async.Loading -> binding.progressbarDebug.visible()
                    is Async.Error -> {
                        binding.progressbarDebug.gone()
                        showToast("Failed! error with msg ${it.msg}")
                    }
                    is Async.Success -> {
                        binding.progressbarDebug.gone()
                        showToast("Success!")
                    }
                }
            }
        }
        binding.btnAdd5000Points.setOnClickListener {
            viewModel.addPoints(5000).observe(viewLifecycleOwner){
                when(it){
                    Async.Loading -> binding.progressbarDebug.visible()
                    is Async.Error -> {
                        binding.progressbarDebug.gone()
                        showToast("Failed! error with msg ${it.msg}")
                    }
                    is Async.Success -> {
                        binding.progressbarDebug.gone()
                        showToast("Got ${it.data} points!")
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}