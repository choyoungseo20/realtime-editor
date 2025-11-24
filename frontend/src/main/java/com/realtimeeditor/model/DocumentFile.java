package com.realtimeeditor.model;

import com.realtimeeditor.crdt.CrdtElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentFile {

    private final List<CrdtElement> elements = new ArrayList<>();
    private final Map<String, CrdtElement> elementById = new HashMap<>();

    public void insert(CrdtElement crdtElement) {
        if (elementById.containsKey(crdtElement.getId())) return;
        CrdtElement previousElement = elementById.get(crdtElement.getPreviousId());
        int index;
        if (previousElement == null) {
            index = 0;
        } else {
            index = elements.indexOf(previousElement) + 1;
        }
        elements.add(index, crdtElement);
        elementById.put(crdtElement.getId(), crdtElement);
    }

    public void delete(String targetId) {
        CrdtElement crdtElement = elementById.get(targetId);
        if (crdtElement != null) {
            crdtElement.makeDeleted();
        }
    }

    public CrdtElement getElement(int index) {
        return elements.get(index);
    }

    public int length() {
        return elements.size();
    }

    public String getText() {
        StringBuilder text = new StringBuilder();
        for (CrdtElement element : elements) {
            if (!element.isDeleted()) {
                text.append(element.getValue());
            }
        }
        return text.toString();
    }
}
