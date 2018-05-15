package io.github.kokoresh.timelist;

import java.util.List;

public class Day {
    private final String name;
    private final List<Lesson> lessons;

    public Day(final String name, final List<Lesson> lessons) {
        this.name = name;
        this.lessons = lessons;
    }

    public String getName() {
        return name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }
}
