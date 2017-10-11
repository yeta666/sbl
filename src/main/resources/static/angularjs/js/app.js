//定义模块actionApp，并依赖路由模块ngRoute
var actionApp = angular.module('actionApp', ['ngRoute']);

//配置路由
actionApp.config(['$routeProvider'], function($routeProvider){
    $routeProvider.when('/oper', {      //路由名称
        controller: 'View1Controller',      //路由的控制器名称
        templateUrl: './view1.html',        //视图的真正地址
    }).when('/directive', {
        controller: 'View2Controller',
        templateUrl: './view2.html',
    });
});