package seedu.address.model.appointment;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

public class AppointmentTime {

    public static final String MESSAGE_CONSTRAINTS =
        "AppointmentTime must be valid, and start must be before end.\n"
        + "AppointmentTime must also not overlap with an existing Appointment's time.\n"
        + "*Note: Time indicated must be XX:XX (i.e. 9AM must be input as 09:00 instead of 9:00).\n"
        + "Eg: start/2020-12-07 08:00 end/2020-12-07 10:00";

    // Data fields
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Creates an AppointmentTime object that stores start and end time of an appointment.
     * @param start start time of appointment.
     * @param end end time of appointment.
     */
    public AppointmentTime(LocalDateTime start, LocalDateTime end) {
        checkArgument(isValidAppointmentTime(start, end), MESSAGE_CONSTRAINTS);
        this.start = start;
        this.end = end;
    }

    /**
     * Every field must be present and not null. Additionally, start must be before end.
     */
    public static boolean isValidAppointmentTime(LocalDateTime start, LocalDateTime end) {
        requireAllNonNull(start, end);
        if (start.isAfter(end)) {
            return false;
        }
        return true;
    }

    // Getter methods
    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "START: " + start + "\n END: " + end;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentTime // instanceof handles nulls
                && getStart().equals(((AppointmentTime) other).getStart()) // check same data fields
                && getEnd().equals(((AppointmentTime) other).getEnd()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

}
