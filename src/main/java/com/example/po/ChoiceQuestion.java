package com.example.po;
//选择题
public class ChoiceQuestion extends Question {
//    private String optionA;
//    private String optionB;
//    private String optionC;
//    private String optionD;
//    private ChoiceOption answer;
//
//    // 构造函数、Getter和Setter方法
//
//    public String getOptionA() {
//        return optionA;
//    }
//
//    public void setOptionA(String optionA) {
//        this.optionA = optionA;
//    }
//
//    public String getOptionB() {
//        return optionB;
//    }
//
//    public void setOptionB(String optionB) {
//        this.optionB = optionB;
//    }
//
//    public String getOptionC() {
//        return optionC;
//    }
//
//    public void setOptionC(String optionC) {
//        this.optionC = optionC;
//    }
//
//    public String getOptionD() {
//        return optionD;
//    }
//
//    public void setOptionD(String optionD) {
//        this.optionD = optionD;
//    }
//
//    public ChoiceOption getAnswer() {
//        return answer;
//    }
//
//    public void setAnswer(ChoiceOption answer) {
//        this.answer = answer;
//    }
//
//    public enum ChoiceOption {
//        A, B, C, D
//    }
    private String option;
    private int questionId;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}

