/*
package seedu.address.model.filters.appointmentfilters;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.model.appointment.Appointment;

public class SearchAppointmentNewMissesFilter implements Predicate<Appointment> {
    private LocalDateTime now;

    public SearchAppointmentNewMissesFilter() {
        this.now = LocalDateTime.now();
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getEndTime().isBefore(now);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchAppointmentNewMissesFilter // instanceof handles nulls
                && now.equals(((SearchAppointmentNewMissesFilter) other).now)); // state check
    }
}
 */
