package seedu.address.logic.parser.appointmentparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.appointmentcommands.AppointmentScheduleCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.appointment.Description;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AppointmentScheduleCommand object
 */
public class AppointmentScheduleCommandParser implements Parser<AppointmentScheduleCommand> {

    public static final String MESSAGE_CONSTRAINTS =
            "Appointments can only be scheduled for future appointment time.\n"
            + "i.e. you cannot schedule an Appointment before the current time.";

    /**
     * Parses the given {@code String} of arguments in the context of the AppointmentScheduleCommand
     * and returns an AppointmentScheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AppointmentScheduleCommand parse(String args)
            throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT_START, PREFIX_APPOINTMENT_END, PREFIX_PATIENT,
                        PREFIX_DESCRIPTION, PREFIX_TAG);
        if (!arePrefixesPresent(argMultimap, PREFIX_APPOINTMENT_START, PREFIX_APPOINTMENT_END, PREFIX_PATIENT,
                PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AppointmentScheduleCommand.MESSAGE_USAGE));
        }
        LocalDateTime startTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_APPOINTMENT_START).get());
        LocalDateTime endTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_APPOINTMENT_END).get());
        // cannot schedule an Appointment before now.
        if (startTime.isBefore(LocalDateTime.now()) || endTime.isBefore(LocalDateTime.now())) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }

        AppointmentTime appointmentTime = new AppointmentTime(startTime, endTime);
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Boolean isMissed = false; // isMissed is always initialised to false for newly scheduled appointment
        Boolean isCompleted = false; // isCompleted is always initialised to false for newly scheduled appointment
        String patient = argMultimap.getValue(PREFIX_PATIENT).get();
        Appointment appointment = new Appointment(appointmentTime, patient, tagList, isCompleted, isMissed,
                description);

        return new AppointmentScheduleCommand(appointment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
