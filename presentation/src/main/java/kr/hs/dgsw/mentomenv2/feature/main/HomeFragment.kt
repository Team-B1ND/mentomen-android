package kr.hs.dgsw.mentomenv2.feature.main

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.ActivityHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<ActivityHomeBinding, HomeFragmentViewModel>() {
    override val viewModel: HomeFragmentViewModel by viewModels()

    override fun setupViews() {
        mBinding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    mBinding.bottomNav.selectedItemId = R.id.action_home
                    true
                }

                R.id.action_my -> {
                    mBinding.bottomNav.selectedItemId = R.id.action_my
                    true
                }

                else -> false
            }
        }
    }
}
