package com.pawelweselak.kotlinjpademo

import com.pawelweselak.kotlinjpademo.repositories.CarrierRepository
import com.pawelweselak.kotlinjpademo.repositories.ShipmentRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestConstructor.AutowireMode.ALL
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.math.BigDecimal
import java.util.*

@Testcontainers
@SpringBootTest
@TestConstructor(autowireMode = ALL)
class KotlinJpaDemoApplicationTests(
	private val carrierRepository: CarrierRepository,
	private val shipmentRepository: ShipmentRepository
) {

	companion object {
		@Container
		val container = PostgreSQLContainer("postgres:12-alpine").apply {
			withDatabaseName("testdb")
			withUsername("john")
			withPassword("s3crEt")
		}

		@JvmStatic
		@DynamicPropertySource
		fun properties(registry: DynamicPropertyRegistry) {
			registry.add("spring.datasource.url", container::getJdbcUrl);
			registry.add("spring.datasource.password", container::getPassword);
			registry.add("spring.datasource.username", container::getUsername);
		}
	}

	@Test
	fun contextLoads() = Unit

	@Test
	fun `Should persist Shipment with Carrier`() {
		// given
		val carrier = carrier()
		val shipment = shipment(carrier = carrier)

		// when
		carrierRepository.save(carrier)
		shipmentRepository.save(shipment)

		// then
		assertNotNull(shipmentRepository.findByIdOrNull(shipment.id))
	}

	@Test
	fun `Should persist update Carrier`() {
		// given
		val carrier = carrierRepository.getByName("DPD")

		// when
		carrierRepository.save(carrier.copy(maxShipmentValue = BigDecimal.ONE))
		val updatedCarrier = carrierRepository.findByIdOrNull(carrier.id)

		// then
		assertEquals(BigDecimal.ONE, updatedCarrier?.maxShipmentValue)
	}
}

fun carrier(
	id: UUID = UUID.randomUUID(),
	name: String = "DB Schenker",
	maxShipmentValue: BigDecimal = BigDecimal.TEN
) = Carrier(
	id = id,
	name = name,
	maxShipmentValue = maxShipmentValue
)

fun shipment(
	id: UUID = UUID.randomUUID(),
	address: Address = address(),
	carrier: Carrier
) = Shipment(
	id = id,
	destination = address,
	carrier = carrier
)

fun address(
	street: String = "Bracka 8",
	city: String = "Krakow",
	country: String = "PL"
) = Address(
	street = street,
	city = city,
	country = country
)
