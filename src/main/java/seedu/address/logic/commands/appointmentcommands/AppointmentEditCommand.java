package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.appointment.Description;
import seedu.address.model.patient.Patient;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing appointment in the address book.
 */
public class AppointmentEditCommand extends Command {
    public static final String COMMAND_WORD = "a-edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_APPOINTMENT_START + "START] "
            + "[" + PREFIX_APPOINTMENT_END + "END] "
            + "[" + PREFIX_PATIENT + "PATIENT] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT_START + "2020-02-05 09:00 "
            + PREFIX_DESCRIPTION + "Therapy session";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the address book.";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param index of the appointment in the filtered appointment list to edit
     * @param editAppointmentDescriptor details to edit the appointment with
     */
    public AppointmentEditCommand(Index index, EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editAppointmentDescriptor);

        this.index = index;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit,
                editAppointmentDescriptor, model.getAddressBook());

        if (appointmentToEdit.isSameAppointment(editedAppointment) && model.hasAppointment(editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment));
    }

    /**
     * Creates and returns a {@code Appointment} with the details of {@code AppointmentToEdit}
     * edited with {@code editAppointmentDescriptor}.
     */
    private static Appointment createEditedAppointment(Appointment appointmentToEdit,
                                                       EditAppointmentDescriptor editAppointmentDescriptor,
                                                       ReadOnlyAddressBook addressBook) {
        assert appointmentToEdit != null;

        LocalDateTime startTime = editAppointmentDescriptor.getStartTime().orElse(appointmentToEdit.getStartTime());
        LocalDateTime endTime = editAppointmentDescriptor.getEndTime().orElse(appointmentToEdit.getEndTime());
        AppointmentTime updatedAppointmentTime = new AppointmentTime(startTime, endTime);

        Patient updatedPatient = editAppointmentDescriptor.getPatient().orElse(appointmentToEdit.getPatient());

        Description updatedDescription = editAppointmentDescriptor.getDescription()
                .orElse(appointmentToEdit.getDescription());
        Set<Tag> updatedTags = editAppointmentDescriptor.getTags().orElse(appointmentToEdit.getTags());

        // Edit command does not allow editing of isCompleted and isMissed status
        Boolean isCompleted = appointmentToEdit.isCompleted();
        Boolean isMissed = appointmentToEdit.isMissed();

        return new Appointment(updatedAppointmentTime, updatedPatient, updatedTags, isCompleted, isMissed,
                updatedDescription);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentEditCommand)) {
            return false;
        }

        // state check
        AppointmentEditCommand e = (AppointmentEditCommand) other;
        return index.equals(e.index) && editAppointmentDescriptor.equals(e.editAppointmentDescriptor);
    }

    /**
     * Stores the details to edit the appointment with. Each non-empty field value will replace the
     * corresponding field value of the appointment.
     */
    public static class EditAppointmentDescriptor {
        private LocalDateTime endTime;
        private LocalDateTime startTime;
        private Patient patient;
        private boolean needsParsePatient;
        private String patientString; // used for EditCommandParser to retrieve patient from Model
        private Boolean isCompleted;
        private Boolean isMissed;
        private Description description;
        private Set<Tag> tags;

        public EditAppointmentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setAppointmentTime(toCopy.startTime, toCopy.endTime);
            setPatient(toCopy.patient);
            setNeedsParsePatient(toCopy.needsParsePatient);
            setPatientString(toCopy.patientString);
            setIsCompleted(toCopy.isCompleted);
            setIsMissed(toCopy.isMissed);
            setDescription(toCopy.description);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(startTime, endTime, patient, patientString, description, tags);
        }

        public void setAppointmentTime(LocalDateTime startTime, LocalDateTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public Optional<LocalDateTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public Optional<LocalDateTime> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        public void setPatient(Patient patient) {
            this.patient = patient;
        }

        public Optional<Patient> getPatient() {
            // reset to false, already updated to parsed patient
            setNeedsParsePatient(false);

            return Optional.ofNullable(patient);
        }

        public void setNeedsParsePatient(boolean needsParsePatient) {
            this.needsParsePatient = needsParsePatient;
        }

        public Optional<Boolean> getNeedsParsePatient() {
            return Optional.ofNullable(needsParsePatient);
        }

        public void setPatientString(String patientString) {
            this.patientString = patientString;
        }

        public Optional<String> getPatientString() {
            return Optional.ofNullable(patientString);
        }

        public void setIsCompleted(Boolean isCompleted) {
            this.isCompleted = isCompleted;
        }

        public Optional<Boolean> getIsCompleted() {
            return Optional.ofNullable(isCompleted);
        }

        public void setIsMissed(Boolean isMissed) {
            this.isMissed = isMissed;
        }

        public Optional<Boolean> getIsMissed() {
            return Optional.ofNullable(isMissed);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentDescriptor)) {
                return false;
            }

            // state check
            EditAppointmentDescriptor e = (EditAppointmentDescriptor) other;

            return getStartTime().equals(e.getStartTime())
                    && getEndTime().equals(e.getEndTime())
                    && getPatient().equals(e.getPatient())
                    && getPatientString().equals(e.getPatientString())
                    && getNeedsParsePatient().equals(e.getNeedsParsePatient())
                    && getIsCompleted().equals(e.getIsCompleted())
                    && getIsMissed().equals(e.getIsMissed())
                    && getDescription().equals(e.getDescription())
                    && getTags().equals(e.getTags());
        }

    }
}
