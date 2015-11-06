var through2=require('through2');
var fs = require('fs');

// though2, read the content form target stream and go though it

// in this case, the content in ex.txt is abcd, after run the content in out.txt is zbcd

fs.createReadStream('ex.txt')
  .pipe(through2(function (chunk, enc, callback) {
    for (var i = 0; i < chunk.length; i++)
      if (chunk[i] == 97)
        chunk[i] = 122 // swap 'a' for 'z' 
 
    this.push(chunk)
 
    callback()
   }))
  .pipe(fs.createWriteStream('out.txt'))

