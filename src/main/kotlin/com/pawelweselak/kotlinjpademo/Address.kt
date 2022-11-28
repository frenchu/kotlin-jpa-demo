package com.pawelweselak.kotlinjpademo

import javax.persistence.Embeddable

@Embeddable
data class Address(
    val street: String,
    val city: String,
    val country: String
)