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

    $('.shopping-cart').on('click', function (e) {

        $('.cart-modal').toggleClass('active');

    });

    $('.close-modal').on('click', function (e) {

        $('.cart-modal').removeClass('active');

    });
    $('.checkout-button').on('click', function (e) {

        window.location.replace("/checkout");

    });


    $(document).on('click', '.fa-shopping-cart', function(){
        $.ajax({
            url:'/getCartContent',
            type: "get",
            success: function(data){
            	var object = JSON.parse(data);
                console.log(object);
            }
        });
    })
    
})


