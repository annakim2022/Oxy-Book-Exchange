  
const mysql = require('mysql');
const express = require('express');
const bodyparser = require('body-parser');

const con = mysql.createConnection({
  host: "34.102.17.159", //IP address of my Cloud SQL Server
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

app.get("/books",(req,res,next)=>{
  con.query('SELECT * FROM books;', function(error, result, fields){
    con.on('error', function(err){
      console.log('[MYSQL]ERROR', err);
    });
    if(result && result.length){
      res.end(JSON.stringify(result));
    }
    else{
      res.end(JSON.stringify('no book here'));
    }
    console.log("hi");
  });
});

app.listen(3308,()=>{
  console.log('port 3308 running api');
});



