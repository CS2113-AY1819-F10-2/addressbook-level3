package seedu.addressbook.data.person;

import java.util.Set;

import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.login.login;

/**
 * A read-only immutable interface for a Person in the addressbook.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyPerson {
    Person getPerson();
    Name getName();
    Nric getNric();
    Phone getPhone();
    Email getEmail();
    Address getAddress();
    Title getTitle();
    Set<Schedule> getSchedules();
    Set<Associated> getAssociateList();
    /**
     * The returned {@code Set} is a deep copy of the internal {@code Set},
     * changes on the returned list will not affect the person's internal tags.
     */
    Set<Tag> getTags();

    /**
     * Returns true if the nric value inside this object is same as those of the other (Note: interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyPerson other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getNric().equals(this.getNric()));
    }

    /**
     * Formats the person as text, showing all contact details.
     */
    default String getAsTextShowAll() {
        final StringBuilder builder = new StringBuilder();
        final String detailIsPrivate = "(private) ";
        builder.append(getName()).append(" NRIC: ");
        if (getNric().isPrivate()){
            builder.append(detailIsPrivate);
        }
        builder.append(getNric())
                .append(" Phone: ");
        if (getPhone().isPrivate()) {
            builder.append(detailIsPrivate);
        }
        builder.append(getPhone())
                .append(" Email: ");
        if (getEmail().isPrivate()) {
            builder.append(detailIsPrivate);
        }
        builder.append(getEmail())
                .append(" Address: ");
        if (getAddress().isPrivate()) {
            builder.append(detailIsPrivate);
        }
        if(login.getAccesslevelF() <= getAddress().getAccessLevel()) {
            builder.append(getAddress())
                    .append(" Title: ");
        }else{
            builder.append(" *** HIDDEN *** ").append(" Title: ");
        }
        builder.append(getTitle())
                .append(" Schedule: ");
        for(Schedule schedule : getSchedules()){
            builder.append(schedule);
        }
        builder.append(" Tags: ");
        for (Tag tag : getTags()) {
            builder.append(tag);
        }
        return builder.toString();
    }

    /**
     * Formats a person as text, showing only non-private contact details.
     */
    default String getAsTextHidePrivate() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        if(!getNric().isPrivate()) {
            builder.append(" NRIC: ").append(getNric());
        }
        if (!getPhone().isPrivate()) {
            builder.append(" Phone: ").append(getPhone());
        }
        if (!getEmail().isPrivate()) {
            builder.append(" Email: ").append(getEmail());
        }
        if (!getAddress().isPrivate()) {
            if(login.getAccesslevelF() <= getAddress().getAccessLevel()){
                builder.append(" Address: ").append(getAddress());
            }else{
                builder.append(" Address: ").append(" *** HIDDEN *** ");
            }
        }
        builder.append(" Title: ").append(getTitle());

        builder.append(" Schedule: ");
        for(Schedule schedule : getSchedules()){
            builder.append(schedule);
        }
        builder.append(" Tags: ");
        for (Tag tag : getTags()) {
            builder.append(tag);
        }
        return builder.toString();
    }

    /**
     * Formats a person as text, showing minimal details - name, NRIC and Schedule.
     */
    default String getAsTextShowMinimal() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        if(!getNric().isPrivate()) {
            builder.append("\t\tNRIC: ").append(getNric());
        }
        builder.append("\t\tTitle: ").append(getTitle());
        builder.append("\n\tSchedule: ");
        for(Schedule schedule : getSchedules()){
            builder.append(schedule);
        }
        return builder.toString();
    }
}


