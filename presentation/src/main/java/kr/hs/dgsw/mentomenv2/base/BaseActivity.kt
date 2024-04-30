package kr.hs.dgsw.mentomenv2.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.BR
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.domain.util.Log
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import kr.hs.dgsw.mentomenv2.feature.main.MainActivity
import kr.hs.dgsw.mentomenv2.feature.signin.LoginActivity
import java.lang.reflect.ParameterizedType
import java.util.Locale
import java.util.Objects

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    protected lateinit var mBinding: VB
    private lateinit var mViewModel: VM

    protected abstract val viewModel: VM

    protected abstract fun start()

    private lateinit var launcher: ActivityResultLauncher<Intent>

    protected fun bindingViewEvent(action: (event: Any) -> Unit) {
        lifecycleScope.launch {
            viewModel.viewEvent.collect {
                it.getContentIfNotHandled()?.let { event ->
                    action.invoke(event)
                }
            }
        }
    }

    private fun performDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, layoutRes())
        mViewModel = if (::mViewModel.isInitialized) mViewModel else viewModel
        mBinding.setVariable(BR.vm, mViewModel)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode != Activity.RESULT_OK) {
                    finish()
                }
            }
        performDataBinding()
        collectViewModel()
        start()
    }

    private fun collectViewModel() {
        viewModel.viewModelScope.launch {
            Log.d("bindingViewEvent: ", "BaseActivity")
            viewModel.error.collect {
                when (it) {
                    Utils.TOKEN_EXCEPTION -> {
                        val intent = Intent(this@BaseActivity, LoginActivity::class.java)
                        launcher.launch(intent)
                    }
                    Utils.NETWORK_ERROR_MESSAGE -> {
                        Log.e("baseActivity", "token, network error")
                        Toast.makeText(this@BaseActivity, it, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@BaseActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    }

                    else -> {
                        Log.e("baseActivity", "else error")
                        Toast.makeText(this@BaseActivity, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mBinding.isInitialized) mBinding.unbind()
    }

    /**
     * Generic Type (Binding) class 를 가져와서 layout 파일명으로 변환 후 자동으로 Layout Resource 를 가져옴
     *
     * @return layout resource
     */
    @LayoutRes
    private fun layoutRes(): Int {
        val split =
            (
                (Objects.requireNonNull(javaClass.genericSuperclass) as ParameterizedType)
                    .actualTypeArguments[0] as Class<*>
            )
                .simpleName.replace(
                    "Binding$".toRegex(),
                    "",
                ).split("(?<=.)(?=\\p{Upper})".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()

        val name = StringBuilder()

        for (i in split.indices) {
            name.append(split[i].lowercase(Locale.ROOT))
            if (i != split.size - 1) name.append("_")
        }

        try {
            return R.layout::class.java.getField(name.toString()).getInt(R.layout::class.java)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }

        return 0
    }
}
