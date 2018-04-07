package nice.models.task;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Status {
    NOT_STARTED ("Not_Started"),
    IN_PROGRESS ("In_Progress"),
    COMPLETE ("Complete");

    private final static String valuesText =
            String.join(", ",
                    Arrays.stream(Status.values()).map(status -> status.text)
                            .collect(Collectors.toList()));

    private final String text;
    Status(final String text) {
        this.text = text;
    }

    @JsonCreator
    public static Status create (String value) {
        if(value == null) {
            throw new IllegalArgumentException("Status cannot be null.");
        }

        for(Status s : Status.values()){
            if(value.equalsIgnoreCase(s.text)){
                return s;
            }
        }

        throw new IllegalArgumentException("Status must be one of: " + Status.valuesText);
    }

    @Override
    public String toString() {
        return text;
    }
}
