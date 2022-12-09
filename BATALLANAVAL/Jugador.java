import java.util.ArrayList;

public class Jugador{
    private Integer Id;
    private String nombreJugador;
    private Integer barcosTotales;
    private Integer barcosDestruidos;
    public ArrayList<Barco> barcosCoordenadas = new ArrayList<Barco>();
    private Integer puntaje;

    // Id Jugador
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    // Nombre Jugador
    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    //Barcos Totales
    public Integer getBarcosTotales() {
        return barcosTotales;
    }

    public void setBarcosTotales(Integer barcosTotales) {
        this.barcosTotales = barcosTotales;
    }

    // Barcos Destruidos
    public Integer getBarcosDestruidos() {
        return barcosDestruidos;
    }

    public void setBarcosDestruidos(Integer barcosDestruidos) {
        this.barcosDestruidos = barcosDestruidos;
    }

    // Puntaje
    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }
}