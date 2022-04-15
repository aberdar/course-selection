package personal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Main {

    public static boolean inputView;
    public static String inputLevel;
    public static double maxDuration;
    public static int inputAdditionalParameter;
    public static int minPrice = 0;
    public static int maxPrice = 0;
    public static Scanner scan = new Scanner(System.in);

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
                } catch ( DataFormatException | URISyntaxException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        } catch (IOException ex) {
            System.out.println("Data error.");
        }

        viewCourseUserInput();
        levelCourseUserInput();
        durationCourseUserInput();

        ArrayList<Course> outputCourses = new ArrayList<>();
        for (Course element : courses) {
            if (trueCourses(element)) {
                outputCourses.add(element);
            }
        }

        if (outputCourses.size() == 0) {
            viewCoursesReset();
            System.out.println("\nПодходящие " + viewCoursePrint() + " курсы не были найдены.\nПоиск по всем видам куров.\n");
            for (Course element : courses) {
                if (trueCourses(element)) {
                    outputCourses.add(element);
                }
            }
        }

        if (outputCourses.size() > 10) {
            additionalParameter();
            System.out.println("\nПодходящие курсы:\n");
            if (inputAdditionalParameter == 1) {
                outputCourses.stream().sorted(new CourseListenersComparator())
                        .limit(10)
                        .forEach(p -> printCourse(p));
            } else {
                outputCourses.stream().sorted(new CourseLecturesComparator())
                        .limit(10)
                        .forEach(p -> printCourse(p));
            }
        } else {
            System.out.println("\nПодходящие курсы:\n");
            for (Course course : outputCourses)  {
                printCourse(course);
            }
        }
    }

    public static void viewCourseUserInput() throws UserInputException {

        System.out.println("Вид курса: \n1.Платный \n2.Бесплатный" );
        int view;
        try {
            view = scan.nextInt();
        } catch (InputMismatchException exception) {
            throw new InputMismatchException("Введён неверный символ.\nОжидалось 1 или 2.");
        }
        inputView = view == 1;

        if (inputView) {
            priceCourseUserInput();
        }
    }

    public static void priceCourseUserInput() throws UserInputException{

        System.out.println("Укажите диапазон стоимости: ");
        System.out.print("От: ");
        try {
            minPrice = scan.nextInt();
        } catch (InputMismatchException exception) {
            throw new InputMismatchException("Введён неверный символ.\nОжидалось положительное число.");
        }

        System.out.print("До: ");
        try {
            maxPrice = scan.nextInt();
        } catch (InputMismatchException exception) {
            throw new InputMismatchException("Введён неверный символ.\nОжидалось положительное число.");
        }

        if (minPrice < 0) {
            throw new UserInputException("Минимальная стоимость должна быть больше либо равной нулю.", minPrice);
        }
        if (minPrice > maxPrice) {
            throw new UserInputException("Минимальная стоимость не может быть больше максимальной.", maxPrice);
        }
        if (maxPrice > Math.pow(10, 9)) {
            throw new UserInputException("Введено некорректное значение.", maxPrice);
        }
    }

    public static void levelCourseUserInput() throws UserInputException {

        System.out.println("Сложность курса: " +
                "\n1. Beginner Level" +
                "\n2. Intermediate Level" +
                "\n3. Expert Level" +
                "\n4. All Levels");
        int levelInput;
        try {
            levelInput = scan.nextInt();
        } catch (InputMismatchException exception) {
            throw new InputMismatchException("Введён неверный символ.\nОжидалось число.");
        }
        if (levelInput < 1 || levelInput > 4) {
            throw new UserInputException("Возможные значения от 1 до 4.", levelInput);
        }

        if (levelInput == 1) inputLevel = "Beginner Level";
        if (levelInput == 2) inputLevel = "Intermediate Level";
        if (levelInput == 3) inputLevel = "Expert Level";
        if (levelInput == 4) inputLevel = "All Levels";
    }

    public static void durationCourseUserInput() throws UserInputException{

        System.out.print("Максимальная продолжительность курса: ");
        try {
            maxDuration = scan.nextDouble();
        } catch (InputMismatchException exception) {
            throw new InputMismatchException("Ожидалось положительное число.");
        }
        if (maxDuration <= 0) {
            throw new UserInputException("Значение должно быть больше нуля.", (int) maxDuration);
        }
    }

    public static void additionalParameter() throws UserInputException {

        System.out.println("Выберите дополнительный параметр:\n1. Большее количество слушателей" +
                "\n2. Меньше количество лекций");
        try {
            inputAdditionalParameter = scan.nextInt();
        } catch (InputMismatchException exception) {
            throw new InputMismatchException("Ожидалось положительное число.");
        }
        if (inputAdditionalParameter < 1 || inputAdditionalParameter > 2) {
            throw new UserInputException("Введено некорректное значение.", inputAdditionalParameter);
        }
    }

    public static boolean trueCourses(Course element) {

        return element.getCoursePrice() >= minPrice
                && element.getCoursePrice() <= maxPrice
                && inputLevel.equals(element.getCourseComplexity())
                && element.getCourseDuration() <= maxDuration;
    }

    public static void printCourse(Course element) {

        System.out.printf("ID: %d\nНазвание: %s\nСсылка: %s\nСлушателей: %d\nКоличество лекций: %d\nОтзывов: %d" +
                        "\nЦена: %d\nПродолжительность: %.1f\n\n",
                element.getCourseId(),
                element.getCourseName(),
                element.getCourseURL(),
                element.getNumberOfListeners(),
                element.getNumberOfLectures(),
                element.getNumberOfReviews(),
                element.getCoursePrice(),
                element.getCourseDuration());
    }

    public static void viewCoursesReset() {

        if (inputView) {
            minPrice = 0;
        }
        maxPrice = (int)Math.pow(10, 9);
    }

    public static String viewCoursePrint() {

        if (inputView) {
            return "платные";
        }
        return "бесплатные";
    }
}
