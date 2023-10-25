package kr.hs.dgsw.mentomenv2.feature.auth.signin

import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivitySignInBinding
import kr.hs.dgsw.mentomenv2.domain.model.Token
import kr.hs.dgsw.mentomenv2.feature.main.HomeActivity
import kr.hs.dgsw.mentomenv2.util.dauth.Client
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.loginWithDodam
import kr.hs.dgsw.smartschool.dodamdodam.dauth.DAuth.settingDAuth

@AndroidEntryPoint
class SignInActivity() : BaseActivity<ActivitySignInBinding, SingInViewModel>() {
    override val viewModel: SingInViewModel by viewModels()

    override fun start() {
        settingDAuth(
            Client.clientId,
            Client.clientSecret,
            Client.redirectUri
        )
        loginWithDodam(this, {
            val intent = Intent(this, HomeActivity::class.java)
            viewModel.setToken(Token(it.accessToken, it.refreshToken))
            startActivity(intent)
        }, {
            loginWithDodam(this, {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }, {
                Toast.makeText(this, "실패입니다", Toast.LENGTH_SHORT).show()
            })
        })
    }
}
