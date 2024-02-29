import {
	HTTP
} from '../utils/http.js';

export default class dept extends HTTP {

	getDeptsByPid (pid) {	
	 return this.request({
		url: '/system/dept/list2/'+pid,
		method: 'GET',
	  })
	}
} 