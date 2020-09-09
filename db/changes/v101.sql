alter table venta add cancel datetime;

alter table producto add stock INT(11);

alter table linea_de_venta add cantidad INT(11);

/* Add disconunt of bill in case if neccesary */
alter table venta add disc decimal(9,2);