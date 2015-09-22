(function($){
	
	var initDestSearch = function(){
		$('#search_dests_in').magicSuggest({
			noSuggestionText:"",
			data : 'dest/suggestByName',
			name:'dests',
			displayField :'name',
			valueField:'id', 
			//dataUrlParams: { 'query' :'d' },
			editable : true,
		});		
	};
	var loadImage = function(){
		$('[data-original]').each(function(i,e){
			$(e).attr('src',$(e).data('original'));
		});
	};
	
	$(function(){
		initDestSearch();
		loadImage();
	});
})(jQuery);
