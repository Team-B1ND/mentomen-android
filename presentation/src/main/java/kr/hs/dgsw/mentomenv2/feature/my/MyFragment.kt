package kr.hs.dgsw.mentomenv2.feature.my

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.dgsw.mentomenv2.adapter.HomeAdapter
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentMyBinding
import kr.hs.dgsw.mentomenv2.feature.home.HomeFragmentDirections
import kr.hs.dgsw.mentomenv2.feature.splash.IntroActivity

class MyFragment : BaseFragment<FragmentMyBinding, MyViewModel>() {
    override val viewModel: MyViewModel by viewModels()
    private lateinit var adapter: HomeAdapter
    private fun initHomeAdapter() {
        adapter = HomeAdapter {
            findNavController().navigate(MyFragmentDirections.actionUserFragmentToDetailFragment(it))
        }
        mBinding.rvMyPage.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvMyPage.adapter = adapter
        mBinding.btnLogout.setOnClickListener {
            val intent = Intent(requireContext(), IntroActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }
    }

    override fun setupViews() {
        initHomeAdapter()
        observeViewModel()
    }

    private fun observeViewModel() = with(viewModel) {
        itemList.observe(viewLifecycleOwner) { adapter.submitList(it) }
    }
}
