import {
	HTTP
} from '../utils/http.js';

export default class checkin extends HTTP {


	examineList (roleId) {
	 return this.request({
		url: '/examine/list/' + roleId,
		method: 'GET',
	  })
	}
	
	getStudent (storageId) {
	 return this.request({
		url: '/user/getStudent/' + storageId,
		method: 'GET',
	  })
	}
	
	getScoreInfo (userId) {
	 return this.request({
		url: '/examine/getInfo/' + userId,
		method: 'GET',
	  })
	}
	

	updateCheckIn () {
	 return this.request({
		url: '/user/list',
		method: 'GET',
	
	  })
	}

	//查询待处理时间事件详情
	selectCheck() {
	 return this.request({
		url: '/user/listPageC1/',
		method: 'POST',
	  })
	}
	//审批同意拒绝
	check(data) {
	 return this.request({
		url: '/examine/check',
		method: 'POST',
		data:data
	  })
	}

	insert(data) {
	 return this.request({
		url: '/examine/insert',
		method: 'POST',
		data:data
	  })
	}
	
	listStorage () {
	 return this.request({
		url: '/storage/appList',
		method: 'GET',
	
	  })
	}
	
} 