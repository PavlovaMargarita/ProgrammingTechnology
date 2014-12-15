package model;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private List<Mark> generalCourses;
    private List<Course> additionalCourseList;

    public Student(){
        super();
        generalCourses = new ArrayList();
        additionalCourseList = new ArrayList<>();
    }

    public List<Mark> getGeneralCourses() {
        return generalCourses;
    }

    public void setGeneralCourses(List<Mark> generalCourses) {
        this.generalCourses = generalCourses;
    }

    public List<Course> getAdditionalCourseList() {
        return additionalCourseList;
    }

    public void setAdditionalCourseList(List<Course> additionalCourseList) {
        this.additionalCourseList = additionalCourseList;
    }

}
