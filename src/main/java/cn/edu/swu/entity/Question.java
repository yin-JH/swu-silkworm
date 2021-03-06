package cn.edu.swu.entity;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long id;
    private String type;
    private String question;
    private List<String> keywords = new ArrayList<>();
    private String answer;
    private String mediaType;

    /*这是数据库中保存的原始关键词数据，每一个关键词之间使用"|"分割*/
    private String originalKeywords;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void addKeyword(String key) {
        if (key == null || key.trim().isEmpty()) {
            return;
        }
        this.keywords.add(key.trim());
    }

    public String getKeywordString() {
        StringBuffer sb = new StringBuffer();
        for(String keyword : this.getKeywords()) {
            sb.append(keyword).append(",");
        }
        return sb.toString();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOriginalKeywords() {
        return originalKeywords;
    }

    public void setOriginalKeywords(String originalKeywords) {
        this.originalKeywords = originalKeywords;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", question='" + question + '\'' +
                ", keywords=" + keywords +
                ", answer='" + answer + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", originalKeywords='" + originalKeywords + '\'' +
                '}';
    }

    public String flat() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getType()).append("。");
        sb.append(this.getQuestion()).append("。");
        for(String keyword : this.getKeywords()) {
            sb.append(keyword).append("。");
        }
        sb.append(this.getAnswer()).append("。");
        return sb.toString();
    }
}
