package seedu.address.model.filters.appointmentfilters;

import java.util.function.Predicate;

import seedu.address.model.appointment.Appointment;

/**
 * Filter appointments  {@code Appointment}'s that are not completed or missed.
 */
public class SearchAppointmentFilter implements Predicate<Appointment> {

    public SearchAppointmentFilter() {}

    @Override
    public boolean test(Appointment appointment) {
        return !appointment.isMissed() && !appointment.isCompleted();
    }

    @Override
    public boolean equals(Object other) {
        return other == this; // short circuit if same object.
    }
}
