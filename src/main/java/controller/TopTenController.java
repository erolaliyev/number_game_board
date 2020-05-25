package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import numbersgameboard.results.GameResultDao;
import numbersgameboard.results.GameResultGAL7RQ;

import java.io.IOException;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
public class TopTenController {

    @FXML
    private TableView<GameResultGAL7RQ> toptenTable;

    @FXML
    private TableColumn<GameResultGAL7RQ, String> player;

    @FXML
    private TableColumn<GameResultGAL7RQ, Integer> steps;

    @FXML
    private TableColumn<GameResultGAL7RQ, Duration> duration;

    @FXML
    private TableColumn<GameResultGAL7RQ, ZonedDateTime> created;

    private GameResultDao gameResultDao;
//
    public void back(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/launch.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        log.info("Loading launch scene.");
    }


    @FXML
    public void initialize() {
        gameResultDao = GameResultDao.getInstance();

        List<GameResultGAL7RQ> toptenList = gameResultDao.findBest(10);

        player.setCellValueFactory(new PropertyValueFactory<>("player"));
        steps.setCellValueFactory(new PropertyValueFactory<>("steps"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        created.setCellValueFactory(new PropertyValueFactory<>("created"));


        duration.setCellFactory(column -> {
            TableCell<GameResultGAL7RQ, Duration> cell = new TableCell<GameResultGAL7RQ, Duration>() {

                @Override
                protected void updateItem(Duration item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(DurationFormatUtils.formatDuration(item.toMillis(),"H:mm:ss"));
                    }
                }
            };

            return cell;
        });

        created.setCellFactory(column -> {
            TableCell<GameResultGAL7RQ, ZonedDateTime> cell = new TableCell<GameResultGAL7RQ, ZonedDateTime>() {
                private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss Z");

                @Override
                protected void updateItem(ZonedDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(item.format(formatter));
                    }
                }
            };

            return cell;
        });

        ObservableList<GameResultGAL7RQ> observableResult = FXCollections.observableArrayList();
        observableResult.addAll(toptenList);

        toptenTable.setItems(observableResult);
    }

}
