$(document).ready(function() {  
	$('input[type="checkbox"]').bind('click', function() {
		var from_id = $(this).closest("form").attr("id");
		var arr_id = $(this).get(0).id;
		var my_input = from_id+':my_'+arr_id;
		if ($(this).is(':checked')) {    
			document.getElementById(my_input).value = "TRUE"; 
		} else {
			document.getElementById(my_input).value = "FALSE"; 
		} 
	});
	
	groups = $('[id^=section]');
	$.each(groups, function(key, group) {
		$(group).find('span, input').each(function (a,b) {
	        var $thisId = $(this);
	        if ($thisId.is("[id^=frm]")) {
	            myId = $thisId.attr("id");
	            var splitId = myId.split("my_");  
	            
//	            if ($thisId.val() == 'TRUE'){ 
//	            	$("switch1".prop('checked', true);
//	            } else {
//	            	$(splitId[1]).prop('checked', false);
//	            }  
	            console.info(splitId[1] + " : " +  $thisId.val());
	            $('#switch2').prop('checked', true);
	        }
	    });
	});
	

//	groups = $('[id^=section]');    
//	var i = 0;
//	$.each(groups, function(key, group) {
//		i = i + 1;
//	    var inputs = $(group).children('[id^=frm]'); 
//		var from_id =  $(inputs).closest("form").attr("id");
//		var arr_id =  $(inputs).get(0).id;  
//		$(inputs).val( "news here! " + i );
//		console.info(from_id);
//	});

	$('#switch2').prop('checked', true); 
});