package com.game.hack3;

import com.game.hack3.DatabaseCode.QueryDatabase;
import com.game.hack3.DatabaseCode.UpdateDatabase;
import com.game.hack3.Enemies.EnemyCharacter;
import com.game.hack3.Enemies.Skeleton;
import com.game.hack3.Enemies.Slime;
import com.game.hack3.Enemies.Troll;
import com.game.hack3.Items.Armor;
import com.game.hack3.Items.Item;
import com.game.hack3.Items.Potion;
import com.game.hack3.Items.Weapon;
import com.game.hack3.ObjectIO.ReadDAT;
import com.game.hack3.ObjectIO.WriteDAT;
import com.game.hack3.PlayableCharacter.Character;
import com.game.hack3.PlayableCharacter.Inventory;
import com.game.hack3.TextIO.DisplayStats;
import com.game.hack3.TextIO.ReadTXT;
import com.game.hack3.Utility.CustomException;
import com.game.hack3.Utility.Randomizer;
import com.game.hack3.Utility.WrongInput;
import com.game.hack3.Startup.StartupCharacter;
import com.game.hack3.Startup.StartupEnemy;
import com.game.hack3.Startup.StartupInventory;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;


public class Game extends Application {

    public static class Launcher{
        public static void main(String[] args){
            Application.launch(Game.class, args);
        }
    }

    @Override
    public void start(Stage stage) {
        TheFirstEnemy.start();
        TheInventory.start();
        TheCharacter.start();
        stage1.setScene(new Scene(CreateContent()));
        stage1.setTitle("HACK v.3");
        stage1.getIcons().add(icon);
        stage1.show();
    }

    //Threads to help start the game.
    StartupInventory TheInventory = new StartupInventory();
    StartupCharacter TheCharacter = new StartupCharacter();
    StartupEnemy TheFirstEnemy = new StartupEnemy();

    //Initialize some global variables: Strings, Ints, Character, EnemyCharacter, and an array.
    public static String line = "----------------------------------------------------------------------------------" +
            "-----------";

    public static EnemyCharacter theEnemy;
    public static Character thePlayer;

    public static Item<Weapon> Sword = new Item<>(new Weapon(25), "Sword");
    public static Item<Armor> Shield = new Item<>(new Armor(5, "Handheld", 3), "Shield");
    public static Item<Potion> HealthPotion = new Item<>(new Potion(20), "Health Potion");

    public static int EnemiesKilled = 0;
    public static int PlayerAttacked = 0;
    public static int PotionsDrank = 0;
    public static int PlayerHP = 0;
    public static int ShieldsBroke = 0;
    public static int DamageToEnemies = 0;
    public static int DamageShielded = 0;

    public static List<EnemyCharacter> EnemiesFought = new ArrayList<>();
    public static String username;

    //Three stages are used throughout the game, this first stage is the main stage.
    Stage stage1 = new Stage();

    //Initialize images used within the game.
    Image player = new Image("file:images/PlayerCharacter1.png");
    Image troll = new Image("file:images/troll.png");
    Image skeleton = new Image("file:images/skeleton.png");
    Image slime = new Image("file:images/Slime.png");
    Image potion = new Image("file:images/HealthPotion.png");
    Image shield = new Image("file:images/Shield.png");
    Image sword = new Image("file:images/Sword.png");
    Image icon = new Image("file:images/HACK_Sword.png");

    ImageView playerView = new ImageView();
    ImageView enemyView = new ImageView();
    ImageView potionView = new ImageView();
    ImageView shieldView = new ImageView();
    ImageView swordView = new ImageView();

    //Font for multiple labels used.
    Font LabelFont = Font.font("Helvetica", 18);

    //Main text area and important labels.
    private static final TextArea output = new TextArea();
    private final Label LabelInventory = new Label();
    private final Label WhatsNext = new Label();

    //First GridPane that holds enemy and player information
    private final GridPane GraphicGridPane = new GridPane();
    private final Label playerHealth = new Label();
    private final Label enemyHealth = new Label();
    public final Label labelPlayer = new Label();
    public final Label labelEnemy = new Label();

    //Second GridPane that organizes all the inventory items.
    private final GridPane InventoryGridPane = new GridPane();
    private final Label PotionAmount = new Label();
    private final Label ShieldAmount = new Label();
    private final Label SwordAmount = new Label();
    private final Label PotionName = new Label("Health Potion: ");
    private final Label ShieldName = new Label("Shield: ");
    private final Label SwordName = new Label("Sword: ");

    //Third GridPane that organizes action buttons.
    private final GridPane ButtonGridPane = new GridPane();
    private final Button attackButton = new Button("Attack the Enemy");
    private final Button healButton = new Button("Drink Health Potion");
    private final Button nameButton = new Button("Set New Name");

    //Fourth GridPane that organizes data buttons.
    private final GridPane DataGridPane = new GridPane();
    private final Button EnemyDamageButton = new Button("Enemy Stats");
    private final Button PlayerCommonDefeat = new Button("Player Defeat Stats");
    private final Button LeaderBoard = new Button("HACK Leaderboard");

    //GridPane at the bottom of the Game window that displays buttons through the entire game.
    //Used a GridPane instead of a ButtonBar because I wanted things to be centered in the window.
    private final GridPane ImportantButtonGrid = new GridPane();
    private final Button enemyButton = new Button("List of Current Enemies");
    private final Button restartButton2 = new Button("Start a New Game");
    private final Button saveButton = new Button("Save Game");
    private final Button loadButton = new Button("Load Game");
    private final Button exitButton = new Button("Exit Game");

    //Background colors for the game.
    BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY);
    Background GreyBackground = new Background(backgroundFill);

    //Re-start button that only appears once the user health = 0.
    private final Button restartButton = new Button("Start a New Game");
    Label StartNew = new Label("Would you like to play again?");

    //Second stage that sets a new username.
    Stage stage2 = new Stage();
    private static final TextArea output2 = new TextArea();
    private final TextField input2 = new TextField();

    //Third stage that asks user to confirm if they want to exit the game.
    Stage stage3 = new Stage();

    //VBox for stage1
    private VBox CreateContent() {
        playerView.setImage(player);
        potionView.setImage(potion);
        shieldView.setImage(shield);
        swordView.setImage(sword);

        LabelInventory.setFont(LabelFont);
        WhatsNext.setFont(LabelFont);

        GraphicGridPane.setHgap(30);
        GraphicGridPane.setAlignment(Pos.CENTER);
        GraphicGridPane.setPadding(new Insets(5));
        GridPane.setHalignment(labelPlayer, HPos.CENTER);
        GridPane.setHalignment(enemyHealth, HPos.CENTER);
        GridPane.setHalignment(playerHealth, HPos.CENTER);
        GridPane.setHalignment(labelEnemy, HPos.CENTER);
        GraphicGridPane.add(labelPlayer, 1, 0);
        GraphicGridPane.add(labelEnemy, 4, 0);
        GraphicGridPane.add(playerHealth, 1, 1);
        GraphicGridPane.add(playerView, 2, 1);
        GraphicGridPane.add(enemyView, 3, 1);
        GraphicGridPane.add(enemyHealth, 4, 1);

        playerHealth.setFont(Font.font(25));
        enemyHealth.setFont(Font.font(25));

        labelEnemy.setFont(LabelFont);
        labelPlayer.setFont(LabelFont);

        InventoryGridPane.setHgap(10);
        InventoryGridPane.setAlignment(Pos.CENTER);
        InventoryGridPane.setPadding(new Insets(15));
        InventoryGridPane.add(PotionName, 1, 0);
        InventoryGridPane.add(potionView, 2, 0);
        InventoryGridPane.add(PotionAmount, 3, 0);
        InventoryGridPane.add(ShieldName, 8, 0);
        InventoryGridPane.add(shieldView, 9, 0);
        InventoryGridPane.add(ShieldAmount, 10, 0);
        InventoryGridPane.add(SwordName, 15, 0);
        InventoryGridPane.add(swordView, 16, 0);
        InventoryGridPane.add(SwordAmount, 17, 0);

        PotionAmount.setFont(Font.font(15));
        ShieldAmount.setFont(Font.font(15));
        SwordAmount.setFont(Font.font(15));
        PotionName.setFont(Font.font(12));
        ShieldName.setFont(Font.font(12));
        SwordName.setFont(Font.font(12));

        output.setPrefHeight(610-80);
        output.setFont(Font.font(14));
        output.setEditable(false);

        attackButton.setOnAction(click -> {
            AttackCmd();
            if(thePlayer.GetHealth() < 1){
                EndGameStats();
            }
            UpdateTable();
        } );

        healButton.setOnAction(click -> {
            HealCmd();
            if(thePlayer.GetHealth() < 1){
                EndGameStats();
            }
            UpdateTable();
        } );

        enemyButton.setOnAction(click -> EnemiesCmd());

        saveButton.setOnAction(click -> {
            SaveCmd();
            if(thePlayer.GetHealth() < 1){
                EndGameStats();
            }
            UpdateTable();
        });

        loadButton.setOnAction(click -> {
            LoadCmd();
            if(thePlayer.GetHealth() < 1){
                EndGameStats();
            }
            UpdateTable();
        });

        nameButton.setOnAction(click -> {
            SetNameCmd();
            if(thePlayer.GetHealth() < 1){
                EndGameStats();
            }
            UpdateTable();
        });

        EnemyDamageButton.setOnAction(click -> QueryDatabase.AverageEnemyStats());
        PlayerCommonDefeat.setOnAction(click -> QueryDatabase.PlayerDefeatedBy());
        LeaderBoard.setOnAction(click -> QueryDatabase.LeaderBoard());

        restartButton.setOnAction(click -> RestartCmd());
        restartButton2.setOnAction(click -> NewGameCmd());

        exitButton.setOnAction(click -> ExitCmd());

        ButtonGridPane.setAlignment(Pos.CENTER);
        ButtonGridPane.setHgap(15);
        ButtonGridPane.setPadding(new Insets(10));
        GridPane.setHalignment(attackButton, HPos.CENTER);
        GridPane.setHalignment(healButton, HPos.CENTER);
        ButtonGridPane.add(attackButton, 1, 0);
        ButtonGridPane.add(healButton, 2, 0);

        attackButton.setPadding(new Insets(10));
        healButton.setPadding(new Insets(10));

        attackButton.setStyle("-fx-font-size: 12");
        healButton.setStyle("-fx-font-size: 12");


        DataGridPane.setAlignment(Pos.CENTER);
        DataGridPane.setHgap(15);
        DataGridPane.setPadding(new Insets(5));
        DataGridPane.addRow(0, enemyButton, EnemyDamageButton, PlayerCommonDefeat, LeaderBoard);
        GridPane.setHalignment(enemyButton, HPos.CENTER);
        GridPane.setHalignment(EnemyDamageButton, HPos.CENTER);
        GridPane.setHalignment(PlayerCommonDefeat, HPos.CENTER);
        GridPane.setHalignment(LeaderBoard, HPos.CENTER);
        DataGridPane.setBackground(GreyBackground);


        ImportantButtonGrid.setAlignment(Pos.CENTER);
        ImportantButtonGrid.setHgap(15);

        ImportantButtonGrid.addRow(0, restartButton2, nameButton, saveButton, loadButton, exitButton);
        ImportantButtonGrid.setBackground(GreyBackground);
        ImportantButtonGrid.setPadding(new Insets(5));
        GridPane.setHalignment(nameButton, HPos.CENTER);
        GridPane.setHalignment(saveButton, HPos.CENTER);
        GridPane.setHalignment(loadButton, HPos.CENTER);
        GridPane.setHalignment(exitButton, HPos.CENTER);

        restartButton.setVisible(false);
        StartNew.setVisible(false);

        VBox root = new VBox(0, GraphicGridPane, output, LabelInventory, InventoryGridPane, WhatsNext,
                ButtonGridPane, StartNew, restartButton, DataGridPane, ImportantButtonGrid);
        root.setPadding(new Insets(20));
        root.setPrefSize(610, 850);
        root.setAlignment(Pos.CENTER);

        try{
            InitGame();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return root;
    }

    //VBox that opens at the beginning of the game for the player to input name, and when "Set Name" command is
    // initialized.
    private VBox SetName(){
        final Label EnterName = new Label("Please enter your name below.");
        final Label Clarification = new Label("Cannot be longer than 10 characters.\nPress 'Enter' to continue.");
        final Label NameWarning = new Label();


        EnterName.setFont(Font.font(18));
        Clarification.setFont(Font.font(12));
        NameWarning.setFont(Font.font(15));
        EnterName.setStyle("-fx-font-weight: bold");
        NameWarning.setStyle("-fx-text-fill: Red");

        input2.setOnAction(e -> {
            String InputName = input2.getText();
            input2.clear();
            if (InputName.isEmpty()){
                NameWarning.setText("You must input your name!");
                NameWarning.setVisible(true);
            }else{
                try {
                    NameInput(InputName);
                } catch (CustomException ex) {
                    ex.printStackTrace();
                    NameWarning.setText("That name is too long! Please try again.");
                    NameWarning.setVisible(true);
                }
            }
        });

        NameWarning.setVisible(false);
        VBox root = new VBox(10, EnterName, Clarification, NameWarning, input2);
        root.setPadding(new Insets(15));
        root.setPrefSize(400,150);
        root.setAlignment(Pos.CENTER);

        return root;
    }

    //VBox for stage3 (Also see StartNewAsk())
    private VBox ExitAsk(){
        final Label exitAsk = new Label("Are you sure you want to exit HACK?");
        final Label warning = new Label("Any unsaved progress will be lost.");
        final Button yes = new Button("Exit HACK");
        final Button no = new Button("Return to HACK");
        final GridPane gridPane = new GridPane();

        yes.setOnAction(click ->{
            stage1.close();
            stage3.close();
        } );

        return GetWarningVBox(exitAsk, warning, yes, no, gridPane, stage3);
    }

    //Gets the warning Vbox that appears whenever the user wants to start new game or exit HACK.
    private VBox GetWarningVBox(Label Ask, Label warning, Button yes, Button no, GridPane gridPane, Stage stage3) {
        no.setOnAction(click -> stage3.close());

        Ask.setFont(Font.font(18));
        warning.setFont(Font.font(15));
        warning.setStyle("-fx-text-fill: Red");
        Ask.setStyle("-fx-font-weight: bold");

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        gridPane.addRow(1, yes, no);
        VBox root = new VBox(15, Ask, warning, gridPane);
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(450, 175);
        root.setPadding(new Insets(15));

        return root;
    }

    //Asks the user if they are sure they want to start a new game.
    private VBox StartNewAsk(){
        final Label StartAsk = new Label("Are you sure you want to start a new game?");
        final Label warning = new Label("Any unsaved progress will be lost.");
        final Button yesNew = new Button("Start New Game");
        final Button noNew = new Button("Return to Game");
        final GridPane gridPane = new GridPane();

        StartAsk.setFont(Font.font(18));
        warning.setFont(Font.font(15));
        warning.setStyle("-fx-text-fill: Red");
        StartAsk.setStyle("-fx-font-weight: bold");

        yesNew.setOnAction(click -> {
            stage3.close();
            RestartCmd();
        });

        return GetWarningVBox(StartAsk, warning, yesNew, noNew, gridPane, stage3);
    }

    //Starts the game in the JavaFX VBox.
    private void InitGame() throws SQLException{
        PrintList(ReadTXT.GetIntro());
        EnemiesFought.add(theEnemy);
        UpdateTable();
        PrintLine(theEnemy.Summon());
        PrintLine(line);
    }

    //This method updates all the stats within stage1, including the GridPanes for inventory and user/enemy health.
    private void UpdateTable(){
        if (Objects.equals(theEnemy.EnemyName(), "Troll")) {
            enemyView.setImage(troll);
        } else if (Objects.equals(theEnemy.EnemyName(), "Skeleton")) {
            enemyView.setImage(skeleton);
        } else if (Objects.equals(theEnemy.EnemyName(), "Slime")) {
            enemyView.setImage(slime);
        }
        playerHealth.setText(String.valueOf(thePlayer.GetHealth()));
        enemyHealth.setText(String.valueOf(theEnemy.GetHealth()));
        labelEnemy.setText(theEnemy.EnemyName() + "'s health:");
        labelPlayer.setText(ReadDAT.LoadName() + "'s health:");
        PotionAmount.setText("x " + Inventory.NumberOfItem("Health Potion"));
        ShieldAmount.setText("x " + Inventory.NumberOfItem("Shield"));
        SwordAmount.setText("x " + Inventory.NumberOfItem("Sword"));
        LabelInventory.setText(ReadDAT.LoadName() + "'s Inventory: ");
        WhatsNext.setText("What would you like to do?");
        if (Inventory.NumberOfItem("Health Potion") == 0){
            potionView.setVisible(false);
            PotionAmount.setVisible(false);
            PotionName.setVisible(false);
            healButton.setVisible(false);
        }else{
            potionView.setVisible(true);
            PotionAmount.setVisible(true);
            PotionName.setVisible(true);
            healButton.setVisible(true);
        }
        if (thePlayer.GetHealth() < 1){
            StartNew.setVisible(true);
            StartNew.setFont(Font.font(15));
            healButton.setVisible(false);
            attackButton.setVisible(false);
            WhatsNext.setText("GAME OVER");
            WhatsNext.setFont(Font.font(40));
            WhatsNext.setTextFill(Color.RED);
            restartButton.setVisible(true);
            restartButton.setPadding(new Insets(10));
            restartButton.setStyle("-fx-border-color: Green; -fx-border-width: 3px");
        }
        if (!Inventory.InInventory("Shield")){
            shieldView.setVisible(false);
            ShieldAmount.setVisible(false);
            ShieldName.setVisible(false);
        }else{
            shieldView.setVisible(true);
            ShieldAmount.setVisible(true);
            ShieldName.setVisible(true);
        }
    }

    //A way to write strings easily into the main VBox.
    public static void PrintLine(String line){
        output.appendText(line + "\n");
    }

    //A way to print lists into the main VBox.
    private static <T> void PrintList(List<T> aList){
        aList.forEach((name) -> output.appendText(name + "\n"));
    }

    //Takes the string from the second window provided and updates the Player name.
    private void NameInput(String string) throws CustomException {
        WrongInput.GetLength(string);
        WriteDAT.SaveName(string);
        UpdateTable();
        output2.clear();
        stage2.close();
    }

    //Command now opens a new window that asks the user if they are sure they want to exit the game.
    private void ExitCmd(){
        stage3.setTitle("Exit HACK");
        stage3.setScene(new Scene(ExitAsk()));
        stage3.getIcons().add(icon);
        stage3.show();
    }

    //Command that opens a new window that asks if the user is sure they want to start a new game.
    private void NewGameCmd(){
        stage3.setTitle("Start a New Game");
        stage3.setScene(new Scene(StartNewAsk()));
        stage3.getIcons().add(icon);
        stage3.show();
    }

    //User attacks the enemy. Depending on user health and enemy health, more methods are implemented.
    private void AttackCmd() {
        FutureTask<Integer> FutureAttack = new FutureTask<>(new AttackCallable());
        Thread aThread = new Thread(FutureAttack);
        aThread.start();
        PrintLine("You attack the Enemy!");
        PrintLine(line);
        theEnemy.Attacked(Sword.Get().DamageStat());
        DamageToEnemies += Sword.Get().DamageStat();
        if (theEnemy.GetHealth() < 1) {
            PrintLine("The enemy has been defeated!");
            EnemiesKilled += 1;
            if (EnemyCharacter.PotionDrop()) {
                PrintLine("\tThe enemy dropped a potion!");
            }
            PrintLine(line);
            PotentialShield();
            GetAnEnemy();
            EnemiesFought.add(theEnemy);
            PrintLine(theEnemy.Summon());
            PrintLine(line);
        }else{
            PrintLine(theEnemy.EnemyAttack());
            PrintLine(line);
            try{
                thePlayer.SetHealth(FutureAttack.get());

                UseShield();
                PlayerAttacked += 1;
                if (Shield.Get().GetDurability() == 0){
                    PrintLine("Your shield has broken!");
                    ShieldsBroke += 1;
                    PrintLine("\tThe enemy will now do 5 more damage to you.");
                    PrintLine(line);
                    Inventory.RemoveItem(Shield.GetName());
                    Shield.Get().ResetDurability();
                }
            }catch (ExecutionException | InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    //Depending on certain circumstances, a shield is dropped by the enemy.
    private void PotentialShield(){
        if (!Inventory.InInventory("Shield")) {
             if (EnemyCharacter.ShieldDrop()){
                 PrintLine("\tThe enemy dropped a shield!");
                 PrintLine("\t\tThe enemy will do 5 less damage to you.");

             }
        }
    }

    //If the user has a shield available, that shield is automatically used.
    private void UseShield(){
        if (Inventory.InInventory("Shield")){
            Shield.Get().Durability();
            DamageShielded += 5;
        }
    }

    //User drinks a health potion.
    private void HealCmd(){
        PrintLine("You drink a health potion!");
        thePlayer.Heal(HealthPotion.Get().HealStat());
        PotionsDrank += 1;
        PrintLine(line);
    }

    //User saves their current game data.
    private void SaveCmd(){
        WriteDAT.SaveToFile(ReadDAT.LoadName(),thePlayer.GetHealth(), theEnemy, EnemiesKilled, PlayerAttacked,
                PotionsDrank, Slime.GetAmount(), Troll.GetAmount(), Skeleton.GetAmount(), Shield.Get().GetDurability(),
                ShieldsBroke, DamageToEnemies, DamageShielded, Inventory.GetInventory(), EnemiesFought);
        PrintLine("You have successfully saved the game data.");
    }

    //User loads their previous saved game data.
    private void LoadCmd(){
        EnemiesFought.clear();
        ReadDAT.LoadFromFile();
        PrintLine("You have successfully loaded the previous game file.");
        PrintLine("\tWelcome back, " + username + "!");
        WriteDAT.SaveName(username);
        thePlayer = new Character(PlayerHP);
    }

    //User sees a list of enemies they have fought during that game.
    private void EnemiesCmd(){
        List<EnemyCharacter> AllofThem = EnemiesFought.stream().
                sorted(Comparator.comparing(EnemyCharacter::OriginalHealth)).collect(Collectors.toList());
        PrintLine("Here is a list of enemies you have fought: \n");
        PrintList(AllofThem);
        PrintLine(line);
    }

    //User can set a new name.
    private void SetNameCmd() {
        stage2.setTitle("Set Player Name");
        stage2.setScene(new Scene(SetName()));
        stage2.getIcons().add(icon);
        stage2.show();
    }

    //User can restart their game, once they have been defeated.
    private void RestartCmd() {
        output.clear();
        EnemiesFought.clear();
        EnemiesKilled = 0;
        PlayerAttacked = 0;
        PotionsDrank = 0;
        PlayerHP = 0;
        ShieldsBroke = 0;
        DamageShielded = 0;
        DamageToEnemies = 0;
        Shield.Get().ResetDurability();
        Skeleton.TimesPrevious = 0;
        Skeleton.DamageDone = 0;
        Slime.TimesPrevious = 0;
        Slime.DamageDone = 0;
        Troll.TimesPrevious = 0;
        Troll.DamageDone = 0;
        Inventory.ClearInventory();
        Inventory.AddItem(Game.Sword.GetName());
        Inventory.AddItem(Game.Shield.GetName());
        Inventory.AddItem(Game.HealthPotion.GetName());
        Inventory.AddItem(Game.HealthPotion.GetName());
        GetAnEnemy();
        thePlayer = new Character(100);
        WhatsNext.setFont(LabelFont);
        WhatsNext.setTextFill(Color.BLACK);
        StartNew.setVisible(false);
        attackButton.setVisible(true);
        try{
            InitGame();
        } catch (SQLException e){
            e.printStackTrace();
        }
        restartButton.setVisible(false);
    }

    //Prints out the stats to a .txt file
    private void EndGameStats(){
        DisplayStats.PrintStats(EnemiesKilled, Slime.GetAmount(), Skeleton.GetAmount(), Troll.GetAmount(),
                PlayerAttacked, theEnemy.EnemyName(), PotionsDrank, ShieldsBroke);
        PrintLine(line);
        try{
            PrintList(ReadTXT.GetStats());
            UpdateDatabase.PlayerTableData();
            UpdateDatabase.EnemyTableData();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //Randomizes an enemy each time an enemy dies
    public static void GetAnEnemy(){
        List<EnemyCharacter> Enemies = Arrays.asList(new Slime(Randomizer.LevelOneHealth()),
                new Skeleton(Randomizer.LevelOneHealth()), new Troll(Randomizer.LevelOneHealth()));
        theEnemy = Randomizer.anyItem(Enemies);
    }

}