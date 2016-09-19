
/*
 * @author		Robert Roth
 * 
 * @descripion	Main application; initialize our angular app
 * 
 * @dependency	sri.factory.User(./services/UserFactory.js)
 * 
 * @controllers	
 */		

angular.module('app:profile', [
     // Services
     'sri.factory.User',
     'sri.factory.Site',
     'sri.service.Profile',
     'sri.directive.Notifyr',
     'sri.directive.MaterialEffect',
     'profile.Router'
 ])
// controllers
.controller('profile.NavigationCtrl', ['$scope', 'User', NavigationCtrl])
.controller('profile.EditCtrl', ['$scope', 'Profile', 'user', EditProfileCtrl])
.controller('profile.MainCtrl', ['$scope', 'user', 'site', MainCtrl])





