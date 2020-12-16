/*
 	******************************* 
	作者：凌涛
	类的作用：搭建系统管理员登陆平台所用的界面
	编写范围：整个平台的搭建、界面显示(UI)、连接数据库、连接登陆界面、各种事件响应函数的模板建立、所有的查询操作
	
	*******************************
	作者：熊志豪
	编写范围：连接对于学院管理员的增、删、改；系统管理员管理参赛者的信息（增、删、改）
 */





package registrationinformationmanagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AthleteApplicationForm extends Application{
	
	private  StackPane backgroundPane;//背景pane，放图片
	private  BorderPane mainPane;
	private  BorderPane rightPane;
	private  String adminId;//管理员的ID
	private int systemORacademy;//判断是学院管理员还是系统
	private  String login_user_id, login_user_telephone, login_user_career, login_username;//管理员的个人信息
	private  Stage stage;//主舞台
	private  Stage fixStage;//修改舞台
	private  Stage addStage;//添加舞台
	
	static ResultSet rs,rs2;

	private TextField studentIdTf,nameTf,academyTf,sexTf,telephoneTf,itemTf,scoreTf;
	private Button enterBtn;
	
	private  VBox selectFunction;//界面左侧的功能选择栏
	private  HBox hBoxTop;
	private  HBox hBoxBottom;
	private  ToggleButton scoreInquiry;//成绩查询
	private  ToggleButton personalDetails;//个人信息
	private  ToggleButton exit;//退出
	private  ToggleButton academyAdminManage; //学院管理员信息管理
	
	private  RadioButton butonMan;//按钮男
	private  RadioButton buttonWoman;//按钮女
	private  RadioButton buttonAll;//按钮全部
	private  String sexString;

	private  ChoiceBox selectAcademy;//选择学院
	private  ChoiceBox selectItem;//选择项目
	private  Label  academyMap;//学院映射数据库
	private  Label	itemMap;//项目映射数据库
	
	private  TextField notification = new TextField();//搜索的文本框
	private  Button selectButton;//搜索按钮
	private  Button deleteButton;//删除按钮
	private  Button insertButton;//增加按钮
	private  Button updateButton;//修改按钮

	private  TableView<AthleteScore> tableView = new TableView();
	private  TableColumn<AthleteScore,String> tRank = new TableColumn();//排名那一列
	private  TableColumn<AthleteScore,String> tStudentId = new TableColumn();//学号
	private  TableColumn<AthleteScore,String> tAthleteName = new TableColumn();//名字
	private  TableColumn<AthleteScore,String> tAcademy = new TableColumn();//学院
	private  TableColumn<AthleteScore,String> tSex = new TableColumn();//性别
	private  TableColumn<AthleteScore,String> tTelephone = new TableColumn();//电话号码
	private  TableColumn<AthleteScore,String> tItem = new TableColumn();//项目
	private  TableColumn<AthleteScore,String> tScore = new TableColumn();//成绩
	ObservableList<AthleteScore> obsList;
	private  ArrayList<AthleteScore> recordsList;

	public AthleteApplicationForm(int systemORacademy,String login_user_id,String login_user_telephone,String login_user_career,String login_username) {
		this.systemORacademy = systemORacademy;
		this.login_user_id = login_user_id;
		this.login_user_telephone = login_user_telephone;
		this.login_user_career = login_user_career;
		this.login_username = login_username;
	}
	
	public void start(Stage athleteApplicationFormStage) throws Exception{
		//创建一个StackPane用来加载背景图片
		backgroundPane = new StackPane();
		//创建一个BorderPane作为mainPane
		mainPane = new BorderPane();
		//在mainPane的右边创建一个BorderPane作为子Pane
		rightPane = new BorderPane();
		
		//加载背景图片到主界面
		backgroundPane.setStyle("-fx-background-image:url('images/BackgroundPhoto.jpg');-fx-background-repeat:no-repeat;-fx-background-size:100% 100%;-fx--moz-background-size:100% 100%;");
		
		//设置mainPain的左边
		selectFunction = mainPaneSetLeft();
		
		//设置右边rightPane的Top
        rightPaneSetTop();
        rightPane.setPadding(new Insets(50,50,50,50));
        rightPane.setMinWidth(mainPane.widthProperty().get() - personalDetails.widthProperty().get());
        rightPane.setMinHeight(mainPane.heightProperty().get() - personalDetails.heightProperty().get());
        
        
        //设置右边rightPane的Center
        rightPaneSetCenter();
        mainPane.setRight(rightPane);
        
        //设置右边rightPane的底部
        rightPaneSetBottom();
        
        //设置mainPane的界面，左边为选择按钮，右边默认先设置为成绩查询界面
		mainPane.setLeft(selectFunction);
		mainPane.setRight(rightPane);
		backgroundPane.getChildren().addAll(mainPane);
		
		//对各种事件进行响应
		eventHandle();
		
		//Create a scene and place it in the stage
		stage = athleteApplicationFormStage;
		stage.setScene(new Scene(backgroundPane, 1200, 500));
		stage.setTitle("运动会管理系统");
		stage.show();
		
	}
	
//	public static void main(String[] args) {
//		Application.launch(args);
//	}
	
	private VBox mainPaneSetLeft() {
		//创建四个按钮：成绩查询、个人信息、退出、学院管理员信息管理
		scoreInquiry = new ToggleButton("成绩 查询");
		personalDetails = new ToggleButton("个人 信息");
		exit = new ToggleButton("   退出     ");
		academyAdminManage = new ToggleButton("管理admin");
		
		//设置三个按钮的样式，包括按钮的阴影和大小
		String buttonStyle = "-fx-font: 30 arial; -fx-base: white;";
		DropShadow shadow = new DropShadow();
		scoreInquiry.setEffect(shadow);
		personalDetails.setEffect(shadow);
		exit.setEffect(shadow);
		academyAdminManage.setEffect(shadow);
		scoreInquiry.setStyle(buttonStyle);
		personalDetails.setStyle(buttonStyle);
		exit.setStyle(buttonStyle);
		academyAdminManage.setStyle(buttonStyle);
		scoreInquiry.setMaxWidth(200);
		exit.setMaxWidth(200);
		personalDetails.setMaxWidth(200);
		academyAdminManage.setMaxWidth(200);
		
		//将三个按钮添加到一个组里面
		ToggleGroup group = new ToggleGroup();
		scoreInquiry.setToggleGroup(group);
		personalDetails.setToggleGroup(group);
		exit.setToggleGroup(group);
		academyAdminManage.setToggleGroup(group);
		
		//默认成绩查询按钮被选中
		scoreInquiry.setSelected(true);
		
		//在mainPane的最左边创建一个Vbox，将三个按钮：成绩查询、个人信息、退出添加到其中去
		VBox select = new VBox();
		select.getChildren().addAll(personalDetails,scoreInquiry,academyAdminManage,exit);
        select.setSpacing(10);
		select.setAlignment(Pos.CENTER);
		return select;
	}
	
	private void rightPaneSetTop() {
		//Create controls to help users filter information(HBox)
    	//Label:姓别
    	Label sex = new Label("性别");
    	//创建一个VBox，用来放置各种组件
    	VBox selectSex = new VBox();
    		//RadioButton:单选按钮三个：男、女、全部
    		butonMan  = new RadioButton("男");
    		buttonWoman = new RadioButton("女");
    		buttonAll = new RadioButton("全部");
			sexString = "allSex";
    		buttonAll.selectedProperty().set(true);
    		
    		//把三个单选按钮放入一个组Group中;而且将它们依次放入VBox中
    		ToggleGroup group = new ToggleGroup();
    		butonMan.setToggleGroup(group);
    		buttonWoman.setToggleGroup(group);
    		buttonAll.setToggleGroup(group);
    		selectSex.getChildren().addAll(butonMan,buttonWoman,buttonAll);
    		selectSex.setSpacing(10);
    
    	//Label:学院
    	Label academyLabel = new Label("学院");
		itemMap = new Label();
		academyMap = new Label();
    	//选择框：选择学院
		final String [] academyDB = new String[] { "computer_science", "medical_science", "education_science", "all_school" };
    	selectAcademy = new ChoiceBox(FXCollections.observableArrayList("杭州国际服务工程学院", "医学院", "教育学院","全校"));
		selectAcademy.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue ov, Number value, Number new_value) {
						academyMap.setText(academyDB[new_value.intValue()]);
						System.out.println(academyMap.getText());

						String academy = academyMap.getText();
						String items = itemMap.getText();
						String sql = "select * from score";
						getDataBaseHandle(sql,sexString,academy,items,notification.getText());
					}
				});
    	selectAcademy.setValue("全校");
    	
    	//Label:项目
    	Label itemLabel = new Label("项目");
    	//选择框：选择项目
		final String [] itemDB = new String[] { "100m", "50m", "3000m", "all_item" };
    	selectItem = new ChoiceBox(FXCollections.observableArrayList("100米", "50米", "3000米","全部项目"));
		selectItem.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue ov, Number value, Number new_value) {
						itemMap.setText(itemDB[new_value.intValue()]);
						System.out.println(itemMap.getText());

						String academy = academyMap.getText();
						String items = itemMap.getText();
						String sql = "select * from score";
						getDataBaseHandle(sql,sexString,academy,items,notification.getText());
					}
				});
    	selectItem.setValue("全部项目");

    	//文本框
    	notification = new TextField ("");
    	notification.setPromptText("按姓名搜索");
    	
    	//Button
//		Image image = new Image(getClass().getResourceAsStream());
		ImageView image = new ImageView("images/search.png");
//		System.out.println(image.getImage());
		image.setFitWidth(20);
		image.setFitHeight(20);
    	selectButton = new Button("", image );
    	
    	hBoxTop = new HBox();
    	hBoxTop.getChildren().addAll(sex,selectSex,academyLabel,selectAcademy,itemLabel,selectItem,notification,selectButton);
    	hBoxTop.setSpacing(20);
    	//hBox.setStyle("-fx-font: 10 arial;-fx-border-color:blue;");
    	hBoxTop.setPadding(new Insets(15,15,15,15));
    	rightPane.setTop(hBoxTop);
	}
	
	private void rightPaneSetCenter() {
        //创建一个 Pagination分页控件 
		Pagination pagination1 = new Pagination(10,0);
		pagination1.setStyle("-fx-font: 12 arial;-fx-border-color:red;");
		pagination1.setPadding(new Insets(0,0,0,0));
		
//		rightPane.setCenter(pagination1);
		

        	//创建textVeiw表视图
			tableView = createTable();
			
			rightPane.setCenter(tableView);
			//将textVeiw添加到分页里面去
//			pagination1.setPageFactory((Integer pageIndex) -> tableView);
			
			//连接数据库，将数据库中的数据导入到tabVeiw中
			String sql = "select * from score";
			getDataBaseHandle(sql,sexString,academyMap.getText(),itemMap.getText(),notification.getText());
	}
	
	private void rightPaneSetBottom() {
		hBoxBottom = new HBox();
		deleteButton = new Button("删除");//删除按钮
		insertButton = new Button("增加");//增加按钮
		updateButton = new Button("修改");//修改按钮
		hBoxBottom.getChildren().addAll(deleteButton,insertButton,updateButton);
		hBoxBottom.setSpacing(100);
		hBoxBottom.setPadding(new Insets(30,0,0,0));
		hBoxBottom.setAlignment(Pos.CENTER);
		rightPane.setBottom(hBoxBottom);
	}
	
	//用来连接数据库，并且将数据与tabView表格关联
	private void getDataBaseHandle(String sql,String sex,String academy,String items,String name) {
		//连接数据库
		Access_AthleteDB.Connect();
		
		//获取数据库中的内容
		recordsList = Access_AthleteDB.getRecordsAthleteMany(sql,sex,academy,items,notification.getText());
		// （4）创建数据列表
		obsList = FXCollections.observableArrayList();
		System.out.println(recordsList.size());
		for(int i = 0; i < recordsList.size(); i++) {
			obsList.addAll(recordsList.get(i));
		}
		//（5）把数据列表和表视图关联
		tableView.setItems(obsList);

		// （6）把数据列表和列对象建立关联
		tRank.setCellValueFactory(new PropertyValueFactory<AthleteScore,String>("rank"));
		tStudentId.setCellValueFactory(new PropertyValueFactory<AthleteScore,String>("studentId"));
		tAthleteName.setCellValueFactory(new PropertyValueFactory<AthleteScore,String>("name"));
		tAcademy.setCellValueFactory(new PropertyValueFactory<AthleteScore,String>("academy"));
		tSex.setCellValueFactory(new PropertyValueFactory<AthleteScore,String>("sex"));
		tTelephone.setCellValueFactory(new PropertyValueFactory<AthleteScore,String>("telephone"));
		tItem.setCellValueFactory(new PropertyValueFactory<AthleteScore,String>("sportsItem"));
		tScore.setCellValueFactory(new PropertyValueFactory<AthleteScore,String>("score"));
		
		//断开数据库连接
		Access_AthleteDB.closeConnection();
	}

	private void buttonOnAction(String sexString) {
		//获取学院、项目的信息
		String academy = academyMap.getText();
		String items = itemMap.getText();
		String sql = "select * from score";
		Access_AthleteDB.Connect();

		getDataBaseHandle(sql,sexString,academy,items,notification.getText());

		Access_AthleteDB.closeConnection();
	}
	
	 private TableView<AthleteScore> createTable() {
		TableView<AthleteScore> table = new TableView<AthleteScore>();
		table.setStyle("-fx-font: 18 arial; -fx-base: white;");
	 	//创建表的列对象
		tRank = new TableColumn<AthleteScore,String>("排名");
		tRank.setMinWidth(50);
		tStudentId = new TableColumn<AthleteScore,String>("学号");
		tAthleteName = new TableColumn<AthleteScore,String>("姓名");
		tAcademy = new TableColumn<AthleteScore,String>("学院");
		tSex = new TableColumn<AthleteScore,String>("性别");
		tTelephone = new TableColumn<AthleteScore,String>("电话");
		tItem = new TableColumn<AthleteScore,String>("项目");
		tScore = new TableColumn<AthleteScore,String>("成绩");
		
		//把列对象添加到表视图中去
		table.getColumns().addAll(tRank,tStudentId,tAthleteName,tAcademy,tSex,tTelephone,tItem,tScore);
		return table;
	 }
	 
	 private void eventHandle() {
		//对选择性别的三个按钮设置事件响应函数
		 butonMan.setOnAction(e->{
				sexString = "male";
				buttonOnAction(sexString);
		});
		
		buttonWoman.setOnAction(e->{
			sexString = "female";
			buttonOnAction(sexString);
		});

		buttonAll.setOnAction(e->{
			sexString = "allSex";
			buttonOnAction(sexString);
		});
		
		//对搜索按钮进行事件响应
		selectButton.setOnAction(e->{
			String academy = academyMap.getText();
			String items = itemMap.getText();
			String sql = "select * from score";
			getDataBaseHandle(sql,sexString,academy,items,notification.getText());
		});

		selectButton.setOnKeyPressed(e->{
			if (e.getCode() == KeyCode.ENTER) {
				String academy = academyMap.getText();
				String items = itemMap.getText();
				String sql = "select * from score";
				getDataBaseHandle(sql,sexString,academy,items,notification.getText());
			}
		});
		
		// 点击TableView事件
		tableView.setOnMouseClicked(e->{
			// 得到TableView中选中行的行号（没选中的话返回-1）
			int index = tableView.getSelectionModel().getSelectedIndex();
			
			// 如果有选中
			if(index!=-1) {
				// 从数据列表中得到该行对象
				AthleteScore abcObj = obsList.get(index);
				
				// 将选中行的列值放到对应的文本框中
//				aTf.setText(abcObj.getA());
//				bTf.setText(abcObj.getB());
//				cTf.setText(abcObj.getC());
			}	
			
		});
		
		//删除按钮
		deleteButton.setOnAction(e->{
			// 获取选中行行号
			int index = tableView.getSelectionModel().getSelectedIndex();
			AthleteScore  AthleteScoreObj=tableView.getSelectionModel().getSelectedItem();
			System.out.println(AthleteScoreObj.getStudentId());
			// 如果有选中
			if(index!=-1){
				
				// 根据行号移除数据列表中的元素
				obsList.remove(index);
			}
			// 删除数据库中的记录
			Access_AthleteDB.Connect();
			String deleteSql = "DELETE FROM athletes WHERE student_id= "+AthleteScoreObj.getStudentId();
			Access_AthleteDB.delete(deleteSql);
			deleteSql = "DELETE FROM score WHERE student_id= "+AthleteScoreObj.getStudentId();
			Access_AthleteDB.delete(deleteSql);
			Access_AthleteDB.closeConnection();
		});
		
		//增加按钮
		insertButton.setOnAction(e->{
			
			addStage=new Stage();
			//网格布局
			GridPane addPane=new GridPane();
			addPane.setPadding(new Insets(10,0,0,0));
			addPane.setAlignment(Pos.CENTER);
			addPane.setHgap(5.5);
			addPane.setVgap(15);
			//标签
			Label studentIdLabel = new Label("学号: ");
			Label nameLabel = new Label("姓名: ");
			Label academyLabel = new Label("学院: ");
			Label sexLabel = new Label("性别: ");
			Label telephoneLabel = new Label("电话: ");
			Label itemLabel = new Label("项目: ");
			Label scoreLabel = new Label("成绩: ");
			//文本框
			studentIdTf = new TextField();
			nameTf = new TextField();
			academyTf = new TextField();
			sexTf = new TextField();
			telephoneTf = new TextField();
			itemTf = new TextField();
			scoreTf = new TextField();
			//确认按钮
			enterBtn = new Button("确认");
			
			//添加第一行
			addPane.add(studentIdLabel, 0, 0);
			addPane.add(nameLabel, 1, 0);
			addPane.add(academyLabel, 2, 0);
			addPane.add(sexLabel, 3, 0);
			addPane.add(telephoneLabel, 4, 0);
			addPane.add(itemLabel, 5, 0);
			addPane.add(scoreLabel, 6, 0);
			//添加第二行
			addPane.add(studentIdTf, 0, 1);
			addPane.add(nameTf, 1, 1);
			addPane.add(academyTf, 2, 1);
			addPane.add(sexTf, 3, 1);
			addPane.add(telephoneTf, 4, 1);
			addPane.add(itemTf, 5, 1);
			addPane.add(scoreTf, 6, 1);
			//添加第三行
			addPane.add(enterBtn,3,2);
			//展示
			Scene addscene=new Scene(addPane,800,200);
			addStage.setScene(addscene);
			addStage.setTitle("Add");
			addStage.show();
			
			//确认按钮
			enterBtn.setOnAction(adde->{
				//排名默认为0
				AthleteScore  AthleteScoreObj=new AthleteScore(studentIdTf.getText(), nameTf.getText(), sexTf.getText(), academyTf.getText(), telephoneTf.getText(), "0", itemTf.getText(),scoreTf.getText());
				if(studentIdTf.getText().equals("")||nameTf.getText().equals("")||sexTf.getText().equals("")||academyTf.getText().equals("")||itemTf.getText().equals("")||scoreTf.getText().equals(""))
				{
					Alert alert = new Alert(Alert.AlertType.INFORMATION,"输入值有空！");
					alert.setTitle("添加错误提示");
					alert.show();
				}
				else {
					if((!sexTf.getText().equals("male")&&!sexTf.getText().equals("female"))||(!academyTf.getText().equals("education_science")&&!academyTf.getText().equals("computer_science")&&!academyTf.getText().equals("medical_science"))) 
					{
						Alert alert = new Alert(Alert.AlertType.INFORMATION,"性别或学院不符！！");
						alert.setTitle("添加错误提示");
						alert.show();
					}
					else {
						//添加一行(默认在尾）
						obsList.add(AthleteScoreObj);
						//添加到数据库
						Access_AthleteDB.Connect();
						String addSql = "INSERT INTO athletes (student_id,name,sex,academy,telephone) VALUES ('"+studentIdTf.getText()+"','"+nameTf.getText()+"','"+sexTf.getText()+"','"+academyTf.getText()+"','"+telephoneTf.getText()+"' )";
						//添加一行(默认在尾）;
						Access_AthleteDB.add(addSql);
						Access_AthleteDB.closeConnection();
						Access_AthleteDB.Connect();
						addSql = "INSERT INTO score (student_id,sports_item,score) VALUES ('"+studentIdTf.getText()+"','"+itemTf.getText()+"','"+scoreTf.getText()+"' )";
						Access_AthleteDB.add(addSql);
						Access_AthleteDB.closeConnection();
				
						addStage.hide();
					}
				}
			});

			

		});
		//修改按钮
		updateButton.setOnAction(e->{
			//网格布局
			fixStage=new Stage();
			GridPane fixPane=new  GridPane();
			fixPane.setPadding(new Insets(10,0,0,0));
			fixPane.setAlignment(Pos.CENTER);
			fixPane.setHgap(5.5);
			fixPane.setVgap(15);
			
			//标签
			Label studentIdLabel = new Label("学号: ");
			Label nameLabel = new Label("姓名: ");
			Label academyLabel = new Label("学院: ");
			Label sexLabel = new Label("性别: ");
			Label telephoneLabel = new Label("电话: ");
			Label itemLabel = new Label("项目: ");
			Label scoreLabel = new Label("成绩: ");
			//文本框
			studentIdTf = new TextField();
			nameTf = new TextField();
			academyTf = new TextField();
			sexTf = new TextField();
			telephoneTf = new TextField();
			itemTf = new TextField();
			scoreTf = new TextField();
			//确认按钮
			Button fixBtn = new Button("确认");
			
			//添加第一行
			fixPane.add(studentIdLabel, 0, 0);
			fixPane.add(nameLabel, 1, 0);
			fixPane.add(academyLabel, 2, 0);
			fixPane.add(sexLabel, 3, 0);
			fixPane.add(telephoneLabel, 4, 0);
			fixPane.add(itemLabel, 5, 0);
			fixPane.add(scoreLabel, 6, 0);
			//添加第二行
			fixPane.add(studentIdTf, 0, 1);
			fixPane.add(nameTf, 1, 1);
			fixPane.add(academyTf, 2, 1);
			fixPane.add(sexTf, 3, 1);
			fixPane.add(telephoneTf, 4, 1);
			fixPane.add(itemTf, 5, 1);
			fixPane.add(scoreTf, 6, 1);
			//添加第三行
			fixPane.add(fixBtn,3,2);
			//展示
			Scene fixscene=new Scene(fixPane,800,200);
			fixStage.setScene(fixscene);
			fixStage.setTitle("Fix");
			fixStage.show();
			// 获取选中行的行号
			int index = tableView.getSelectionModel().getSelectedIndex();
			if(index!=-1){
				// 根据选中内容创建新对象
				AthleteScore  tempObj=obsList.get(index);
				
				studentIdTf.setText(tempObj.getStudentId());
				nameTf.setText(tempObj.getName());
				sexTf.setText(tempObj.getSex());
				academyTf.setText(tempObj.getAcademy());
				telephoneTf.setText(tempObj.getTelephone());
				itemTf.setText(tempObj.getSportsItem());
				scoreTf.setText(tempObj.getScore());
			}
			fixBtn.setOnAction(fixe->{
				
				// 如果有选中行
				if(index!=-1){
					//根据文本框中的内容创建新对象
					//排名默认为0
					AthleteScore  AthleteScoreObj=new AthleteScore(studentIdTf.getText(), nameTf.getText(), sexTf.getText(), academyTf.getText(), telephoneTf.getText(), "0", itemTf.getText(),scoreTf.getText());
					
					// 根据行号更新数据列表
					obsList.set(index, AthleteScoreObj);
				}
				
				//更改数据库
				Access_AthleteDB.Connect();
				String fixSql = "UPDATE athletes SET name = "+"'"+nameTf.getText()+"', sex = '"+sexTf.getText()+"', academy = '"+academyTf.getText()+"', telephone = '"+telephoneTf.getText()+"' WHERE student_id = '"+studentIdTf.getText()+"'";
				//添加一行(默认在尾）;
				Access_AthleteDB.fix(fixSql);
				Access_AthleteDB.closeConnection();
				Access_AthleteDB.Connect();
				fixSql = "UPDATE score SET sports_item = "+"'"+itemTf.getText()+"', score = '"+scoreTf.getText()+"' WHERE student_id = '"+studentIdTf.getText()+"'";
				Access_AthleteDB.delete(fixSql);
				Access_AthleteDB.closeConnection();
				
				fixStage.hide();
			});
			
		});
		
		//当用户点击个人信息按钮时，可修改信息
		personalDetails.setOnAction(e->{
			Information infromation = new Information(this.systemORacademy,this.login_user_id,this.login_user_telephone,this.login_user_career,this.login_username);
			Pane pane = infromation.getInformationPane();
			pane.setMinWidth(mainPane.widthProperty().get() - personalDetails.widthProperty().get());
			pane.setMinHeight(mainPane.heightProperty().get() - personalDetails.heightProperty().get());
			pane.setPadding(new Insets(50,50,50,50));
			mainPane.setRight(pane);
		});
		
		//当点击成绩查询按钮时，切换到该页面
		scoreInquiry.setOnAction(e->{
			mainPane.setRight(rightPane);
		});
		
		//当点击管理admin时，切换到该界面
		academyAdminManage.setOnAction(e->{
			ManagementAdministrator management=new ManagementAdministrator();
			Pane pane=management.getManagementPane();
			pane.setMinWidth(mainPane.widthProperty().get() - personalDetails.widthProperty().get());
			pane.setMinHeight(mainPane.heightProperty().get() - personalDetails.heightProperty().get());
			pane.setPadding(new Insets(50,50,50,50));
			mainPane.setRight(pane);
		});
		
		//当点击退出按钮时，退出程序
		exit.setOnAction(e->{
			stage.close();
		});
	 }

}
