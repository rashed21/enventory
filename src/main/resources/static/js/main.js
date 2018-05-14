$( document ).ready(function() {
	
	ajaxGet();
	// SUBMIT FORM
    $("#colorForm").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPost();
		ajaxGet();
	});
    
    
    function ajaxPost(){
    	
    	// PREPARE FORM DATA
    	var formData = {
    			code : $("#code").val(),
    			name :  $("#name").val()
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/admin/color/save",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "Done"){
					console.log(result);
				}else{
					console.log(result);
				}
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    	
    	// Reset FormData after Posting
    	resetData();
    }
    
    function resetData(){
    	$("#code").val("");
    	$("#name").val("");
    }
    
    
    //Get all color from server side-------------------
    
 // GET REQUEST
/*	$("#getAllcolor").click(function(event){
		event.preventDefault();
		ajaxGet();
	});
	*/
	// DO GET
	function ajaxGet(){
		$.ajax({
			type : "GET",
			url  : "/admin/color/all",
			success: function(result){
				
				console.log("Retriving data from database");
				console.log(result);
				
				$.each(result, function(i, color){
					
					var colorsGrid = '<tr>' +
										'<td>' + color.code + '</td>' +
										'<td>' + color.name + '</td>'+
									  '</tr>';
					
					$('#getAllcolor tbody').append(colorsGrid);
					
		        });
				
//				$( "#getAllcolor tbody tr:odd" ).addClass("info");
//				$( "#getAllcolor tbody tr:even" ).addClass("success");
			},
			error : function(e) {
				/*$("#getResultDiv").html("<strong>Error</strong>");*/
				console.log("ERROR: ", e);
			}
		});	
	}
    
})