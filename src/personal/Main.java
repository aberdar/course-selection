package personal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Course> courses = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("course.txt"));
            String r;

            while ((r = reader.readLine()) != null) {
                try {
                    String[] collectionData = new String[10];
                    collectionData[0] = r;
                    for (int i = 1; i < 10; i++) {
                        collectionData[i] = "";
                        collectionData[i] += reader.readLine();
                    }

                    Course course = new Course(Integer.parseInt(collectionData[0]));
                    course.setCourseName(collectionData[1]);
                    course.setCourseURL(collectionData[2]);
                    course.setCourseType(Boolean.parseBoolean(collectionData[3]));
                    course.setCoursePrice(Integer.parseInt(collectionData[4]));
                    course.setNumberOfListeners(Integer.parseInt(collectionData[5]));
                    course.setNumberOfReviews(Integer.parseInt(collectionData[6]));
                    course.setNumberOfLectures(Integer.parseInt(collectionData[7]));
                    course.setCourseComplexity(collectionData[8]);
                    course.setCourseDuration(Double.parseDouble(collectionData[9]));

                    courses.add(course);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        } catch (IOException ex) {
            System.out.println("Data error.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        for (Course e : courses) {
            if (e.getCoursePrice() == 0) {
                System.out.println(e.getCourseName());
            }
        }
    }
}
