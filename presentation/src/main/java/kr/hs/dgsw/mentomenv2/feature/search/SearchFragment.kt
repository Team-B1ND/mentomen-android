package kr.hs.dgsw.mentomenv2.feature.search

import androidx.fragment.app.viewModels
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val viewModel: SearchViewModel by viewModels()

    override fun setupViews() {

    }
}
