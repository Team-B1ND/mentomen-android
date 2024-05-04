package com.b1nd.mentomen.feature.my

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.b1nd.mentomen.R
import com.b1nd.mentomen.adapter.HomeAdapter
import com.b1nd.mentomen.base.BaseFragment
import com.b1nd.mentomen.databinding.FragmentMyBinding
import com.b1nd.mentomen.domain.model.NoticeStatus
import com.b1nd.mentomen.domain.util.Log
import com.b1nd.mentomen.feature.main.MainActivity
import com.b1nd.mentomen.feature.splash.IntroActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyFragment : BaseFragment<FragmentMyBinding, MyViewModel>() {
    override val viewModel: MyViewModel by viewModels()

    private val adapter =
        HomeAdapter { post ->
            findNavController().navigate(
                MyFragmentDirections.actionMyFragmentToDetailFragment(
                    post,
                ),
            )
        }

    private fun initHomeAdapter() {
        mBinding.rvMyPage.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvMyPage.adapter = adapter
        mBinding.btnLogout.setOnClickListener {
            viewModel.clearDataStore()
            val intent = Intent(requireContext(), IntroActivity::class.java)
            startActivity(intent)
        }
        mBinding.btnNotification.setOnClickListener {
            findNavController().navigate(MyFragmentDirections.actionMyFragmentToNoticeFragment())
        }
    }

    override fun setupViews() {
        (activity as MainActivity).hasBottomBar(true)
        loginSuccessAction = {
            viewModel.getMyInfo()
            viewModel.getMyPost()
            viewModel.getNotificationStatus()
        }
        Log.d("MyFragment", "OnCreate")
        initHomeAdapter()
        observeViewModel()
        collectState()
        viewModel.getNotificationStatus()
        viewModel.getMyInfo()
        viewModel.getMyPost()
    }

    private fun observeViewModel() {
        viewModel.post.observe(viewLifecycleOwner) {
            adapter.submitList(
                it,
            )
        }
    }

    private fun collectState() {
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
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
        lifecycleScope.launch {
            viewModel.notificationState.collect { notificationState ->
                when (notificationState) {
                    NoticeStatus.EXIST -> {
                        mBinding.btnNotification.setImageResource(R.drawable.ic_turn_on_notification)
                    }

                    NoticeStatus.NONE -> {
                        mBinding.btnNotification.setImageResource(R.drawable.ic_notification)
                    }
                }
            }
        }
    }
}
