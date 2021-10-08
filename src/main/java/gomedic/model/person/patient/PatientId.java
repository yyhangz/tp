package gomedic.model.person.patient;

import gomedic.commons.util.AppUtil;
import gomedic.model.commonfield.Id;

/**
 * Represents a general id owned by a patient.
 * Guarantees: immutable; is valid as declared in {@link #isValidPatientId(Id)})}
 */
public class PatientId extends Id {
    public static final String MESSAGE_CONSTRAINTS =
            "Id should only contain from 1 to 999, Prefix should be P";
    private static final Character ACTIVITY_PREFIX = 'P';

    /**
     * {@inheritDoc}
     *
     * @param id Integer from 1 to 999.
     */
    public PatientId(Integer id) {
        super(id, ACTIVITY_PREFIX);
        AppUtil.checkArgument(isValidPatientId(this), MESSAGE_CONSTRAINTS);
    }

    /**
     * {@inheritDoc}
     *
     * @param id a string of format "PDDD", where "P" is an alphabetic character and "D" is a decimal number.
     */
    public PatientId(String id) {
        super(Integer.parseInt(id.substring(1)), ACTIVITY_PREFIX);
        AppUtil.checkArgument(isValidPatientId(this), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given stringId is a valid patient id.
     * Valid if integer is 3 digit, from 1 to 999, prefix is P.
     *
     * @param id Id.
     * @return true if valid.
     */
    public static boolean isValidPatientId(Id id) {
        int number = Integer.parseInt(id.toString().substring(1));
        Character prefix = id.toString().charAt(0);
        boolean isValidPrefix = prefix.equals(ACTIVITY_PREFIX);

        return isValidId(number, prefix) && isValidPrefix;
    }
}