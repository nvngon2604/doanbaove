app.controller("authority-ctrl", function($scope, $http, $location){
	$scope.roles = [];
	$scope.admins = [];
	$scope.authorities = [];
	$scope.authoritiesCust = [];
	$scope.users = [];
	$scope.itemsUser = [];
	$scope.formUser = {};
	
	$scope.statisticalUsers = [];
	$scope.statisticalCategories = [];
	$scope.statisticalProducts = [];
	$scope.statisticalOrders = [];
	
	$scope.orderDetailSuccess = [];
	$scope.orderDetailFail = [];
	$scope.totalRevenue = [];
	$scope.totalQuantity = [];
	
	$scope.authorityDirector = [];
	$scope.authorityStaff = [];
	
	$scope.initialize = function(){
		//load all roles
		$http.get("/rest/roles").then(resp => {
			$scope.roles = resp.data;
		})
		
		//load staffs and directors (adminstrators)
		$http.get("/rest/accounts?admin=true").then(resp => {
			$scope.admins = resp.data;
		})
		
		//load customers
		$http.get("/rest/accounts/user?cust=true").then(resp => {
			$scope.cust = resp.data;
		})
		
		//load authorities of staffs and directors
		$http.get("/rest/authorities?admin=true").then(resp => {
			$scope.authorities = resp.data;
		}).catch(error => {
		})
		
		//load authorities of customers
		$http.get("/rest/authorities/user?cust=true").then(resp => {
			$scope.authoritiesCust = resp.data;
		}).catch(error => {
		})
		
		//load all users
		$http.get("/rest/accounts/users").then(resp => {
			$scope.users = resp.data;
		})
		
		//load statistical users
		$http.get("/rest/statistical/users").then(resp => {
			$scope.statisticalUsers = resp.data;
		})
		
		//load statistical categories
		$http.get("/rest/statistical/categories").then(resp => {
			$scope.statisticalCategories = resp.data;
		})
		
		//load statistical products
		$http.get("/rest/statistical/products").then(resp => {
			$scope.statisticalProducts = resp.data;
		})
		
		//load statistical orders
		$http.get("/rest/statistical/orders").then(resp => {
			$scope.statisticalOrders = resp.data;
		})
		
		//load all orderDetail success
		$http.get("/rest/list/orders/success").then(resp => {
			$scope.orderDetailSuccess = resp.data;
		})
		
		//load all orderDetail fail
		$http.get("/rest/list/orders/fail").then(resp => {
			$scope.orderDetailFail = resp.data;
		})
		
		//load totalRevenue
		$http.get("/rest/list/orders/totalRevenue").then(resp => {
			$scope.totalRevenue = resp.data;
		})
		
		//load totalQuantity
		$http.get("/rest/list/orders/totalQuantity").then(resp => {
			$scope.totalQuantity = resp.data;
		})
		
		//load authorityDirector
		$http.get("/rest/authorities/directors").then(resp => {
			$scope.authorityDirector = resp.data;
		})
		
		//load authorityStaff
		$http.get("/rest/authorities/staffs").then(resp => {
			$scope.authorityStaff = resp.data;
		})
	}
	
	$scope.authority_of = function(acc,role){
		if($scope.authorities){
			return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
		}
	}
	
	$scope.authority_of_cust = function(acc,role){
		if($scope.authoritiesCust){
			return $scope.authoritiesCust.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
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
	
	$scope.authority_changed_cust = function(acc,role){
		var authority = $scope.authority_of_cust(acc,role);
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
			alert("Cấp quyền thất bại");
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
		$scope.formUser = angular.copy(item);
	}
	
	//xoa account
	$scope.deleteAccount = function(item){
		$http.delete(`/rest/accounts/users/${item.username}`).then(resp => {
			var index = $scope.itemsUser.findIndex(p => p.username == item.username);
			$scope.itemsUser.splice(index, 1);
			alert("Xóa tài khoản thành công");
		}).catch(error => {
			alert("Lỗi")
			console.log("Error", error);
		})
	}
	
	
	
	
	$scope.initialize();
	
	
});