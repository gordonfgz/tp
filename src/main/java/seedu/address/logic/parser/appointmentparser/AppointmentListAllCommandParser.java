package seedu.address.logic.parser.appointmentparser;

import seedu.address.logic.commands.appointmentcommands.AppointmentListAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AppointmentListAllCommand object
 */
public class AppointmentListAllCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AppointmentListAllCommand
     * and returns a AppointmentListAllCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AppointmentListAllCommand parse() {
        return new AppointmentListAllCommand();
    }
}
