(function() {
	define(['app'], function(smc) {

		var FILTER_NAME = 'myFilter';
		var filter = function() {
			return function(input, option) {
				return input + option;
			}
		};

		smc.filter(FILTER_NAME, filter);
		return filter;

	});
})(this);