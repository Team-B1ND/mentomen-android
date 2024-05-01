package kr.hs.dgsw.mentomenv2.feature.home

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.adapter.HomeAdapter
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentHomeBinding
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.feature.main.MainActivity
import kr.hs.dgsw.mentomenv2.state.PostState

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    private val adapter: HomeAdapter =
        HomeAdapter { post ->
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    item = post,
                ),
            )
        }

    override fun setupViews() {
        (activity as MainActivity).hasBottomBar(true)
        mBinding.rvHome.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvHome.adapter = adapter
        collectPostStates()
        observeViewModel()

        mBinding.ivNotification.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNoticeFragment())
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
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
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
    }

    override fun onResume() {
        viewModel.getAllPost()
        super.onResume()
    }

    private fun collectPostStates() {
        lifecycleScope.launch {
            viewModel.postState.collect { state ->
                adapter.submitList(state.postList!!)
                if (state.error.isNotBlank()) {
                    Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
