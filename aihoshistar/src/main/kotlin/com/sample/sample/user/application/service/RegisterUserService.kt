package com.aihoshistar.sample.user.application.service

import com.aihoshistar.sample.user.application.exception.UserAlreadyExistException
import com.aihoshistar.sample.user.application.port.`in`.RegisterUserCommand
import com.aihoshistar.sample.user.application.port.`in`.RegisterUserUseCase
import com.aihoshistar.sample.user.application.port.out.GetUserPersistencePort
import com.aihoshistar.sample.user.application.port.out.SaveUserPersistencePort
import com.aihoshistar.sample.user.domain.model.User
import com.aihoshistar.sample.user.domain.vo.Location
import com.aihoshistar.sample.user.domain.vo.UserStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class RegisterUserService(
    private val getUserPersistencePort: GetUserPersistencePort,
    private val saveUserPersistencePort: SaveUserPersistencePort,
) : RegisterUserUseCase {
    override fun execute(command: RegisterUserCommand): User {
        getUserPersistencePort.findByEmailOrNickname(
            email = command.email, nickname = command.nickname,
        ) ?. run {
            throw UserAlreadyExistException()
        }

        // TODO: encrypt password
        val user = with(command) {
            User(
                password = password,
                email = email,
                nickname = nickname,
                status = UserStatus.ONLINE,
                location = Location(lat = lat, lng = lng),
                stayedAt = LocalDateTime.now(),
            )
        }
        return saveUserPersistencePort.save(user = user)
    }
}
