package com.b1nd.mentomen.feature.main

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.b1nd.mentomen.R
import com.b1nd.mentomen.base.BaseActivity
import com.b1nd.mentomen.databinding.ActivityMainBinding
import com.b1nd.mentomen.feature.post.PostActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

    override fun start() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        mBinding.bottomNav.background = null
        mBinding.bottomNav.menu.getItem(1).isEnabled = false

        mBinding.bottomNav.setupWithNavController(navController)

        mBinding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    navController.popBackStack(R.id.home_fragment, true)
                    navController.navigate(R.id.home_fragment)
                    true
                }

                R.id.action_my -> {
                    navController.popBackStack(R.id.my_fragment, true)
                    navController.navigate(R.id.my_fragment)
                    true
                }

                else -> false
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.home_fragment -> mBinding.bottomNav.menu.findItem(R.id.action_home).isChecked = true
                R.id.my_fragment -> mBinding.bottomNav.menu.findItem(R.id.action_my).isChecked = true
            }
        }

        mBinding.btnAdd.setOnClickListener {
            val intent = Intent(this, PostActivity::class.java)
            intent.putExtra("isEdit", false)
            startActivity(intent)
        }
    }

    fun hasBottomBar(hasBottomBar: Boolean = true) {
        mBinding.bottomNav.visibility = if (hasBottomBar) View.VISIBLE else View.GONE
        mBinding.bottomAppBar.visibility = if (hasBottomBar) View.VISIBLE else View.GONE
        mBinding.btnAdd.visibility = if (hasBottomBar) View.VISIBLE else View.GONE
    }
}
