package com.resumematch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.resumematch.proto.ResumeRequest;
import com.resumematch.proto.ResumeResponse;
import com.resumematch.proto.getMatchRatioGrpc;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.sql.Timestamp;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class MainController {   
	
	 @Autowired
	 private AmazonS3ClientService amazonS3ClientService;
	 
	 @Autowired
	 private DynamoDBClientService dynamodb;
	 
	 private ManagedChannel channel = ManagedChannelBuilder.forAddress("pythonresume", 50051).usePlaintext().build();
	 private getMatchRatioGrpc.getMatchRatioBlockingStub client = getMatchRatioGrpc.newBlockingStub(this.channel);
	 
	 
	 Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
	 private String uniq; 
	 Random rand = new Random(); 

	 
    @GetMapping({"/","/home"})
    public String hello() {
        return "index";
    }
    
    @PostMapping("/home/upload")
    public String uploadFile(Model model, @RequestPart(value = "file") MultipartFile file, @RequestParam(value="jd") String jobD)
    {
    	int randint = rand.nextInt(1000);
    	this.uniq= (timestamp.getTime())+""+randint;
    	String filename = this.uniq+".pdf";
    	this.amazonS3ClientService.uploadFileToS3Bucket(filename,file, true);
        String url = this.amazonS3ClientService.getURL(filename);
        this.dynamodb.insertResumeData(this.uniq, jobD, url);
        
        ResumeRequest rreq = ResumeRequest.newBuilder().setUniquekey(this.uniq).setJd(jobD).setUrl(url).build();
        ResumeResponse rres;
       
        String match="";
        try { 
        	rres = this.client.getratio(rreq);
        	match = rres.getMessage();
        }
        catch (StatusRuntimeException e) {
        	System.out.println(e.getStatus());
        }
        
        this.dynamodb.updateMatch(this.uniq, match);
        
        model.addAttribute("uniqueid",this.uniq);
        model.addAttribute("match", match);

        return "result";
    }

    @PostMapping("/data/delete")
    public String deleteFile(@RequestParam("uqid") String fileName)
    {
        this.amazonS3ClientService.deleteFileFromS3Bucket(fileName+".pdf");
        this.dynamodb.deleteResumeData(fileName);

        return "redirect:/index";
    }
    
    @GetMapping("/data/get")
    public String getdata(Model model, @RequestParam(value="getdataid") String uid) {
    	
    	String match = this.dynamodb.getResumeData(uid);
    	model.addAttribute("uniqueid",uid);
        model.addAttribute("match", match);
		return "result";
    	
    }
    
    @PostMapping("/data/update")
    public String updateData(Model model, @RequestPart(value = "file") MultipartFile file, @RequestParam(value="jd") String jobD, @RequestParam(value="uqid") String uqid) {
    	String filename = uqid+".pdf";
    	this.amazonS3ClientService.deleteFileFromS3Bucket(filename);
    	this.amazonS3ClientService.uploadFileToS3Bucket(filename,file, true);
    	String url = this.amazonS3ClientService.getURL(filename);
    	this.dynamodb.updateRecords(uqid, jobD, url);
    	
    	ResumeRequest rreq = ResumeRequest.newBuilder().setUniquekey(uqid).setJd(jobD).setUrl(url).build();
        ResumeResponse rres;
       
        String match="";
        try { 
        	rres = this.client.getratio(rreq);
        	match = rres.getMessage();
        }
        catch (StatusRuntimeException e) {
        	System.out.println(e.getStatus());
        }
        
        this.dynamodb.updateMatch(uqid, match);
        model.addAttribute("uniqueid",uqid);
        model.addAttribute("match", match);
    	
    	return "result";
    	
    }
}
