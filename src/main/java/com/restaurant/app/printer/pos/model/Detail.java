package com.restaurant.app.printer.pos.model;

import java.util.List;

public class Detail {

    private List<Line> lines;

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }
}