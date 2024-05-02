package kr.hs.dgsw.mentomenv2.feature.detail.image

import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentImageDetailBinding

class DetailImageFragment : BaseFragment<FragmentImageDetailBinding, DetailImageViewModel>() {
    override val viewModel: DetailImageViewModel by viewModels()
    private val args: DetailImageFragmentArgs by navArgs()

    override fun setupViews() {
        activity?.window?.apply {
            statusBarColor = ContextCompat.getColor(requireContext(), R.color.black)
            WindowInsetsControllerCompat(this, this.decorView).isAppearanceLightStatusBars = false
        }
        mBinding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        Glide.with(requireContext())
            .load(args.photo)
            .into(mBinding.photoView)
    }

    override fun onDestroy() {
        activity?.window?.apply {
            statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
            WindowInsetsControllerCompat(this, this.decorView).isAppearanceLightStatusBars = true
        }
        super.onDestroy()
    }
}
