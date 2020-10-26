package seedu.address.logic.commands.appointmentcommands;

import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AppointmentListAllCommand.
 * NOT DONE
 */
public class AppointmentListAllCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new AppointmentListAllCommand(), model,
                AppointmentListAllCommand.MESSAGE_LIST_APPOINTMENT_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        assertCommandSuccess(new AppointmentListAllCommand(), model,
                AppointmentListAllCommand.MESSAGE_LIST_APPOINTMENT_SUCCESS, expectedModel);
    }
}
