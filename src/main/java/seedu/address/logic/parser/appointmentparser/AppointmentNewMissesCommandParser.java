package seedu.address.logic.parser.appointmentparser;

import seedu.address.logic.commands.appointmentcommands.AppointmentNewMissesCommand;

/**
 * Parses input arguments and creates a new AppointmentNewMissesCommand object
 */
public class AppointmentNewMissesCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AppointmentNewMissesCommand
     * and returns a AppointmentNewMissesCommand object for execution.
     */
    public AppointmentNewMissesCommand parse() {
        return new AppointmentNewMissesCommand();
    }
}
