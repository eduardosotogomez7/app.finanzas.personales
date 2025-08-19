package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Persona {
    private String nombre;
    private ArrayList<Cuenta> cuentas;


    public Persona(String nombre) {
        setNombre(nombre);
        this.cuentas = new ArrayList<>();
    }

    //Métodos//
    public void agregarCuenta(Cuenta cuenta){
        cuentas.add(cuenta);
    }

    public void eliminarCuenta(Cuenta cuenta){
        cuentas.remove(cuenta);
    }

    public Movimiento realizarMovimiento(TipoMovimiento tipoMovimiento,String nombreMovimiento, double cantidad, Cuenta cuenta){
        Movimiento movimiento = null;
        TerminalMovimiento terminalMovimientos = new TerminalMovimiento();
        movimiento = terminalMovimientos.realizarMovimiento(nombreMovimiento,tipoMovimiento,cantidad,cuenta);
        return movimiento;
    }

    public Movimiento realizarMovimiento(TipoMovimiento tipoMovimiento,String nombreMovmiento, double cantidad, Cuenta cuenta, Cuenta cuentaTransferir){
        Movimiento movimiento = null;
        TerminalMovimiento terminalMovimientos = new TerminalMovimiento();
        movimiento = terminalMovimientos.realizarMovimiento(nombreMovmiento,tipoMovimiento,cantidad,cuenta,cuentaTransferir);
        return movimiento;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre.isEmpty()){
            System.out.println("Debes de ingresar un nombre");
        }
        else {
            this.nombre = nombre;
        }
    }

    public void mostrarCuentas() {
        for(Cuenta cuenta: cuentas){
            System.out.println(cuenta);
        }
    }

    public String balanceTotal(){
        double balanceTotal = 0;
        for(Cuenta cuenta: cuentas){
            balanceTotal = balanceTotal + cuenta.getSaldoDisponible();
        }
        return Double.toString(balanceTotal);
    }

    public List<Cuenta> getCuentas() {
        return Collections.unmodifiableList(cuentas);
    }

    public Cuenta buscarCuenta(String nombreCuenta){
        Cuenta cuentaEncontrada = null;
        int i = 0;

        while(cuentaEncontrada == null && i <= cuentas.size()){
            Cuenta cuentaActual = cuentas.get(i);
            if(cuentaActual.getNombreCuenta().equals(nombreCuenta)){
                cuentaEncontrada = cuentaActual;
            }
            i++;
        }

        return cuentaEncontrada;
    }

    public void agregarMovimiento(Cuenta cuentaOrigen, Movimiento movimiento) {
        cuentaOrigen.agregarMovimiento(movimiento);
    }



    public List<Cuenta> cuentasPrincipales(){
        List<Cuenta> cuentasPrincipales = new ArrayList<>();
        int fin = Math.min(2, cuentas.size()-1); //1:0, 2:1, 3:2, 4:2

        for(int i = 0; i <= fin; i++){
            cuentasPrincipales.add(cuentas.get(i));
        }

        System.out.println(cuentasPrincipales);

        return cuentasPrincipales;
    }


}
