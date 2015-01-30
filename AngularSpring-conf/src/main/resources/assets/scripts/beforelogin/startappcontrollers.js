'use strict';

/**
 * @ngdoc function
 * @name StartApp Controller
 * @description
 * # TerminalController
 * Controller of the Terminal
 */
var TerminalControllerCtrls = angular.module("TerminalControllerCtrls", []);
TerminalControllerCtrls.controller('TerminalCtrl', function ($scope, $modal, TerminalGroupService, TerminalService) {
	
	$scope.terminalGrops = TerminalGroupService.groups;
	$scope.terminal = TerminalService.terminal;
	
	//Open terminal form
	$scope.openTerminalForm = function (size, cugroup) {
		var modalInstance = $modal.open({
			templateUrl: URLS.base+'template/addTerminal',
			controller: 'AddTerminalModalInstanceCtrl',
			size: size,
			backdrop: 'static',
			resolve: {
				group: function(){
					console.log(size);
					return cugroup;
				}
			}
		});

		modalInstance.result.then(function (status) {
			console.log('sucess');
		}, function () {
			console.info('Modal dismissed at: ' + new Date());
			console.log('show lading end');
		});
	};
	
	//Open terminal Grop form
	$scope.openTerminalGroupForm = function (size) {
		var modalInstance = $modal.open({
			templateUrl: URLS.base+'template/addTerminalGroup',
			controller: 'AddTerminalGroupModalInstanceCtrl',
			size: size,
			backdrop: 'static',
			resolve: {
				
			}
		});

		modalInstance.result.then(function (status) {
			console.log('Register Completed, Navigate to the verify page'+status);
		}, function () {
			console.info('Modal dismissed at: ' + new Date());
			console.log('show lading end');
		});
	};
	
});


TerminalControllerCtrls.controller('AddTerminalModalInstanceCtrl', function ($scope, $http, $modalInstance, TerminalGroupService, TerminalService, group) {
	$scope.name = '';
	$scope.loc = '';
	$scope.locs = [];
	$scope.group = group;
	$scope.addTerminal = function () {
		var term = TerminalService.terminal.createNew($scope.name, $scope.loc);
		group.addTerminal(term);
		$modalInstance.close('sucess');
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
	//Get the location based on the group
	$http({
        method: 'GET',
        url: URLS.base+'lookup/loc/'+$scope.group.id,
    }).success(function (result) {
    	$scope.locs = result;
    });
	
});
//Add Terminal Group
TerminalControllerCtrls.controller('AddTerminalGroupModalInstanceCtrl', function ($scope, $modalInstance, TerminalGroupService) {
	
	$scope.name = '';
	$scope.addTerminalGroup = function () {
		TerminalGroupService.createNewGroup($scope.name);
		$modalInstance.close('sucess');
	};
	
	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
	
});
