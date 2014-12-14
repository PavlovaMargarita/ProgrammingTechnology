package model;

/**
 * Created by Margarita on 14.12.2014.
 */
public class Mark {
    private Course course;
    private int mark;

    public Mark(Course course, int mark) {
        this.course = course;
        this.mark = mark;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
