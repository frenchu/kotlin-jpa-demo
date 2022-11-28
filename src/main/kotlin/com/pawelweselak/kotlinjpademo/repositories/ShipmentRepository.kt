package com.pawelweselak.kotlinjpademo.repositories

import com.pawelweselak.kotlinjpademo.Shipment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ShipmentRepository : CrudRepository<Shipment, UUID>