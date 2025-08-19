package model;

public class Tarjeta extends Cuenta{

    private String numeroTarjeta;
    private EntidadFinanciera entidadFinanciera;
    private EntidadBancaria entidadBancaria;
    private String fechaVencimiento;

    public Tarjeta(double saldoDisponible, String nombreCuenta, String colorCuenta, String colorTexto, String numeroTarjeta, EntidadFinanciera entidadFinanciera, EntidadBancaria entidadBancaria, String fechaVencimiento) {
        super(saldoDisponible, nombreCuenta, colorCuenta, colorTexto);
        this.numeroTarjeta = numeroTarjeta;
        this.entidadFinanciera = entidadFinanciera;
        this.entidadBancaria = entidadBancaria;
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "numeroTarjeta='" + numeroTarjeta + '\'' +
                "saldoDisponible='" + getSaldoDisponible() + '\'' +
                ", entidadFinanciera=" + entidadFinanciera +
                ", entidadBancaria=" + entidadBancaria +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                '}';
    }
}
