  
  const mysql = require('mysql');

  const con = mysql.createConnection({
   
    host: "34.102.17.159", //IP address of my Cloud SQL Server
    user: 'root',
    password: 'oxybookexchange1',
    database: 'oxybookexchange'
  
  });


con.connect(function(err) {
  if (err) throw err;
  con.query("DELETE FROM books WHERE ISBN=1234567890123", function (err, result, fields) {
    if (err) throw err;
    console.log(result);
  });


});