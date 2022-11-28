package com.pawelweselak.kotlinjpademo

import java.math.BigDecimal
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Carrier(
    @Id
    val id: UUID,
    val name: String,
    val maxShipmentValue: BigDecimal,
)
