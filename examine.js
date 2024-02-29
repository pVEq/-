import {
	HTTP
} from '../utils/http.js';

export default class examine extends HTTP {

	examineList () {	
	 return this.request({
		url: '/examine/list',
		method: 'GET',
	  })
	}
	getPendingApproval (id) {
		 return this.request({
			url: `/examine/getPendingApproval?userId=${id}`,
			method: 'GET',
		})
	}
	
} 