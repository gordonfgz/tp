package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Appointment appointment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label patientName;
    @FXML
    private Label description;
    @FXML
    private Label isCompleted;
    @FXML
    private Label isMissed;
    @FXML
    private Label tag;
    @FXML
    private Label appointmentTime;
    @FXML
    private FlowPane tags;


    /**
     * Creates a {@code PersonCode} with the given {@code Patient} and index to display.
     */
    public AppointmentCard(Appointment appointment, int displayedIndex) {
        super(FXML);
        this.appointment = appointment;
        id.setText(displayedIndex + ". ");
        description.setText(appointment.getDescription().toString());
        patientName.setText(appointment.getPatientString());
        isCompleted.setText("Completed: " + appointment.isCompleted().toString());
        appointmentTime.setText(appointment.getAppointmentTime().toString());
        isMissed.setText("Missed: " + appointment.isMissed().toString());
        appointment.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientCard)) {
            return false;
        }

        // state check
        AppointmentCard card = (AppointmentCard) other;
        return id.getText().equals(card.id.getText())
                && appointment.equals(card.appointment);
    }
}
