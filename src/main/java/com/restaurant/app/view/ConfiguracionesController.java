package com.restaurant.app.view;

import com.restaurant.app.model.*;
import com.restaurant.app.persistence.CategoriasPersistence;
import com.restaurant.app.persistence.ClientesPersistence;
import com.restaurant.app.persistence.ParametrosGlobalesPersistence;
import com.restaurant.app.persistence.ProductosPersistence;
import com.restaurant.app.persistence.impl.jdbc.CategoriasPersistenceJdbc;
import com.restaurant.app.persistence.impl.jdbc.ClientesPersistenceJdbc;
import com.restaurant.app.persistence.impl.jdbc.ParametrosGlobalesPersistenceJdbc;
import com.restaurant.app.persistence.impl.jdbc.ProductosPersistenceJdbc;
import com.restaurant.app.utils.Message;
import javafx.beans.value.ObservableValueBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

public class ConfiguracionesController extends AnchorPane {

	public static final String CLIENTE = "CLIENTES";
	public static final String PRODUCTOS = "PRODUCTOS";
	public static final String CATALOGOS = "CATALOGOS";	

	@FXML
	private TableView<Productos> tblEntidades;
	@FXML
	private TableColumn<Productos, Long> colCodigo;

	@FXML
	private TableColumn<Productos, String> colNombre;

	@FXML
	private TableColumn<Productos, String> colPrecio;
	
	@FXML
	private TableView<Categoria> tblCategorias;
	@FXML
	private TableColumn<Categoria, Long> colCategoriaId;

	@FXML
	private TableColumn<Categoria, String> colCategoriaNombre;

	@FXML
	private Button btnNuevoEntidad;
	@FXML
	private Button btnEliminarEntidad;
	@FXML
	private TextArea txtEntidadNombre;
	@FXML
	private TextField txtEntidadCodigo;
	@FXML
	private TextField txtEntidadPrecio;
	@FXML
	private ComboBox<Categoria> cbxCategorias;
	@FXML
	private Button btnAplicarEntidad;
	@FXML
	private Button btnCerrar;

	@FXML
	private AnchorPane usuariosView;

	@FXML
	private Tab tabProductos;

	@FXML
	private Tab tabUsuarios;
	
	@FXML
	private TabPane tabPane;

	@FXML
	private Label lblNombre;

	@FXML
	private Label lblTara;
	@FXML
	private Label lblTaraValue;

	@FXML
	private Label lblFecha;
	@FXML
	private Label lblFechaValue;

	@FXML
	private Label lblCuitAlias;
	@FXML
	private Label lblMov;
	@FXML
	private Label lblAcum;
	
	/*Tab categorias*/
	@FXML
	private TextArea txtCategoriaNombre;

	private ClientesPersistence clientesPersistence;
	private ProductosPersistence productosPersistence;
	private CategoriasPersistence categoriaPersistence;
	private ParametrosGlobalesPersistence parametrosGlobalesPersistence;
	private boolean modoEditEntidades = false;
	private boolean modoEditCategoria = false;

	@FXML
	private void handleAplicarAduana(ActionEvent event) {
		ParametrosGlobales pg = new ParametrosGlobales();		
		Message.info("Los datos se guardaron correctamente.");
	}

	@FXML
	private void handleCerrar(ActionEvent event) {
		final Node source = (Node) event.getSource();
		((Stage) source.getScene().getWindow()).close();
	}

	@FXML
	private void handleSelectedEntidades(ActionEvent event) {
		loadFormEntidades();		
	}
	
	@FXML
	private void handleTblEntidadesSelected(MouseEvent event) {
		if (!tblEntidades.getSelectionModel().isEmpty()) {			
			modoEditEntidades = true;
			Productos p = (Productos) tblEntidades.getSelectionModel().getSelectedItem();			
			txtEntidadCodigo.setText(p.getCodigo());
			txtEntidadNombre.setText(p.getNombre());			
			txtEntidadPrecio.setText(String.valueOf(p.getPrecio()));
			
			if(p.getCategoriaId() != null) {		
				List<Categoria> categorias = cbxCategorias.getItems();
				for(Categoria c : categorias) {
					if(p.getCategoriaId().longValue() == c.getId().longValue() ) {
						cbxCategorias.setValue(c);
						break;
					}									
				}				
			} else {
				cbxCategorias.setValue(null);
			}
			//SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");				
		}
	}
	
	@FXML
	private void handleTblCategoriasSelected(MouseEvent event) {
		if (!tblCategorias.getSelectionModel().isEmpty()) {
			cleanFormEntidades();
			modoEditCategoria = true;
			Categoria c = (Categoria) tblCategorias.getSelectionModel().getSelectedItem();
			txtCategoriaNombre.setText(c.getNombre());
			//SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");				
		}
	}

	@FXML
	private void handleAplicarEntidades(ActionEvent event) {
		saveEntidades();
		txtEntidadNombre.requestFocus();
	}

	@FXML
	private void handleNuevoEntidades(ActionEvent event) {
		cleanFormEntidades();
	}

	@FXML
	private void handleEliminarCategoria(ActionEvent event) {
		if (!tblCategorias.getSelectionModel().isEmpty()) {
			Categoria p = (Categoria) tblCategorias.getSelectionModel().getSelectedItem();
			categoriaPersistence.deleteById(p.getId());			
			loadFormCategorias();
		} else {

		}
	}
	
	@FXML
	private void handleAplicarCategoria(ActionEvent event) {
		Categoria categoria = new Categoria();
		if (modoEditCategoria && tblCategorias.getSelectionModel().getSelectedItem() != null) {
			categoria.setId(tblCategorias.getSelectionModel().getSelectedItem().getId());
		}

		categoria.setNombre(txtCategoriaNombre.getText());
		categoriaPersistence.save(categoria);
		txtEntidadNombre.requestFocus();
		loadFormCategorias();
	}

	@FXML
	private void handleNuevoCategoria(ActionEvent event) {
		this.modoEditCategoria = false;
		cleanFormCategoria();
	}

	@FXML
	private void handleEliminarEntidades(ActionEvent event) {
		if (!tblEntidades.getSelectionModel().isEmpty()) {
			Productos p = (Productos) tblEntidades.getSelectionModel().getSelectedItem();
			productosPersistence.deleteById(p.getId());
			loadFormEntidades();
		} 
	}


	private void loadFormEntidades() {
		cleanFormEntidades();
		tblEntidades.getItems().clear();
		tblEntidades.getItems().addAll(productosPersistence.findAll());		
		
		cbxCategorias.getItems().clear();
		cbxCategorias.getItems().addAll(categoriaPersistence.findAll());
	}

	private void cleanFormEntidades() {
		this.modoEditEntidades = false;
		txtEntidadCodigo.setText("");
		txtEntidadNombre.setText("");	
		txtEntidadPrecio.setText("");
		cbxCategorias.setValue(null);
	}
	
	private void cleanFormCategoria() {
		this.modoEditEntidades = false;
		txtCategoriaNombre.setText("");		
	}
	
	private void loadFormCategorias() {
		cleanFormCategoria();
		tblCategorias.getItems().clear();
		tblCategorias.getItems().addAll(categoriaPersistence.findAll());		
	}

	private void saveEntidades() {
		Productos producto = new Productos();
		if (modoEditEntidades && tblEntidades.getSelectionModel().getSelectedItem() != null) {
			producto.setId(tblEntidades.getSelectionModel().getSelectedItem().getId());
		}

		producto.setCodigo(txtEntidadCodigo.getText());
		producto.setNombre(txtEntidadNombre.getText());
		producto.setPrecio(Double.valueOf(txtEntidadPrecio.getText()));
		if(cbxCategorias.getSelectionModel().getSelectedItem() != null) {			
			producto.setCategoriaId(cbxCategorias.getSelectionModel().getSelectedItem().getId());
		}
		
		productosPersistence.save(producto);
		this.loadFormEntidades();

	}
	
	public void initialize() {
		switch (Usuarios.getPerfilLogeado()) {
		case Usuarios.P_SUPERVISOR:
			break;
		case Usuarios.P_CAJA:
			tabUsuarios.setDisable(true);
			break;
		default:
			break;
		}

		initPersistence();
		initTableView();		
		initTextUpperCase();

		tabProductos.setOnSelectionChanged (e -> {
			cbxCategorias.getItems().clear();
			cbxCategorias.getItems().addAll(categoriaPersistence.findAll());
		});
	}

	private void initTextUpperCase() {
		txtEntidadNombre.textProperty().addListener((ov, oldValue, newValue) -> {
			txtEntidadNombre.setText(newValue.toUpperCase());
		});
	}

	private void initPersistence() {
		this.parametrosGlobalesPersistence = new ParametrosGlobalesPersistenceJdbc();
		this.clientesPersistence = new ClientesPersistenceJdbc();
		this.productosPersistence = new ProductosPersistenceJdbc();		
		this.categoriaPersistence = new CategoriasPersistenceJdbc();
		
		loadFormEntidades();
		loadFormCategorias();
	}

	private void initTableView() {
		colCodigo.setCellValueFactory(cellData -> new ObservableValueBase<Long>() {

			@Override
			public Long getValue() {
				return cellData.getValue().getId();
			}
		});

		colNombre.setCellValueFactory(cellData -> new ObservableValueBase<String>() {

			@Override
			public String getValue() {
				return cellData.getValue().getNombre();
			}
		});


		colPrecio.setCellValueFactory(cellData -> new ObservableValueBase<String>() {

			@Override
			public String getValue() {
				return cellData.getValue().getPrecio().toString();
			}
		});

		colCategoriaId.setCellValueFactory(cellData -> new ObservableValueBase<Long>() {

			@Override
			public Long getValue() {
				return cellData.getValue().getId();
			}

		});
		colCategoriaNombre.setCellValueFactory(cellData -> new ObservableValueBase<String>() {

			@Override
			public String getValue() {
				return cellData.getValue().getNombre();
			}
		});
	}
}
