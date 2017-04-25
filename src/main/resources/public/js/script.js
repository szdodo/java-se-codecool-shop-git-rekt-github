$(document).on('click', '.filter_title', function () {
    $('.dropdown').addClass('show_filter');
})

$(document).ready(function () {

	$(document).on('click', '.btn', function(){
		event.preventDefault();
		var productId = $(this).data('product_id');
		$.ajax({
		url:'/addToCart?productId='+productId,
		type: "get",
		success: function(response){
				console.log(response);
			}
	});
	})
    
})


