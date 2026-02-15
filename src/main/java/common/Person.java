package common;

import java.time.Instant;

public record Person(
    Integer id,
    String firstName,
    String secondName,
    String middleName,
    Instant createdAt
) {
}
