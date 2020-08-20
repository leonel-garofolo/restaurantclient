package com.restaurant.app.printer.pos;

import com.restaurant.app.printer.pos.model.Line;
import com.restaurant.app.utils.UtilString;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketDocument extends Document{

    public String build(){
        String text = "";
        if(header != null){
            text += header.getTitle() + NEW_LINE;
            text += header.getDireccion() + " - " + header.getLocalidad() + NEW_LINE;
            text += header.getTelefono() + NEW_LINE;
            text += NEW_LINE;

            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            text += df.format(new Date()) + NEW_LINE;
            text += "Mesa: " + header.getMesa() + NEW_LINE + NEW_LINE;
        }
        if(detail != null){
            for(Line l:detail.getLines()){
                text += UtilString.hardString(l.getProductName().trim(), 20)  + "     " + l.getProductPrice().trim() + NEW_LINE;
            }
            text += NEW_LINE + UtilString.hardString("Total:", 20) + "     " + detail.getTotal() + NEW_LINE;
        }
        if(footer != null){
            if(footer.getAgradecimiento() != null && !footer.getAgradecimiento().isEmpty()){
                text += NEW_LINE + footer.getAgradecimiento() + NEW_LINE;
            }
        }
        return NEW_LINE + NEW_LINE + text + NEW_LINE + NEW_LINE;
    }


}
