<html>
<head><title>Job Matcher</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<style>
.jumbotron{
    height: 100vh;
}
</style>

<head>
<body>

<div class="jumbotron">
  <h1 class="display-4">Job Match</h1>
  <p class="lead">Your resume and the job description matches...</p>
  <h1 class="display-4 center ">${match} %</h1>
  <hr class="my-4">
<p class="lead">Your Unique ID is ${uniqueid} </p>
<p class="lead">Use this to fetch your record the next time </p>
  <hr class="my-4">
  
  <div class = "container">
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
  Update record
</button>
</div>
<br>
<div class = "container">
<form method="POST" action="/data/delete">
<input type="hidden" name = "uqid" value= ${uniqueid} >
<input type ="submit" value="Click here"> to remove your record from our system.
</form>
</div>


</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Fetch previous record</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       
       <form method="POST" action="/data/update" enctype="multipart/form-data">
       <div class="form-group">
    <label >Unique ID</label>
    <input type="text" class="form-control-file" placeholder=${uniqueid} id="uu" readonly>
 	<input type="hidden" value=${uniqueid} name="uqid">
  </div>
<div class="form-group">
    <label for="exampleFormControlFile1">Upload Resume</label>
    <input type="file" class="form-control-file"  name = "file" id="resume">
  </div>
  <div class="form-group">
    <label for="jd">Job Description</label>
    <textarea class="form-control" id="jd" aria-describedby="Job Description" name="jd" placeholder="Paste Job Description"></textarea>
  </div>
  
  <button type="submit" class="btn btn-primary">Submit</button>
</form>

      </div>
    </div>
  </div>
</div>
</body>
</html>