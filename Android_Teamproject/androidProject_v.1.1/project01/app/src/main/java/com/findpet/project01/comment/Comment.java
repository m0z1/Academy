package com.findpet.project01.comment;

import com.findpet.project01.Board.findBoard.FindBoard;
import com.findpet.project01.Board.missingBoard.MissingBoard;
import com.findpet.project01.Board.storyBoard.StoryBoard;
import com.findpet.project01.account.Member;

public class Comment {
    private Long commentId;
    private String content;

    private FindBoard findBoard;

    private Member member;

    private StoryBoard storyBoard;

    private MissingBoard missingBoard;

    public Comment() {
    }

    public Comment(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }

    public FindBoard getFindBoard() {
        return findBoard;
    }

    public void setFindBoard(FindBoard findBoard) {
        this.findBoard = findBoard;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public StoryBoard getStoryBoard() {
        return storyBoard;
    }

    public void setStoryBoard(StoryBoard storyBoard) {
        this.storyBoard = storyBoard;
    }

    public MissingBoard getMissingBoard() {
        return missingBoard;
    }

    public void setMissingBoard(MissingBoard missingBoard) {
        this.missingBoard = missingBoard;
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

}
