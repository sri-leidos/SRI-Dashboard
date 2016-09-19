
'use strict';

(function($, window, document, undefined) {
	// Actives a fixed navigation on page scroll
	// Currently not working because the page is just static
	// in size.
	$('body').bind('scroll', function() {
         if ($('body').scrollTop() > 0) {
             $('.navbar-custom').addClass('navbar-fixed-top').css('margin-top', 0);
             $('.container-header').css('padding-top', '120px');
             $('#mainLogo').addClass('hidden');
             $('#mainLogo2').removeClass('hidden').css('margin-top', '-10px');
         }
         else {
             $('.navbar-custom').removeClass('navbar-fixed-top').css('margin-top', '40px');
             $('.container-header').css('padding-top', '0');
             $('#mainLogo').removeClass('hidden');
             $('#mainLogo2').addClass('hidden');
         }
    });
	// Slider control next button
	$('#nextSlide').bind('click', function(e) {
		e.preventDefault();
		
		var slides = $('.slide'); //Array of all slides
		var currentSlide = $('.slide.visible');
		var slideIndex = currentSlide.attr('id').split('slide')[1];
		
		currentSlide.removeClass('visible flipInX animated').addClass('hidden');
		
		if( slideIndex != slides.length ) {
			showSlide(slides[slideIndex]);
		} else {
			showSlide(slides[0]);
		}
		
		function showSlide(x) {
			$(x).removeClass('hidden').addClass('visible flipInX animated');
		}
		
	});
	// Slider control previous button
	$('#prevSlide').on('click', function(e) {
		e.preventDefault();
		
		var slides = $('.slide'); //Array of all slides
		var currentSlide = $('.slide.visible');
		var slideIndex = currentSlide.attr('id').split('slide')[1];
		
		currentSlide.removeClass('visible flipInX animated').addClass('hidden');
		
		if( slideIndex - 1 <= 0 ) {
			showSlide(slides[slides.length - 1]);
		} else {
			showSlide(slides[slideIndex - 2]);
		}
		
		function showSlide(x) {
			$(x).removeClass('hidden').addClass('visible flipInX animated');
			
		};
	});
	
})(jQuery, window, document, undefined);