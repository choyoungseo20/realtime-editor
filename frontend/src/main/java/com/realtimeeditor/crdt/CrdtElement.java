package com.realtimeeditor.crdt;

public class CrdtElement {

    private final String id;
    private final char value;
    private final String previousId;
    private boolean deleted = false;

    public CrdtElement(String id, char value, String previousId) {
        this.id = id;
        this.value = value;
        this.previousId = previousId;
    }

    public String getId() {
        return id;
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
