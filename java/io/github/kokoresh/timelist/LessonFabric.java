package io.github.kokoresh.timelist;

public final class LessonFabric {
    private final static String[] times = {
            "8:30 - 10:00",
            "10:10 - 11:40",
            "11:50 - 13:20",
            "13:30 - 15:00",
            "15:10 - 16:40",
            "16:50 - 18:20",
            "18:30 - 20:00"
    };

    public static final Lesson create(
            final int numberOfLesson, final String hall, final String name, final String lecturer) {
        return new Lesson(times[numberOfLesson], hall, name, lecturer);
    }
}
