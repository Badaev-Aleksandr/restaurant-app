package de.ait.restaurantapp.repositories;

import de.ait.restaurantapp.model.Reservation;
import de.ait.restaurantapp.enums.ReservationStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// do we use List or ...DB???!!!
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository

public interface ReservationRepo extends JpaRepository<Reservation, Long> {

    //looking for reservation for exact  table, time overlapping
    List<Reservation> findByRestaurantTable_IdAndReservationStatusAndStartDateTimeLessThanAndEndDateTimeGreaterThan(
            Integer tableId,
            ReservationStatus status,
            LocalDateTime end,
            LocalDateTime start
    );
    /**
     * Находит все резервации для указанного стола,
     * у которых startDateTime >= from и < to.
     * и статус CONFIRMED
     */
    List<Reservation> findByRestaurantTable_IdAndStartDateTimeGreaterThanEqualAndStartDateTimeLessThanAndReservationStatus(
            Integer tableId,
            LocalDateTime start,
            LocalDateTime end,
            ReservationStatus reservationStatus
    );

    List<Reservation> findByCustomerEmailIgnoreCaseAndReservationStatusAndStartDateTimeBetween(
            String customerEmail,
            ReservationStatus status,
            LocalDateTime start,
            LocalDateTime end
    );



    Optional<Reservation> findByReservationCode(String reservationCode);

}
