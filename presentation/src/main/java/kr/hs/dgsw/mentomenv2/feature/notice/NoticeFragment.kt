package kr.hs.dgsw.mentomenv2.feature.notice

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.adapter.NoticeAdapter
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentNoticeBinding
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.feature.main.MainActivity

@AndroidEntryPoint
class NoticeFragment : BaseFragment<FragmentNoticeBinding, NoticeViewModel>() {
    override val viewModel: NoticeViewModel by viewModels()
    private val adapter =
        NoticeAdapter {
            findNavController().navigate(
                NoticeFragmentDirections.actionNoticeFragmentToDetailFragment(
                    item = Post(),
                    postId = it,
                ),
            )
        }

    override fun setupViews() {
        (activity as MainActivity).hasBottomBar(false)
        mBinding.icBack.setOnClickListener {
            findNavController().navigateUp()
        }
        viewModel.getNotices()
        collectState()
        mBinding.rvNotice.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvNotice.adapter = adapter
    }

    private fun collectState() {
        lifecycleScope.launch {
            viewModel.noticeState.collect {
                adapter.submitList(it)
            }
        }
    }
}
