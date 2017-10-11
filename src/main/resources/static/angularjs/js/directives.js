//定义一个指令datePicker
actionApp.directive('datePicker', function(){
   return {
       restrict: 'AC',      //限制为属性指令和样式指令
       link: function(scope, elem, attrs){      //
           elem.datepicker();       //初始化jQueryUI的datepicker
       }
   };
});