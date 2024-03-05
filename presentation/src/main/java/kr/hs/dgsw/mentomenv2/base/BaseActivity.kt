package kr.hs.dgsw.mentomenv2.base

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.BR
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import kr.hs.dgsw.mentomenv2.feature.splash.IntroActivity
import java.lang.reflect.ParameterizedType
import java.util.Locale
import java.util.Objects

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    protected lateinit var mBinding: VB
    protected lateinit var mViewModel: VM

    protected abstract val viewModel: VM

    protected abstract fun start()

    protected fun bindingViewEvent(action: (event: Any) -> Unit) {
        viewModel.viewEvent.observe(this) {
            it.getContentIfNotHandled()?.let { event ->
                action.invoke(event)
            }
        }
        lifecycleScope.launch {
            viewModel.error.collect {
                if (it == Utils.TOKEN_EXCEPTION) {
                    Toast.makeText(this@BaseActivity, "세션이 만료되었습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@BaseActivity, IntroActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
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
        performDataBinding()
        start()
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
