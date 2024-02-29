import {
	HTTP
} from '../utils/http.js';

export default class out extends HTTP {

	addOut (data) {	
	 return this.request({
		url: '/out/out',
		method: 'POST',
		data:data
	  })
	}
	
	
	 lookOut (id) {	
	 return this.request({
		url: '/out/out/updateStatus/' + id,
		method: 'GET',
	  })
	}
} 