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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.util.store.CharacterInStore
import com.kocci.healtikuy.core.util.store.generateCharacterInStore
import com.kocci.healtikuy.databinding.FragmentStoreBinding
import com.kocci.healtikuy.util.extension.showToast
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

            setupAdapter(it.coin, storeItem)
            binding.tvStoreCoin.text = it.coin.toString()
        }

    }

    private fun setupAdapter(coin: Int, list: List<CharacterInStore>) {
        val mAdapter = StoreAdapter(list, requireActivity())
        mAdapter.listener = object : StoreAdapter.Listener {
            override fun onItemClick(item: CharacterInStore) {
                MaterialAlertDialogBuilder(requireActivity())
                    .setTitle("Are you sure?")
                    .setMessage("You want to buy ${item.name} with ${item.price} Coin?")
                    .setPositiveButton("YES") { dialog, which ->
                        if (viewModel.isCoinEnough(coin, item)) {
                            viewModel.buyAvatar(item).observe(viewLifecycleOwner) {
                                when (it) {
                                    is Async.Error -> {
                                        showToast(it.msg)
                                    }
                                    Async.Loading -> {
                                        showToast("loading")
                                    }
                                    is Async.Success -> {
                                        showToast("success")
                                    }
                                }
                            }

                        } else {
                            showToast("Sorry, not enough coin")
                        }
                    }.setNegativeButton("CANCEL") { dialog, which ->

                    }.show()
            }
        }
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