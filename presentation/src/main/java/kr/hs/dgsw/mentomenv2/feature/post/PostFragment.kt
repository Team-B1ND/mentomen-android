package kr.hs.dgsw.mentomenv2.feature.post

import androidx.fragment.app.viewModels
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentPostBinding

class PostFragment : BaseFragment<FragmentPostBinding, PostViewModel>() {

    override val viewModel: PostViewModel by viewModels()

    override fun setupViews() {

    }
}
