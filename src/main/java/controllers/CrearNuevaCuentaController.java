package controllers;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.*;
import javafx.scene.paint.Color;
import java.util.Arrays;
import java.util.List;

public class CrearNuevaCuentaController {

    //---------------------------------------------------Atributos-------------------------------------//

    private Persona persona;
    private VistaPrincipalController principalController;
    private RealizarMovimientoController realizarMovimientoController; //Necesitamos este controlador ya qué al crear una nueva cuenta hay que modificar la vista de realizar Movimiento

    @FXML
    private Label bancoLabel;
    @FXML
    private Label fechaVencimientoLabel;
    @FXML
    private Label numeroTarjetaLabel;
    @FXML
    private TextField FechaVencimientoField;
    @FXML
    private ColorPicker colorCuenta;
    @FXML
    private ColorPicker colorTexto;
    @FXML
    private MenuButton menuBoton;
    @FXML
    private MenuButton menuEntidadBancaria;
    @FXML
    private MenuButton menuEntidadFinanciera;
    @FXML
    private TextField nombreCuentaField;
    @FXML
    private TextField numeroTarjetaField;
    @FXML
    private MenuItem opcionBanamex;
    @FXML
    private MenuItem opcionBancomer;
    @FXML
    private MenuItem opcionEfectivo;
    @FXML
    private MenuItem opcionMasterCard;
    @FXML
    private MenuItem opcionNU;
    @FXML
    private MenuItem opcionOtraBancaria;
    @FXML
    private MenuItem opcionOtraFinanciera;
    @FXML
    private MenuItem opcionSantander;
    @FXML
    private MenuItem opcionTarjeta;
    @FXML
    private MenuItem opcionVisa;
    @FXML
    private TextField saldoDisponibleField;


    //---------------------------------------------------Métodos-------------------------------------//

    @FXML
    private void initialize() {

        colorCuenta.setTooltip(new Tooltip("Selecciona un color para tu cuenta"));
        colorTexto.setTooltip(new Tooltip("Slecciona el color del texto"));

        opcionTarjeta.setOnAction(e -> {
            menuBoton.setText(opcionTarjeta.getText()); // Cambiar el color del texto al que se eliga en las opciones
            System.out.println("Elegiste: " + opcionTarjeta.getText());
            numeroTarjetaField.setEditable(true); //TextField para numero de la tarjeta disponible
            FechaVencimientoField.setEditable(true);
            menuEntidadBancaria.setDisable(false);
            menuEntidadFinanciera.setDisable(false);

        });

        opcionEfectivo.setOnAction(e -> {
            menuBoton.setText(opcionEfectivo.getText());
            System.out.println("Elegiste: " + opcionEfectivo.getText());
            numeroTarjetaField.setEditable(false); //No se puede escribir un numero de tarjeta
            FechaVencimientoField.setEditable(false);
            menuEntidadFinanciera.setDisable(true);
            menuEntidadBancaria.setDisable(true);
        });

        //Creamos una lista con las opciones de banco que tenemos
        List<MenuItem> opcionesBanco = Arrays.asList(opcionBanamex,opcionNU,opcionBancomer,opcionSantander,opcionOtraBancaria);

        //Este for nos ayuda a que un menu tenga el texto de la opcion seleccionada por el usuario
        for(MenuItem opciones: opcionesBanco){
            opciones.setOnAction(e ->{
                menuEntidadBancaria.setText(opciones.getText());
            });
        }


        List<MenuItem> opcionesFinancieras = Arrays.asList(opcionVisa,opcionMasterCard,opcionOtraFinanciera);
        for(MenuItem opciones: opcionesFinancieras){
            opciones.setOnAction(e ->{
                menuEntidadFinanciera.setText(opciones.getText());
            });
        }

        //...................................................................................//
        //Estos métodos nos ayudan a modificar el valor de un label en tiempo real, según lo que el usuario escriba en un textFieild

        numeroTarjetaField.textProperty().addListener((observable, oldValue, newValue) -> {
            numeroTarjetaLabel.setText(newValue);
        });

        FechaVencimientoField.textProperty().addListener((observable, oldValue, newValue) -> {
            fechaVencimientoLabel.setText(newValue);
        });

        menuEntidadBancaria.textProperty().addListener((observable, oldValue, newValue) -> {
            bancoLabel.setText(newValue);
        });
        //....................................................................................//


    }

    @FXML
    void crearCuenta(ActionEvent event) {

        //Obtenemos los datos necesarios para crear una cuenta, se obtienen de los Textfield
        double saldoDisponible =  Double.parseDouble(saldoDisponibleField.getText());
        String nombreCuenta = nombreCuentaField.getText();
        String cuentaColor = obtenerColorCuenta();
        String textoColor = obtenerColorTexto();


        if(menuBoton.getText().equals("Efectivo")){ //Si es una cuenta en efectivo ya no necesitamos mas datos
            Cuenta cuentaNueva = new Cuenta(saldoDisponible,nombreCuenta,cuentaColor,textoColor);
            persona.agregarCuenta(cuentaNueva);
        }else{ //Si la cuenta es una tarjeta necesitamos datos adicinales
            String numeroTarjeta = numeroTarjetaField.getText();
            String fechaVencimiento = FechaVencimientoField.getText();
            EntidadBancaria entidadBancaria = EntidadBancaria.valueOf(menuEntidadBancaria.getText().toUpperCase().trim());
            EntidadFinanciera entidadFinanciera = EntidadFinanciera.valueOf(menuEntidadFinanciera.getText().toUpperCase().trim());
            Tarjeta tarjeta = new Tarjeta(saldoDisponible,nombreCuenta,cuentaColor,textoColor,numeroTarjeta,entidadFinanciera,entidadBancaria,fechaVencimiento);
            persona.agregarCuenta(tarjeta);
        }

        //.................................................................
        //Una vez creada una cuenta debemos de actualzar las vistas
        if (principalController != null) {
            principalController.cargarDatos();

        }

        if(realizarMovimientoController != null){
            realizarMovimientoController.actualizarMenuCuentas();
            realizarMovimientoController.actualizarMenuDestinos();

        }
        //..........................................................


        //Despues de crear una nueva cuenta cerramos la ventana
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    private String obtenerColorTexto() {

        Color color = colorTexto.getValue(); // Obtiene el color elegido
        //Despues vamos a transformar ese color a formato hexColor
        String hexColorTexto = String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));

        return hexColorTexto; //Regresamos el color en tipo Strig para que pueda ser utilizado
    }

    private String obtenerColorCuenta() {

        Color color = colorCuenta.getValue();
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    //---------------------------------------------------Getters y Setters-------------------------------------//

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setVistaPrincipalController(VistaPrincipalController principalController) {
        this.principalController = principalController;
    }
    public void setRealizarMovimientoController(RealizarMovimientoController realizarMovimientoController){
        this.realizarMovimientoController = realizarMovimientoController;
    }

}
