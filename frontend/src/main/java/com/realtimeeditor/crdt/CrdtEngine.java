package com.realtimeeditor.crdt;

import static com.realtimeeditor.crdt.CrdtType.DELETE;
import static com.realtimeeditor.crdt.CrdtType.INSERT;

import com.realtimeeditor.model.DocumentFile;

public class CrdtEngine {

    private final DocumentFile documentFile;

    public CrdtEngine(DocumentFile documentFile) {
        this.documentFile = documentFile;
    }

    public CrdtOperation localInsert(int index, char c) {
        documentFile.insert(index, c);
        return new CrdtOperation(INSERT, index, c);
    }

    public CrdtOperation localDelete(int index) {
        documentFile.delete(index);
        return new CrdtOperation(DELETE, index);
    }

    public void applyRemote(CrdtOperation operation) {
        if (operation.crdtType == INSERT) {
            documentFile.insert(operation.index, operation.value);
        } else if (operation.crdtType == DELETE) {
            documentFile.delete(operation.index);
        }
    }

    public int length() {
        return documentFile.length();
    }

    public String getDocumentFileText() {
        return documentFile.getText();
    }
}
