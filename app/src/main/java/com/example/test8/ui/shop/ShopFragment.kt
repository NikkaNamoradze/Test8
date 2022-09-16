package com.example.test8.ui.shop

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test8.R
import com.example.test8.adapters.ShopAdapter
import com.example.test8.databinding.ShopFragmentBinding
import com.example.test8.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShopFragment : BaseFragment<ShopFragmentBinding>(ShopFragmentBinding::inflate) {

    private val shopAdapter: ShopAdapter by lazy {
        ShopAdapter()
    }

    private val viewModel: ShopViewModel by viewModels()

    override fun start() {
        init()
    }

    private fun init() {
        observer()
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.rvShopItems.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = shopAdapter
        }
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                if (viewModel.isOnline(requireContext())){
                    viewModel.loadShopItems()
                    viewModel.coursesFlow.collect {
//                        viewModel.addItemsInLocalBase()
                        shopAdapter.setData(it.data?.toList() ?: emptyList())
                        d("data_frag", "$it")
                    }
                }else{
                    viewModel.coursesFlow.collect{
                        viewModel.deleteLocalItems()
                        viewModel.loadLocalItems()
                        shopAdapter.setData(it.data?.toList() ?: emptyList())
                    }
                }

            }
        }
    }
}