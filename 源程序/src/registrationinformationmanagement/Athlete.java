/*
	作者：凌涛
	作用：用来记录运动员的身份，只能有一个Id
*/

package registrationinformationmanagement;

public class Athlete {
	//运动员的属性
	private String studentId;
	private String name;
	private String sex;
	private String academy;
	private String telephone;
	public Athlete(String studentId, String name, String sex, String academy, String telephone) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.sex = sex;
		this.academy = academy;
		this.telephone = telephone;
	}
	
	
	//运动员方法
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSex() {
		return sex;
	}
	
	public String getAcademy() {
		return academy;
	}
	
	public String getTelephone() {
		return telephone;
	}

}
