package net.parwand.springregister.domain.model.zins;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Parwand Alsino
 * 26.09.2022
 */
class ZinsTest {

    @Test
    @DisplayName("Mit Anfangskapital 5000 €, 3 Jahren Laufzeit  und 5 % Zinssatz soll Endkapital 5788.13€ betragen")
    void test_1() {
        // Arrange
        Zins zins = new Zins();
        zins.setAnfangskapital(5000);
        zins.setLaufzeit(3);
        zins.setZinssatz(5);
        // Act
        zins.zinsenBerechnen();
        double result = zins.getEndkapital();
        // Assert
        assertThat(result).isEqualTo(5788.13);
    }

    @Test
    @DisplayName("Mit Anfangskapital 5000 €, 3 Jahren Laufzeit  und 5 % Zinssatz sollen Zinsen 275.63€ betragen ")
    void test_2() {
        // Arrange
        Zins zins = new Zins();
        zins.setAnfangskapital(5000);
        zins.setLaufzeit(3);
        zins.setZinssatz(5);
        // Act
        zins.zinsenBerechnen();
        double result = zins.getZinsen();
        // Assert
        assertThat(result).isEqualTo(275.63);
    }
}