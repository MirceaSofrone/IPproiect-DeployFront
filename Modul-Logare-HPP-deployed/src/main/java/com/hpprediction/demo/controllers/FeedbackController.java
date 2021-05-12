package com.hpprediction.demo.controllers;
import com.hpprediction.demo.service.FeedbackService;
import com.hpprediction.demo.payload.request.Feedback;
import com.hpprediction.demo.payload.response.MessageResponse;
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
    public ResponseEntity<MessageResponse> sendFeedback(@RequestBody Feedback feedback){

        MessageResponse response;

        try{
            response = feedbackService.sendFeedback(feedback);
        }
        catch(IllegalStateException exception){
            return new ResponseEntity<>(new MessageResponse(exception.getMessage()), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
