package controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RealizarMovimientoController {


    //---------------------------------------------------Atributos-------------------------------------//


    private Persona persona;
    private VistaPrincipalController vistaPrincipalController;

    @FXML
    private Button botonContinuar;
    @FXML
    private MenuButton menuPrueba;
    @FXML
    private TextField cantidadField;
    @FXML
    private MenuButton menuCuenta;
    @FXML
    private MenuButton menuDes;
    @FXML
    private MenuButton menuTipoMovimiento;
    @FXML
    private TextField nombreMovimientoField;
    @FXML
    private MenuItem opcionGasto;
    @FXML
    private MenuItem opcionIngreso;
    @FXML
    private MenuItem opcionTransaccion;

    //---------------------------------------------------Métodos-------------------------------------//


    @FXML
    private void initialize(){

        System.out.println("menuDestiino es: " + menuDes);
        System.out.println("menuDestiino es: " + menuCuenta);
        mandarControlador(); //Mandamos una referencia de nuestro controlador a la clase main para que después la use otro controlador

        List<MenuItem> opcionesMovimientos = Arrays.asList(opcionTransaccion,opcionGasto,opcionIngreso);
        for(MenuItem opcionMovimiento: opcionesMovimientos){
            opcionMovimiento.setOnAction(e ->{
                menuTipoMovimiento.setText(opcionMovimiento.getText());
                if(!(menuTipoMovimiento.getText().equals(opcionTransaccion.getText()))){
                    menuDes.setDisable(true);

                }else {
                    menuDes.setDisable(false);
                }

            });




        }


        //Esta instruccion se debe de hacer para que al iniciarse la ventana aparezcan las cuentas que tenemos actualmente
        if(persona != null){
            actualizarMenuCuentas();
            actualizarMenuDestinos();
        }

    }

    @FXML
    private void movimientoSeleccioinado(ActionEvent event) throws IOException {
        TipoMovimiento tipoMovimiento;
        Cuenta cuentaOrigen = persona.buscarCuenta(menuCuenta.getText());
        double cantidad = Double.parseDouble(cantidadField.getText());
        String nombreMovimiento = nombreMovimientoField.getText();
        Movimiento movimiento;
        System.out.println(menuTipoMovimiento.getText());
        if(menuTipoMovimiento.getText().equals("Transacción")){
            tipoMovimiento = TipoMovimiento.TRANSACCION;
            Cuenta cuentaDestino = persona.buscarCuenta(menuDes.getText());
            movimiento = persona.realizarMovimiento(tipoMovimiento,nombreMovimiento,cantidad,cuentaOrigen,cuentaDestino);

        }else {
            tipoMovimiento = TipoMovimiento.valueOf(menuTipoMovimiento.getText().toUpperCase().trim());
            System.out.println(tipoMovimiento);
            movimiento = persona.realizarMovimiento(tipoMovimiento,nombreMovimiento,cantidad,cuentaOrigen);
        }

        if(movimiento == null){ //Si al llamar a realizarMovimiento nos regresa null significa que el saldo no es sificiente
            cantidadField.setStyle("-fx-border-color: red;");
        }else{ //Si no nos regresa null, significa que si se pudo realizar el movimiento y lo podemos guardar
            persona.agregarMovimiento(cuentaOrigen,movimiento);
            vistaPrincipalController.agregarMovimientoReciente(movimiento); //Agregamos este movimiento a los recientes
            vistaPrincipalController.cargarDatos(); //Actualizamos vista principal
            vistaPrincipalController.actualizarIngresosGastos(menuTipoMovimiento.getText(), cantidad);

            //Al finalizar de realizar un movimiento cerramos la ventana
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

        }
    }


    //En este método lo que hacemos es ver todas las cuentas que tiene el usuario y ponerlas en el menuIItem correspondiente
    @FXML
    public void actualizarMenuDestinos(){
        menuDes.getItems().clear();

        for(Cuenta cuenta: persona.getCuentas()){
            MenuItem item = new MenuItem(cuenta.getNombreCuenta());
            menuDes.getItems().add(item);
            item.setOnAction(e -> {
                menuDes.setText(cuenta.getNombreCuenta());
            });

        }
    }

    @FXML
    public void actualizarMenuCuentas() {
        menuCuenta.getItems().clear();

        for (Cuenta cuenta : persona.getCuentas()) {
            MenuItem item = new MenuItem(cuenta.getNombreCuenta());
            menuCuenta.getItems().add(item);
            item.setOnAction(e -> {
                menuCuenta.setText(cuenta.getNombreCuenta());
            });
        }

    }

    //Este método manda una referencia de nuestro controlador a la clase principal para que otro controlador la pueda usar
    private void mandarControlador(){
        Main.app.setRealizarMovimientoController(this);
    }


    //---------------------------------------------------Setters-------------------------------------//

    public void setPersona(Persona persona) {
        this.persona = persona;
        actualizarMenuCuentas();
        actualizarMenuDestinos();
    }

    public void setVistaPrincipalController(VistaPrincipalController vistaPrincipalController) {
        this.vistaPrincipalController = vistaPrincipalController;
    }

}



