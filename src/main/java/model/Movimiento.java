package model;

public class Movimiento {
    private TipoMovimiento tipoMovimiento;
    private String nombreMovimiento;
    private Double cantidad;
    private Cuenta cuenta1;
    private Cuenta cuenta2;

    public Movimiento(TipoMovimiento tipoMovimiento, String nombreMovimiento, Double cantidad, Cuenta cuenta1, Cuenta cuenta2) {
        this.tipoMovimiento = tipoMovimiento;
        this.nombreMovimiento = nombreMovimiento;
        this.cantidad = cantidad;
        this.cuenta1 = cuenta1;
        this.cuenta2 = cuenta2;
    }

    public Movimiento(TipoMovimiento tipoMovimiento, String nombreMovimiento, Double cantidad, Cuenta cuenta1) {
        this(tipoMovimiento,nombreMovimiento,cantidad,cuenta1,null);
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public String getNombreMovimiento() {
        return nombreMovimiento;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public Cuenta getCuenta1() {
        return cuenta1;
    }
}
