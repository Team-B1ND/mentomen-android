package kr.hs.dgsw.mentomenv2.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kr.hs.dgsw.mentomenv2.BR
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.domain.util.Util
import kr.hs.dgsw.mentomenv2.feature.splash.IntroActivity
import java.lang.reflect.ParameterizedType
import java.util.Locale
import java.util.Objects

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    protected lateinit var mBinding: VB
    protected lateinit var mViewModel: VM
    protected abstract val viewModel: VM

    protected var savedInstanceState: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.savedInstanceState = savedInstanceState
        initialize()

        viewModel.error.observe(viewLifecycleOwner) {
            if (it == Util.TOKEN_EXCEPTION) {
                val intent = Intent(requireContext(), IntroActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)
                this.requireActivity().finishAffinity()
            }
        }

        setupViews()
    }

    private fun initialize() {
        mViewModel = if (::mViewModel.isInitialized) mViewModel else viewModel
        mBinding.setVariable(BR.vm, mViewModel)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.executePendingBindings()
    }

    protected abstract fun setupViews()

    @LayoutRes
    private fun layoutRes(): Int {
        val split =
            (
                (Objects.requireNonNull(javaClass.genericSuperclass) as ParameterizedType)
                    .actualTypeArguments[0] as Class<*>
                )
                .simpleName.replace("Binding$".toRegex(), "")
                .split("(?<=.)(?=\\p{Upper})".toRegex())
                .dropLastWhile { it.isEmpty() }.toTypedArray()

        val name = StringBuilder()

        for (i in split.indices) {
            name.append(split[i].lowercase(Locale.ROOT))
            if (i != split.size - 1)
                name.append("_")
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
