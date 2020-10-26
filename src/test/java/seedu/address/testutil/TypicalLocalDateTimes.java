package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * A utility class containing a list of {@code LocalDateTime} objects to be used in tests.
 */
public class TypicalLocalDateTimes {
    public static final LocalTime FIRST_TIME = LocalTime.of(12, 30);
    public static final LocalTime SECOND_TIME = LocalTime.of(18, 15);

    public static final LocalDate FIRST_DATE = LocalDate.of(2020, 10, 25);
    public static final LocalDate SECOND_DATE = LocalDate.of(2020, 10, 10);

    public static final LocalDateTime FIRST_DATETIME = LocalDateTime.of(FIRST_DATE, FIRST_TIME);
    public static final LocalDateTime SECOND_DATETIME = LocalDateTime.of(SECOND_DATE, SECOND_TIME);
}
