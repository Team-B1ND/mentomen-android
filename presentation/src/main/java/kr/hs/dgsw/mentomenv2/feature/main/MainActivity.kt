package kr.hs.dgsw.mentomenv2.feature.main

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivityMainBinding
import kr.hs.dgsw.mentomenv2.databinding.DialogLoginBinding
import kr.hs.dgsw.mentomenv2.feature.home.HomeFragment
import kr.hs.dgsw.mentomenv2.feature.my.MyFragment
import kr.hs.dgsw.mentomenv2.feature.post.PostActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()
    private val dialogBinding: DialogLoginBinding by lazy {
        DialogLoginBinding.inflate(layoutInflater)
    }
    private val timeInterval: Long = 2000
    private var mBackPressed: Long = 0

    override fun start() {
        mBinding.bottomNav.background = null
        mBinding.bottomNav.menu.getItem(1).isEnabled = false
        mBinding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
//                    viewModel.isBottomBarInvisible.value = false
//                    mBinding.bottomAppBar.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                    true
                }

                R.id.action_my -> {
//                    viewModel.isBottomBarInvisible.value = false
//                    mBinding.bottomAppBar.setBackgroundColor(ContextCompat.getColor(this, R.color.background))
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MyFragment())
                        .commit()
                    true
                }

                else -> false
            }
        }

        mBinding.btnAdd.setOnClickListener {
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
        }
    }

    fun hasBottomBar(hasBottomBar: Boolean = true) {
        mBinding.bottomNav.visibility = if (hasBottomBar) View.VISIBLE else View.GONE
        mBinding.bottomAppBar.visibility = if (hasBottomBar) View.VISIBLE else View.GONE
        mBinding.btnAdd.visibility = if (hasBottomBar) View.VISIBLE else View.GONE
    }

    fun showLoginDialog() {
        val builder =
            AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .create()
        val view = layoutInflater.inflate(R.layout.dialog_login, null)
        builder.setView(view)
        dialogBinding.btnDauth.setOnClickListener {
            builder.dismiss()
        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()
    }
}
