package kr.hs.dgsw.mentomenv2.feature.my

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.adapter.HomeAdapter
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentMyBinding
import kr.hs.dgsw.mentomenv2.feature.main.MainActivity
import kr.hs.dgsw.mentomenv2.feature.splash.IntroActivity

@AndroidEntryPoint
class MyFragment : BaseFragment<FragmentMyBinding, MyViewModel>() {
    override val viewModel: MyViewModel by viewModels()
    private lateinit var adapter: HomeAdapter

    private fun initHomeAdapter() {
        adapter =
            HomeAdapter { post ->
                findNavController().navigate(
                    MyFragmentDirections.actionUserFragmentToDetailFragment(
                        post
                    )
                )
            }
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
        viewModel.getMyInfo()
        viewModel.getMyPost()
    }

    private fun observeViewModel() =
        with(viewModel) {
            post.observe(viewLifecycleOwner) { adapter.submitList(it) }
            isLoading.observe(viewLifecycleOwner) { isLoading ->
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
