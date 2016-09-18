$("a[href='#']").click(function(e){
	var url, id, tmp_this, shoppingCartLabel;

	id = $(this).attr('id');
	tmp_this = this;
	e.preventDefault();

	if($(this).hasClass('submit-hidden-ajax')) {
		url = $('form[id="'+id+'"]').attr('action');
		data = $('form[id="'+id+'"]').serialize();
		$.ajax({
			url: url,
			type: 'POST',
			data: data
		}).done(function(data) {
			
			// Add to cart specific behaviour
			if($(tmp_this).html().indexOf('Add to Cart') >= 0){
				$(tmp_this).html('In Cart');
				$(tmp_this).removeAttr('href');
				$(tmp_this).removeClass('text-link-info');
				$(tmp_this).removeClass('submit-hidden-ajax');
				$(tmp_this).addClass('link-as-text');
				shoppingCartLabel = $('#shopping-cart').html().substring(0,$('#shopping-cart').html().indexOf('Shopping'));
				shoppingCartLabel += "Shopping Cart ("+data+")";
				$('#shopping-cart').html(shoppingCartLabel);
			}
		});
	}
});

$('.add-remove-cart:checkbox').change(function(){
	console.log('click');
});

$(':checkbox').change(function() {
	var count;
	count = $(".checked-count:checked").length;
	$("#checked-count-update").val('Remove from Cart ('+count+')');
	if(count > 0) {
		$('#checked-count-update').prop('disabled', false);
	} else {
		$('#checked-count-update').prop('disabled', true);
	}
});

$('.submit-checkout').click(function(e){
	e.preventDefault();
	$('.form-checkout').html($('.datasource-checkout').find($('input')));
	$('.form-checkout').submit();
});

$('.submit-cart-remove').click(function(e){
	e.preventDefault();
	$('.form-cart-remove').html($('.datasource-cart-remove').find($('input')));
	$('.form-cart-remove').submit();
});

$('.submit-signup-resend').click(function(e){
	e.preventDefault();
	$('.form-signup-resend').submit();
});

$(".dropdown-menu li a").click(function(e){
	var id;
	id = $(this).parent().parent().attr('id');
	if($(".btn#"+id).length > 0)
		$(".btn#"+id).html($(".btn#"+id).html().replace($(".btn#"+id).text().trim(), $(this).text()));
	if($("input#"+id).length > 0)
		$("input#"+id).val($(this).html().trim());
});

$(".dropdown-menu#dropdown-publication-type li a").click(function(e){
	if( $(this).html().trim() === 'Conference' || $(this).html().trim() === 'Any'){
		$('#enable-on-conference-selected').prop('disabled', false);
		$('#update-on-conference-selected').val("1");
	} else {
		$('#enable-on-conference-selected').prop('disabled', true);
		$('#update-on-conference-selected').val("0");
	}
});