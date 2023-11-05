package kr.hs.dgsw.mentomenv2.feature.auth.signin

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivitySignInBinding
import kr.hs.dgsw.mentomenv2.domain.usecase.auth.SignInUseCase
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding, SingInViewModel>() {
    override val viewModel: SingInViewModel by viewModels()
    override fun start() {
        mBinding.vm = viewModel
        mBinding.btnLogin.setOnClickListener {
            viewModel.onClickLogin()
        }
    }
}
