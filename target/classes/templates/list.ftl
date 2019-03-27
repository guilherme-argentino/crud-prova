<#-- macro para gerar select a partir de Enum -->
<#macro enumSelect selectName enumValues modelName>
    <select ng-model="${modelName}" id="risco" class="form-control input-sm" required name="${selectName}">
        <#list enumValues as enum>
        <option value="${enum.name()}">${enum.name()}</option>
        </#list>
    </select>
</#macro>
<div class="generic-container">
    <div class="panel panel-default">
        <!-- Painel Principal -->
        <div class="panel-heading"><span class="lead">Cliente </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.cliente.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Nome do Cliente</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.cliente.name" id="uname" class="username form-control input-sm" placeholder="Digite o nome do Cliente" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="limite">Limite de Crédito</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.cliente.limite" id="limite" class="form-control input-sm" placeholder="Digite o limite desejado" required ng-currency min="1" hard-cap="true" />
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="risco">Risco</label>
	                        <div class="col-md-7">
	                        	<@enumSelect "risco-novo" riscos "ctrl.cliente.risco"/>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="juros">Juros</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.cliente.juros" id="juros" class="form-control input-sm" placeholder="Preencha os campos acima" readonly />
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="totalAPagar">Total a Pagar</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.cliente.totalAPagar" id="totalAPagar" class="form-control input-sm" placeholder="Preencha os campos acima" ng-currency readonly />
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.cliente.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Lista de Clientes </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>Nome</th>
		                <th>Limite</th>
		                <th>Risco</th>
		                <th>Juros</th>
		                <th>Total</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllUsers()">
		                <td>{{u.id}}</td>
		                <td>{{u.name}}</td>
		                <td>{{u.limite | currency}}</td>
		                <td>{{u.risco}}</td>
		                <td>{{u.juros}}</td>
		                <td>{{u.totalAPagar | currency}}</td>
		                <td><button type="button" ng-click="ctrl.editUser(u.id)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="ctrl.removeUser(u.id)" class="btn btn-danger custom-width">Remove</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>