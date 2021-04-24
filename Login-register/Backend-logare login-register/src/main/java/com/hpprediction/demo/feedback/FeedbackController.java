package com.hpprediction.demo.feedback;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor

public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping(path = "feedback")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> sendFeedback(@RequestBody Feedback feedback){

        String response = "";

        try{
            response = feedbackService.sendFeedback(feedback);
        }
        catch(IllegalStateException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
