package kr.hs.dgsw.mentomenv2.feature.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentDetailBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    override val viewModel: DetailViewModel by viewModels()

    override fun setupViews() {
        TODO("Not yet implemented")
    }
}
