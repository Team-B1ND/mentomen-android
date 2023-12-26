package kr.hs.dgsw.mentomenv2.feature.search

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentSearchBinding
import kr.hs.dgsw.mentomenv2.feature.main.MainActivity

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val viewModel: SearchViewModel by viewModels()

    override fun setupViews() {
        (activity as MainActivity).hasBottomBar(false)
        mBinding.backButton.setOnClickListener {
            (activity as MainActivity).hasBottomBar(true)
            findNavController().navigateUp()
        }
    }
}
