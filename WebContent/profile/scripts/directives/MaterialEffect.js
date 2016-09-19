

angular.module('sri.directive.MaterialEffect', [])
	.directive('ngMaterial', function() {
		return {
			restrict: 'A',
			link: function( scope, element, attr ) {
				var container, material, diameter, x, y;
				// sadly jQuery is a dependency for now
				container = $( element );
				element.on('click', function( evnt ) {
					container.css({
						'overflow': 'hidden',
						'position': 'relative'
					});
					// create the ripple if it doesn't exist
					if( container.find('.material').length == 0 ) {
						container.prepend('<span class="material"></span>');
					}
					material = container.find('.material');
					// catch multiple clicks and just reanimate
					material.removeClass('animate-material');
					// set size of our ripple
					if( !material.height() && !material.width() )
					{
						// to create the circle, get the larger between height and width and use for our ripple
						diameter = Math.max(container.outerWidth(), container.outerHeight());
						material.css({height: diameter, width: diameter});
					}
					// get click coordinates
					x = evnt.pageX - container.offset().left - material.width()/2;
					y = evnt.pageY - container.offset().top - material.height()/2;
					// set the ripple's position and animate
					material.css({top: y+'px', left: x+'px'}).addClass('animate-material');
					// this is a fix for revealers like wowJS and scrollrevealJS
					material.on('animationend webkitAnimationEnd oanimationend MSAnimationEnd', function() {
						material.removeClass('animate-material');
						material.remove();
					})
				})
			}
		}
	})