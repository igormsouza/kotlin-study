//package com.example.support
//
//import com.example.controller.model.domain.User
//import com.example.controller.model.request.UserRequest
//import jakarta.inject.Singleton
//import java.util.concurrent.ConcurrentHashMap
//import java.util.concurrent.atomic.AtomicLong
//
//@Singleton
//class UserService {
//    private val seq = AtomicLong(0)
//    private val dicItems = ConcurrentHashMap<Long, User>()
//
//    fun list(): List<User> = dicItems.values.sortedBy { it.id }
//
//    fun get(id: Long): User? = dicItems[id]
//
//    fun create(req: UserRequest): User {
//        val id = seq.incrementAndGet()
//        val user = User(id = id, name = req.name.trim(), email = req.email.trim().lowercase())
//        dicItems[id] = user
//        return user
//    }
//
//    fun update(id: Long, req: UserRequest): User? {
//        val existing = dicItems[id] ?: return null
//        val updated = existing.copy(
//            name = req.name.trim(),
//            email = req.email.trim().lowercase()
//        )
//        dicItems[id] = updated
//        return updated
//    }
//
//    fun delete(id: Long): Boolean = dicItems.remove(id) != null
//}
