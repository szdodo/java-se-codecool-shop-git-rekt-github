// $(document).on('click', '.filter_title', function () {
//     $('.dropdown').addClass('show_filter');
// })
//
// $(document).ready(function () {
//
// 	$(document).on('click', '.btn', function(){
// 		event.preventDefault();
// 		var productId = $(this).data('product_id');
// 		$.ajax({
// 		url:'/addToCart?productId='+productId,
// 		type: "get",
// 		success: function(response){
// 				console.log(response);
// 			}
// 	    });
// 	})
//
//     $('.shopping-cart').on('click', function (e) {
//
//         $('.cart-modal').toggleClass('active');
//
//     });
//
//     $('.close-modal').on('click', function (e) {
//
//         $('.cart-modal').removeClass('active');
//
//     });
//     $('.checkout-button').on('click', function (e) {
//
//         window.location.replace("/checkout");
//
//     });
//
//
//     $(document).on('click', '.fa-shopping-cart', function(){
//         $('.cart-item-container').empty();
//         $.ajax({
//             url:'/getCartContent',
//             type: "get",
//             success: function(data){
//             	var products = JSON.parse(data);
//                 for(var i = 0; i < products.length; i++){
//                     var name = products[i].name;
//                     var quantity = products[i].defaultCurrency;
//                     var price = products[i].defaultPrice;
//                     var cartItem = populate(name, quantity, price);
//                     renderCartItems(cartItem);
//                 }
//
//
//             }
//         });
//     })
//
//
//     var populate = function(name, quantity, price) {
//         var cartItem = `<div class="cart-item">
//                         <div class="item-name">`+name+`</div>
//                         <div class="item-quantity">quantity: `+quantity+`</div>
//                         <div class="item-price">price: `+price+`</div>
//                         <i class="fa fa-trash" aria-hidden="true"></i>
//                     </div>`
//         return cartItem;
//     }
//
//     var renderCartItems = function(cartItem) {
//         $('.cart-item-container').append(cartItem);
//     }

    //
    // var renderModalItems(name, quantity, price){
    //     $cartItem.find('.item-name').html(name);
    //     console.log($cartItem);
    // }
    
// })


