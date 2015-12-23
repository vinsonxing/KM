var http = require('http');

var server = http.createServer({auth: function(req, res) {
	res.writeHead(200);
	res.end("hello world");
}}).listen(8000, function() {
	console.log('start listening port 8000');
});