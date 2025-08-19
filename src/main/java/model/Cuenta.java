package model;

import java.util.ArrayList;

public class Cuenta {

    private double saldoDisponible;
    private String nombreCuenta;
    private boolean cuentaFijada;
    private String colorCuenta;
    private String colorTexto;
    private ArrayList<Movimiento> movimientos;

    public Cuenta(double saldoDisponible, String nombreCuenta,String colorCuenta, String colorTexto) {
        this.saldoDisponible = saldoDisponible;
        this.nombreCuenta = nombreCuenta;
        this.colorCuenta = colorCuenta;
        this.colorTexto = colorTexto;
        this.movimientos = new ArrayList<>();
    }

    public void agregarSaldo(double cantidad){
        this.saldoDisponible = saldoDisponible + cantidad;
    }

    public void descontarSaldo(double cantidad){
        this.saldoDisponible = saldoDisponible - cantidad;
    }

    public boolean saldoDisponible(double cantidad){
        return saldoDisponible >= cantidad;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "saldoDisponible=" + saldoDisponible +
                ", nombreCuenta='" + nombreCuenta + '\'' +
                '}';
    }

    public double getSaldoDisponible(){
        return saldoDisponible;
    }

    public String getNombreCuenta(){
        return nombreCuenta;
    }

    public String getColorCuenta(){
        return colorCuenta;
    }

    public String getColorTexto(){
        return colorTexto;
    }

    public void agregarMovimiento(Movimiento movimiento) {
        movimientos.add(movimiento);
    }
}
