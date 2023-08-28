package com.findpet.project01.comment;

public class Comment {
    private Long commentId;
    private String content;

    private Long find_id;

    private Long member_id;

    private Long story_id;

    private Long missing_id;

    public Comment() {
    }

    public Comment(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getFind_id() {
        return find_id;
    }

    public void setFind_id(Long find_id) {
        this.find_id = find_id;
    }

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public Long getStory_id() {
        return story_id;
    }

    public void setStory_id(Long story_id) {
        this.story_id = story_id;
    }

    public Long getMissing_id() {
        return missing_id;
    }

    public void setMissing_id(Long missing_id) {
        this.missing_id = missing_id;
    }
}
