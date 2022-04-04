package personal;

import java.net.MalformedURLException;
import java.net.URL;

public class Course {

    public int courseId;
    public String courseName;
    public String courseURL;
    public boolean courseType;
    public int coursePrice;
    public int numberOfListeners;
    public int numberOfReviews;
    public int numberOfLectures;
    public String courseComplexity;
    public double courseDuration;

    public Course(int id) {
        this.courseId = id;
    }

    public Course(int id, String name, String url, boolean type, int price,
                  int listeners, int reviews, int lectures, String complexity, double duration) {

        this.courseId = id;
        this.courseName = name;
        this.courseURL = url;
        this.courseType = type;
        this.coursePrice = price;
        this.numberOfListeners = listeners;
        this.numberOfReviews = reviews;
        this.numberOfLectures = lectures;
        this.courseComplexity = complexity;
        this.courseDuration = duration;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setCourseURL(String courseURL) {
        this.courseURL = courseURL;
    }
    public void setCourseType(boolean courseType) {
        this.courseType = courseType;
    }
    public void setCoursePrice(int coursePrice) {
        this.coursePrice = coursePrice;
    }
    public void setNumberOfListeners(int numberOfListeners) {
        this.numberOfListeners = numberOfListeners;
    }
    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }
    public void setNumberOfLectures(int numberOfLectures) {
        this.numberOfLectures = numberOfLectures;
    }
    public void setCourseComplexity(String courseComplexity) {
        this.courseComplexity = courseComplexity;
    }
    public void setCourseDuration(double courseDuration) {
        this.courseDuration = courseDuration;
    }

    public int getCourseId() {
        return courseId;
    }
    public String getCourseName() {
        return courseName;
    }
    public String getCourseURL() {
        return courseURL;
    }
    public double getCourseDuration() {
        return courseDuration;
    }
    public int getCoursePrice() {
        return coursePrice;
    }
    public int getNumberOfLectures() {
        return numberOfLectures;
    }
    public int getNumberOfListeners() {
        return numberOfListeners;
    }
    public int getNumberOfReviews() {
        return numberOfReviews;
    }
    public String getCourseComplexity() {
        return courseComplexity;
    }

}
