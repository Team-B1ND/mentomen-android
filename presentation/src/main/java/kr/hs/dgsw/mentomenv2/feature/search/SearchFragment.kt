package kr.hs.dgsw.mentomenv2.feature.search

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.hs.dgsw.mentomenv2.adapter.HomeAdapter
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentSearchBinding
import kr.hs.dgsw.mentomenv2.domain.model.Post
import kr.hs.dgsw.mentomenv2.feature.main.MainActivity

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val viewModel: SearchViewModel by viewModels()
    private val adapter = HomeAdapter {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(
                it
            )
        )
    }

    override fun setupViews() {
        (activity as MainActivity).hasBottomBar(false)
        mBinding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        mBinding.cancelButton.setOnClickListener {
            mBinding.etSearch.text.clear()
        }
        mBinding.searchPage.setOnClickListener {
            hideKeyboard()
        }
        mBinding.swipeRefreshLayout.setOnClickListener {
            hideKeyboard()
        }
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getAllPost()
            mBinding.swipeRefreshLayout.isRefreshing = false
        }
        observeKeyWord()
        collectPostStates()
        mBinding.rvSearch.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvSearch.adapter = adapter
        viewModel.getAllPost()
    }

    private fun hideKeyboard() {
        mBinding.etSearch.clearFocus()
        val imm =
            requireView().context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    private fun updateFilterPostList(postList: List<Post>) {
        var filteredList = postList.filter { post ->
            post.content.contains(viewModel.keyWord.value.toString(), ignoreCase = true)
        }
        if (viewModel.keyWord.value.isNullOrBlank()) {
            filteredList = emptyList()
        }
        adapter.submitList(filteredList)
    }

    private fun collectPostStates() {
        lifecycleScope.launch {
            viewModel.postState.collect { state ->
                updateFilterPostList(state.postList ?: emptyList())
                if (state.error.isNotBlank()) {
                    Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun observeKeyWord() {
        viewModel.keyWord.observe(this) { _ ->
            viewModel.postState.value.postList?.let { postList ->
                updateFilterPostList(postList)
            }
        }
    }
}
