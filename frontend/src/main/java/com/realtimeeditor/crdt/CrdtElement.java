package com.realtimeeditor.crdt;

public class CrdtElement {

    private final String userId;
    private final long counter;
    private final char value;
    private final String previousId;
    private boolean deleted = false;

    public CrdtElement(String userId, long counter, char value, String previousId) {
        this.userId = userId;
        this.counter = counter;
        this.value = value;
        this.previousId = previousId;
    }

    public String getId() {
        return userId + "-" + counter;
    }

    public char getValue() {
        return value;
    }

    public String getPreviousId() {
        return previousId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void makeDeleted() {
        this.deleted = true;
    }
}
