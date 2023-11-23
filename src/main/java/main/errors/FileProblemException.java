package main.errors;

public class FileProblemException extends Throwable {
    @Override
    public String toString() {
        return "Файл не найден.";
    }
}
