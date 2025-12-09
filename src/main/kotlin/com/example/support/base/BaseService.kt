package com.example.support.base

import com.example.controller.model.base.BaseDomain
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

abstract class BaseService<T: BaseDomain, Req : Any>(
    private val insertPredicate: (Long, Req) -> T,
    private val updatePredicate: (T, Req) -> T,
)
{
    protected val seq = AtomicLong(0)
    protected var items = ConcurrentHashMap<Long, T>()

    open fun list(): List<T> = items.values.sortedBy { it.id }

    fun get(id: Long): T? = items[id]

    open fun delete(id: Long): Boolean = items.remove(id) != null

    open fun create(req: Req): T {
        val id = seq.incrementAndGet()
        val entity = insertPredicate(id, req)
        items[id] = entity
        return entity
    }

    open fun update(id: Long, req: Req): T? {
        val existing = items[id] ?: return null
        val updated = updatePredicate(existing, req)
        items[id] = updated
        return updated
    }
}