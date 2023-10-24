package kr.hs.dgsw.mentomenv2.feature.main

import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivityHomeBinding

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {
    override val viewModel: HomeActivityViewModel by viewModels()

    override fun start() {
        mBinding.ivNotification.setOnClickListener {
            Toast.makeText(this, "알림", Toast.LENGTH_SHORT).show()
        }
        mBinding.ivSearch.setOnClickListener {
            Toast.makeText(this, "검색", Toast.LENGTH_SHORT).show()
        }
    }
}