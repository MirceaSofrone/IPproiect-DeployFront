package com.hpprediction.demo.service;
import com.hpprediction.demo.payload.request.Feedback;
import com.hpprediction.demo.payload.response.MessageResponse;
import com.hpprediction.demo.security.encoder.Encoder;
import com.hpprediction.demo.entity.User;
import com.hpprediction.demo.email.EmailTemplate;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FeedbackService {
    private final EmailService emailService;
    private final UserService userService;
    public MessageResponse sendFeedback(Feedback feedback){

        User userRequesting;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;


        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        if(!username.equals(Encoder.encrypt(feedback.getEmailFrom()))){
            throw new IllegalStateException("You're not logged in as the sending user!");
        }


        Optional<User> user=userService.loadAllUserData(Encoder.encrypt(feedback.getEmailTo()));
        if(user.isPresent()){
            userRequesting = user.get();
        } else{
            throw new BadCredentialsException("User was not found");
        }


        Map<String, Object> model = new HashMap<>();
        model.put("name", Encoder.decrypt(userRequesting.getName()));
        model.put("emailFrom", feedback.getEmailFrom());
        model.put("message", feedback.getMessage());

        emailService.send(feedback.getEmailTo(),
                "New Message",
                EmailTemplate.TEMPLATEFEEDBACK,
                model);
        return new MessageResponse("Message sent successfully!");
    }
}
