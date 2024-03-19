package kr.hs.dgsw.mentomenv2.feature.main

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivityMainBinding
import kr.hs.dgsw.mentomenv2.feature.home.HomeFragment
import kr.hs.dgsw.mentomenv2.feature.my.MyFragment
import kr.hs.dgsw.mentomenv2.feature.post.PostActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

    override fun start() {
        mBinding.bottomNav.background = null
        mBinding.bottomNav.menu.getItem(1).isEnabled = false

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        mBinding.bottomNav.setupWithNavController(navController)

        mBinding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, HomeFragment())
                        .commit()
                    true
                }

                R.id.action_my -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, MyFragment())
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
}
