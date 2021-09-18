app.controller("product-ctrl", function($scope, $http){
	$scope.items =[];
	$scope.itemsCategory =[];
	$scope.itemsOrder = [];
	$scope.itemsOrderDetail = [];
	$scope.orderDetail =[];
	$scope.form = {};
	$scope.formCategory = {};
	$scope.formOrder = {};
	$scope.formOrderDetail = {};
	
	$scope.initialize = function(){
		//load products
		$http.get("/rest/products").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate)
			})
		});
		
		//load categories
		$http.get("/rest/categories").then(resp => {
			$scope.categories = resp.data;
		});
		
		//load order detail
		$http.get("/rest/list/orders").then(resp => {
			$scope.orderDetail = resp.data;
		});
	}
	
	//khoi dau
	$scope.initialize();
	
	//xoa form
	$scope.reset = function(){
		$scope.form = {
			createDate: new Date(),
			image: 'no-image.png',
			available: true
		};
	}
	
	//xoa form category
	$scope.resetCategory = function(){
		$scope.formCategory = {
			id: "",
			name: ""
		};
	}
	
	//xoa form orderDetail
	$scope.resetOrderDetail = function(){
		$scope.formOrderDetail = {
			id: ""
		};
	}
	
	//hien thi len form
	$scope.edit = function(item){
		$scope.form = angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show')
	}
	
	//hien thi category len form
	$scope.editCategory = function(item){
		$scope.formCategory = angular.copy(item);
	}
	
	//hien thi orderDetail len form
	$scope.editOrderDetail = function(item){
		$scope.formOrderDetail = angular.copy(item);
	}
	
	//them san pham moi
	$scope.create = function(){
		var item = angular.copy($scope.form);
		$http.post(`/rest/products`, item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Thêm mới sản phẩm thành công ^^");
		}).catch(error => {
			alert("Vui lòng điền đầy đủ thông tin");
			console.log("Error", error);
		});
	}
	
	//them danh muc san pham
	$scope.createCategory = function(){
		var item = angular.copy($scope.formCategory);
		$http.post(`/rest/categories`, item).then(resp =>{
			resp.items.push(resp.data);
			alert("Thêm danh mục thành công");		
		}).catch(error => {
			console.log("Error", error);
		});
	}
	
	//cap nhat san pham
	$scope.update = function(){
		var item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			alert("Cập nhật sản phẩm thành công ^^");
		})
		.catch(error => {
			alert("Vui lòng điền đầy đủ thông tin");
			console.log("Error", error);
		})
	}
	
	//cap nhat danh muc san pham
	$scope.updateCategory = function(){
		var item = angular.copy($scope.formCategory);
		$http.put(`/rest/categories/${item.id}`, item).then(resp => {
			var index = $scope.itemsCategory.findIndex(p => p.id == item.id);
			$scope.itemsCategory[index] = item;
			$scope.resetCategory();
			alert("Cập nhật danh mục thành công");
		}).catch(error => {
			alert("Vui lòng điền đầy đủ thông tin");
			console.log("Error", error);
		})
	}
	
	//cap nhat don hang chi tiet
	$scope.updateOrderdetail = function(){
		var item = angular.copy($scope.formOrderDetail);
		$http.put(`/rest/list/orders/${item.id}`, item).then(resp => {
			var index = $scope.itemsOrderDetail.findIndex(p => p.id == item.id);
			$scope.itemsOrderDetail[index] = item;
			$scope.resetOrderDetail();
			alert('Cập nhật đơn hàng thành công');
		}).catch(error => {
			alert("Vui lòng điền đầy đủ thông tin");
			console.log("Error", error);
		})
	}
	
	
	//xoa san pham
	$scope.delete = function(item){
		var result = confirm("Xác nhận xóa sản phẩm ?");
			if (result) {
			    $http.delete(`/rest/products/${item.id}`).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items.splice(index, 1);
					$scope.reset();
					alert("Xóa sản phẩm thành công ^^");
				})
				.catch(error => {
					alert("Có lỗi");
					console.log("Error", error);
				});
			}		
	}
	
	//xoa danh muc san pham
	$scope.deleteCategory = function(item){
		var result = confirm("Xác nhận xóa danh mục sản phẩm ?");
			if (result) {
			    $http.delete(`/rest/categories/${item.id}`).then(resp => {
					var index = $scope.itemsCategory.findIndex(p => p.id == item.id);
					$scope.itemsCategory.splice(index, 1);
					$scope.resetCategory();
					alert("Xóa danh mục thành công");
				}).catch(error => {
					alert("Điền mã danh mục để xóa");
					console.log("Error", error);
				})
			}	
	}
	
	//xoa don hang
	$scope.deleteOrder = function(item){
		$http.delete(`/rest/orders/${item.id}`).then(resp => {
			var index = $scope.itemsOrder.findIndex(p => p.id == item.id);
			$scope.itemsOrder.splice(index, 1);
			$scope.resetOrderDetail();
			alert("Xóa đơn hàng thành công");
		}).catch(error => {
			console.log("Error", error);
		})
	}
	
	//xoa chi tiet don hang
	$scope.deleteOrderDetail = function(item){
		var result = confirm("Xác nhận xóa đơn hàng ?");
			if (result) {
			    $http.delete(`/rest/list/orders/${item.id}`).then(resp => {
					var index = $scope.itemsOrderDetail.findIndex(p => p.id == item.id);
					$scope.itemsOrderDetail.splice(index, 1);
					alert("Xóa đơn hàng thành công");
				}).catch(error => {
					alert("Vui lòng điền đầy đủ thông tin");
					console.log("Error", error);
				})
			}
	}
	
	
	$scope.imageChanged = function(files){
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: {'Content-Type': undefined}
		}).then(resp => {
			$scope.form.image = resp.data.name;
		}).catch(error => {
			alert("Không upload được hình ảnh");
			console.log("Error ", error);
		})
	}
	
	$scope.pager = {
		page: 0, //begin
		size: 10, //pageSize
		get items(){
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count(){
			return Math.ceil(1.0 * $scope.items.length / this.size)
		},
		first(){
			this.page = 0;
		},
		prev(){
			this.page--;
			if(this.page < 0){
				this.last();
			}
		},
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			}
		},
		last(){
			this.page = this.count - 1;
		}
	}
	
	$scope.orderByMe = function(x){
		$scope.myOrderBy = x;
	}
	
});