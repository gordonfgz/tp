package seedu.address.logic.commands.appointmentcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBook;
import static seedu.address.testutil.TypicalLocalDateTimes.FIRST_DATETIME;
import static seedu.address.testutil.TypicalLocalDateTimes.SECOND_DATETIME;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code AppointmentNewMissesCommand}.
 */
public class AppointmentNewMissesCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        AppointmentNewMissesCommand newMissesFirstCommand = new AppointmentNewMissesCommand(FIRST_DATETIME);
        AppointmentNewMissesCommand newMissesSecondCommand = new AppointmentNewMissesCommand(SECOND_DATETIME);

        // same object -> returns true
        assertTrue(newMissesFirstCommand.equals(newMissesFirstCommand));

        // same values -> returns true
        AppointmentNewMissesCommand newMissesFirstCommandCopy = new AppointmentNewMissesCommand(FIRST_DATETIME);
        assertTrue(newMissesFirstCommand.equals(newMissesFirstCommandCopy));

        // different types -> returns false
        assertFalse(newMissesFirstCommand.equals(1));

        // null -> returns false
        assertFalse(newMissesFirstCommand.equals(null));

        // different patient -> returns false
        assertFalse(newMissesFirstCommand.equals(newMissesSecondCommand));
    }
}
