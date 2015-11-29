window.addEventListener('message', function(e) {
  var $iframe = document.getElementById("launchButtonIframe");
  var eventName = e.data[0];
  var data = e.data[1];
  switch (eventName) {
  case 'setSize':
    var width = data.split('#')[0];
    var height = data.split('#')[1];
    $iframe.style.width = width+"px";
    $iframe.style.height = height+"px";
    break;
  }
}, false);

var frameDiv = document.createElement('div');
frameDiv.setAttribute('style', 'position:fixed;right:20px;bottom:20px;overflow:hidden;');
var iframe = document.createElement('iframe');
iframe.setAttribute('id', 'launchButtonIframe');
iframe.setAttribute('style', 'border:none;width:40px;height:40px;');
iframe.setAttribute('src', 'http://localhost:9000/#/launchbtn');
frameDiv.appendChild(iframe);
document.body.appendChild(frameDiv);
