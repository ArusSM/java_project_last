package views;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import main.MainApp;
import util.Util;

public class MainController extends MasterController {

	@FXML
	private GridPane gridBlock;
	private int idx = 0;
	@FXML
	private Label loginInfo;

	@FXML
	private WebView web;

	private WebEngine browser;

	@FXML
	private void initialize() {
		List<String> nolist = new ArrayList<>();
		nolist.add("https://youtu.be/irlR8f7GaNE");
		nolist.add("https://youtu.be/Gj9BT7c9aM0");
		nolist.add("https://youtu.be/_gLA72ydRR8");
		nolist.add("https://youtu.be/fUzlY91-eq0");
		nolist.add("https://youtu.be/CfcGIQSvgsE");
		nolist.add("https://youtu.be/Fy_M2yWPfbY");
		nolist.add("https://youtu.be/wtdIyQDbTy4");
		nolist.add("https://youtu.be/wQQGp7sWKPU");
		nolist.add("https://youtu.be/PTbCnDZjgBo");
		nolist.add("https://youtu.be/91dwPHneeDQ");
		nolist.add("https://youtu.be/goqejnvtE_Y");
		nolist.add("https://youtu.be/jQtWlNdMBso");
		nolist.add("https://youtu.be/s-g1YlJbsGw");
		nolist.add("https://youtu.be/u3EBE1JbzOM");
		nolist.add("https://youtu.be/XBulCQIMpZk");
		nolist.add("https://youtu.be/7yfcVovULcE");
		nolist.add("https://youtu.be/6ib6QeMeby0");
		nolist.add("https://youtu.be/eWyJxPxyWE8");
		nolist.add("https://youtu.be/k32PY2Xi-KA");
		nolist.add("https://youtu.be/7MxekA2GDmE");
		List<String> exlist = new ArrayList<>();
		exlist.add("https://youtu.be/1yQvpsIW1UA");
		exlist.add("https://youtu.be/LNGfYScNK3w?");
		exlist.add("https://youtu.be/QYOb0KrHYV4");
		exlist.add("https://youtu.be/63L8Ln1phdM");
		exlist.add("https://youtu.be/CfcGIQSvgsE");
		exlist.add("https://youtu.be/2mAHSk52anU");
		exlist.add("https://youtu.be/wtdIyQDbTy4");
		exlist.add("https://youtu.be/wQQGp7sWKPU");
		exlist.add("https://youtu.be/1uB5DuluyMs");
		exlist.add("https://youtu.be/ApZzNrIOW2w");
		exlist.add("https://youtu.be/_WIRxBNNnbc");
		exlist.add("https://youtu.be/L0j3q92Xk7c");
		exlist.add("https://youtu.be/ZhWSsfVwoyQ");
		exlist.add("https://youtu.be/k0zNkTM-poA");
		exlist.add("https://youtu.be/xIiiQsKu1qg");
		exlist.add("https://youtu.be/7yfcVovULcE");
		exlist.add("https://youtu.be/6ib6QeMeby0");
		exlist.add("https://youtu.be/eWyJxPxyWE8");
		exlist.add("https://youtu.be/k32PY2Xi-KA");
		exlist.add("https://youtu.be/7MxekA2GDmE");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				this.idx++;
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/views/BlockLayout.fxml"));
				try {
					AnchorPane ap = loader.load();
					gridBlock.add(ap, j, i);
					BlockController bc = loader.getController();
					bc.setImg(idx);
					bc.setButton(nolist.get(idx - 1), exlist.get(idx - 1));

					bc.setRoot(ap);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.printf("j : %d, i : %d 번째 그리는 중 오류 발생\n", j, i);
					Util.showAlert("에러", "공략의 각 칸을 그리는 중 오류가 발생", AlertType.ERROR);
				}
			}

		}
		browser = web.getEngine();
	}

	public void logout() {
		MainApp.app.loadPane("login");
	}

	public void webV(String url) {
		browser.load(url);
	}
}
