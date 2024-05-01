package kr.hs.dgsw.mentomenv2.feature.signin

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivityLoginBinding
import kr.hs.dgsw.mentomenv2.domain.util.Log

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModels()

    override fun start() {
        mBinding.icBack.setOnClickListener {
            finish()
        }
        mBinding.btnLogin.setOnClickListener {
            if (viewModel.id.value != "" && viewModel.pw.value != "") {
                viewModel.login()
            }
        }
        mBinding.tvSingIn.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_VIEW).apply {
                    data =
                        Uri.parse(
                            "https://play.google.com/store/apps/details?id=com.b1nd.dodam.student",
                        )
                    setPackage("com.android.vending")
                }
            startActivity(intent)
        }
        lifecycleScope.launch {
            viewModel.id.collect {
                if (it != "" && viewModel.pw.value != "") {
                    mBinding.btnLogin.alpha = 1f
                } else {
                    mBinding.btnLogin.alpha = 0.5f
                }
            }
        }
        lifecycleScope.launch {
            viewModel.pw.collect {
                if (it != "" && viewModel.id.value != "") {
                    mBinding.btnLogin.alpha = 1f
                } else {
                    mBinding.btnLogin.alpha = 0.5f
                }
            }
        }
        bindingViewEvent {
            when (it) {
                LoginViewModel.FAILURE_LOGIN -> {
                    Toast.makeText(this@LoginActivity, "로그인에 실패했습니다.", Toast.LENGTH_SHORT)
                        .show()
                }

                LoginViewModel.SUCCESS_LOGIN -> {
                    Toast.makeText(this@LoginActivity, "로그인에 성공했습니다.", Toast.LENGTH_SHORT)
                        .show()
                    setResult(RESULT_OK)
                    finish()
                }

                LoginViewModel.WRONG_PASSWORD -> {
                    Toast.makeText(this@LoginActivity, "id 혹은 비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onDestroy() {
        Log.d("LoginActivity", "onDestroy")
        setResult(RESULT_CANCELED)
        super.onDestroy()
    }
}
