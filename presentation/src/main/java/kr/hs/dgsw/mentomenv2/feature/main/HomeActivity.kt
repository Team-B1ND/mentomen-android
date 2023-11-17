package kr.hs.dgsw.mentomenv2.feature.main

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivityHomeBinding
import kr.hs.dgsw.mentomenv2.feature.my.MyFragment
import kr.hs.dgsw.mentomenv2.feature.post.PostActivity

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {
    override val viewModel: HomeActivityViewModel by viewModels()
    private val TIME_INTERVAL: Long = 2000
    private var mBackPressed: Long = 0

    override fun start() {
        mBinding.bottomNav.background = null
        mBinding.bottomNav.menu.getItem(1).isEnabled = false

        mBinding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                    true
                }

                R.id.action_my -> {

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
