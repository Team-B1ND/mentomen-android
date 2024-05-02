package kr.hs.dgsw.mentomenv2.feature.detail.image

import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentImageDetailBinding

class DetailImageFragment : BaseFragment<FragmentImageDetailBinding, DetailImageViewModel>() {
    override val viewModel: DetailImageViewModel by viewModels()

    override fun setupViews() {
        collectState()
        activity?.window?.apply {
            // 상태 표시줄의 색상 변경
            statusBarColor = ContextCompat.getColor(requireContext(), R.color.black)
            // 상태 표시줄 아이콘 색상 변경
            WindowInsetsControllerCompat(this, this.decorView).isAppearanceLightStatusBars = false
        }
    }

    private fun collectState() {
        lifecycleScope.launch {
            viewModel.titleText.collect {
                mBinding.tvTitle.text = it
            }
        }
    }

    override fun onDestroy() {
        activity?.window?.apply {
            // 상태 표시줄의 색상 변경
            statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
            // 상태 표시줄 아이콘 색상 변경
            WindowInsetsControllerCompat(this, this.decorView).isAppearanceLightStatusBars = true
        }
        super.onDestroy()
    }
}
