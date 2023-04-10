package com.kocci.healtikuy.ui.main.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kocci.healtikuy.core.util.store.CharacterInStore
import com.kocci.healtikuy.core.util.store.generateCharacterInStore
import com.kocci.healtikuy.databinding.FragmentStoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreFragment : Fragment() {
    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StoreViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarStore.setupWithNavController(findNavController())

        viewModel.userData.observe(viewLifecycleOwner) {
            val inventory = it.inventory.toList()
            val generatedList = generateCharacterInStore()
            val storeItem = generatedList.filterNot { it.name in inventory }

            setupAdapter(storeItem)
            binding.tvStoreCoin.text = it.coin.toString()
        }

    }

    private fun setupAdapter(list: List<CharacterInStore>) {
        val mAdapter = StoreAdapter(list, requireActivity())
        val mLayoutManager = GridLayoutManager(requireActivity(), 3)
        binding.rvStore.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}