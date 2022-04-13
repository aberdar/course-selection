package personal;

public class UserInputException extends Exception{

    private final int errorNumber;

    public UserInputException(String message, int errorNumber) {
        super(message);
        this.errorNumber = errorNumber;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

}
