package kr.hs.dgsw.mentomenv2.feature.signin

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentLoginBinding
import kr.hs.dgsw.mentomenv2.domain.util.Log

@AndroidEntryPoint
class LoginFragment: BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModels()

    override fun setupViews() {
        mBinding.icBack.setOnClickListener {
            Log.d("LoginFragment", "onClickBack")
            findNavController().popBackStack()
        }
    }
}
