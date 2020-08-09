package com.restaurant.app.printer.pos;

import com.restaurant.app.printer.pos.model.Line;

public class CocinaDocument extends Document{

    public String build(){
        String text = "";
        if(header != null){
            text += header.getTitle();
            text += header.getMesa();
        }
        if(detail != null){
            for(Line l:detail.getLines()){
                text += l.getProductName();
            }
        }
        if(footer != null){
            text += footer.getNote();
        }
        return text;
    }
}
