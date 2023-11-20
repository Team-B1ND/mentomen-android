package kr.hs.dgsw.mentomenv2.feature.my

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.HomeAdapter
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentMyBinding

class MyFragment : BaseFragment<FragmentMyBinding, MyViewModel>() {
    private lateinit var homeAdapter: HomeAdapter
    override val viewModel: MyViewModel by viewModels()
    private fun initHomeAdapter() {
        homeAdapter = HomeAdapter {
            findNavController().navigate(R.id.action_myFragment_to_homeFragment)
        }
        mBinding.rvMyPage.adapter = homeAdapter
    }

    override fun setupViews() {
        mBinding.vm = viewModel
        mBinding.lifecycleOwner = this
        initHomeAdapter()
    }

    private fun observeViewModel() = with(viewModel) {
        itemList.observe(viewLifecycleOwner) { homeAdapter.submitList(it) }
    }
}
