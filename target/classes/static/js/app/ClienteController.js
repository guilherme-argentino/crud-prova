'use strict';

angular.module('crudApp').controller('ClienteController',
    ['ClienteService', '$scope',  function( ClienteService, $scope) {

        var self = this;
        self.cliente = {};
        self.clientes=[];

        self.submit = submit;
        self.getAllUsers = getAllUsers;
        self.createUser = createUser;
        self.updateUser = updateUser;
        self.removeUser = removeUser;
        self.editUser = editUser;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.cliente.id === undefined || self.cliente.id === null) {
                console.log('Saving New Cliente', self.cliente);
                createUser(self.cliente);
            } else {
                updateUser(self.cliente, self.cliente.id);
                console.log('Cliente updated with id ', self.cliente.id);
            }
        }

        function createUser(user) {
            console.log('About to create Cliente');
            ClienteService.createUser(user)
                .then(
                    function (response) {
                        console.log('Cliente created successfully');
                        self.successMessage = 'Cliente created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.cliente={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Cliente');
                        self.errorMessage = 'Error while creating Cliente: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateUser(user, id){
            console.log('About to update Cliente');
            ClienteService.updateUser(user, id)
                .then(
                    function (response){
                        console.log('Cliente updated successfully');
                        self.successMessage='Cliente updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Cliente');
                        self.errorMessage='Error while updating Cliente '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeUser(id){
            console.log('About to remove Cliente with id '+id);
            ClienteService.removeUser(id)
                .then(
                    function(){
                        console.log('Cliente '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing Cliente '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllUsers(){
            return ClienteService.getAllUsers();
        }

        function editUser(id) {
            self.successMessage='';
            self.errorMessage='';
            ClienteService.getUser(id).then(
                function (user) {
                    self.cliente = user;
                },
                function (errResponse) {
                    console.error('Error while removing Cliente ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.cliente={};
            $scope.myForm.$setPristine(); //reset Form
        }
        
        $scope.$watch('ctrl.cliente.limite', function(newValue,oldValue){
        	console.log('Tentativa de calcular detectada pelo valor do limite');
            if(newValue != oldValue)
            {
                $scope.changeInterest();
            }
        });
        
        $scope.$watch('ctrl.cliente.risco', function(newValue,oldValue){
        	console.log('Tentativa de calcular detectada pela classificacao risco');
            if(newValue != oldValue)
            {
                $scope.changeInterest();
            }
        });
        
        $scope.changeInterest = function(){
            var limite = 0;
            var risco = 0;
            
            limite = $scope.ctrl.cliente.limite;
            if($scope.ctrl.cliente.risco == "A") {
            	risco = 1;
            	$scope.ctrl.cliente.juros = "0%"
            } else if ($scope.ctrl.cliente.risco == "B") {
            	risco = 1.1;
            	$scope.ctrl.cliente.juros = "10%"
            } else if ($scope.ctrl.cliente.risco == "C") {
            	risco = 1.2;
            	$scope.ctrl.cliente.juros = "20%"
            }
            
            if(risco > 0 && limite > 0)
            {
                $scope.ctrl.cliente.totalAPagar = risco * limite;
            }          
        }
    }


    ]);