import {
	HTTP
} from '../utils/http.js';

export default class login extends HTTP {

//登陆
	login (uid) {	
	 return this.request({
		url: '/appLogin/'+uid,
		method: 'GET',
	  })
	}
	
	getOpenId (uid) {
	 return this.request({
		url: '/getOpenId/'+uid,
		method: 'GET',
	  })
	}
	//注册
	register (data) {
		return this.request({
			url: '/user/insert',
			method: 'POST',
			data:data
	  })
	}
	update (data) {
	 return this.request({
		url: '/user/saveOrMod',
		method: 'POST',
		data:data
	  })
	}
	
	verifyUser (data) {
		return this.request({
			url: '/user/verifyUser',
			method: 'post',
			data
		})
	}
	getUserInfo (id) {
		return this.request({
			url: `/user/getUserInfo/${id}`,
			method: 'get',
		})
	}
	
} 