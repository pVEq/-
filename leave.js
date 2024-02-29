import {
	HTTP
} from '../utils/http.js';

export default class leave extends HTTP {

	addLeave (data) {	
	 return this.request({
		url: '/leave/leave',
		method: 'POST',
		data:data
	  })
	}
} 