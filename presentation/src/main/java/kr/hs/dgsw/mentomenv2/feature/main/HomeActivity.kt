package kr.hs.dgsw.mentomenv2.feature.main

import android.content.Intent
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
}
