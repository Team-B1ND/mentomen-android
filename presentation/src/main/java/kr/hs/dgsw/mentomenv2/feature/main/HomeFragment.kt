package kr.hs.dgsw.mentomenv2.feature.main

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.adapter.HomeAdapter
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>() {
    override val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var adapter: HomeAdapter
    override fun setupViews() {
        adapter = HomeAdapter()
        mBinding.rvHome.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvHome.adapter = adapter
        viewModel.itemList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}
