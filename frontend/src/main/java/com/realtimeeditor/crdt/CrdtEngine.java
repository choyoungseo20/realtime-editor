package com.realtimeeditor.crdt;

import static com.realtimeeditor.crdt.CrdtType.DELETE;
import static com.realtimeeditor.crdt.CrdtType.INSERT;

import com.realtimeeditor.model.DocumentFile;
import com.realtimeeditor.service.SessionManager;

public class CrdtEngine {

    private final String userId;
    private final DocumentFile documentFile;

    private long counter = 0;

    public CrdtEngine(DocumentFile documentFile) {
        this.documentFile = documentFile;
        this.userId = SessionManager.getInstance().getCurrentUser().getId();
    }

    public CrdtOperation localInsert(int index, char value) {
        String prevId = (index == 0) ? null : documentFile.getElement(index - 1).getId();
        CrdtElement crdtElement = new CrdtElement(userId, counter++, value, prevId);
        insertElement(crdtElement);
        return new CrdtOperation(crdtElement);
    }

    public CrdtOperation localDelete(int index) {
        CrdtElement element = documentFile.getElement(index);
        removeElement(element.getId());
        return new CrdtOperation(element.getId());
    }

    public void applyRemote(CrdtOperation operation) {
        if (operation.crdtType == INSERT) {
            insertElement(operation.crdtElement);
        } else if (operation.crdtType == DELETE) {
            removeElement(operation.targetId);
        }
    }

    private void insertElement(CrdtElement element) {
        documentFile.insert(element);
    }

    private void removeElement(String id) {
        documentFile.delete(id);
    }

    public int length() {
        return documentFile.length();
    }

    public String getDocumentFileText() {
        return documentFile.getText();
    }
}
