package kr.hs.dgsw.mentomenv2.feature.detail

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kr.hs.dgsw.mentomenv2.R
import kr.hs.dgsw.mentomenv2.adapter.DetailImageAdapter
import kr.hs.dgsw.mentomenv2.base.BaseFragment
import kr.hs.dgsw.mentomenv2.databinding.FragmentDetailBinding
import kr.hs.dgsw.mentomenv2.feature.main.MainActivity

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(){
    override val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    override fun setupViews() {
        (activity as MainActivity).hasBottomBar(false)
        viewModel.userName.value = args.item.userName
        viewModel.content.value = args.item.content
        viewModel.createDateTime.value = args.item.createDateTime
        viewModel.stdInfo.value = args.item.stdInfo

        if (args.item.profileUrl.isNotEmpty()) {
            Glide.with(requireContext())
                .load(args.item.profileUrl)
                .into(mBinding.ivProfile)
        } else {
            Glide.with(requireContext())
                .load(R.drawable.ic_default_user)
                .into(mBinding.ivProfile)
        }
        if (args.item.imgUrls.isNotEmpty()) {
            mBinding.viewpagerFrame.visibility = View.VISIBLE
            val imageAdapter = DetailImageAdapter(args.item.imgUrls) {}
            mBinding.viewpager.adapter = imageAdapter
            mBinding.wormDotsIndicator.attachTo(mBinding.viewpager)

            mBinding.wormDotsIndicator.visibility = View.VISIBLE
        } else {
            mBinding.viewpagerFrame.visibility = View.GONE
            mBinding.wormDotsIndicator.visibility = View.GONE
        }

        mBinding.backButton.setOnClickListener {
            (activity as MainActivity).hasBottomBar(true)
            findNavController().navigateUp()
        }
        mBinding.ivSend.setOnClickListener {
            Toast.makeText(requireContext(), "댓글 작성 완료", Toast.LENGTH_SHORT).show()
        }
        mBinding.datetime.text = args.item.createDateTime
        TODO("nav arg로 받는 게 아니라 post Id를 받아와서 호출하는 방식으로 바꿔야함, 실시간성 때문에")
    }
}