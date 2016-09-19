

angular.module('sri.config.SiteRouter', ['ui.router'])
	.config(['$urlRouterProvider', '$stateProvider', 
         function siteRouteConfig($urlRouterProvider, $stateProvider) {
			$urlRouterProvider.otherwise('/site');
			$stateProvider
				// State for Listing all sites
				.state('listSites', {
					url: '/site',
					templateUrl: 'admin/templates/site/list-site.html',
					controller: 'sri.ListSitesCTRL',
					resolve: {
						sites: ['Site', function( Site ) {
							return Site.getAll().then( function( response ) {
								return response;
							});
						}]
					}
				})
				// State for editing specific sites
				.state('editSite', {
					url: '/site/edit/:siteId',
					templateUrl: 'admin/templates/site/edit-site.html',
					controller: 'sri.EditSiteCTRL',
					resolve: {
						site: ['Site', '$stateParams', function( Site, $stateParams ) {
							return Site.get( $stateParams.siteId ).then(function( response ) {
								return response;
							});
						}]
					}
				})
				// State for creating sites
				.state('createSite', {
					url: '/site/create',
					templateUrl: 'admin/templates/site/create-site.html',
					controller: 'sri.CreateSiteCTRL',
					resolve: {
						stateList: ['$http', function( $http ) {
							return $http.get('/DashCon/admin/resources/states.json').then(function( response ) {
								return response.data;
							});
						}],
						siteTypes: ['$http', function( $http ) {
							return $http.get('/DashCon/resources/site/admin/types').then(function( response ) {
								return response.data.siteType;
							});
						}]
					}
				})
		}])