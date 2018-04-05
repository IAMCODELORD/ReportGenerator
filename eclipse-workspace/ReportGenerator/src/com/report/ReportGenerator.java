package com.report;

public class ReportGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadFile rf = new ReadFile("Input.txt");
		ProcessFileData pfd = new ProcessFileData(rf);
		StudentDetailsProcessing sdp = new StudentDetailsProcessing(pfd);
		StudentDetailsDB sd = new StudentDetailsDB(sdp);
		WriteReport wr = new WriteReport(sdp);
		wr.writeReport();
		sd.storeDetailsDB();
	}

}
