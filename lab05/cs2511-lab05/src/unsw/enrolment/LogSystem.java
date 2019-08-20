package unsw.enrolment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Observable;
import java.util.Observer;

public class LogSystem implements Observer{
	
	private String logFileName;
	private int marks;
	
	public LogSystem() {
	}
	
	private void setFileName(Enrolment enrolment) {
		logFileName = enrolment.getCourseCode() + "-" + enrolment.getTerm() + "-" + enrolment.getZID() + ".txt";
	}
	
	public String createLog(Enrolment enrolment, Marks grade) {
		setFileName(enrolment);
		File logFile = new File(logFileName);
		if (logFile.exists()) return "FAIL logFile exist";
		enrolment.setGrade(grade);
		String header = "Course Code \t Marks \t Date & Time\n";
		String content = enrolment.getCourseCode() + "\t\t " + this.marks + "\t\t " + LocalDate.now() + " " + LocalTime.now() + "\n";
	
		try {
			if(!logFile.exists()) logFile.createNewFile();
			FileOutputStream fs = new FileOutputStream(logFile, true);
			
			byte[] headerInBytes = header.getBytes();
			byte[] contentInBytes = content.getBytes();
			
			fs.write(headerInBytes);
			fs.write(contentInBytes);
			fs.flush();
			fs.close();
			
			return "SUCCESS created logFile for " + enrolment.getZID() + "'s " + enrolment.getCourseCode();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "FAIL to create a logFile for " + enrolment.getZID() + "'s " + enrolment.getCourseCode();
	}
	
	public String updateLog(Enrolment enrolment, Marks grade) {
		enrolment.setGrade(grade);
		try {
			if(!new File(logFileName).exists()) return "UPDATE FAILED log file doesn't exist";
			BufferedReader logFile = new BufferedReader(new FileReader(logFileName));
//			StringBuffer sb = new StringBuffer();
			String content = enrolment.getCourseCode() + "\t\t " + this.marks
				+ "\t\t " + LocalDate.now() + " " + LocalTime.now() + "\n";
//			String line = null;
			// the spec doesn't want me to overwrite the old entry
//			while ((line = logFile.readLine()) != null) {
//				if (line.contains(enrolment.getCourseCode())) {
//					sb.append(content);
//					break;
//				}
//				sb.append(line + '\n');
//			}
//			logFile.close();
			
			FileOutputStream fs = new FileOutputStream(logFileName, true);
//			fs.write(sb.toString().getBytes());
			fs.write(content.getBytes());
			fs.flush();
			fs.close();
			
			logFile.close();
			return "UPDATE SUCCESS";
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "FAIL to update";
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Marks grade = (Marks) arg;
		this.marks = grade.getFinalMarks();
	}
}
