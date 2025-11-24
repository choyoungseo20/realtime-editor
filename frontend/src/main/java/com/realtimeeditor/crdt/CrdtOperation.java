package com.realtimeeditor.crdt;

public class CrdtOperation {

    public CrdtType crdtType;
    public int index;
    public char value;

    public CrdtOperation(CrdtType crdtType, int index, char value) {
        this.crdtType = crdtType;
        this.index = index;
        this.value = value;
    }

    public CrdtOperation(CrdtType crdtType, int index) {
        this.crdtType = crdtType;
        this.index = index;
    }
}
