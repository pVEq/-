import {
	HTTP
} from '../utils/http.js';

export default class act extends HTTP {

	getUserAct (pid) {	
	 return this.request({
		url: '/act/selectAct',
		method: 'GET',
	  })
	}
	
	getActInfo (id,type) {
	 return this.request({
		url: '/act/getInfoAct/'+id+'/'+type,
		method: 'GET',
	  })
	}
	
	agreeAct(lid,type){
		return this.request({
			url: '/act/agree/'+lid+"/"+type,
			method: 'GET',
		})
	}
	agreedisAct(lid,type){
		return this.request({
			url: '/act/agreeids/'+lid+"/"+type,
			method: 'GET',
		})
	}
	
	getStatus (status){
		if(status=='SUBMIN'){
			return "待审核"
		}else if(status=='AGREE'){
			return "通过"
		}else{
			return "未通过"
		}
	}
	
	
	getOperation (status){
		if(status=='START'){
			return "发起申请"
		}else if(status=='PASS'){
			return "同意"
		}else {
			return "未同意"
		}
	}
} 