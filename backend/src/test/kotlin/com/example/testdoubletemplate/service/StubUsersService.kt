package com.example.testdoubletemplate.service

class StubUsersService:UsersService {
    var getUsersService_result = listOf(Users())
    override fun getUsersService():List<Users> {
        return getUsersService_result
    }

    override fun postUserService(users: Users) {
        TODO("Not yet implemented")
    }

}