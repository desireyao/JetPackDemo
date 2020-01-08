package com.yaoh.jetpack.viewModel2;

/**
 * @author yaoh
 * @date Create in 2020-01-07 09:56
 * @description TODO
 */
public class MessageInfo {

    private int messageId;
    private String content;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "messageId=" + messageId +
                ", content='" + content + '\'' +
                '}';
    }
}
