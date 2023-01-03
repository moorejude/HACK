module com.game.hack {

    requires javafx.controls;
    requires javafx.base;
    requires javafx.graphics;
    requires java.sql;

    opens com.game.hack3;
    exports com.game.hack3;
    exports com.game.hack3.Startup;
    exports com.game.hack3.Enemies;
    exports com.game.hack3.Items;
    exports com.game.hack3.PlayableCharacter;
    exports com.game.hack3.ObjectIO;
    exports com.game.hack3.TextIO;
    exports com.game.hack3.Utility;
    exports com.game.hack3.DatabaseCode;
}