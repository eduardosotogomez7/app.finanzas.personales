package model;

public class TerminalMovimiento {


    public Movimiento realizarMovimiento(String nombreMovimiento, TipoMovimiento tipoMovimiento, double cantidad, Cuenta cuenta) {
        Movimiento movimiento = null;
        if(tipoMovimiento == TipoMovimiento.INGRESO){
            cuenta.agregarSaldo(cantidad);
            movimiento = new Movimiento(tipoMovimiento,nombreMovimiento,cantidad,cuenta);
        }else{
            if(cuenta.saldoDisponible(cantidad)){
                cuenta.descontarSaldo(cantidad);
                movimiento = new Movimiento(tipoMovimiento,nombreMovimiento,cantidad,cuenta);
            }
        }

        return  movimiento;


    }


    public Movimiento realizarMovimiento(String nombreMovimiento, TipoMovimiento tipoMovimiento, double cantidad, Cuenta cuenta, Cuenta cuentaTransferir) {
        Movimiento movimiento = null;
        if(cuenta.saldoDisponible(cantidad)){
            cuenta.descontarSaldo(cantidad);
            cuentaTransferir.agregarSaldo(cantidad);
            movimiento = new Movimiento(tipoMovimiento,nombreMovimiento,cantidad,cuenta,cuentaTransferir);
        }

        return movimiento;


    }
}
