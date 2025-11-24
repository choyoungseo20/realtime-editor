package com.realtimeeditor.crdt;

public class CrdtOperation {

    public CrdtType crdtType;
    public CrdtElement crdtElement;
    public String targetId;

    public CrdtOperation(CrdtElement crdtElement) {
        this.crdtType = CrdtType.INSERT;
        this.crdtElement = crdtElement;
    }

    public CrdtOperation(String targetId) {
        this.crdtType = CrdtType.DELETE;
        this.targetId = targetId;
    }
}
