package kr.hs.dgsw.mentomenv2.feature.main

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivityHomeBinding
import kr.hs.dgsw.mentomenv2.databinding.DialogLoginBinding
import kr.hs.dgsw.mentomenv2.feature.my.MyFragment
import kr.hs.dgsw.mentomenv2.feature.post.PostActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityHomeBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()
    private val dialogBinding: DialogLoginBinding by lazy {
        DialogLoginBinding.inflate(layoutInflater)
    }
    private val TIME_INTERVAL: Long = 2000
    private var mBackPressed: Long = 0

    override fun start() {
        collectBottomBarVisible()
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

    fun collectBottomBarVisible() {
        viewModel.isBottomBarInvisible.observe(this) {
            if (it) {
                mBinding.bottomNav.visibility = android.view.View.VISIBLE
                mBinding.bottomAppBar.visibility = android.view.View.VISIBLE
                mBinding.btnAdd.show()
            } else {
                showLoginDialog()
                mBinding.bottomNav.visibility = android.view.View.GONE
                mBinding.bottomAppBar.visibility = android.view.View.GONE
                mBinding.btnAdd.hide()
            }
        }
    }

    fun showLoginDialog() {
        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.dialog_login, null)
        builder.setView(view)
        dialogBinding.btnDauth.setOnClickListener {
            builder.dismiss()
        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()
    }

    override fun onBackPressed() {
        Log.d("Debug", "onBackPressed() called")
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed()
            return
        } else {
            Toast.makeText(baseContext, "한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }

        mBackPressed = System.currentTimeMillis()
    }
}
