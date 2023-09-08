package kr.hs.dgsw.mentomenv2.feature.auth.login

import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivityLoginBinding

@AndroidEntryPoint
class LoginActivity() : BaseActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {
    override val viewModel: LoginViewModel by viewModels()

    override fun start() {

    }
}
