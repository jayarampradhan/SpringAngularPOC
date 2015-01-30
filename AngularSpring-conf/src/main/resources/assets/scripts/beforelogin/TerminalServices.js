var TerminalServ = angular.module("TerminalServ", []);


TerminalServ.constant('TERMINAL_CONSTANT', (function() {
	  // Define your variable
	  var CON = {};
	  CON.STATUS = {
			INACTIVE:"IN",
			ACTIVE:"A",
			DELETED:"D",
				  
	  };
	  CON.RES = {
			  MSG: '',
			  CODE: ''
	  }
	  // Use the variable in your constants
	  return CON;
})());

//Terminal Group Mock 
TerminalServ.factory('TerminalGroupService', function ($q, TERMINAL_CONSTANT, TerminalService) {
	var TerminalGroup = {};
	TerminalGroup.init = function(){
		this.id = '';
		this.name = '';
		this.createdOn = '';
		this.deletedOn = '';
		this.status = TERMINAL_CONSTANT.STATUS.INACTIVE;
		this.terminals = [];
		return this;
	};
	//Create a new Terminal group will return a new instance
	TerminalGroup.createNew = function(id, name){
		//DO validate
		newTerminal = angular.copy(this.init());
		newTerminal.id = id;
		newTerminal.name = name;
		newTerminal.createdOn = new Date();
		newTerminal.status = TERMINAL_CONSTANT.STATUS.ACTIVE;
		return newTerminal; 
	};
	//Delete Group
	TerminalGroup.deleteGroup = function(){
		var deferred = $q.defer();
		var resp = angular.copy(TERMINAL_CONSTANT.RES);
		//DO validate
		var terminalExists = _.every(this.terminals, { 'status': TERMINAL_CONSTANT.STATUS.ACTIVE });
		if(terminalExists){
			resp.CODE = 405;
			res.MSG = 'Delete the Terminals first To delete this group';
			deferred.reject(res);
		}else{
			this.status = TERMINAL_CONSTANT.STATUS.DELETED;
			this.deletedOn = new Date();
			resp.CODE = 200;
			res.MSG = 'Successfully Deleted group: '+this.name;
			deferred.resolve(res);
		};
	};
	//Add new Terminal
	TerminalGroup.addTerminal = function(terminal){
		var deferred = $q.defer();
		var resp = angular.copy(TERMINAL_CONSTANT.RES);
		//DO validate
		this.terminals.push(terminal);
		terminal.status = TERMINAL_CONSTANT.STATUS.ACTIVE;
		resp.CODE = 200;
		resp.MSG = 'Successfully Added New Terminal';
		deferred.resolve(resp);
	};
	//Mark As Actieve 
	TerminalGroup.markAsActive = function(){
		this.status = TERMINAL_CONSTANT.STATUS.ACTIVE;
		var deferred = $q.defer();
		var resp = angular.copy(TERMINAL_CONSTANT.RES);
		resp.CODE = 200;
		resp.MSG = 'Group: '+this.name+' Has been activated';
		deferred.resolve(resp);
	};
	//Mark As InActieve 
	TerminalGroup.markAsInActive = function(){
		this.status = TERMINAL_CONSTANT.STATUS.INACTIVE;
		var deferred = $q.defer();
		var resp = angular.copy(TERMINAL_CONSTANT.RES);
		resp.CODE = 200;
		res.MSG = 'Group: '+this.name+' Has been marked as Inactieve.';
		deferred.resolve(res);
	};
	//Mark As Deleted 
	TerminalGroup.removeTerminal = function(terminal){
		console.log(terminal);
		var deferred = $q.defer();
		var resp = angular.copy(TERMINAL_CONSTANT.RES);
		resp.CODE = 200;
		console.log(this.terminals);
		this.terminals = _.pull(this.terminals, terminal);
		console.log(this.terminals);
		resp.MSG = 'Terminal Removed';
		deferred.resolve(resp);
	};
	//Is Actieve 
	TerminalGroup.isActive = function(){
		return TERMINAL_CONSTANT.STATUS.ACTIVE == this.status;
	};
	//Is Deleted 
	TerminalGroup.isDeleted = function(){
		return TERMINAL_CONSTANT.STATUS.DELETED == this.status;
	};
	var service = {};
	service.groups = [];
	//Creating the New Group
	service.createNewGroup = function(name){
		var deferred = $q.defer();
		var newGroup = angular.copy(TerminalGroup.init());
		newGroup.name = name;
		newGroup.id = _.size(service.groups)+1;
		service.groups.push(newGroup);
		var resp = angular.copy(TERMINAL_CONSTANT.RES);
		resp.CODE = 200;
		resp.MSG = 'Group: '+name+' Has been Created.';
		deferred.resolve(resp);
	};
	return service; 
});

//Terminal Mocks
TerminalServ.factory('TerminalService', function ($q, TERMINAL_CONSTANT) {
	var Terminal = {};
	var autoId = 100;
	console.log(TERMINAL_CONSTANT);
	Terminal.init = function(){
		this.id = '';
		this.name = '';
		this.locationName = '';
		this.status = TERMINAL_CONSTANT.STATUS.INACTIVE;
		this.createdOn = '';
		this.deletedOn = '';
		return this;
	};
	//Create a New Terminal
	Terminal.createNew = function(name, location){
		var newTerminal = angular.copy(Terminal.init());
		newTerminal.name = name;
		newTerminal.locationName = location;
		newTerminal.id = autoId++;
		newTerminal.status = TERMINAL_CONSTANT.STATUS.ACTIVE;
		return newTerminal;
	};
	var service = {};
	service.terminal = angular.copy(Terminal.init());
	return service;
});


//As soon as page loads this will be loaded to intialize
//Its Mocked Now
TerminalServ.run(function (TerminalGroupService, TerminalService) {
	var term1 = angular.copy(TerminalService.terminal.init()).createNew('Test', "Bangalore");
	var term2 = angular.copy(TerminalService.terminal.init()).createNew('Test1', "Delhi");
	var term3 = angular.copy(TerminalService.terminal.init()).createNew('Test2', "Mumbai");
	var term4 = angular.copy(TerminalService.terminal.init()).createNew('Test3', "Delhi");
	
	//Create Dummy Terminal
	TerminalGroupService.createNewGroup('TestGroup');
	_.forEach(TerminalGroupService.groups, function(n) { 
		console.log(n); 
		n.markAsActive();
		n.addTerminal(term1);
		n.addTerminal(term2);
		n.addTerminal(term3);
		n.addTerminal(term4);
	});
	
	console.log('Intialized the Terminal Groups');
});