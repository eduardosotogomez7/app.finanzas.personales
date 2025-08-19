package application;

import controllers.CrearNuevaCuentaController;
import controllers.RealizarMovimientoController;
import controllers.VistaPrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Persona;
import model.TerminalMovimiento;
import utilities.Paths;

public class Main extends Application {
    public static Main app;
    Persona persona1 = new Persona("Eduardo");
    private RealizarMovimientoController realizarMovimientoController;

    public void setRealizarMovimientoController(RealizarMovimientoController realizarMovimientoController){
        this.realizarMovimientoController = realizarMovimientoController;
    }
    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        app = this;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.VISTA_PRINCIPAL));
        AnchorPane pane = loader.load();
        VistaPrincipalController controller = loader.getController();
        controller.setPersona(persona1);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    public void ventanaNuevaCuenta(VistaPrincipalController pricipalController) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.VENTANA_NUEVA_CUENTA));
        try {
            AnchorPane pane = loader.load();
            CrearNuevaCuentaController controller = loader.getController();
            controller.setRealizarMovimientoController(realizarMovimientoController);
            controller.setVistaPrincipalController(pricipalController);
            controller.setPersona(persona1);
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ventanaNuevoMovimiento(VistaPrincipalController vistaPrincipalController) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.VENTANA_SELECCION_MOVIMIENTO));
        try {
            AnchorPane pane = loader.load();
            RealizarMovimientoController controller2 = loader.getController();
            controller2.setVistaPrincipalController(vistaPrincipalController);
            controller2.setPersona(persona1);
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}


