package com.rest.demo.service

import com.rest.demo.entity.UserEntity
import com.rest.demo.repository.UserRepository
import com.rest.demo.utils.exception.CustomException
//import com.rest.demo.utils.exception.CustomException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService {

    @Autowired
    lateinit var repository: UserRepository

    fun createUser(user: UserEntity): UserEntity{
        return repository.save(user)
    }

    fun getUsers(): List<UserEntity> = repository.findAll()

    fun getUserById(id: Long): UserEntity? {
        //return repository.findById(id)
        val userTable = repository.findById(id)

        if (userTable.isPresent)
            return userTable.get()

        throw CustomException("User not found")
    }

    fun updateUser(user: UserEntity): UserEntity? {
        val userTable = repository.findById(user.id)

        if (userTable.isPresent) {
            val muser = userTable.get()
            muser.name = user.name

            repository.save(muser)
            return muser
        }

        throw CustomException("User not found")
    }

    fun deteleteUser(id: Long) {
        val userTable = repository.findById(id)

        if (userTable.isPresent)
            return repository.deleteById(id)

        throw CustomException("User not found")
    }
}
