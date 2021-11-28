  
const mysql = require('mysql');
const express = require('express');
const bodyparser = require('body-parser');

const con = mysql.createConnection({
  host: "134.69.236.202", //IP address of my computer
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


// app.use(bodyparser.urlencoded({extended : true}));
app.post("/newlisting", function(request, response) {
  console.log(request.body); //This prints the JSON document received (if it is a JSON document)
  var listingID = parseInt(request.body.listingID);
  var userEmail = request.body.userEmail;
  var ISBN = BigInt(request.body.ISBN);
  var title = request.body.title;
  var quality = request.body.quality;
  var price = request.body.price;
  var course = request.body.course;
  var semester = request.body.semester;
  var yearPublished = request.body.yearPublished;
  var authors = request.body.authors;
  var professors = request.body.professors;
  
  var sql = `INSERT INTO listings (listingID, userEmail, ISBN, title, quality, price, course, semester, yearPublished, authors, professors) VALUES (${listingID}, "${userEmail}", ${ISBN}, "${title}", "${quality}","${price}","${course}","${semester}","${yearPublished}","${authors}","${professors}");`;
  con.query(sql, function(err, result) {
    if (err) throw err;
    console.log('record inserted');
    // res.redirect('/listings');
  });
});


app.post("/updatelisting", function(request, response) {
  console.log(request.body); //This prints the JSON document received (if it is a JSON document)
  var listingID = parseInt(request.body.listingID);
  // var userEmail = request.body.userEmail;
  var ISBN = BigInt(request.body.ISBN);
  var title = request.body.title;
  var quality = request.body.quality;
  var price = request.body.price;
  var course = request.body.course;
  var semester = request.body.semester;
  var yearPublished = request.body.yearPublished;
  var authors = request.body.authors;
  var professors = request.body.professors;
  
  var sql = `UPDATE listings SET ISBN = ${ISBN}, title = "${title}", quality = "${quality}", price = "${price}", course = "${course}", semester = "${semester}", authors= "${authors}", yearPublished = "${yearPublished}", professors = "${professors}" WHERE listingID = ${listingID};`
  con.query(sql, function(err, result) {
    if (err) throw err;
    console.log(result.affectedRows + " record(s) updated");
    // req.flash('success', 'Data added successfully!');
    // res.redirect('/listings');
  });
});



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


// app.get("/users",(req,res,next)=>{
//   con.query('SELECT * FROM users;', function(error, result, fields){
//     con.on('error', function(err){
//       console.log('[MYSQL]ERROR', err);
//     });
//     if(result && result.length){
//       res.end(JSON.stringify(result));
//     }
//     else{
//       res.end(JSON.stringify('no book here'));
//     }
//     console.log("hi");
//   });
// });

// app.listen(3308,()=>{
//   console.log('port 3308 running api');
// });

// app.on('close', function(){
//   console.log('closed')
//   // something to trigger close here
// });


app.post("/deletelisting", function(request, response) {
  console.log(request.body); //This prints the JSON document received (if it is a JSON document)
  var listingID = parseInt(request.body.listingID);

  var sql = `DELETE FROM listings WHERE listingID = ${listingID};`
  con.query(sql, function(err, result) {
    if (err) throw err;
    console.log(result.affectedRows + " record(s) updated");
    // req.flash('success', 'Data added successfully!');
    // res.redirect('/listings');
  });
});




