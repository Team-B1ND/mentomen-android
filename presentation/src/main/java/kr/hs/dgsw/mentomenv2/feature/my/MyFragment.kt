package kr.hs.dgsw.mentomenv2.feature.my

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.HomeAdapter
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentMyBinding
import kr.hs.dgsw.mentomenv2.feature.main.HomeFragmentDirections

class MyFragment : BaseFragment<FragmentMyBinding, MyViewModel>() {
    private lateinit var homeAdapter: HomeAdapter
    override val viewModel: MyViewModel by viewModels()
    private lateinit var adapter: HomeAdapter
    private fun initHomeAdapter() {
        adapter = HomeAdapter{
            val navAction =
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(navAction)
        }
        mBinding.rvMyPage.adapter = homeAdapter
    }

    override fun setupViews() {
        initHomeAdapter()
        observeViewModel()
    }

    private fun observeViewModel() = with(viewModel) {
        itemList.observe(viewLifecycleOwner) { homeAdapter.submitList(it) }
        profileImage.observe(viewLifecycleOwner) {
            Glide.with(mBinding.profileImage.context)
                .load(it)
                .transform(CircleCrop())
                .into(mBinding.profileImage)
        }
    }
}
