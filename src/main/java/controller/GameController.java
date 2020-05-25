package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import numbersgameboard.results.GameResultDao;
import numbersgameboard.results.GameResultGAL7RQ;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

@Slf4j
public class GameController {

    private GameResultDao gameResultDao;

    @FXML
    private Label usernameLabel;
//
    @FXML
    private GridPane gameGrid;
//
    @FXML
    private Label stepLabel;
//
    @FXML
    private Label solvedLabel;

    @FXML
    private Button doneButton;

    @FXML
    private Circle Stone;


    private Label clickedLabel;
    private int numOfMoves;
    private int oldClickedColumn = 0;
    private int oldClickedRow = 0;
    private String userName;
    private int stepCount;
    private Instant beginGame;
    int clickedColumn = 0;
    int clickedRow = 0;

    public void initdata(String userName) {
        this.userName = userName;
        usernameLabel.setText("Current user: " + this.userName);
    }
    private static int getRandom() {
        int rnd = new Random().nextInt(4);
        return rnd+1;
    }

    private void drawGame(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Label view = (Label) gameGrid.getChildren().get(i * 8 + j);
                view.setText(String.valueOf(getRandom()));
            }
        }
        int diag[] = {10,35,50,14,25,37,42,55,13,28,33};
        for (int num:diag){
            Label view = (Label) gameGrid.getChildren().get(num);
            view.setBorder(new Border(new BorderStroke(Paint.valueOf("#000"), BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        }
    }

    @FXML
    public void initialize() {
        gameResultDao = GameResultDao.getInstance();

        GridPane.setColumnIndex(Stone, 0);
        GridPane.setRowIndex(Stone,0);

        stepCount = 0;
        stepLabel.setText(String.valueOf(stepCount));

        beginGame = Instant.now();

        drawGame();

        clickedLabel = (Label) gameGrid.getChildren().get(0);
        numOfMoves = Integer.valueOf(clickedLabel.getText());
        clickedColumn = 0;
        clickedRow = 0;
        oldClickedRow = 0;
        oldClickedColumn = 0;

        Label last = (Label)gameGrid.getChildren().get(63);
        last.setText("*");

        System.out.println(numOfMoves);
    }

    public void numberClick(MouseEvent mouseEvent) {
        Stone.setVisible(true);
        if (GridPane.getColumnIndex((Node)mouseEvent.getSource()) != null && GridPane.getRowIndex((Node)mouseEvent.getSource()) != null ) {
            clickedColumn = GridPane.getColumnIndex((Node)mouseEvent.getSource());
            clickedRow = GridPane.getRowIndex((Node)mouseEvent.getSource());
        }
        else if (GridPane.getColumnIndex((Node)mouseEvent.getSource()) == null && GridPane.getRowIndex((Node)mouseEvent.getSource()) == null){
            clickedColumn = 0;
            clickedRow = 0;
        }
        else if (GridPane.getColumnIndex((Node)mouseEvent.getSource()) == null){
            clickedColumn = 0;
            clickedRow = GridPane.getRowIndex((Node)mouseEvent.getSource());
        }
        else if (GridPane.getRowIndex((Node)mouseEvent.getSource()) == null){
            clickedColumn = GridPane.getColumnIndex((Node)mouseEvent.getSource());
            clickedRow = 0;
        }
        else{
            System.out.println("Invalid Move!");
        }

        if (clickedLabel.getBorder() != null){
            System.out.println("BORDER HIT");
            System.out.println(oldClickedColumn + " , " + clickedColumn);
            if(Math.abs(oldClickedColumn - clickedColumn) == numOfMoves && Math.abs(oldClickedRow - clickedRow) == numOfMoves) {
                oldClickedColumn = clickedColumn;
                oldClickedRow = clickedRow;
                GridPane.setRowIndex(Stone,clickedRow);
                GridPane.setColumnIndex(Stone,clickedColumn);
                clickedLabel = (Label) gameGrid.getChildren().get(clickedColumn * 8 + clickedRow);
                if (clickedLabel.getText() == "*"){
                    numOfMoves = 0;
                }
                else{
                    numOfMoves = Integer.valueOf(String.valueOf(clickedLabel.getText()));
                }
                stepCount++;
//                System.out.println("Diag" + numOfMoves);
                System.out.println(clickedLabel.getBorder() +  " " + numOfMoves);
            }
        }
        if (clickedLabel.getBorder() == null){
            if (Math.abs(oldClickedRow - clickedRow) == numOfMoves && oldClickedColumn == clickedColumn){ //CHANGING THE ROW
                oldClickedColumn = clickedColumn;
                oldClickedRow = clickedRow;
//                System.out.println("Row Moved from " + oldClickedRow + " to " + clickedRow);
                GridPane.setRowIndex(Stone,clickedRow);
                clickedLabel = (Label) gameGrid.getChildren().get(clickedColumn * 8 + clickedRow);
                if (clickedLabel.getText() == "*"){
                    numOfMoves = 0;
                }
                else{
                    numOfMoves = Integer.valueOf(String.valueOf(clickedLabel.getText()));
                }
                stepCount++;
//                System.out.println("New: " + numOfMoves);
                System.out.println(clickedLabel.getBorder() +  " " + numOfMoves);
            }
            else if (Math.abs(oldClickedColumn - clickedColumn) == numOfMoves && oldClickedRow == clickedRow){ //CHANGING COLUMN
                oldClickedColumn = clickedColumn;
//                System.out.println("Column Moved from " + oldClickedColumn + " to " + clickedColumn);
                GridPane.setColumnIndex(Stone,clickedColumn);
                clickedLabel = (Label) gameGrid.getChildren().get(clickedColumn * 8 + clickedRow);
                if (clickedLabel.getText() == "*"){
                    numOfMoves = 0;
                }
                else{
                    numOfMoves = Integer.valueOf(String.valueOf(clickedLabel.getText()));
                }
                stepCount++;
//                System.out.println("New: " + numOfMoves);
                System.out.println(clickedLabel.getBorder() +  " " + numOfMoves);
            }
        }



        stepLabel.setText(String.valueOf(stepCount));
//        System.out.println("numOfMoves: " + numOfMoves);
        isSolved();
    }

    @FXML
    public boolean isSolved(){
        if (GridPane.getColumnIndex(Stone) == 7 && GridPane.getRowIndex(Stone) == 7){
            Stone.setDisable(true);
            doneButton.setText("Finish Game");
            solvedLabel.setText("You Won!");
            return true;
        }
        return false;
    }
    public void resetGame(ActionEvent actionEvent) {
        initialize();
        stepCount = 0;
        solvedLabel.setText("");
        beginGame = Instant.now();
        log.info("Game reset.");
    }

    private GameResultGAL7RQ getResult() {

        GameResultGAL7RQ result = GameResultGAL7RQ.builder()
                .player(userName)
                .solved(isSolved())
                .duration(Duration.between(beginGame, Instant.now()))
                .steps(stepCount)
                .build();
        return result;
    }
//
    public void finishGame(ActionEvent actionEvent) throws IOException {
        if (isSolved()) {
            gameResultDao.persist(getResult());
        }

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/topten.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        log.info("Finished game, loading Top Ten scene.");
    }
}
