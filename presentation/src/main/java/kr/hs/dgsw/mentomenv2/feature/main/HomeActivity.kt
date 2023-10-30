package kr.hs.dgsw.mentomenv2.feature.main

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivityHomeBinding
import kr.hs.dgsw.mentomenv2.feature.post.PostFragment

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {
    override val viewModel: HomeActivityViewModel by viewModels()

    override fun start() {
        mBinding.bottomNav.background = null
        mBinding.bottomNav.menu.getItem(1).isEnabled = false

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

        mBinding.btnAdd.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PostFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
