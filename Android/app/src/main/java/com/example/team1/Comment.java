package com.example.team1;

public class Comment {
    private Long commentId;
    private String content;

    private Member member;

    public Comment(String content, Member member, FindBoard findboard, StoryBoard storyboard, MissyouBoard missingboard) {
        this.content = content;
        this.member = member;
        this.findboard = findboard;
        this.storyboard = storyboard;
        this.missingboard = missingboard;
    }

    private FindBoard findboard;

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public FindBoard getFindboard() {
        return findboard;
    }

    public void setFindboard(FindBoard findboard) {
        this.findboard = findboard;
    }

    public StoryBoard getStoryboard() {
        return storyboard;
    }

    public void setStoryboard(StoryBoard storyboard) {
        this.storyboard = storyboard;
    }

    public MissyouBoard getMissingboard() {
        return missingboard;
    }

    public void setMissingboard(MissyouBoard missingboard) {
        this.missingboard = missingboard;
    }

    private StoryBoard storyboard;

    private MissyouBoard missingboard;
}
