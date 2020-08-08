package com.restaurant.app.view.caja;

import com.restaurant.app.model.LineaDeVenta;
import com.restaurant.app.model.Productos;
import com.restaurant.app.persistence.ProductosPersistence;
import com.restaurant.app.persistence.impl.jdbc.ProductosPersistenceJdbc;
import com.restaurant.app.utils.Message;
import com.restaurant.app.view.IView;
import javafx.beans.value.ObservableValueBase;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class CajaController extends AnchorPane implements Initializable, IView {
	final static Logger logger = Logger.getLogger(CajaController.class);

	private enum OPERACIONES{
		OP_CANTIDAD,
		OP_DESCUENTO,
		OP_PRECIO
	}
	
	private OPERACIONES currentOp;
	private Stage stage;
	private String user;
	private String perfil;

	private Map<String, List<LineaDeVenta>> ventasPreCargadas;

	@FXML
	private TabPane tPanePedidos;
	@FXML
	private TableView<LineaDeVenta> tblProductos;
	@FXML
	private TableColumn<LineaDeVenta, String> colProductoNombre;
	@FXML
	private TableColumn<LineaDeVenta, String> colProductoCant;
	@FXML
	private TableColumn<LineaDeVenta, Double> colProductoPrecio;
	@FXML
	private TableColumn<LineaDeVenta, String> colProductoDesc;
	@FXML
	private TableColumn<LineaDeVenta, Double> colProductoSubTotal;

	@FXML
	private Button btnCant;
	@FXML
	private Button btnDesc;
	@FXML
	private Button btnPrecio;
	@FXML
	private Button btnDel;
	@FXML
	private Label lblTotal;

	private ProductosPersistence productosPersistence;

	@Override
	public void initialize(URL url, ResourceBundle resouces) {
		this.ventasPreCargadas = new HashMap<>();
		this.user = "Leonel";
		this.perfil = "";
		currentOp = OPERACIONES.OP_CANTIDAD;
		btnPrecio.setVisible(false);

		productosPersistence = new ProductosPersistenceJdbc();
		colProductoNombre.setCellValueFactory(cellData -> new ObservableValueBase<String>() {

			@Override
			public String getValue() {
				return cellData.getValue().getProducto().getNombre();
			}
		});

		colProductoCant.setCellValueFactory(cellData -> new ObservableValueBase<String>() {

			@Override
			public String getValue() {
				return cellData.getValue().getCant();
			}

		});
		colProductoPrecio.setCellValueFactory(cellData -> new ObservableValueBase<Double>() {

			@Override
			public Double getValue() {
				return cellData.getValue().getProducto().getPrecio();
			}
		});

		colProductoDesc.setCellValueFactory(cellData -> new ObservableValueBase<String>() {

			@Override
			public String getValue() {
				return (cellData.getValue().getDesc() == null? "":cellData.getValue().getDesc().toString()).split(".0")[0];
			}
		});

		colProductoSubTotal.setCellValueFactory(cellData -> new ObservableValueBase<Double>() {

			@Override
			public Double getValue() {
				return cellData.getValue().getSubTotal().doubleValue();
			}
		});
	}

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	public void handleBtn1(Event e) {
		operationItem("1");
	}
	
	@FXML
	public void handleBtn2(Event e) {
		operationItem("2");
	}
	
	@FXML
	public void handleBtn3(Event e) {
		operationItem("3");
	}
	
	@FXML
	public void handleBtn4(Event e) {
		operationItem("4");
	}
	
	@FXML
	public void handleBtn5(Event e) {
		operationItem("5");
	}

	@FXML
	public void handleBtn6(Event e) {
		operationItem("6");
	}
	
	@FXML
	public void handleBtn7(Event e) {
		operationItem("7");
	}
	
	@FXML
	public void handleBtn8(Event e) {
		operationItem("8");
	}
	
	@FXML
	public void handleBtn9(Event e) {
		operationItem("9");
	}
	
	@FXML
	public void handleBtn0(Event e) {
		operationItem("0");
	}
	
	@FXML
	public void handleBtnComa(Event e) {
		operationItem(".");
	}
	
	@FXML
	public void handleBtnPrecio(Event e) {
		currentOp = OPERACIONES.OP_PRECIO;
		operationItem(null);
	}

	@FXML
	public void handleBtnDelete(Event e) {
		operationItem(null);

		if(currentOp == OPERACIONES.OP_CANTIDAD) {
			if(tblProductos.getSelectionModel().getSelectedItem().getCant().length() >1)
				tblProductos.getSelectionModel().getSelectedItem().setCant(tblProductos.getSelectionModel().getSelectedItem().getCant().substring(0, tblProductos.getSelectionModel().getSelectedItem().getCant().length() - 1));
			else
				tblProductos.getSelectionModel().getSelectedItem().setCant("0");
			calculateSubtotal(tblProductos.getSelectionModel().getSelectedItem(), null, null);
		}
		else if(currentOp == OPERACIONES.OP_DESCUENTO) {
			if(tblProductos.getSelectionModel().getSelectedItem().getDesc().length() >1)
				tblProductos.getSelectionModel().getSelectedItem().setDesc(tblProductos.getSelectionModel().getSelectedItem().getDesc().substring(0, tblProductos.getSelectionModel().getSelectedItem().getDesc().length() - 1));
			else
				tblProductos.getSelectionModel().getSelectedItem().setDesc("");
			calculateSubtotal(tblProductos.getSelectionModel().getSelectedItem(), null, null);
		}
	}

	@FXML
	public void handleBtnCant(Event e) {
		currentOp = OPERACIONES.OP_CANTIDAD;
		operationItem(null);
	}
	
	@FXML
	public void handleBtnDesc(Event e) {
		currentOp = OPERACIONES.OP_DESCUENTO;
		operationItem(null);
	}
	
	@FXML
	public void handleBtnPagar(Event e) {
		if(tblProductos.getItems().isEmpty()){
			Message.error("Es necesario tener cargado al menos un producto.");
		} else {

		}

	}
	
	@FXML
	public void handleBtnNota(Event e) {
		
	}
	
	@FXML
	public void handleBtnPedir(Event e) {
		if(tblProductos.getItems().isEmpty()){
			Message.error("Es necesario tener cargado al menos un producto.");
		} else {

		}
	}

	@FXML
	public void handleNewOrder(ActionEvent actionEvent) {
		final String mesa = Message.addElement("Ingrese la mesa");
		if(mesa == null){
			handleNewOrder(actionEvent);
		} else
			saveLastOrderAndClear();

		Tab t = new Tab();
		ScrollPane sp = new ScrollPane();
		GridPane gp = new GridPane();
		gp.setHgap(5);
		gp.setVgap(5);

		Productos p = null;
		List<Productos> productosList = productosPersistence.findAll();
		final int count = productosList.size();
		int countVertical = 0;
		int countHorizontal = 0;
		for(int i = 0; i < count; i++){
			p = productosList.get(i);
			final Button b = new Button(p.getNombre());
			b.setId(p.getId().toString());
			b.setOnAction(event -> {
				boolean exist = false;
				Productos pNewTemp = productosPersistence.findById(Long.valueOf(b.getId()));
				for(LineaDeVenta lineaTemp: tblProductos.getItems()){
					if(lineaTemp.getProductoId().longValue() == pNewTemp.getId().longValue()){
						exist = true;
						lineaTemp.setCant(String.valueOf(Integer.valueOf(lineaTemp.getCant()).intValue() + 1));
						lineaTemp.setSubTotal(new BigDecimal(pNewTemp.getPrecio().doubleValue() * Integer.valueOf(lineaTemp.getCant()).intValue()));
						break;
					}
				}

				if(!exist){
					LineaDeVenta lineaDeVenta  = new LineaDeVenta();
					lineaDeVenta.setProductoId(pNewTemp.getId().intValue());
					lineaDeVenta.setProducto(pNewTemp);
					lineaDeVenta.setCant("1");
					lineaDeVenta.setSubTotal(new BigDecimal(pNewTemp.getPrecio().doubleValue()));
					tblProductos.getItems().add(lineaDeVenta);
					tblProductos.getSelectionModel().select(lineaDeVenta);
				} else
					tblProductos.refresh();

				//calcular el total
				double total = 0;
				for(LineaDeVenta lineaTemp: tblProductos.getItems()){
					total = total + lineaTemp.getSubTotal().doubleValue();
				}
				BigDecimal bd = BigDecimal.valueOf(total);
				bd = bd.setScale(3, RoundingMode.HALF_UP);
				lblTotal.setText("Total: " + bd.doubleValue());
			});
			b.setMinHeight(100);
			b.setMinWidth(100);
			GridPane.setMargin(b, new Insets(10, 0, 0, 10));
			gp.add(b, countHorizontal, countVertical);
			if(i > 0 && (i % 5) % 1 == 0){
				countVertical++;
				countHorizontal=0;
			}
			countHorizontal++;
		}
		sp.setContent(gp);
		t.setText(mesa);
		t.setContent(sp);
		tPanePedidos.getTabs().add(t);
	}

	private void saveLastOrderAndClear(){
		if(!tPanePedidos.getTabs().isEmpty()){
			ventasPreCargadas.put(tPanePedidos.getSelectionModel().getSelectedItem().getText(), tblProductos.getItems());
			tblProductos.getItems().clear();
			currentOp = OPERACIONES.OP_CANTIDAD;
		}
	}
	
	private void operationItem(String valor) {
		if(!tblProductos.getItems().isEmpty()){
			btnCant.getStyleClass().removeAll("button-active");
			btnDesc.getStyleClass().removeAll("button-active");
			btnPrecio.getStyleClass().removeAll("button-active");

			switch (currentOp) {
				case OP_CANTIDAD:
					btnCant.getStyleClass().add("button-active");
					if(valor != null)
						calculateSubtotal(tblProductos.getSelectionModel().getSelectedItem(), valor, null);
					break;
				case OP_DESCUENTO:
					btnDesc.getStyleClass().add("button-active");
					if(valor != null)
						calculateSubtotal(tblProductos.getSelectionModel().getSelectedItem(), null, valor);
					break;
				case OP_PRECIO:
					btnPrecio.getStyleClass().add("button-active");

					break;

				default: //coma
					break;
			}
			tblProductos.refresh();
		}
	}

	private void calculateSubtotal(final LineaDeVenta currentLinea, final String cant, final String desc){
		String sCurrentDesc = "";
		if(desc != null){
			if(desc.contains(".")){
				if(!currentLinea.getDesc().contains("."))
					sCurrentDesc = currentLinea.getDesc() + ".";
			} else {
				sCurrentDesc = (currentLinea.getDesc() != null ? currentLinea.getDesc() : "") + desc;
			}
		} else if(currentLinea.getDesc() != null)
			sCurrentDesc = currentLinea.getDesc();

		final String currentCant = (cant == null? currentLinea.getCant() : (currentLinea.getCant().equals("0")? "" : currentLinea.getCant()) + (cant == "." ? "":cant));
		final double currentDesc = sCurrentDesc.equals("")? 0d: Double.valueOf(sCurrentDesc);
		final double precio = (Integer.valueOf(currentCant).intValue() * currentLinea.getProducto().getPrecio().doubleValue());
		final double subtotal = precio - ((precio * currentDesc) / 100 );

		BigDecimal bd = BigDecimal.valueOf(subtotal);
		bd = bd.setScale(3, RoundingMode.HALF_UP);
		currentLinea.setCant(currentCant);
		if(!sCurrentDesc.equals("0")){
			currentLinea.setDesc(sCurrentDesc);
		}
		currentLinea.setSubTotal(new BigDecimal(bd.doubleValue()));
	}
}
