<template>
	<view style="background-color: #FFFFFF;">
			<cu-custom bgColor="bg-gradual-pink" :isBack="true">
				<block slot="content">上报</block>
			</cu-custom>
			<view style="padding-left: 30rpx;padding-right: 30rpx;">
				<u-form :model="form" ref="uForm">
					<u-form-item :required="true" label="姓名"  prop="name"><u-input v-model="form.name" /></u-form-item>
					<u-form-item :required="true"label="性别"  prop="sex">
							<u-radio-group v-model="form.sex" >
									<u-radio 
										name="男">
										男
									</u-radio>
									<u-radio style="margin-left: 50rpx;" 
										name="女">
										女
									</u-radio>
							</u-radio-group>
					</u-form-item>
					<u-form-item :required="true"labelWidth="150" label="联系方式"  prop="phone"><u-input  type="number" v-model="form.phone" /></u-form-item>
					<u-form-item :required="true"labelWidth="160" label="身份证号码"  prop="idCard"><u-input type="number" v-model="form.idCard" /></u-form-item>
					<u-form-item labelWidth="160" label="身份证照片"  prop="cardImg">
						<u-upload  ref="upload" :action="fileUploadUrl" :file-list="fileList" :header="header" @on-success="uploadSuccess" @on-remove = "remove" ></u-upload>
					</u-form-item>
					<u-form-item :required="true"labelWidth="160" label="欠薪建设单位名称"  prop="qxaJsCompanyName"><u-input v-model="form.qxaJsCompanyName" /></u-form-item>
					<u-form-item labelWidth="160" label="相关负责人联系方式"  prop="qxJsAdminPhone"><u-input type="number" v-model="form.qxJsAdminPhone" /></u-form-item>
					<u-form-item :required="true"labelWidth="160" label="欠薪总包单位名称"  prop="qxZbCompanyName"><u-input v-model="form.qxZbCompanyName" /></u-form-item>
					<u-form-item labelWidth="160" label="相关负责人联系方式" prop="qxZbAdminPhone"><u-input type="number"  v-model="form.qxZbAdminPhone" /></u-form-item>
					<u-form-item :required="true"labelWidth="160" label="雇佣老板联系方式" prop="bossPhone"><u-input type="number" v-model="form.bossPhone" /></u-form-item>
					<u-form-item :required="true"labelWidth="160" label="实际工作地" prop="workAddress"><u-input v-model="form.workAddress" /></u-form-item>
					<u-form-item :required="true"labelWidth="160" label="欠薪人数" prop="qxPeopleNum"><u-input type="number" v-model="form.qxPeopleNum" /></u-form-item>
					<u-form-item :required="true"labelWidth="180"label="欠薪金额(元)" prop="qxMoney"><u-input type="number" v-model="form.qxMoney" /></u-form-item>
					<u-form-item :required="true"labelWidth="180"label="欠薪起时间" prop="qxStartTime"  @click="dateShow = true; dateFeild ='qxStartTime'" >
						<u-input v-model="form.qxStartTime"   @click="dateShow = true; dateFeild ='qxStartTime' "/>
					</u-form-item>
					
					<u-form-item :required="true"labelWidth="180"label="欠薪止时间" prop="qxEndTime">
						<u-input v-model="form.qxEndTime" type="select" @click="dateShow = true; dateFeild ='qxEndTime' "   />
					</u-form-item>
					
					<u-form-item :required="true"labelWidth="160"label="所属领域" prop="qxLingyu">
							<u-input v-model="form.qxLingyu" type="select"   @click="qxLingyuShow = true"  />
					</u-form-item>
					<u-form-item :required="true"labelWidth="250"label="是否签订劳动合同" prop="isHetong">
						<u-radio-group v-model="form.isHetong" >
								<u-radio 
									name="是">
									是
								</u-radio>
								<u-radio style="margin-left: 50rpx;" 
									name="否">
									否
								</u-radio>
						</u-radio-group>
					</u-form-item>
					<u-form-item :required="true"labelWidth="100"label="欠薪情况概述"  prop="remark"><u-input type="textarea" v-model="form.remark" /></u-form-item>
				</u-form>
				<u-picker mode="time" v-model="dateShow" @confirm="formatShowConfirm"
							:params=" {
							year: true,
							month: true,
							day: true,
						}"></u-picker>
				<view style="height: 200rpx;"></view>
			</view>
			<u-select v-model="qxLingyuShow" :list="lingyuDistList" @confirm="qxSelectConfim"></u-select>
			<view style="position:fixed ; bottom: 0rpx; z-index: 9; background-color:#FFFFFF;width: 750rpx;height: 100rpx;">
				<view style="display: flex; align-items: center; height: 100rpx; padding: 20px;">
					<button class="flex-sub cu-btn block bg-blue" @click="submit()">
						提交
					</button>
				</view>
			</view>
	</view>

</template>


<script>
	import {getTokenStorage} from '@/common/service/service.js'
	import configService from '@/common/service/config.service.js'
	import api from "@/api/api.js"
	import qx from "@/api/qx.js"
export default {
	data() {
		return {
			qxLingyuShow:false,
			fileUploadUrl:"",
			header:{},
			fileList:[],
			dateShow:false,
			dateFeild:"",
			lingyuDistList:[],
			form: {
					name: '',
					intro: '',
					sex: ''
				},
			rules: {
				name: [
					{ 
						required: true, 
						message: '请输入姓名', 
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change','blur'],
					}
				],
				sex: [
					{ 
						required: true, 
						message: '请输入性别', 
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change','blur'],
					}
				],
				phone: [
					{ 
						required: true, 
						message: '请输入手机号码', 
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change','blur'],
					}
				],
				idCard: [
					{ 
						required: true, 
						message: '请输入身份证号码', 
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change','blur'],
					}
				],
				qxaJsCompanyName: [
					{ 
						required: true, 
						message: '请输入欠薪建设单位名称', 
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change','blur'],
					}
				],
				qxZbCompanyName: [
					{ 
						required: true, 
						message: '请输入欠薪总包单位名称', 
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change','blur'],
					}
				],
				bossPhone: [
					{ 
						required: true, 
						message: '请输入雇佣老板联系方式', 
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change','blur'],
					}
				],
				workAddress: [
					{ 
						required: true, 
						message: '请输入实际工作地', 
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change','blur'],
					}
				],
				qxPeopleNum: [
					{ 
						required: true, 
						message: '请输入欠薪人数', 
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change','blur'],
					}
				],
				qxMoney: [
					{ 
						required: true, 
						message: '请输入欠薪金额', 
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change','blur'],
					}
				],
				qxStartTime: [
					{ 
						required: true, 
						message: '请输入欠薪金额', 
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change','blur'],
					}
				],
				qxMoney: [
					{ 
						required: true, 
						message: '请输入欠薪起止时间', 
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change','blur'],
					}
				],
				qxLingyu: [
					{ 
						required: true, 
						message: '请输入所属领域', 
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change','blur'],
					}
				],
				isHetong: [
					{ 
						required: true, 
						message: '请输入是否签订劳动合同', 
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change','blur'],
					}
				],
				remark: [
					{ 
						required: true, 
						message: '请输入欠薪情况概述', 
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change','blur'],
					}
				],
			}
		};
	},
	onLoad(option) {
		console.log(option);
		let id = option.id;
		if(id != null){
			qx.queryById({id:id}).then(res=>{
				this.form = res.data.result;
				this.showOldImg();
			});
		}
		
		this.header['X-Access-Token'] = getTokenStorage();
		this.fileUploadUrl = configService.apiUrl+"/sys/common/upload";
		this.getLingYuList();
	},
	methods: {
		sexSelect(e) {
			this.model1.userInfo.sex = e.name
			this.$refs.uForm.validateField('userInfo.sex')
		},
		showOldImg(){
			if(	this.form.cardImg != null){
				this.fileList = JSON.parse(this.form.cardImg);
			}
		},
		uploadSuccess(data, index, lists, name){
			this.$refs.upload.clear();
			let file = {
				fileUrl:data.message
			}
			let url = api.getFileAccessHttpUrl(data.message);
			let fileItems = {
				url:url
			}
			this.fileList.push(fileItems);
		},
		remove(data, index, lists, name){
			this.fileList.splice(index,1);
		},
		getLingYuList(){
			api.getDictItems({dictCode:"suoshulingyu"}).then(res=>{
				console.log(res.data);
				this.lingyuDistList = res.data;
			})
		},
		qxSelectConfim(data){
			this.form.qxLingyu = data[0].value;
		},
		submit(){
			this.form.cardImg = JSON.stringify(this.fileList);
			qx.add(this.form).then(res=>{})
		},
		formatShowConfirm(data){
			let dataStr = "";
			if(data.year != null){
				dataStr += data.year +"-";
			}
			if(data.month != null){
				dataStr += data.month +"-";
			}
			if(data.day != null){
				dataStr += data.day;
			}
			if(data.hour != null){
				if(data.day != null){
					dataStr += " ";
				}
				dataStr += data.hour +":";
			}
			if(data.minute != null){
				dataStr += data.minute;
			}
		
			this.form[this.dateFeild] = dataStr;
		},
	},
	onReady() {
		//如果需要兼容微信小程序，并且校验规则中含有方法等，只能通过setRules方法设置规则。
    		this.$refs.uForm.setRules(this.rules);
    },
};
</script>
<style lang="scss"></style>