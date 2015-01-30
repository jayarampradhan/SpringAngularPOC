'use strict';

/**
 * @ngdoc overview
 * @name terminalApp
 * @description It's the main Module for the terminal Apps.
 * # terminalApp
 *
 * Main module of the application.
 */

var TerminalApp = angular.module('TerminalApp', 
		['ngMessages', 'ui.bootstrap', 'cgNotify','TerminalServ', 'TerminalControllerCtrls']);
