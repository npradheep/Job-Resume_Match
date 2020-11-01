package com.resumematch;

public interface DynamoDBClientService {
	
	void insertResumeData(String key, String jd, String url);
	
	void deleteResumeData(String key);
	
	String getResumeData(String key);
	
	void updateMatch(String key, String match);
	
	void updateRecords(String key, String jd, String url);

}
