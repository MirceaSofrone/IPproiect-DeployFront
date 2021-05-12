package com.hpprediction.demo.email;

public class EmailTemplate {
    public static final String TEMPLATECONFIRM = "confirmEmail.ftl";
    public static final String TEMPLATERESETPWR = "resetPasswordEmail.ftl";
    public static final String TEMPLATEFEEDBACK = "feedback.ftl";
    private EmailTemplate(){
        throw new IllegalStateException("Utility class");
    }
}
