package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Looks through all appointments in the addressbook.
 * Sets all past appointments that have not been completed as missed.
 */
public class AppointmentNewMissesCommand extends Command {

    public static final String COMMAND_WORD = "a-new-misses";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets all past uncompleted appointments as missed. "
            + "\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_MISS_APPOINTMENT_SUCCESS = "Checked for new missed appointments!";

    private final LocalDateTime now;

    public AppointmentNewMissesCommand() {
        this.now = LocalDateTime.now();
    }

    public AppointmentNewMissesCommand(LocalDateTime time) {
        this.now = time;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setMissedAppointments(now); // Sets all new missed appointments
        return new CommandResult(
                String.format(MESSAGE_MISS_APPOINTMENT_SUCCESS, model.getFilteredAppointmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentNewMissesCommand // instanceof handles nulls
                && now.equals(((AppointmentNewMissesCommand) other).now)); // state check
    }
}
