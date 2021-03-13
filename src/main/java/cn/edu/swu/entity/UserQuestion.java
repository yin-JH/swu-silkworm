package cn.edu.swu.entity;

import java.util.Date;

public class UserQuestion {
    private Long id;
    private String userProblem;
    private Long systemAnswer;
    private String askDate;
    private Integer flag;

    public UserQuestion() {
    }

    public UserQuestion(Long id, String userProblem, Long systemAnswer, String askDate, Integer flag) {
        this.id = id;
        this.userProblem = userProblem;
        this.systemAnswer = systemAnswer;
        this.askDate = askDate;
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserProblem() {
        return userProblem;
    }

    public void setUserProblem(String userProblem) {
        this.userProblem = userProblem;
    }

    public Long getSystemAnswer() {
        return systemAnswer;
    }

    public void setSystemAnswer(Long systemAnswer) {
        this.systemAnswer = systemAnswer;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getAskDate() {
        return askDate;
    }

    public void setAskDate(String askDate) {
        this.askDate = askDate;
    }

    @Override
    public String toString() {
        return "UserQuestion{" +
                "id=" + id +
                ", userProblem='" + userProblem + '\'' +
                ", systemAnswer=" + systemAnswer +
                ", askDate=" + askDate +
                ", flag=" + flag +
                '}';
    }
}
