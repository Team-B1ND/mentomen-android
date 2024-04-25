package kr.hs.dgsw.mentomenv2.feature.signin

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kr.hs.dgsw.mentomenv2.databinding.DialogLoginBinding

class LoginDialog(context: Context): Dialog(context) {
    private val binding by lazy {DialogLoginBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}