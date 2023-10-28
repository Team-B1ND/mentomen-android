package kr.hs.dgsw.mentomenv2.feature.main

import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivityHomeBinding

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {
    override val viewModel: HomeActivityViewModel by viewModels()

    override fun start() {
        mBinding.bottomNav.background = null
        mBinding.bottomNav.menu.getItem(1).isEnabled = false

        mBinding.ivNotification.setOnClickListener {
            Toast.makeText(this, "알림", Toast.LENGTH_SHORT).show()
        }
        mBinding.ivSearch.setOnClickListener {
            Toast.makeText(this, "검색", Toast.LENGTH_SHORT).show()
        }

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
