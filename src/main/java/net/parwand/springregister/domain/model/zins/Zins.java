package net.parwand.springregister.domain.model.zins;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Zins {

    @NotNull
    @DecimalMin(value = "0.0", message = "Muss positiv sein!")
    private double anfangskapital;
    @NotNull
    @Min(value = 1, message = "Muss größer als 0 sein")
    public int laufzeit;
    @NotNull
    @DecimalMin(value = "0.0", message = "Muss positiv sein!")
    private double zinssatz;
    private int jahr;
    private double zinsen;
    private double endkapital;
    private boolean checked;

    public List<Zins> zinsList = new ArrayList<>();

    public Zins(double anfangskapital,
                int laufzeit,
                double zinssatz,
                int jahr,
                double zinsen,
                double endkapital,
                boolean checked,
                List<Zins> zinsList) {
        this.anfangskapital = anfangskapital;
        this.laufzeit = laufzeit;
        this.zinssatz = zinssatz;
        this.jahr = jahr;
        this.zinsen = zinsen;
        this.endkapital = endkapital;
        this.checked = checked;
        this.zinsList = zinsList;
    }

    public Zins() {
    }

    public void zinsenBerechnen() {
        this.zinsen = 0;
        this.zinsList.clear();
        this.jahr = 0;
        double anfangskapitalExchange = this.anfangskapital;
        for (int i = 1; i <= laufzeit; i++) {
            this.jahr++;

            zinsen = anfangskapitalExchange * (this.zinssatz / 100);
            this.endkapital = (anfangskapitalExchange + zinsen);

            this.endkapital = (double) Math.round(this.endkapital * 100) / 100;
            anfangskapitalExchange = (double) Math.round(anfangskapitalExchange * 100) / 100;
            zinsen = (double) Math.round(zinsen * 100) / 100;

            this.zinsList.add(new Zins(anfangskapitalExchange, this.laufzeit,
                    this.zinssatz,
                    this.jahr, zinsen,
                    this.endkapital,
                    this.checked,
                    this.zinsList));
            anfangskapitalExchange += zinsen;
        }
    }

    public double getAnfangskapital() {
        return anfangskapital;
    }

    public void setAnfangskapital(double anfangskapital) {
        this.anfangskapital = anfangskapital;
    }

    public int getLaufzeit() {
        return laufzeit;
    }

    public void setLaufzeit(int laufzeit) {
        this.laufzeit = laufzeit;
    }

    public double getZinssatz() {
        return zinssatz;
    }

    public void setZinssatz(double zinssatz) {
        this.zinssatz = zinssatz;
    }

    public int getJahr() {
        return jahr;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }

    public double getZinsen() {
        return zinsen;
    }

    public void setZinsen(double zinsen) {
        this.zinsen = zinsen;
    }

    public double getEndkapital() {
        return endkapital;
    }

    public void setEndkapital(double endkapital) {
        this.endkapital = endkapital;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<Zins> getZinsList() {
        return zinsList;
    }

    public void setZinsList(List<Zins> zinsList) {
        this.zinsList = zinsList;
    }
}
