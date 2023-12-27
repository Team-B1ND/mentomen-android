package kr.hs.dgsw.mentomenv2.feature.my

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.adapter.HomeAdapter
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentMyBinding
import kr.hs.dgsw.mentomenv2.feature.splash.IntroActivity

@AndroidEntryPoint
class MyFragment : BaseFragment<FragmentMyBinding, MyViewModel>() {
    override val viewModel: MyViewModel by viewModels()
    private lateinit var adapter: HomeAdapter

    private fun initHomeAdapter() {
        adapter =
            HomeAdapter {
                TODO("여기에 바텀 시트 다이얼로그 짜야합니다.")
            }
        mBinding.rvMyPage.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvMyPage.adapter = adapter
        mBinding.btnLogout.setOnClickListener {
            viewModel.logout()
            val intent = Intent(requireContext(), IntroActivity::class.java)
            startActivity(intent)
        }
    }

    override fun setupViews() {
        initHomeAdapter()
        observeViewModel()
    }

    private fun observeViewModel() =
        with(viewModel) {
            post.observe(viewLifecycleOwner) { adapter.submitList(listOf(it)) }
        }
}
