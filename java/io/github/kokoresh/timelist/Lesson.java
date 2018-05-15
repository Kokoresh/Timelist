package io.github.kokoresh.timelist;

public class Lesson {
    private final String time;
    private final String name;
    private final String hall;
    private final String lecturer;

    public boolean isEmpty() {
        return name == null && hall == null && lecturer == null;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getHall() {
        return hall;
    }

    public String getLecturer() {
        return lecturer;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Время: ").append(time)
                .append("\nАудитория: ").append(hall == null? "Не указана": hall)
                .append("\nПредмет: ").append(name == null ? "Паустая пара": name)
                .append("\nПреподаватель: ").append(lecturer == null? "Нет преподавателя": lecturer)
                .toString();
    }

    public Lesson(final String time, final String hall, final String name, final String lecturer) {
        this.time = time;
        this.name = name;
        this.hall = hall;
        this.lecturer = lecturer;
    }
}
