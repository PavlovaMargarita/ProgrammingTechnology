package dao;

import model.Course;

import java.util.Map;

public interface StudentDao {

    public Map<Course, Integer> getMarks();
}
