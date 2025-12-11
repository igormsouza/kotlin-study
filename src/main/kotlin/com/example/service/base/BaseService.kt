package com.example.service.base

import com.example.model.base.BaseDomain
import io.micronaut.data.repository.CrudRepository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

abstract class BaseService<T: BaseDomain, Req : Any>(
    private val insertPredicate: (Long, Req) -> T,
    private val updatePredicate: (T, Req) -> T,
    private val repo: CrudRepository<T, Long>
)
{
    protected val seq = AtomicLong(0)
//    protected var items = ConcurrentHashMap<Long, T>()

    open fun list(): List<T> = repo.findAll().toList().sortedBy { it.id }

    fun get(id: Long): T? = repo.findById(id).orElse(null)

    open fun delete(id: Long): Boolean =
        if (repo.existsById(id)) {
            repo.deleteById(id)
            true
        } else false

    open fun create(req: Req): T {
        val id = seq.incrementAndGet()
        val entity = insertPredicate(id, req)
        return repo.save(entity)
    }

    open fun update(id: Long, req: Req): Boolean {
        val existing = repo.findById(id).orElse(null) ?: return false
        val updated = updatePredicate(existing, req)
        return repo.update(updated) != null;
    }
}