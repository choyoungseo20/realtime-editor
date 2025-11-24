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
        if (isDuplicate(crdtElement)) return;

        CrdtElement previous = findPreviousElement(crdtElement);
        int index = calculateInsertIndex(crdtElement, previous);
        doInsert(crdtElement, index);
    }

    public void delete(String targetId) {
        CrdtElement crdtElement = elementById.get(targetId);
        if (crdtElement != null) {
            crdtElement.makeDeleted();
        }
    }

    public List<CrdtElement> getVisibleElements() {
        return elements.stream()
                .filter(element -> !element.isDeleted())
                .toList();
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

    private boolean isDuplicate(CrdtElement element) {
        return elementById.containsKey(element.getId());
    }

    private CrdtElement findPreviousElement(CrdtElement element) {
        if (element.getPreviousId() == null) return null;
        return elementById.get(element.getPreviousId());
    }

    private int calculateInsertIndex(CrdtElement element, CrdtElement previousElement) {
        int index = (previousElement == null) ? 0 : elements.indexOf(previousElement) + 1;

        while (index < elements.size() &&
                elements.get(index).getPreviousId() != null &&
                elements.get(index).getPreviousId().equals(element.getPreviousId()) &&
                elements.get(index).getId().compareTo(element.getId()) < 0) {
            index++;
        }

        return index;
    }

    private void doInsert(CrdtElement element, int index) {
        elements.add(index, element);
        elementById.put(element.getId(), element);
    }
}
