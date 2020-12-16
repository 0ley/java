/*
	作者：熊志豪
	作用：管理学院管理员界面
*/


package registrationinformationmanagement;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
class ManagementAdministrator extends Login{
	private TableView<AcademyAdmin> tView;
	private ObservableList<AcademyAdmin> list;
	private TextField userNameTf,passwordTf,telephoneTf,academyTf;
	private Button addBtn, updateBtn, deleteBtn;
	public Pane getManagementPane() {
		BorderPane pane_management = new BorderPane();
		pane_management.setPadding(new Insets(40, 10, 10, 0));
		// ************创建表视图
		tView = new TableView<AcademyAdmin>();
				
		TableColumn<AcademyAdmin, String> userNameColumn = new TableColumn<AcademyAdmin, String>("userName");
		TableColumn<AcademyAdmin, String> passwordColumn = new TableColumn<AcademyAdmin, String>("password");
		TableColumn<AcademyAdmin, String> telephoneColumn = new TableColumn<AcademyAdmin, String>("telephone");
		TableColumn<AcademyAdmin, String> academyColumn = new TableColumn<AcademyAdmin, String>("academy");
		
		// 设置列宽
		userNameColumn.setPrefWidth(150);
		passwordColumn.setPrefWidth(150);
		telephoneColumn.setPrefWidth(150);
		academyColumn.setPrefWidth(150);
		tView.getColumns().addAll(userNameColumn,passwordColumn, telephoneColumn,academyColumn);
		tView.setStyle("-fx-font: 18.5 arial; -fx-base: white;");
		//tView.setMinWidth(500);
		//tView.setPrefWidth(5);
		// *****创建表视图中的数据（数据存放在ObservableList列表中）
		Access_AthleteDB.Connect();
		list = FXCollections.observableArrayList();
		list.addAll(Access_AthleteDB.getRecordsAcademyAdmin("select * from academy_admin"));
		Access_AthleteDB.closeConnection();
				
		userNameColumn.setCellValueFactory(new PropertyValueFactory<AcademyAdmin, String>("userName"));
		userNameColumn.setMinWidth(200);
		//userNameColumn.setPrefWidth(50);
		passwordColumn.setCellValueFactory(new PropertyValueFactory<AcademyAdmin, String>("password"));
		passwordColumn.setMinWidth(200);
		telephoneColumn.setCellValueFactory(new PropertyValueFactory<AcademyAdmin, String>("telephone"));
		telephoneColumn.setMinWidth(200);
		academyColumn.setCellValueFactory(new PropertyValueFactory<AcademyAdmin, String>("academy"));
		academyColumn.setMinWidth(200);
		tView.setItems(list);
		
		// *************创建底部包含标签，文本框和按钮的水平面板
		VBox bottomVPane=new VBox(3);
		HBox bottomHPane1 = new HBox(4);
		HBox bottomHPane2 = new HBox(4);
		HBox bottomHPane3 = new HBox(3);
		bottomHPane1.setPadding(new Insets(10,0,0,0));
		bottomVPane.setAlignment(Pos.CENTER);
		bottomHPane1.setAlignment(Pos.CENTER);
		bottomHPane2.setAlignment(Pos.CENTER);
		bottomHPane3.setAlignment(Pos.CENTER);
		Label userNameLabel = new Label("userName: ");
		userNameLabel.setTextFill(Color.WHITE);
		Label passwordLabel = new Label("password: ");
		passwordLabel.setTextFill(Color.WHITE);
		Label telephoneLabel = new Label("telephone: ");
		telephoneLabel.setTextFill(Color.WHITE);
		Label academyLabel = new Label("academy: ");
		academyLabel.setTextFill(Color.WHITE);
		userNameTf = new TextField();
		passwordTf = new TextField();
		telephoneTf = new TextField();
		academyTf = new TextField();
		addBtn = new Button("添加");
		updateBtn = new Button("修改");
		deleteBtn = new Button("删除");
		bottomHPane1.getChildren().addAll(userNameLabel, userNameTf, passwordLabel, passwordTf);
		bottomHPane2.getChildren().addAll(telephoneLabel,telephoneTf, academyLabel, academyTf);
		bottomHPane3.getChildren().addAll(addBtn,updateBtn,deleteBtn);
		bottomHPane3.setPadding(new Insets(10,0,0,0));
		bottomVPane.getChildren().addAll(bottomHPane1,bottomHPane2,bottomHPane3);
		//bottomHPane.getChildren().addAll( userNameLabel, userNameTf, passwordLabel, passwordTf, telephoneLabel, telephoneTf, careerLabel,careerTf,addBtn, updateBtn, deleteBtn);

		// **************将表视图和水平面板放入VBox(mainPane)
		pane_management.setCenter(tView);
		pane_management.setBottom(bottomVPane);
		
		
		// **************注册事件
		// 点击TableView事件
		tView.setOnMouseClicked(e->{
			// 得到TableView中选中行的行号（没选中的话返回-1）
			int index = tView.getSelectionModel().getSelectedIndex();
			
			// 如果有选中
			if(index!=-1){
				// 从数据列表中获取该行对象
				AcademyAdmin AcademyAdminObj = list.get(index);
				
				// 将选中行的列存放到对应的文本框中
				userNameTf.setText(AcademyAdminObj.getUserName());
				passwordTf.setText(AcademyAdminObj.getPassword());
				telephoneTf.setText(AcademyAdminObj.getTelephone());
				academyTf.setText(AcademyAdminObj.getAcademy());
			}
		});
		
		// 点击add按钮事件
		addBtn.setOnAction(e->{
			// 根据文本框内容创建新对象
			AcademyAdmin AcademyAdminObj = new AcademyAdmin(userNameTf.getText(), passwordTf.getText(), telephoneTf.getText(), academyTf.getText());
			
			if(!(academyTf.getText().equals("education_science")||academyTf.getText().equals("computer_science")||academyTf.getText().equals("medical_science")&&!(userNameTf.getText().equals(""))&&!(passwordTf.getText().equals("")))) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION,"用户名密码为空或"
					+ "管理员身份错误！\n请重新添加~\n");
			alert.setTitle("添加错误提示");
			alert.show();
			}
			else {
				// 把新创建的对象添加到数据列表
				list.add(AcademyAdminObj);
			// 数据库中添加记录
			Access_AthleteDB.Connect();
			rs = Access_AthleteDB.getResultSet("select * from academy_admin");
			try {
				rs.moveToInsertRow();
				rs.updateString("user_name", userNameTf.getText());
				rs.updateString("password", passwordTf.getText());
				rs.updateString("telephone", telephoneTf.getText());
				rs.updateString("career",academyTf.getText());
				rs.insertRow();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Access_AthleteDB.closeConnection();
			}
		});
		
		// 点击修改按钮事件
		updateBtn.setOnAction(e->{
			// 获取选中行的行号
			int index = tView.getSelectionModel().getSelectedIndex();
			// 如果有选中行
			if(index!=-1){
				// 根据文本框中的内容创建新对象
				AcademyAdmin AcademyAdminObj = new AcademyAdmin(userNameTf.getText(), passwordTf.getText(), telephoneTf.getText(), academyTf.getText());
				
				// 根据行号更新数据列表
				list.set(index, AcademyAdminObj);
			}
			
			// 修改数据库中的记录
			Access_AthleteDB.Connect();
			rs = Access_AthleteDB.getResultSet("select * from academy_admin");
			try {
				rs.absolute(index+1);
				rs.updateString("user_name", userNameTf.getText());
				rs.updateString("password", passwordTf.getText());
				rs.updateString("telephone", telephoneTf.getText());
				rs.updateString("academy",academyTf.getText());
				rs.updateRow();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Access_AthleteDB.closeConnection();
		});
		
		// 点击删除按钮事件
		deleteBtn.setOnAction(e->{
			// 获取选中行行号
			int index = tView.getSelectionModel().getSelectedIndex();
			// 如果有选中
			if(index!=-1){
				// 根据行号移除数据列表中的元素
				list.remove(index);
			}
			
			// 删除数据库中的记录
			Access_AthleteDB.Connect();
			rs = Access_AthleteDB.getResultSet("select * from academy_admin");
			try {
				rs.absolute(index+1);
				rs.deleteRow();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Access_AthleteDB.closeConnection();
		});
		
		return  pane_management;
	}
}
