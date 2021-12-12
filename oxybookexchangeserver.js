  
const mysql = require('mysql');
const express = require('express');
const bodyparser = require('body-parser');

const con = mysql.createConnection({
  host: "<IP Address>", // IP address of my computer
  user: 'root',
  password: 'oxybookexchange1',
  database: 'oxybookexchange'
});

con.connect(function (err) {
    if (err) throw err;
    console.log("Connected!");
});

const app = express();
const publicDir = (__dirname + '/public/');
app.use(express.static(publicDir));
app.use(bodyparser.json());
app.use(bodyparser.urlencoded({extended:true}));

// retrieve all listings
app.get("/listings",(req,res,next)=>{
  con.query('SELECT * FROM listings;', function(error, result, fields){
    con.on('error', function(err){
      console.log('[MYSQL]ERROR', err);
    });
    if(result && result.length){
      res.end(JSON.stringify(result));
    }
    else{
      res.end(JSON.stringify('no listings here'));
    }
    console.log("hi");
  });
});

// retrieve listings by user email
app.get("/listings/:userEmail",(req,res, next)=>{
  const userEmail = req.params.userEmail;
  console.log(userEmail);
  const sql = `SELECT * from listings WHERE userEmail = "${userEmail}"`;
  console.log(sql);

  con.query(sql, function(error, result, fields){
    con.on('error', function(err){
      console.log('[MYSQL]ERROR', err);
    });
    if(result && result.length){
       res.end(JSON.stringify(result));
     }
     else{
       res.end(JSON.stringify(userEmail));
     }
    console.log("hi");
  });
});

// create new listing in database
app.post("/newlisting", function(req, res) {
  console.log(req.body); //This prints the JSON document received (if it is a JSON document)
  var listingID = parseInt(req.body.listingID);
  var userEmail = req.body.userEmail;
  var ISBN = BigInt(req.body.ISBN);
  var title = req.body.title;
  var quality = req.body.quality;
  var price = req.body.price;
  var course = req.body.course;
  var semester = req.body.semester;
  var yearPublished = req.body.yearPublished;
  var authors = req.body.authors;
  var professors = req.body.professors;
  
  var sql = `INSERT INTO listings (listingID, userEmail, ISBN, title, quality, price, course, semester, yearPublished, authors, professors) VALUES (${listingID}, "${userEmail}", ${ISBN}, "${title}", "${quality}","${price}","${course}","${semester}","${yearPublished}","${authors}","${professors}");`;
  con.query(sql, function(err, result) {
    if (err) throw err;
    console.log('record inserted');
    res.redirect('/listings');
  });
});

// update existing listing in database
app.post("/updatelisting", function(req, res) {
  console.log(req.body); //This prints the JSON document received (if it is a JSON document)
  var listingID = parseInt(req.body.listingID);
  var ISBN = BigInt(req.body.ISBN);
  var title = req.body.title;
  var quality = req.body.quality;
  var price = req.body.price;
  var course = req.body.course;
  var semester = req.body.semester;
  var yearPublished = req.body.yearPublished;
  var authors = req.body.authors;
  var professors = req.body.professors;
  
  var sql = `UPDATE listings SET ISBN = ${ISBN}, title = "${title}", quality = "${quality}", price = "${price}", course = "${course}", semester = "${semester}", authors= "${authors}", yearPublished = "${yearPublished}", professors = "${professors}" WHERE listingID = ${listingID};`
  con.query(sql, function(err, result) {
    if (err) throw err;
    console.log(result.affectedRows + " record(s) updated");
    res.redirect('/listings');
  });
});

// delete existing listing in database
app.post("/deletelisting", function(req, res) {
  console.log(req.body); //This prints the JSON document received (if it is a JSON document)
  var listingID = parseInt(req.body.listingID);

  var sql = `DELETE FROM listings WHERE listingID = ${listingID};`
  con.query(sql, function(err, result) {
    if (err) throw err;
    console.log(result.affectedRows + " record(s) updated");
    res.redirect('/listings');
  }); 
});

app.listen(3308,()=>{
  console.log('port 3308 running api');
});
