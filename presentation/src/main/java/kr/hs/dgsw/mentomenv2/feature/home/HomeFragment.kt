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
import kr.hs.dgsw.mentomenv2.feature.main.MainActivity

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    private val adapter: HomeAdapter = HomeAdapter {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                it
            )
        )
    }

    override fun setupViews() {
        (activity as MainActivity).hasBottomBar(true)
        mBinding.rvHome.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvHome.adapter = adapter
        collectPostStates()
        observeViewModel()


        mBinding.ivNotification.setOnClickListener {
            Toast.makeText(requireContext(), "알림", Toast.LENGTH_SHORT).show()
        }
        mBinding.ivSearch.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
        }
        mBinding.srlMain.setOnRefreshListener {
            viewModel.getAllPost()
            mBinding.srlMain.isRefreshing = false
        }
        mBinding.logo.setOnClickListener {
            viewModel.getAllPost()
        }
        lifecycleScope.launch {
            viewModel.error.collect { text ->
                Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                mBinding.sflHome.startShimmer()
                mBinding.rvHome.visibility = android.view.View.GONE
                mBinding.sflHome.visibility = android.view.View.VISIBLE
            } else {
                mBinding.sflHome.stopShimmer()
                mBinding.rvHome.visibility = android.view.View.VISIBLE
                mBinding.sflHome.visibility = android.view.View.GONE
            }
        }
    }

    private fun collectPostStates() {
        lifecycleScope.launch {
            viewModel.postState.collect { state ->
                if ((state.postList ?: emptyList()).isNotEmpty()) {
                    Log.d("collectPostStates: ", state.postList.toString())
                    adapter.submitList(state.postList)
                }
                if (state.error.isNotBlank()) {
                    Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
