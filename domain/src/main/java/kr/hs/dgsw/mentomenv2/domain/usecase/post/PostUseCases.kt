package kr.hs.dgsw.mentomenv2.domain.usecase.post

data class PostUseCases (
    val getAllPostUseCase: GetAllPostUseCase,
    val getPostsByTagUseCases: GetPostsByTagUseCase
)
