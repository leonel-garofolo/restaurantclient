package com.restaurant.app.printer.pos;

import com.restaurant.app.printer.pos.model.Line;

public class CocinaDocument extends Document{

    public String build(){
        String text = "";
        if(header != null){
            text += header.getTitle() + NEW_LINE;
            text += "Mesa: " + header.getMesa() + NEW_LINE + NEW_LINE;
        }
        if(detail != null){
            for(Line l:detail.getLines()){
                text += l.getProductName().trim() + NEW_LINE;
            }
        }
        if(footer != null){
            if(footer.getNote() != null && !footer.getNote().isEmpty()){
                text += NEW_LINE + "Nota:" + NEW_LINE;
                text += footer.getNote() + NEW_LINE;
            }
        }
        return NEW_LINE + NEW_LINE + text + NEW_LINE + NEW_LINE;
    }
}
