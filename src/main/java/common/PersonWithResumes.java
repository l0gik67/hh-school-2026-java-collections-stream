package common;

import java.util.Set;

public record PersonWithResumes(
    Person person,
    Set<Resume> resumes
) {
}
