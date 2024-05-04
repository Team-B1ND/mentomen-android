package com.b1nd.mentomen.feature.notice

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.b1nd.mentomen.adapter.NoticeAdapter
import com.b1nd.mentomen.base.BaseFragment
import com.b1nd.mentomen.databinding.FragmentNoticeBinding
import com.b1nd.mentomen.domain.model.Post
import com.b1nd.mentomen.feature.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        loginSuccessAction = {
            viewModel.getNotices()
        }
    }

    private fun collectState() {
        lifecycleScope.launch {
            viewModel.noticeState.collect {
                adapter.submitList(it)
            }
        }
    }
}
