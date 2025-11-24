package com.realtimeeditor.model;

import java.util.ArrayList;
import java.util.List;

public class DocumentFile {

    private final List<Character> elements = new ArrayList<>();

    public void insert(int index, char c) {
        elements.add(index, c);
    }

    public void delete(int index) {
        elements.remove(index);
    }

    public int length() {
        return elements.size();
    }

    public String getText() {
        StringBuilder text = new StringBuilder();
        for (Character element : elements) {
            text.append(element);
        }
        return text.toString();
    }
}
