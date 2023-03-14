package com.kocci.healtikuy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kocci.healtikuy.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.statusIndicator.setProgressCompat(50, true)
    }

    override fun onClick(v: View?) {
        when(v){
            /**
             * handle button click
             */
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}