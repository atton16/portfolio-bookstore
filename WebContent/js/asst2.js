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
			
			// Remove Pub specific behaviour
			if($(tmp_this).hasClass('submit-ajax-remove')){
				$(tmp_this).html('Removed');
				$(tmp_this).removeAttr('href');
				$(tmp_this).removeClass('submit-hidden-ajax');
				$(tmp_this).addClass('link-as-text');
			}
			
			// Ban-Unban specific behaviour
			if($(tmp_this).hasClass('ban-unban-toggle')){
				console.log(id);
				if($(tmp_this).text() === 'Ban'){
					$(tmp_this).text('Unban');
					$(tmp_this).attr('id', id.substring(0,id.length-4)+'-unban');
				} else {
					$(tmp_this).text('Ban');
					$(tmp_this).attr('id', id.substring(0,id.length-6)+'-ban');
				}
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

$(':checkbox.submit-hidden-ajax').change(function() {
	var url, id, tmp_this, checked;
	id = $(this).attr('id');
	checked = $(this).prop('checked');
	if(checked)
		url = $('form[id="'+id+'-checked"]').attr('action');
	else
		url = $('form[id="'+id+'-unchecked"]').attr('action');
	data = $(this).attr('name')+"="+$(this).val();

	$.ajax({
		url: url,
		type: 'POST',
		data: data
	});
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

$(".disable-input-on-publication-type-select li a").click(function(e){
	if($(this).text() === "Publication Type"){
		$("input.disable-input-on-publication-type-select").prop('disabled', true);
	} else {
		$("input.disable-input-on-publication-type-select").prop('disabled', false);
	}
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

$(".dropdown-menu#dropdown-search-type-users li a").click(function(e){
	if( $(this).html().trim() === 'All Customers' || $(this).html().trim() === 'All Sellers'){
		$('#disable-on-all-customers-or-sellers-selected').prop('disabled', true);
	} else {
		$('#disable-on-all-customers-or-sellers-selected').prop('disabled', false);
	}
});

$("form.submit-ajax").submit(function(e){
	var url, data, method;
	e.preventDefault();
	url = $(this).attr('action');
	data = $(this).serialize();
	method = $(this).attr('method');

	$(".submit-ajax-empty-result").slideUp();
	$(".submit-ajax-result").slideUp();
	$.ajax({
		url: url,
		type: method,
		data: data
	}).always(function (jqXHR, textStatus) {
		if (textStatus === 'success') {
			data = JSON.parse(jqXHR);
			id = data.id;
			title = data.title;
			authors = data.authors;
			editors = data.editors;
			picurl = data.picurl;
			price = data.price;
			seller = data.seller;
			listed = data.listed;
			authorsEditorsStr = "";
			for(var i = 0; i < authors.length; i++){
				authorsEditorsStr += authors[i];
				if(i+1 < authors.length)
					authorsEditorsStr += ", ";
			}
			for(var i = 0; i < editors.length; i++){
				authorsEditorsStr += editors[i];
				if(i+1 < editors.length)
					authorsEditorsStr += ", ";
			}
			$("#submit-ajax-title").html(title);
			$("#submit-ajax-authors-and-editors").html(authorsEditorsStr);
			$("#submit-ajax-title").attr('href', $("#submit-ajax-context-path").val()+"/pubinfo?id="+id);
			$("#submit-ajax-picurl").attr('src', picurl);
			$("#submit-ajax-price").html("A$"+price);
			$("#submit-ajax-seller").html("Seller: "+seller);
			$("#submit-ajax-listed").html("Listed: "+listed);
			$(".submit-ajax-remove").attr('id', id);
			$("a.submit-ajax-remove").html('Remove');
			$("a.submit-ajax-remove").attr('href', '#');
			if(!$("a.submit-ajax-remove").hasClass('submit-hidden-ajax'))
				$("a.submit-ajax-remove").addClass('submit-hidden-ajax');
			$("a.submit-ajax-remove").removeClass('link-as-text');
			$(".submit-ajax-result").slideDown();
		} else if (textStatus === 'nocontent') {
			$(".submit-ajax-empty-result").slideDown();
		}
	});
});