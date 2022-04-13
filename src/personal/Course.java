package personal;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.zip.DataFormatException;

public class Course implements Comparable<Course> {

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

    private final Set<String> complexityName = new HashSet<>(List.of(new String[]{
            "Beginner Level",
            "Intermediate Level",
            "Expert Level",
            "All Levels"
    }));

    public Course(int id) {
        this.courseId = id;
    }

    public Course(int id,
                  String name,
                  String url,
                  boolean type,
                  int price,
                  int listeners,
                  int reviews,
                  int lectures,
                  String complexity,
                  double duration ) throws DataFormatException, URISyntaxException {

        this.courseId = id;
        if (courseId < 0) {
            courseId = Math.abs(id);
            throw new DataFormatException("ID не может быть отрицательным числом.\nНовое значение - " + courseId + "\n");
        }

        this.courseName = name;
        char upperCaseChar;
        if (courseName.charAt(0) != (upperCaseChar = Character.toUpperCase(courseName.charAt(0)))) {
            this.courseName = upperCaseChar + name.substring(1, name.length());
            throw new DataFormatException("Название курса " + courseId + " должно начинаться с большой буквы." +
                    "\nСимвол заменён на верхний регистр.\n");
        }

        this.courseURL = url;
        if (!isValidUrl(courseURL)) {
            throw new URISyntaxException("Неправильная ссылка.", "");
        }

        this.courseType = type;

        this.coursePrice = price;
        if (coursePrice < 0) {
            this.coursePrice = Math.abs(price);
            throw new DataFormatException("Цена не может быть отрицательным числом.\nНовое значение - " + coursePrice + "\n");
        }

        this.numberOfListeners = listeners;
        if (numberOfListeners < 0) {
            this.numberOfListeners = Math.abs(listeners);
            throw new DataFormatException("Количество слушателей не может быть отрицательным числом." +
                    "\nНовое значение - " + numberOfListeners + "\n");
        }

        this.numberOfReviews = reviews;
        if (numberOfReviews < 0) {
            this.numberOfReviews = Math.abs(reviews);
            throw new DataFormatException("Количество отзывов не может быть отрицательным числом." +
                    "\nНовое значение - " + numberOfReviews + "\n");
        }

        this.numberOfLectures = lectures;
        if (numberOfLectures < 0) {
            this.numberOfLectures = Math.abs(lectures);
            throw new DataFormatException("Количество лекций не может быть отрицательным числом." +
                    "\nНовое значение - " + numberOfLectures + "\n");
        }

        this.courseComplexity = complexity;
        if (!complexityName.contains(complexity)) {
            throw new DataFormatException("Неправильный уровень курса " + courseId + ".\n");
        }

        this.courseDuration = duration;
        if (courseDuration < 0) {
            this.courseDuration = Math.abs(duration);
            throw new DataFormatException("Продолжительность курса не может быть отрицательным числом." +
                    "\nНовое значение - " + this.courseDuration + "\n");
        }
    }

    public void setCourseId(int courseId) throws DataFormatException {
        this.courseId = courseId;
        if (courseId < 0) {
            this.courseId = Math.abs(courseId);
            throw new DataFormatException("ID не может быть отрицательным числом.\nНовое значение - " + this.courseId + "\n");
        }
    }
    public void setCourseName(String courseName) throws DataFormatException {
        this.courseName = courseName;
        char upperCaseChar;
        if (courseName.charAt(0) != (upperCaseChar = Character.toUpperCase(courseName.charAt(0)))) {
            this.courseName = upperCaseChar + courseName.substring(1, courseName.length());
            throw new DataFormatException("Название курса " + courseId + " должно начинаться с большой буквы." +
                    "\nСимвол заменён на верхний регистр.\n");
        }
    }
    public void setCourseURL(String courseURL) throws URISyntaxException{
        this.courseURL = courseURL;
        if (!isValidUrl(courseURL)) {
            throw new URISyntaxException("Неправильная ссылка.", "");
        }
    }
    public void setCourseType(boolean courseType) {
        this.courseType = courseType;
    }
    public void setCoursePrice(int coursePrice) throws DataFormatException {
        this.coursePrice = coursePrice;
        if (coursePrice < 0) {
            this.coursePrice = Math.abs(coursePrice);
            throw new DataFormatException("Цена не может быть отрицательным числом.\nНовое значение - " + this.coursePrice + "\n");
        }
    }
    public void setNumberOfListeners(int numberOfListeners) throws DataFormatException {
        this.numberOfListeners = numberOfListeners;
        if (numberOfListeners < 0) {
            this.numberOfListeners = Math.abs(numberOfListeners);
            throw new DataFormatException("Количество слушателей не может быть отрицательным числом." +
                    "\nНовое значение - " + this.numberOfListeners + "\n");
        }
    }
    public void setNumberOfReviews(int numberOfReviews) throws DataFormatException {
        this.numberOfReviews = numberOfReviews;
        if (numberOfReviews < 0) {
            this.numberOfReviews = Math.abs(numberOfReviews);
            throw new DataFormatException("Количество отзывов не может быть отрицательным числом." +
                    "\nНовое значение - " + this.numberOfReviews + "\n");
        }
    }
    public void setNumberOfLectures(int numberOfLectures) throws DataFormatException {
        this.numberOfLectures = numberOfLectures;
        if (numberOfLectures < 0) {
            this.numberOfLectures = Math.abs(numberOfLectures);
            throw new DataFormatException("Количество лекций не может быть отрицательным числом." +
                    "\nНовое значение - " + this.numberOfLectures + "\n");
        }
    }
    public void setCourseComplexity(String courseComplexity) throws DataFormatException {
        this.courseComplexity = courseComplexity;
        if (!complexityName.contains(courseComplexity)) {
            throw new DataFormatException("Неправильный уровень курса " + courseId + ".\n");
        }
    }
    public void setCourseDuration(double courseDuration) throws DataFormatException {
        this.courseDuration = courseDuration;
        if (courseDuration < 0) {
            this.courseDuration = Math.abs(courseDuration);
            throw new DataFormatException("Продолжительность курса не может быть отрицательным числом." +
                    "\nНовое значение - " + this.courseDuration + "\n");
        }
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

    public static boolean isValidUrl(String urlInput) {
        try {
            URL url = new URL(urlInput);
            url.toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int compareTo(Course o) {
        return courseName.compareTo(o.courseName);
    }
}

class CourseListenersComparator implements Comparator<Course> {

    @Override
    public int compare(Course o1, Course o2) {
        return Integer.compare(o2.getNumberOfListeners(), o1.getNumberOfListeners());
    }
}

class CourseLecturesComparator implements Comparator<Course> {

    @Override
    public int compare(Course o1, Course o2) {
        return Integer.compare(o1.getNumberOfLectures(), o2.getNumberOfLectures());
    }
}
