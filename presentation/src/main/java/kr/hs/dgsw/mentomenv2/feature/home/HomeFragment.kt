package kr.hs.dgsw.mentomenv2.feature.home

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.adapter.HomeAdapter
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    private val adapter = HomeAdapter{
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
        Log.d("HomeFragemnt", "HomeFragment item clicked")
    }
    override fun setupViews() {
        mBinding.rvHome.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvHome.adapter = adapter

        collectPostStates()

        mBinding.ivNotification.setOnClickListener {
            Toast.makeText(requireContext(), "알림", Toast.LENGTH_SHORT).show()
        }
        mBinding.ivSearch.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
        }
        mBinding.logo.setOnClickListener {
            viewModel.getAllPost()
        }
        lifecycleScope.launch {
            viewModel.errorFlow.collect { text ->
                Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun collectPostStates() {
        lifecycleScope.launch {
            viewModel.postState.collect { state ->
                if (state.postList != null) {
                    adapter.submitList(state.postList)
                }
                if (state.error.isNotBlank()) {
                    Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
