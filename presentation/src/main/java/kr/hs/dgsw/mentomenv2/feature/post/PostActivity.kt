package kr.hs.dgsw.mentomenv2.feature.post

import androidx.activity.viewModels
import kr.hs.dgsw.mentomenv2.base.BaseActivity
import kr.hs.dgsw.mentomenv2.databinding.ActivityPostBinding

class PostActivity : BaseActivity<ActivityPostBinding, PostViewModel>() {
    override val viewModel: PostViewModel by viewModels()

    override fun start() {
        TODO("Not yet implemented")
    }
}
