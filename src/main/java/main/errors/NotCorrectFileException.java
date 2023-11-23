package main.errors;

public class NotCorrectFileException extends Throwable {
    @Override
    public String toString() {
        return "Неподходящий формат файла.";
    }
}
