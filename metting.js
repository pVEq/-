import {
	HTTP
} from '../utils/http.js';

export default class metting extends HTTP {

	userMetting (data) {	
	 return this.request({
		url: '/meetRecord/record',
		method: 'put',
		data:data
	  })
	}
	getMeetingById(id){
		return this.request({
			url: '/meeting/meeting/'+id,
			method: 'get'
		 })
	}
	getNowMettingList () {	
	 return this.request({
		url: '/meetRecord/record/getTodayRecord',
		method: 'get',
	  })
	}
	getOldMettingList () {
	 return this.request({
		url: '/meetRecord/record/getUserInfo',
		method: 'get',
	  })
	}
	
} 