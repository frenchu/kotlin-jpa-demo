package com.pawelweselak.kotlinjpademo.repositories

import com.pawelweselak.kotlinjpademo.Carrier
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CarrierRepository : CrudRepository<Carrier, UUID> {
    fun findByName(name: String): Carrier?
    fun getByName(name: String): Carrier
}