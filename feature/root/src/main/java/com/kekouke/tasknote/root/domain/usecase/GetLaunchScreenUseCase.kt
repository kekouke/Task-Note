package com.kekouke.tasknote.root.domain.usecase

import com.kekouke.tasknote.login.domain.UserRepository
import com.kekouke.tasknote.root.domain.entities.LaunchScreen
import javax.inject.Inject

class GetLaunchScreenUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): LaunchScreen {
        return when {
            userRepository.isAuthenticated -> LaunchScreen.Tasks
            else -> LaunchScreen.Login
        }
    }
}
