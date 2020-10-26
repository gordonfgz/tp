package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_END_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_END_TWO;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_START_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_START_TWO;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.UserHistoryManager;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.AppointmentBuilder;

public class AppointmentScheduleCommandTest {
    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentScheduleCommand(null));
    }

    //    @Test
    //    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
    //        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
    //        Appointment validAppointment = new AppointmentBuilder().build();
    //
    //        CommandResult commandResult = new AppointmentScheduleCommand(validAppointment).execute(modelStub);
    //
    //        assertEquals(String.format(AppointmentScheduleCommand.MESSAGE_SUCCESS, validAppointment),
    //                commandResult.getFeedbackToUser());
    //        assertEquals(Arrays.asList(validAppointment), modelStub.appointmentAdded);
    //    }

    //    @Test
    //    public void execute_duplicateAppointment_throwsCommandException() {
    //        Appointment validAppointment = new AppointmentBuilder().build();
    //        AppointmentScheduleCommand appointmentScheduleCommand = new AppointmentScheduleCommand(validAppointment);
    //        AppointmentScheduleCommandTest.ModelStub modelStub =
    //                new AppointmentScheduleCommandTest.ModelStubWithAppointment(validAppointment);
    //
    //        assertThrows(CommandException.class, AppointmentScheduleCommand.MESSAGE_DUPLICATE_APPOINTMENT, () ->
    //                appointmentScheduleCommand.execute(modelStub));
    //    }

    @Test
    public void equals() {
        Appointment apt1 = new AppointmentBuilder().withAppointmentTime(VALID_START_ONE, VALID_END_ONE).build();
        Appointment apt2 = new AppointmentBuilder().withAppointmentTime(VALID_START_TWO, VALID_END_TWO).build();
        AppointmentScheduleCommand addCommand1 = new AppointmentScheduleCommand(apt1);
        AppointmentScheduleCommand addCommand2 = new AppointmentScheduleCommand(apt2);

        // same object -> returns true
        assertTrue(addCommand1.equals(addCommand1));

        // same values -> returns true
        AppointmentScheduleCommand addCommand1Copy = new AppointmentScheduleCommand(apt1);
        assertTrue(addCommand1.equals(addCommand1Copy));

        // different types -> returns false
        assertFalse(addCommand1.equals(1));

        // null -> returns false
        assertFalse(addCommand1.equals(null));

        // different patient -> returns false
        assertFalse(addCommand1.equals(addCommand2));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePatient(Patient target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatient(Patient target, Patient editedPatient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Patient> getFilteredPatientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPatientList(Predicate<Patient> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void completeAppointment(Appointment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(Appointment target, Appointment editedAppointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMissedAppointments(LocalDateTime now) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public UserHistoryManager getUserHistoryManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoHistory() {
            throw new AssertionError("This method should not be called.");
        }

    }
    /**
     * A Model stub that contains a single appointment.
     */
    private class ModelStubWithAppointment extends AppointmentScheduleCommandTest.ModelStub {
        private final Appointment appointment;

        ModelStubWithAppointment(Appointment appointment) {
            requireNonNull(appointment);
            this.appointment = appointment;
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return this.appointment.isSameAppointment(appointment);
        }
    }

    /**
     * A Model stub that always accept the appointment being added.
     */
    private class ModelStubAcceptingAppointmentAdded extends AppointmentScheduleCommandTest.ModelStub {
        final ArrayList<Appointment> appointmentAdded = new ArrayList<>();

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentAdded.stream().anyMatch(appointment::isSameAppointment);
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentAdded.add(appointment);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
