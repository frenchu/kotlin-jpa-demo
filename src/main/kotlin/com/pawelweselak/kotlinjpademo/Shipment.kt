package com.pawelweselak.kotlinjpademo

import java.util.*
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Shipment(
    @Id
    val id: UUID,
    @Embedded
    val destination: Address,
    @ManyToOne
    val carrier: Carrier
)