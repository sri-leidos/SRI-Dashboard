


'use strict';

angular.module('SRI.DASHBOARD')
/*
 * @author Robert Roth
 * @desc This directive replaces an img src
 * on error with the given url link attribute 
 */
.directive('defaultImage', function() {
    return {
        link: function( scope, element, attrs ) {
          element.bind('error', function() {
//            if ( attrs.src != attrs.defaultImage ) {
              attrs.$set( 'src', attrs.defaultImage );
//            }
          });
        }
    }
});