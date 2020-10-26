package seedu.address.model.filters.appointmentfilters;

import java.util.function.Predicate;

import seedu.address.model.appointment.Appointment;

/**
 * Tests that a {@code Appointment}'s {@code Patient}'s {@code isMissed} matches the Boolean provided.
 */
public class SearchAppointmentMissedFilter implements Predicate<Appointment> {

    private static final String ASSERT_MESSAGE = "An appointment cannot be both completed and missed at the same time.";

    private final Boolean isMissed;

    public SearchAppointmentMissedFilter() {
        this.isMissed = true;
    }

    @Override
    public boolean test(Appointment appointment) {
        assert appointment.isCompleted() && appointment.isMissed() : ASSERT_MESSAGE;
        return appointment.isMissed().equals(isMissed);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchAppointmentMissedFilter // instanceof handles nulls
                && isMissed.equals(((SearchAppointmentMissedFilter) other).isMissed)); // state check
    }
}
