package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.BootNotificationConfirmation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.TimeZone;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Thomas Volden on 10-May-16.
 */
public class BootNotificationConfirmationTest {
    BootNotificationConfirmation confirmation;

    @Before
    public void setUp() throws Exception {

        confirmation = new BootNotificationConfirmation();
    }

    @Test
    public void setCurrentTime_now_currentTimeIsSet() {
        // Given
        Calendar now = Calendar.getInstance();

        // When
        confirmation.setCurrentTime(now);

        // Then
        assertThat(confirmation.objCurrentTime(), equalTo(now));
    }

    @Test
    public void getCurrentTime_currentTimeIsSet_returnsFormattetString() {
        // Given
        String expected = "2016-01-01T01:01:01.001Z";
        Calendar someDate = new Calendar.Builder().setDate(2016,00,01)
                .setTimeOfDay(01,01,01,001).setTimeZone(TimeZone.getTimeZone("GMT+00:00")).build();
        confirmation.setCurrentTime(someDate);

        // When
        String result = confirmation.getCurrentTime();

        // Then
        assertThat(result, equalTo(expected));
    }

    @Test
    public void setInterval_anInteger_intervalIsSet() throws Exception {
        // Given
        int anInterval = 41;

        // When
        confirmation.setInterval(anInterval);

        // Then
        assertThat(confirmation.getInterval(), equalTo(anInterval));
    }

    @Test
    public void setInterval_aNegativeValue_throwsPropertyConstraintException() {
        // Given
        int aNegativeValue = - 42;

        // When
        try {
            confirmation.setInterval(aNegativeValue);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("interval"));
            assertThat(ex.getFieldValue(), equalTo(aNegativeValue));
        }
    }

    @Test
    public void setStatus_illegal_throwsPropertyConstraintException() {
        // Given
        String illegalStatus = "Illegal";

        // When
        try {
            confirmation.setStatus(illegalStatus);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            // Then
            assertThat(ex.getFieldKey(), equalTo("status"));
            assertThat(ex.getFieldValue(), equalTo(illegalStatus));
        }
    }

    @Test
    public void setStatus_accepted_statusIsAccepted() throws Exception {
        // Given
        String status = "Accepted";

        // When
        confirmation.setStatus(status);

        // Then
        assertThat(confirmation.getStatus(), equalTo(status));
    }

    @Test
    public void setStatus_pending_statusIsAccepted() throws Exception {
        // Given
        String status = "Pending";

        // When
        confirmation.setStatus(status);

        // Then
        assertThat(confirmation.getStatus(), equalTo(status));
    }

    @Test
    public void setStatus_rejected_statusIsAccepted() throws Exception {
        // Given
        String status = "Rejected";

        // When
        confirmation.setStatus(status);

        // Then
        assertThat(confirmation.getStatus(), equalTo(status));
    }

    @Test
    public void validate_currentTimeAndIntervalAndStatusIsSet_returnTrue() throws Exception {
        // Given
        confirmation.setCurrentTime(Calendar.getInstance());
        confirmation.setInterval(42);
        confirmation.setStatus("Accepted");

        // When
        boolean isValid = confirmation.validate();

        // Then
        assertThat(isValid, is(true));
    }

    @Test
    public void validate_returnFalse() {
        // When
        boolean isValid = confirmation.validate();

        // Then
        assertThat(isValid, is(false));
    }
}