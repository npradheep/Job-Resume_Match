package com.resumematch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

@Component
public class DynamoDBClientServiceImpl implements DynamoDBClientService{
	
	private DynamoDB dynamoDB;

	Table table;
	@Autowired
	public DynamoDBClientServiceImpl(Region awsRegiondb, AWSCredentialsProvider awsCredentialsProviderdb, String dbTableName) {
		AmazonDynamoDB clientdb = AmazonDynamoDBClientBuilder.standard()
				.withCredentials(awsCredentialsProviderdb)
                .withRegion(awsRegiondb.getName()).build();
		this.dynamoDB = new DynamoDB(clientdb);
		this.table = this.dynamoDB.getTable(dbTableName);
		
	}
	
	 

	@Async
	public void insertResumeData(String uniquekey, String jobD, String url) {
	
		try {

            Item item = new Item().withPrimaryKey("uniquekey", uniquekey).withString("JobDescription", jobD).withString("URL", url);
            this.table.putItem(item);

        }
        catch (Exception e) {
            System.err.println("Create items failed.");
            System.err.println(e.getMessage());

        }
		
	}



	@Async
	public void deleteResumeData(String key) {
		// TODO Auto-generated method stub
		DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("uniquekey",key);
		this.table.deleteItem(deleteItemSpec);
		
	}



	@Async
	public String getResumeData(String key) {
		String mtch= "";
		GetItemSpec spec = new GetItemSpec().withPrimaryKey("uniquekey",key);
		try {
            System.out.println("Attempting to read the item...");
            Item outcome = table.getItem(spec);
            mtch = (String) outcome.get("matchScore");
            System.out.println("GetItem succeeded: " + outcome);
            

        }
        catch (Exception e) {
        	
            System.err.println(e.getMessage());
        }
		
		return mtch;
	}



	@Async
	public void updateMatch(String key, String match) {
		UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("uniquekey",key)
				.withUpdateExpression("set matchScore = :s").withValueMap(new ValueMap().withString(":s", match)).withReturnValues(ReturnValue.UPDATED_NEW);
		
		
		try {
            System.out.println("Updating the item...");
            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
            System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());

        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
	}



	@Async
	public void updateRecords(String key, String jd, String url) {
		// TODO Auto-generated method stub
		UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("uniquekey",key)
				.withUpdateExpression("set JobDescription = :j, URL = :u").withValueMap(new ValueMap().withString(":s", jd).withString(":u", url)).withReturnValues(ReturnValue.UPDATED_NEW);
		
		try {
            System.out.println("Updating the item...");
            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
            System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());

        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        
		
		
		
	}
	}
}


