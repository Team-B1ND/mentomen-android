package kr.hs.dgsw.mentomenv2.feature.auth.signin

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivitySignInBinding
import kr.hs.dgsw.mentomenv2.feature.main.HomeActivity
import kr.hs.dgsw.mentomenv2.util.dauth.Client
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.getCode
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.settingDAuth

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding, SingInViewModel>() {
    override val viewModel: SingInViewModel by viewModels()
    var code: String? = null
    override fun start() {
        settingDAuth(
            Client.clientId,
            Client.clientSecret,
            Client.redirectUri
        )
        getCode(this@SignInActivity,
            {
                viewModel.getToken(it)
                Intent(this@SignInActivity, HomeActivity::class.java).apply {
                    startActivity(this)
                }
            },
            {
                getCode(this@SignInActivity,
                    {
                        viewModel.getToken(it)
                        Intent(this@SignInActivity, HomeActivity::class.java).apply {
                            startActivity(this)
                        }
                    },
                    {
                        Log.d("error: ", "how did you get here")
                    })
            })
    }
}
