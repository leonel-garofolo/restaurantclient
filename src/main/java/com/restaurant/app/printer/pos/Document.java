package com.restaurant.app.printer.pos;

import com.restaurant.app.printer.pos.model.Detail;
import com.restaurant.app.printer.pos.model.Footer;
import com.restaurant.app.printer.pos.model.Header;

public abstract class Document {

    protected Header header;
    protected Detail detail;
    protected Footer footer;

    public void setHeader(Header header) {
        this.header = header;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
    }
}
