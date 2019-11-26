package views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.MainApp;

public class BlockController extends MasterController {
	@FXML
	private Button nobtn;

	@FXML
	private Button exbtn;

	@FXML
	private ImageView img;

	private String noUrl;
	private String exUrl;

	public void setImg(int idx) {
		this.img.setImage(new Image("/resources/" + idx + ".png"));
		System.out.println(idx);
	}

	public void setButton(String noUrl, String exUrl) {
		this.noUrl = noUrl;
		this.exUrl = exUrl;
	}

	public void noBtnClick() {
		MainController mc = (MainController) MainApp.app.getController("main");
		mc.webV(noUrl);
	}

	public void exBtnClick() {
		MainController mc = (MainController) MainApp.app.getController("main");
		mc.webV(exUrl);
	}
}
