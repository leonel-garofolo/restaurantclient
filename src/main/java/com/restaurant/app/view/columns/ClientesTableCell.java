package com.restaurant.app.view.columns;

import com.restaurant.app.model.Clientes;
import com.restaurant.app.model.Productos;
import javafx.scene.control.TableCell;

public class ClientesTableCell<Taras> extends TableCell<Taras, Clientes> {
	@Override
	protected void updateItem(Clientes item, boolean empty) {
		super.updateItem(item, empty);
		setText(empty || item == null ? "" : item.getNombre());
	}
}





