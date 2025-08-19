package controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VistaPrincipalController {

    //---------------------------------------------------Atributos-------------------------------------//
    private Persona persona;
    private double gastosTotales = 0;
    private double ingresosTotales = 0;
    private List<Movimiento> movimientos = new ArrayList<>();


    @FXML
    private Label balanceTotalLabel;
    @FXML
    private Pane contenedorMovimientos;
    @FXML
    private Label botonPluesTres;
    @FXML
    private Label botonPlusDos;
    @FXML
    private Label botonPlusUno;
    @FXML
    private Label nombreCuenta1;
    @FXML
    private Label nombreCuenta2;
    @FXML
    private Label nombreCuenta3;
    @FXML
    private Label cantidadCuenta1;
    @FXML
    private Label cantidadCuenta2;
    @FXML
    private Label cantidadCuenta3;
    @FXML
    private Label CuentaMov1;
    @FXML
    private Label CuentaMov2;
    @FXML
    private Label CuentaMov3;
    @FXML
    private Label cantidadMov1;
    @FXML
    private Label cantidadMov2;
    @FXML
    private Label cantidadMov3;
    @FXML
    private Label nombreMov1;
    @FXML
    private Label nombreMov2;
    @FXML
    private Label nombreMov3;
    @FXML
    private Label gastosTotalesLabel;
    @FXML
    private Label ingresosTotalesLabel;
    @FXML
    private Label labelNombrePrincipal;
    @FXML
    private AnchorPane root;
    @FXML
    private Button botonInicio;
    @FXML
    private Button botonCuenta1;
    @FXML
    private Button botonCuenta2;
    @FXML
    private Button botonCuenta3;

    //---------------------------------------------------Métodos-------------------------------------//

    @FXML
    void initialize(){
        if(persona != null){
            cargarDatos();
        }
    }

    public void cargarDatos() {
        System.out.println(persona);
        balanceTotalLabel.setText("$"+persona.balanceTotal());
        cargarCuentasPrincipales();
        cargarMovimientosRecientes();
        cargarIngresosGastos();
    }

    private void cargarCuentasPrincipales(){
        List<Cuenta> cuentasPrincipales = persona.cuentasPrincipales();

        Label[] nombreCuentas = {nombreCuenta1,nombreCuenta2,nombreCuenta3};
        Label[] cantidadCuentas = {cantidadCuenta1,cantidadCuenta2,cantidadCuenta3};
        Label[] botonesPlus = {botonPlusUno,botonPlusDos,botonPluesTres};
        Button[] botonesPrincipales = {botonCuenta1,botonCuenta2,botonCuenta3};


        for(int i = 0; i < 3; i++){
            nombreCuentas[i].setText("");
            cantidadCuentas[i].setText("");
        }

        for(int i = 0; i < cuentasPrincipales.size(); i++){
            Cuenta cuentaActual = cuentasPrincipales.get(i);
            nombreCuentas[i].setText(cuentaActual.getNombreCuenta());
            cantidadCuentas[i].setText(Double.toString(cuentaActual.getSaldoDisponible()));
            nombreCuentas[i].setStyle("-fx-text-fill: " + cuentaActual.getColorTexto() + ";");
            cantidadCuentas[i].setStyle("-fx-text-fill: " + cuentaActual.getColorTexto() + ";");
            botonesPlus[i].setText("");
            botonesPrincipales[i].getStyleClass().add("boton-1");
            botonesPrincipales[i].setStyle(
                    "-fx-background-color: " + cuentaActual.getColorCuenta() + ";" +
                            "-fx-border-color: " + cuentaActual.getColorCuenta() + ";" +
                            "-fx-text-fill: " + cuentaActual.getColorTexto() + ";"
            );

        }
    }

    private void cargarMovimientosRecientes() {

        List<Movimiento> movimientosRecientes = movimientos.reversed();


        // Arreglos de Labels para poder iterar
        Label[] nombres = {nombreMov1, nombreMov2, nombreMov3};
        Label[] cantidades = {cantidadMov1, cantidadMov2, cantidadMov3};
        Label[] cuentas = {CuentaMov1, CuentaMov2, CuentaMov3};

        // Primero vaciamos todos los labels
        for (int i = 0; i < 3; i++) {
            nombres[i].setText("");
            cantidades[i].setText("");
            cuentas[i].setText("");
        }


        for (int i = 0; i < movimientosRecientes.size(); i++) {
            Movimiento mov = movimientosRecientes.get(i);
            nombres[i].setText(mov.getNombreMovimiento());
            cantidades[i].setText("$" + mov.getCantidad().toString());
            cantidades[i].setStyle("-fx-text-fill: " +
                    (mov.getTipoMovimiento() == TipoMovimiento.GASTO ? "red" : "green"));
            cuentas[i].setText(mov.getCuenta1().getNombreCuenta());
        }
    }

    private void cargarIngresosGastos(){
        gastosTotalesLabel.setText("$"+Double.toString(gastosTotales));
        ingresosTotalesLabel.setText("$"+ingresosTotales);
    }


    //---------------------------------------------------Métodos para ir a otra ventana-------------------------------------//
    @FXML
    void agregarCuenta(ActionEvent event) {
        agregarCuenta();

    }
    @FXML
    void agregarCuenta(){
        Main.app.ventanaNuevaCuenta(this);
    }


    @FXML
    void nuevoMovimiento(ActionEvent event) {
        nuevoMovimiento();
    }
    @FXML
    void nuevoMovimiento(){
        Main.app.ventanaNuevoMovimiento(this);
    }




    @FXML
    void CrearVisitarUno(ActionEvent event) {
        if(nombreCuenta1.getText().isEmpty()){
            agregarCuenta();
        }
    }
    @FXML
    void crearVisitarDos(ActionEvent event) {
        if(nombreCuenta2.getText().isEmpty()){
            agregarCuenta();
        }

    }
    @FXML
    void CrearVisitarTres(ActionEvent event) {
        if(nombreCuenta3.getText().isEmpty()){
            agregarCuenta();
        }
    }



    public void agregarMovimientoReciente(Movimiento movimiento){
        if(movimientos.size() < 3){
            movimientos.add(movimiento);
        }else{
            movimientos.removeFirst();
            movimientos.add(movimiento);
        }
    }


    public void actualizarIngresosGastos(String tipoMovimiento, double cantidad) {
        if(tipoMovimiento.equals("Gasto")){
            gastosTotales += cantidad;
            gastosTotalesLabel.setText("$"+Double.toString(gastosTotales));
        }else if(tipoMovimiento.equals("Ingreso")) {
            ingresosTotales += cantidad;
            ingresosTotalesLabel.setText("$"+ingresosTotales);
        }
    }


    //---------------------------------------------------Setters-------------------------------------//


    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
