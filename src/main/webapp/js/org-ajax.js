$(document).ready(function() {
//	alert("start")
	 $(document).on('click', '.info', function() {
//		 alert("click")
		 var $this = $(this);
	        if ($this.hasClass('refresh-button')) {
	            return false;
	        }
    	var data = {};
    	var productId = $this.attr('id');
//    	alert(productId);
        data = productId;
        data = {"org":productId};
        $.ajax({
            
        	dataType: 'html',
        	data: data,
            url : 'SelectValidity',
//            jsonpCallback: 'drawTable',
            	success : function (response) {
//           	alert(response);
           	
           	var serializedUser = JSON.stringify(response);
            		
            		var tomUser = JSON.parse(serializedUser);
            		
            alert(tomUser.date);
            	
            	  $('#rez').append(tomUser);
            	}
      
});
	
});
});
	 