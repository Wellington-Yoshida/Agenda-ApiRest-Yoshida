var app = angular.module('app', []);

app.controller('ListarAgenda', function($scope, $http){
    $scope.getAgendas = function(){
      
        $http.get('http://localhost:8090/api/Agenda/listarAll')
            .then(function (response) {
                $scope.agendas = response.data;
            }

        );
    };

    $scope.verificaSeAgendaNaoEstaVazia = function(){
        return $scope.agendas ? true : false;
    };

    $scope.confirmarDeletaAgenda = function(agenda){
        $scope.agendaParaDeletar = agenda;    
    };

    $scope.deletaAgenda = function(agenda){
        $http.delete('http://localhost:8090/api/Agenda//remover/' + agenda.id)
        .then(function (response) {
            $scope.mensagem = response.data.sucesso;
        }

    )};

    $scope.verificaMensagem = function(){
        return $scope.mensagem ? true : false;
    };
});