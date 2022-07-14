package kz.springboot.springbootFitness.email;

public interface EmailSender {
    void send(String to, String email);
}
