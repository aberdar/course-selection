package personal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static boolean inputView;
    public static String inputLevel;
    public static double maxDuration;
    public static int inputAdditionalParameter;
    public static int minPrice = 0;
    public static int maxPrice = 0;

    public static void main(String[] args) throws Exception {

        ArrayList<Course> courses = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("data_course.txt"));
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

        userRequest();

        ArrayList<Course> outputCourses = new ArrayList<>();
        for (Course element : courses) {
            if (trueCourses(element)) {
                outputCourses.add(element);
            }
        }

        if (outputCourses.size() > 10) {
            additionalParameter();
            System.out.println("\nПодходящие курсы:\n");
            if (inputAdditionalParameter == 1) {
                courses.stream().sorted(new CourseListenersComparator())
                        .limit(10)
                        .forEach(p -> System.out.printf("ID: %d\nНазвание: %s\nСсылка: %s\nСлушателей: %d\nКоличество лекций: %d\nОтзывов: %d" +
                                   "\nЦена: %d\nПродолжительность: %.1f\n\n",
                            p.getCourseId(), p.getCourseName(), p.getCourseURL(), p.getNumberOfListeners(), p.getNumberOfLectures(),
                           p.getNumberOfReviews(), p.getCoursePrice(), p.getCourseDuration()));
            } else {
                courses.stream().sorted(new CourseLecturesComparator())
                        .limit(10)
                        .forEach(p -> System.out.printf("ID: %d\nНазвание: %s\nСсылка: %s\nСлушателей: %d\nКоличество лекций: %d\nОтзывов: %d" +
                                        "\nЦена: %d\nПродолжительность: %.1f\n\n",
                            p.getCourseId(), p.getCourseName(), p.getCourseURL(),p.getNumberOfListeners(), p.getNumberOfLectures(),
                            p.getNumberOfReviews(), p.getCoursePrice(), p.getCourseDuration()));
            }
        } else {
            System.out.println("\nПодходящие курсы:\n");
            for (Course course : outputCourses)  {
                System.out.printf("ID: %d\nНазвание: %s\nСсылка: %s\nСлушателей: %d\nКоличество лекций: %d\nОтзывов: %d" +
                                "\nЦена: %d\nПродолжительность: %.1f\n\n",
                        course.getCourseId(), course.getCourseName(), course.getCourseURL(),course.getNumberOfListeners(), course.getNumberOfLectures(),
                        course.getNumberOfReviews(), course.getCoursePrice(), course.getCourseDuration());
            }
        }
    }

    public static void userRequest() throws Exception {
        Scanner scan = new Scanner(System.in);

        System.out.println("Вид курса: \n1.Платный \n2.Бесплатный" );
        int view = scan.nextInt();
        inputView = view == 1;

        if (inputView) {
            System.out.println("Укажите диапазон стоимости: ");
            System.out.print("От: ");
            minPrice = scan.nextInt();
            if (minPrice < 0) {
                throw new Exception("Минимальная стоимость должна быть больше либо равной нулю.");
            }
            System.out.print("До: ");
            maxPrice = scan.nextInt();
            if (minPrice > maxPrice) {
                throw new Exception("Минимальная стоимость не может быть больше максимальной.");
            }
            if (maxPrice > Math.pow(10, 9)) {
                throw new Exception("Введено некорректное значение.");
            }
        }

        System.out.println("Сложность курса: \n1. Beginner Level" +
                                            "\n2. Intermediate Level" +
                                            "\n3. Expert Level" +
                                            "\n4. All Levels");
        int levelInput = scan.nextInt();
        if (levelInput < 1 || levelInput > 4) {
            throw new Exception("Возможные значения от 1 до 4.");
        }
        if (levelInput == 1) inputLevel = "Beginner Level";
        if (levelInput == 2) inputLevel = "Intermediate Level";
        if (levelInput == 3) inputLevel = "Expert Level";
        if (levelInput == 4) inputLevel = "All Levels";

        System.out.print("Максимальная продолжительность курса: ");
        maxDuration = scan.nextDouble();
        if (maxDuration <= 0) {
            throw new Exception("Значение должно быть больше нуля.");
        }
    }

    public static void additionalParameter() throws Exception {
        Scanner scan = new Scanner(System.in);

        System.out.println("Выберите дополнительный параметр:\n1. Большее количество слушателей" +
                "\n2. Меньше количество лекций");
        inputAdditionalParameter = scan.nextInt();
        if (inputAdditionalParameter < 1 || inputAdditionalParameter > 2) {
            throw new Exception("Введено некорректное значение.");
        }
    }

    public static boolean trueCourses(Course element) {
        return element.getCoursePrice() >= minPrice
                && element.getCoursePrice() <= maxPrice
                && inputLevel.equals(element.getCourseComplexity())
                && element.getCourseDuration() <= maxDuration;
    }
}
