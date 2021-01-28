package com.rest.demo.service

import com.rest.demo.model.UserModel
import com.rest.demo.repository.UserRepository
import com.rest.demo.utils.CustomException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService {

    @Autowired private lateinit var repository: UserRepository

    fun createUser(user: UserModel): UserModel{
        print("M<<<<<<<")
        print(user)
        return repository.save(user)
    }

    fun getUsers(): List<UserModel> = repository.findAll()

    fun getUserById(id: Long): UserModel {
        //return repository.findById(id)
        val userTable = repository.findById(id)

        if (userTable.isPresent)
            return userTable.get()

        throw CustomException("User not found")
    }

    fun updateUser(user: UserModel): UserModel {
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
