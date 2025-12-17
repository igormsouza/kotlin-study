package com.example.service.base

import com.example.model.base.BaseDomain
import io.micronaut.data.repository.CrudRepository
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

    open fun get(id: Long): T? = repo.findById(id).orElse(null)

    open fun delete(id: Long): Boolean =
        if (repo.existsById(id)) {
            repo.deleteById(id)
            true
        } else false

    open fun create(req: Req): T? {
        if (!this.preCreate(req)) return null

        val id = seq.incrementAndGet()
        val entity = insertPredicate(id, req)

        this.postCreate(req, entity)
        return repo.save(entity)
    }

    open fun update(id: Long, req: Req): Boolean {
        if (!this.preUpdate(req)) return false

        val entity = repo.findById(id).orElse(null) ?: return false
        val updated = updatePredicate(entity, req)

        this.postUpdate(req, entity)
        return repo.update(updated) != null
    }

    open fun preCreate(req: Req): Boolean = true
    open fun preUpdate(req: Req): Boolean = true

    open fun postCreate(req: Req, entity: T) { }
    open fun postUpdate(req: Req, entity: T) { }
}