package kr.hs.dgsw.mentomenv2.feature.splash

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivityIntroBinding
import kr.hs.dgsw.mentomenv2.feature.main.MainActivity

@AndroidEntryPoint
class IntroActivity : BaseActivity<ActivityIntroBinding, IntroViewModel>() {
    override val viewModel: IntroViewModel by viewModels()

    override fun start() {
        lifecycleScope.launch {
            delay(2000)
            val intent = Intent(this@IntroActivity, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}
