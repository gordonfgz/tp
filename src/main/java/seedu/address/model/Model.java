package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.UserHistoryManager;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Patient;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /* Patient abstract methods */

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the address book.
     */
    boolean hasPatient(Patient patient);

    /**
     * Deletes the given patient.
     * The patient must exist in the address book.
     */
    void deletePatient(Patient target);

    /**
     * Adds the given patient.
     * {@code patient} must not already exist in the address book.
     */
    void addPatient(Patient patient);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the address book.
     * The patient identity of {@code editedPatient} must not be
     * the same as another existing patient in the address book.
     */
    void setPatient(Patient target, Patient editedPatient);

    /** Returns an unmodifiable view of the filtered patient list */
    ObservableList<Patient> getFilteredPatientList();

    // ObservableList<Patient> getStartFilteredPatientList();

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);

    /* Appointment abstract methods */

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the address book.
     */
    boolean hasAppointment(Appointment appointment);

    /**
     * Deletes the given appointment.
     * The appointment must exist in the address book.
     */
    void deleteAppointment(Appointment target);

    /**
     * Sets the given appointment as completed.
     * The appointment must exist in the address book.
     */
    void completeAppointment(Appointment target);

    /**
     * Adds the given appointment.
     * {@code appointment} must not already exist in the address book.
     */
    void addAppointment(Appointment appointment);

    /**
     * Replaces the given appointment {@code target} with {@code editedAppointment}.
     * {@code target} must exist in the address book.
     * The appointment identity of {@code editedAppointment} must not be
     * the same as another existing appointment in the address book.
     */
    void setAppointment(Appointment target, Appointment editedAppointment);

    /**
     * Updates the appointments in the addressbook that have been missed by {@code now}.
     * @throws NullPointerException if {@code now} is null.
     */
    void setMissedAppointments(LocalDateTime now);

    /** Returns an unmodifiable view of the filtered appointment list */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);

    /**
     * Returns user history.
     */
    UserHistoryManager getUserHistoryManager();

    /**
     * Undo the history.
     */
    void undoHistory();

    /**
     * Redo the history.
     */
    void redoHistory();

    // TODO: include undo operations for Appointment class.
}
