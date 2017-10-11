//定义控制器View1Controller
actionApp.controller('View1Controller', ['$rootScope', '$scope', '$http', function($rootScope, $scope, $http){

    //监听$viewContentLoaded事件
    $scope.$on('$viewContentLoaded', function(){
        console.log('页面加载完成');
    });

    //定义search方法，页面通过ng-click调用
    $scope.search = function(){
        personName = $scope.personName;     //获取页面中对应的ng-model的值
        $http.get('search', {       //get方法请求search路径
            params: {name: personName}        //参数
        }).success(function(data){      //成功回调
            $scope.person = data;       //赋值给模型person
        });
    };
}]);

//定义控制器View2Controller
actionApp.controller('View2Controller', ['$rootScope', '$scope', function($rootScope, $scope){
    $scope.$on('$viewContentLoaded', function(){
        console.log('页面加载完成');
    });
}]);