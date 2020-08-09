package com.restaurant.app.printer.pos;

import com.restaurant.app.printer.pos.model.Line;

public class TicketDocument extends Document{

    public String build(){
        String text = "";
        if(header != null){
            text += header.getTitle();
            text += header.getMesa();
        }
        if(detail != null){
            // TODO acotar nombre y precio para que siempre esten alineados
            for(Line l:detail.getLines()){
                text += l.getProductName() + "  " + l.getProductPrice();
            }
        }
        if(footer != null){
            text += footer.getNote();
        }
        return text;
    }
}
