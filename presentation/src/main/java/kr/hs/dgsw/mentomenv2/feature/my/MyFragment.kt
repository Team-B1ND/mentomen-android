package kr.hs.dgsw.mentomenv2.feature.my

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.adapter.HomeAdapter
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentMyBinding
import kr.hs.dgsw.mentomenv2.feature.main.MainActivity
import kr.hs.dgsw.mentomenv2.feature.splash.IntroActivity

@AndroidEntryPoint
class MyFragment : BaseFragment<FragmentMyBinding, MyViewModel>() {
    override val viewModel: MyViewModel by viewModels()
    private val adapter =
        HomeAdapter { post ->
            findNavController().navigate(
                MyFragmentDirections.actionMyFragmentToDetailFragment(
                    post,
                ),
            )
        }

    private fun initHomeAdapter() {
        mBinding.rvMyPage.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvMyPage.adapter = adapter
        mBinding.btnLogout.setOnClickListener {
            viewModel.clearDataStore()
            val intent = Intent(requireContext(), IntroActivity::class.java)
            startActivity(intent)
        }
    }

    override fun setupViews() {
        (activity as MainActivity).hasBottomBar(true)
        initHomeAdapter()
        observeViewModel()
        collectState()
        viewModel.getMyInfo()
        viewModel.getMyPost()
    }

    private fun observeViewModel() {
        viewModel.post.observe(viewLifecycleOwner) { adapter.submitList(it) }
    }

    private fun collectState() {
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                if (isLoading) {
                    mBinding.sflMy.startShimmer()
                    mBinding.clMy.visibility = android.view.View.GONE
                    mBinding.sflMy.visibility = android.view.View.VISIBLE
                } else {
                    mBinding.sflMy.stopShimmer()
                    mBinding.clMy.visibility = android.view.View.VISIBLE
                    mBinding.sflMy.visibility = android.view.View.GONE
                }
            }
        }
    }
}
