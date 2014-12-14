package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends User {
    private Map<Course, Integer> generalCourses;
    private List<Course> additionalCourseList;

    public Student(){
        super();
        generalCourses = new HashMap<>();
        additionalCourseList = new ArrayList<>();
    }

    public Map<Course, Integer> getGeneralCourses() {
        return generalCourses;
    }

    public void setGeneralCourses(Map<Course, Integer> generalCourses) {
        this.generalCourses = generalCourses;
    }

    public List<Course> getAdditionalCourseList() {
        return additionalCourseList;
    }

    public void setAdditionalCourseList(List<Course> additionalCourseList) {
        this.additionalCourseList = additionalCourseList;
    }
}
