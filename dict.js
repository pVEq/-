import {
	HTTP
} from '../utils/http.js';

export default class dict extends HTTP {

//根据字典类型查询字典信息
	getDicts (dictType) {	
	 return this.request({
		url: '/system/dict/data/type/' + dictType,
		method: 'GET'
	  })
	}
} 