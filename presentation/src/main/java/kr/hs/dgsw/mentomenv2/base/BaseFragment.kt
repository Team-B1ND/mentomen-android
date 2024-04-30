package kr.hs.dgsw.mentomenv2.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.BR
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.domain.util.Log
import kr.hs.dgsw.mentomenv2.domain.util.Utils
import kr.hs.dgsw.mentomenv2.feature.signin.LoginActivity
import kr.hs.dgsw.mentomenv2.feature.splash.IntroActivity
import java.lang.reflect.ParameterizedType
import java.util.Locale
import java.util.Objects

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    protected lateinit var mBinding: VB
    protected lateinit var mViewModel: VM
    protected abstract val viewModel: VM

    protected var savedInstanceState: Bundle? = null

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)
        return mBinding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        this.savedInstanceState = savedInstanceState
        initialize()
        lifecycleScope.launch {
            viewModel.error.collect {
                when (it) {
                    Utils.TOKEN_EXCEPTION -> {
                        val intent = Intent(requireContext(), LoginActivity::class.java)
                        launcher.launch(intent)
                    }

                    Utils.NETWORK_ERROR_MESSAGE -> {
                        Log.e("baseFragment", "network error")
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                        val intent = Intent(requireContext(), IntroActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        this@BaseFragment.requireActivity().finishAffinity()
                    }

                    else -> {
                        Log.e("baseFragment", "else error")
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        setupViews()
    }

    private fun initialize() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode != Activity.RESULT_OK) {
                findNavController().popBackStack()
            }
        }
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
            if (i != split.size - 1) {
                name.append("_")
            }
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
