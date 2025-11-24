package com.realtimeeditor.crdt;

import static com.realtimeeditor.crdt.CrdtType.DELETE;
import static com.realtimeeditor.crdt.CrdtType.INSERT;

import com.realtimeeditor.model.DocumentFile;
import com.realtimeeditor.service.SessionManager;
import java.util.List;

public class CrdtEngine {

    private final String userId;
    private final DocumentFile documentFile;
    private long counter = 0;

    public CrdtEngine(DocumentFile documentFile) {
        this.documentFile = documentFile;
        this.userId = SessionManager.getInstance().getCurrentUser().getId();
    }

    public CrdtOperation localInsert(String previousId, char value) {
        String id = userId + "-" + (counter++);
        CrdtElement crdtElement = new CrdtElement(id, value, previousId);
        documentFile.insert(crdtElement);
        return new CrdtOperation(crdtElement);
    }

    public CrdtOperation localDelete(String id) {
        documentFile.delete(id);
        return new CrdtOperation(id);
    }

    public void applyRemote(CrdtOperation operation) {
        if (operation.crdtType == INSERT) {
            documentFile.insert(operation.crdtElement);
        } else if (operation.crdtType == DELETE) {
            documentFile.delete(operation.targetId);
        }
    }

    public List<CrdtElement> getVisibleElements() {
        return documentFile.getVisibleElements();
    }

    public String getDocumentFileText() {
        return documentFile.getText();
    }

    public int length() {
        return documentFile.length();
    }
}
