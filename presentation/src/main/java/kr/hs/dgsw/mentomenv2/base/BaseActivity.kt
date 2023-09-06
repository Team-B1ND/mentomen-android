package kr.hs.dgsw.mentomenv2.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kr.hs.dgsw.mentomenv2.BR

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> constructor(
    @LayoutRes private val layoutRes: Int
) : AppCompatActivity() {
    protected lateinit var binding: B
    protected lateinit var mViewModel: VM

    protected abstract val viewModel: VM

    protected abstract fun start()

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        mViewModel = if(::mViewModel.isInitialized) mViewModel else viewModel
        binding.setVariable(BR._all, mViewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performDataBinding()
        start()
    }
}
