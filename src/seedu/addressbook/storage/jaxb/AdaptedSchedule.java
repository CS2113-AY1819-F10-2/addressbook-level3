package seedu.addressbook.storage.jaxb;

import seedu.addressbook.common.Utils;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Schedule;

import javax.xml.bind.annotation.XmlValue;

/**
 * JAXB-friendly adapted tag data holder class.
 */
public class AdaptedSchedule {

    @XmlValue
    public String scheduleName;

    /**
     * No-arg constructor for JAXB use.
     */
    public AdaptedSchedule() {}

    /**
     * Converts a given Tag into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created AdaptedTag
     */
    public AdaptedSchedule(Schedule source) {
        scheduleName = source.value;
    }

    /**
     * Returns true if any required field is missing.
     *
     * JAXB does not enforce (required = true) without a given XML schema.
     * Since we do most of our validation using the data class constructors, the only extra logic we need
     * is to ensure that every xml element in the document is present. JAXB sets missing elements as null,
     * so we check for that.
     */
    public boolean isAnyRequiredFieldMissing() {
        return Utils.isAnyNull(scheduleName);
    }

    /**
     * Converts this jaxb-friendly adapted tag object into the Tag object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Schedule toModelType() throws IllegalValueException {
        return new Schedule(scheduleName);
    }
}
