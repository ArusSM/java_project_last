package views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.MainApp;
import util.JDBCUtil;
import util.Util;

public class RegisterController extends MasterController {
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private PasswordField pass;
	@FXML
	private PasswordField passConfirm;
	@FXML
	private TextArea txtInfo;

	@FXML
	private AnchorPane registerPage;

	public void register() {
		String id = txtId.getText().trim();
		String name = txtName.getText().trim();
		String strPass = pass.getText().trim();
		String confirm = passConfirm.getText().trim();
		String info = txtInfo.getText().trim();

		if (id.isEmpty() || name.isEmpty() || strPass.isEmpty() || info.isEmpty()) {
			Util.showAlert("비어있음", "필수 입력창이 비어있습니다.", AlertType.INFORMATION);
			return;
		}

		if (!strPass.equals(confirm)) {
			Util.showAlert("불일치", "비밀번호와 비밀번호 확인이 일치하지 않습니다.", AlertType.INFORMATION);
			return;
		}

		Connection con = JDBCUtil.getConnection(); // 데이터베이스 연결 가져오고
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlExist = "SELECT * FROM diary_users WHERE id = ?"; // 해당 유저가 존재하는지 확인하기 위한 sql
		String sqlInsert = "INSERT INTO diary_users(`id`, `name`, `pass`, `info`) VALUES (?, ?, PASSWORD(?), ?)";

		try {
			pstmt = con.prepareStatement(sqlExist); // 존재하는지 확인하는 sql을 준비하고
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 이미 다른 유저가 존재함.
				Util.showAlert("회원 중복", "이미 해당 id의 유저가 존재합니다. 다른 아이디를 사용하세요.", AlertType.INFORMATION);
				return;
			}

			JDBCUtil.close(pstmt);

			pstmt = con.prepareStatement(sqlInsert);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, strPass);
			pstmt.setString(4, info);

			if (pstmt.executeUpdate() != 1) {
				Util.showAlert("에러", "데이터베이스에 회원정보가 올바르게 입력되지 않았습니다. 입력값을 확인하세요.", AlertType.ERROR);
				return;
			}

			Util.showAlert("성공", "회원가입이 완료되었습니다. 로그인해주세요.", AlertType.INFORMATION);
			MainApp.app.slideOut(getRoot()); // 루트를 잡아서 슬라이드 아웃 시킴.

		} catch (Exception e) {
			e.printStackTrace();
			Util.showAlert("에러", "데이터베이스 연결중 오류가 발생했습니다. 인터넷 연결을 확인하세요.", AlertType.ERROR);
			return;
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}

	public void cancel() {
		MainApp.app.slideOut(getRoot()); // 루트를 잡아서 슬라이드 아웃 시킴.
	}
}