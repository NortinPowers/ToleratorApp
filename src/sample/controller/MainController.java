package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sample.Constant;
import sample.animations.Shake;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button fTolButton;

    @FXML
    private Button mTolButton;

    @FXML
    private Button cTolButton;

    @FXML
    private TextField enterField;

    @FXML
    private TextField outField;

    @FXML
    private TextField minOutField;

    @FXML
    private TextField maxOutField;

    @FXML
    private Button vTolButton;

    @FXML
    private Button ISOButtonGLRT;

    @FXML
    private Button ISOButtonGST;

    @FXML
    void initialize() {

        enterField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                if (fTolButton.isDefaultButton()) {
                    fTolButton.setDefaultButton(false);
                    mTolButton.setDefaultButton(true);
                } else if (mTolButton.isDefaultButton()) {
                    mTolButton.setDefaultButton(false);
                    cTolButton.setDefaultButton(true);
                } else if (cTolButton.isDefaultButton()) {
                    cTolButton.setDefaultButton(false);
                    vTolButton.setDefaultButton(true);
                } else if (vTolButton.isDefaultButton()) {
                    vTolButton.setDefaultButton(false);
                    fTolButton.setDefaultButton(true);
                }
            }
            if (event.getCode() == KeyCode.LEFT) {
                if (fTolButton.isDefaultButton()) {
                    fTolButton.setDefaultButton(false);
                    vTolButton.setDefaultButton(true);
                } else if (mTolButton.isDefaultButton()) {
                    mTolButton.setDefaultButton(false);
                    fTolButton.setDefaultButton(true);
                } else if (cTolButton.isDefaultButton()) {
                    cTolButton.setDefaultButton(false);
                    mTolButton.setDefaultButton(true);
                } else if (vTolButton.isDefaultButton()) {
                    vTolButton.setDefaultButton(false);
                    cTolButton.setDefaultButton(true);
                }
            }
        });

        fTolButton.setOnAction(event -> {
            f();
            fTolButton.setDefaultButton(true);
            mTolButton.setDefaultButton(false);
            cTolButton.setDefaultButton(false);
            vTolButton.setDefaultButton(false);
        });
        mTolButton.setOnAction(event -> {
            m();
            mTolButton.setDefaultButton(true);
            fTolButton.setDefaultButton(false);
            cTolButton.setDefaultButton(false);
            vTolButton.setDefaultButton(false);
        });
        cTolButton.setOnAction(event -> {
            c();
            cTolButton.setDefaultButton(true);
            mTolButton.setDefaultButton(false);
            fTolButton.setDefaultButton(false);
            vTolButton.setDefaultButton(false);
        });
        vTolButton.setOnAction(event -> {
            v();
            vTolButton.setDefaultButton(true);
            mTolButton.setDefaultButton(false);
            cTolButton.setDefaultButton(false);
            fTolButton.setDefaultButton(false);
        });

        ISOButtonGLRT.setOnAction(event -> {
            openScene("/sample/fxml/iso2.fxml");
        });

        ISOButtonGST.setOnAction(event -> {
            openScene("/sample/fxml/iso1.fxml");
        });

    }

    public void f() {
        double[] fA = Constant.fAccurance;
        UseSize(fA, Constant.fRange[0], Constant.fRange[1]);
    }

    public void m() {
        double[] mA = Constant.mAccurance;
        UseSize(mA, Constant.mRange[0], Constant.mRange[1]);
    }

    public void c() {
        double[] cA = Constant.cAccurance;
        UseSize(cA, Constant.cRange[0], Constant.cRange[1]);
    }

    public void v() {
        double[] vA = Constant.vAccurance;
        UseSize(vA, Constant.vRange[0], Constant.vRange[1]);
    }

    private void UseSize(double[] A, double sR, double eR) {
        outField.clear();
        minOutField.clear();
        maxOutField.clear();
        String size = enterField.getText().trim().replace(",", ".");

        try {
            double dSize = Double.parseDouble(size);
            if (dSize == 0.5 && sR == 0.5) {
                outField.appendText("+/- " + A[0]);
                minOutField.appendText(new Double(0.5 - A[0]).toString());
                maxOutField.appendText(new Double(0.5 + A[0]).toString());
            } else {
                if (dSize > sR && dSize <= eR) {
                    for (int i = 0; i < 12; i++) {
                        if (Constant.Range[i][0] < dSize && Constant.Range[i][1] >= dSize) {
                            outField.appendText("+/- " + A[i]);
                            minOutField.appendText("" + (dSize * 10000 - A[i] * 10000) / 10000);
                            maxOutField.appendText("" + (dSize * 10000 + A[i] * 10000) / 10000);
                            break;
                        }
                    }
                } else {
                    outField.clear();
                    outField.appendText("out of range");
                    ShakeAnim(enterField);
                }
            }
        } catch (NumberFormatException e) {
            outField.clear();
            outField.appendText("Invalid size");
            ShakeAnim(enterField);
        }
    }

    private void ShakeAnim(Node node) {
        Shake enFS = new Shake(node);
        enFS.playAnim();
    }

    private void openScene(String scene) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(scene));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:resources/images/icon.png"));
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.showAndWait();
    }
}

