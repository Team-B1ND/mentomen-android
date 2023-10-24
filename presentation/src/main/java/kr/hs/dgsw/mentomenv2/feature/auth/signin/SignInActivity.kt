package kr.hs.dgsw.mentomenv2.feature.auth.signin

import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivitySignInBinding
import kr.hs.dgsw.mentomenv2.feature.main.HomeActivity
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.getCode
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.loginWithDodam
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.settingDAuth

@AndroidEntryPoint
class SignInActivity() : BaseActivity<ActivitySignInBinding, SingInViewModel>() {
    override val viewModel: SingInViewModel by viewModels()

    override fun start() {
        settingDAuth("29d5f73236bc4586990b4ec610442d8879b5030758bf489fbb8c3bc851ac5c06", R.string.clientSecret.toString(), R.string.redirectUrl.toString())
        loginWithDodam(this, {
            //homeActivity로 이동하는 코드 짜줘
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }, {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        })
        mBinding.lifecycleOwner = this
    }
}
