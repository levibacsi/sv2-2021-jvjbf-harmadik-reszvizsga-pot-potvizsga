package emailservice;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class EmailService {
    private Set<User> users = new LinkedHashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void registerUser(String emailAddress) {
        if (!isValidEmail(emailAddress)) {
            throw new IllegalArgumentException("Email address is not valid: " + emailAddress);
        }

        for (User u: users){
            if (u.getEmailAddress().equals(emailAddress)){
                throw new IllegalArgumentException("Email address is already taken!");
            }
        }
        users.add(new User(emailAddress));
    }

    private boolean isValidEmail(String emailAddress){
        return emailAddress.indexOf('@') >= 1 &&
                (emailAddress.indexOf('.') > 1 + emailAddress.indexOf('@')) &&
                emailAddress.toLowerCase().equals(emailAddress);
    }

    public void sendEmail(String from, String to, String subject, String content){
        User sender = users.stream().filter(user -> user.getEmailAddress().equals(from)).findFirst().orElseThrow(()-> new IllegalStateException("Sender not found"));
        User receiver = users.stream().filter(user -> user.getEmailAddress().equals(to)).findFirst().orElseThrow(()-> new IllegalStateException("Recipient not found"));
        sender.sendEmail(subject, content, receiver);
    }

    public void sendSpam(String subject, String content){
        for (User u: users){
            u.getEmail(new Spam(u, subject, content));
        }
    }
}
