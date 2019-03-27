<!DOCTYPE html>

<html lang="en" ng-app="crudApp">
    <head>
        <title>${title}</title>
        <link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
    </head>
    <body>

        <div ui-view></div>
        
        <script src="/webjars/angularjs/angular.min.js"></script>
		<script src="/webjars/angular-i18n/angular-locale_pt-br.js"></script>
        <script src="/webjars/angular-ui-router/release/angular-ui-router.min.js"></script>
        <script src="/webjars/ng-currency/dist/ng-currency.js"></script>
        <script src="js/lib/localforage.min.js"></script>
        <script src="js/lib/ngStorage.min.js"></script>
        <script src="js/app/app.js"></script>
        <script src="js/app/ClienteService.js"></script>
        <script src="js/app/ClienteController.js"></script>
    </body>
</html>