app.controller("user-ctrl", function($scope, $http, $location){
	$scope.roles = [];
	$scope.admins = [];
	$scope.authorities = [];
	$scope.users = [];
	$scope.formUsers = {};
	
	$scope.initialize = function(){
		//load all roles
		$http.get("/rest/roles").then(resp => {
			$scope.roles = resp.data;
		})
		
		//load staffs and directors (adminstrators)
		$http.get("/rest/accounts?admin=true").then(resp => {
			$scope.admins = resp.data;
		})
		
		//load authorities of staffs and directors
		$http.get("/rest/authorities?admin=true").then(resp => {
			$scope.authorities = resp.data;
		}).catch(error => {
		})
		
		//load all users
		$http.get("/rest/accounts/users").then(resp => {
			$scope.users = resp.data;
		})
	}
	
	$scope.authority_of = function(acc,role){
		if($scope.authorities){
			return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
		}
	}
	
	$scope.authority_changed = function(acc,role){
		var authority = $scope.authority_of(acc,role);
		if(authority){	//da cap quyen => thu hoi quyen (xoa)
			$scope.revoke_authority(authority);
		}else{ //chua duoc cap quyen => cap quyen(them moi)
			authority = {account: acc, role: role};
			$scope.grant_authority(authority);
		}
	}
	
	//them moi authority
	$scope.grant_authority = function(authority){
		$http.post(`/rest/authorities`, authority).then(resp => {
			$scope.authorities.push(resp.data)
			alert("Bạn đã cấp quyền thành công ^^");
		}).catch(error => {
			alert("	Cấp quyền thất bại");
			console.log("error", error);
		})
	}
	
	//xoa authority
	$scope.revoke_authority = function(authority){
		$http.delete(`/rest/authorities/${authority.id}`).then(resp => {
			var index = $scope.authorities.findIndex(a => a.id == authority.id);
			$scope.authorities.splice(index, 1);
			alert("Bạn đã thu hồi quyền sử dụng thành công");
		}).catch(error => {
			alert("Thu hồi quyền sử dụng thất bại");
			console.log("error", error);
		})
	}
	
	//hien thi user len form
	$scope.editUser = function(item){
		$scope.formUsers = angular.copy(item);
	}
	
	//xoa account
	$scope.deleteAccount = function(item){
		$http.delete(`/rest/accounts/users/${item.username}`).then(resp => {
			var index = $scope.users.findIndex(p => p.username == account.username);
			$scope.users.splice(index, 1);
			alert("Xóa tài khoản thành công");
		}).catch(error => {
			alert("Lỗi")
			console.log("Error", error);
		})
	}
	
	
	
	$scope.initialize();
	
	
});