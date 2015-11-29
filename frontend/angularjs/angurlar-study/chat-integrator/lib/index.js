
'use strict';
angular.module('chat-integrator', ['propel-endpoints'])
.run(['propelEndpoints', '$window', function(propelEndpoints, $window) {

	var embedChat = function(win, chat) {
		var chatUrl = chat.url;
		win.addEventListener('message', function(e) {
			if (chatUrl.indexOf(e.origin) === -1) return;
			var $iframe = document.getElementById("launchButtonIframe");
			var eventName = e.data[0];
			var data = e.data[1];
			switch (eventName) {
				case 'setSize':
					var width = data.split('#')[0];
					var height = data.split('#')[1];
					$iframe.style.width = width + "px";
					$iframe.style.height = height + "px";
					break;
				default:
					break;
			}
		}, false);

		var iframe = $('<iframe></iframe>');
		var styleIF = $.extend({
			'border': 'none',
			'width': '40px',
			'height': '40px',
			'position':'fixed',
			'right':'20px',
			'bottom':'20px',
			'overflow':'hidden'
		}, chat.styles);
		iframe.css(styleIF);
		iframe.attr({
			'id': 'launchButtonIframe',
			'src': chatUrl
		});

		$(win.document.body).append(iframe);

	};

	propelEndpoints().then(function(data) {
		// mock
		/*data = [{
			name: 'chat',
			url: 'https://localhost:9000/#/launchbtn',
			enable: true
		}];*/
		var chat = data.filter(function(el, index) {
			return el.name === 'chat';
		});

		console.log(chat[0]);

		// load chat feature if it is enabled.
		if (chat[0] && !!chat[0].enable === true) {
			embedChat($window, chat[0])
		}
	}, function(err) {
		throw new Error('Get endpoints failed. ' + err);
	})
}]);
