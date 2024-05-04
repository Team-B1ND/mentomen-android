package com.b1nd.mentomen.feature.splash

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.b1nd.mentomen.base.BaseActivity
import com.b1nd.mentomen.databinding.ActivityIntroBinding
import com.b1nd.mentomen.feature.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
