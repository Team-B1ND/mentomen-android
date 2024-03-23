package kr.hs.dgsw.mentomenv2.feature.splash

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivityIntroBinding
import kr.hs.dgsw.mentomenv2.feature.main.MainActivity
import kr.hs.dgsw.mentomenv2.feature.signin.SignInActivity
import java.lang.Thread.sleep

@AndroidEntryPoint
class IntroActivity : BaseActivity<ActivityIntroBinding, IntroViewModel>() {
    override val viewModel: IntroViewModel by viewModels()

    override fun start() {
        viewModel.getToken()
        bindingViewEvent { event ->
            when (event) {
                IntroViewModel.START -> {
                    navigate(MainActivity::class.java)
                }
                IntroViewModel.LOGIN -> {
                    navigate(SignInActivity::class.java)
                }
            }
        }
    }

    private fun navigate(destination: Class<*>) {
        lifecycleScope.launch {
            val intent = Intent(this@IntroActivity, destination)
            sleep(2000)
            finish()
            startActivity(intent)
        }
    }
}
