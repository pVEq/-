<template>
	<view class="box">
		<!-- <u-tabs :list="list" :is-scroll="false" :current="current" @change="change"></u-tabs> -->
		<u-form :model="form" ref="uForm">
			<u-form-item label="事由" label-position=top prop="reason">
				<u-input  placeholder="请输入" v-model='form.reason'  />
			</u-form-item>
			<view class="">
				<u-form-item label="获得日期" label-position=top>
					<u-input type="select" placeholder="请选择" @click="startTime = true" v-model='form.date' />
				</u-form-item>
				<u-form-item label="申请日期" label-position=top>
					<u-input type="select" placeholder="请选择" @click="endTime = true" v-model='form.createTime' />
				</u-form-item>
			</view>
			<view class="">
				<view class="flex">
					<text>获奖明细</text>
			<!-- 		<text style="padding-right: 50rpx; color:#35a3ef ;" @click="del(index)">删除</text> -->
				</view>
				<u-form-item label="级别" label-position=top>
					<u-input v-model="form.type" type="select"   @click="typeShow = true"  />
				</u-form-item>
				<u-form-item label="类型" label-position=top>
					<u-input v-model="form.level" type="select"   @click="typeShow2 = true"  />
				</u-form-item>
				<u-form-item label="名称" label-position=top>
					<u-input placeholder="请输入" v-model="form.name" />
				</u-form-item>
				
				
				<u-form-item labelWidth="160" label="佐证"  prop="cardImg"> 
					<u-upload  ref="upload" :action="fileUploadUrl" :file-list="fileList" :header="header" @on-success="uploadSuccess" @on-remove = "remove" ></u-upload>
				</u-form-item>
				
<!-- 				<u-form-item label="其他要求" label-position=top>
					<u-upload :action="action" :file-list="fileList" ></u-upload>
				</u-form-item> -->
				<!-- <text>如需多种奖项，请点击"增加申请"</text> -->

			</view>
			<!-- <u-form-item style="text-align: center;">
				<view class="add" @click="addTab">
					+增加申请
				</view>
			</u-form-item> -->
			<!-- <u-form-item label="备注" label-position=top>
				<u-input placeholder="请输入" v-model = 'form.reason' />
			</u-form-item> -->
		</u-form>
		<u-button type="primary" :custom-style="customStyle" @click='sumblie'>提交</u-button>

		<u-select v-model="typeShow" :list="list" @confirm='typeClick'></u-select>
		<u-select v-model="typeShow2" :list="list2" @confirm='type2Click'></u-select>
		<!-- 用车日期 -->
		<u-picker mode="time" v-model="startTime" :params="params" @confirm='sClickDate'></u-picker>
		<!-- 返回日期 -->
		<u-picker mode="time" v-model="endTime" :params="params" @confirm='eClickDate'></u-picker>
		<!-- 消息提示 -->
		<u-toast ref="uToast" />
		<!-- 确定成功弹框 -->
		<view>
			<u-modal v-model="show" :content="content" :show-cancel-button="false" @confirm="que"></u-modal>
		</view>
		<!-- 失败弹框 -->
		<view>
			<u-modal v-model="showS" :content="contentS" :show-cancel-button="false"></u-modal>
		</view>
	</view>
</template>

<script>
	import checkin from "@/api/checkin.js";
	import { config } from "@/utils/http.js";
	import util from "@/utils/util.js"
	const checkInApi = new checkin();
	export default {
		data() {
			return {
				typeShow: false,
				typeShow2: false,
				list: [
					{
						value: '1',
						label: '院级'
					},
					{
						value: '2',
						label: '校级'
					},
					{
						value: '3',
						label: '省级'
					},
					{
						value: '4',
						label: '国家级'
					} 
				],
				list2: [
					{
						value: '1',
						label: '思想引领'
					},
					{
						value: '2',
						label: '专业技能'
					},
					{
						value: '3',
						label: '文体活动'
					},
					{
						value: '4',
						label: '社会工作和志愿服务'
					},
					{
						value: '5',
						label: '日常表现综合评价'
					}
					,
					{
						value: '6',
						label: '劳动教育'
					},
					{
						value: '7',
						label: '扣分项目'
					}
					
					
				],
				// 表单
				form: {
					carDetailsDatas: []
				},
				
				rules: {
					reason: [
						{ 
							required: true, 
							reason: '请输入获奖原因', 
							// 可以单个或者同时写两个触发验证方式 
							trigger: ['change','blur'],
						}
					]
				},
				
				fileUploadUrl:"",
				header:{},
				fileList:[],

				// list: [{
				// 	name: '发起提交'
				// }, {
				// 	name: '查看数据'
				// }],
				// current: 0,
				// 日期
				params: {
					year: true,
					month: true,
					day: true,
					hour: false,
					minute: false,
					second: false
				},
				startTime: false,
				endTime: false,
				// 按钮样式
				customStyle: {
					margin: '20rpx', // 注意驼峰命名，并且值必须用引号包括，因为这是对象

				},
				//提交成功
				content: '提交成功',
				show: false,
				// 失败
				contentS: '提交失败',
				showS: false,
			};
		},
		methods: {
			// change(index) {
			// 	if(index == 1) {
			// 		uni.reLaunch({
			// 			url: 'record'
			// 		});
			// 	}
				
			// },
			addTab() {
				console.log(this.form.carDetailsDatas)
				this.form.carDetailsDatas.push({

				});
			},

			del(index) {
				console.log(this.index)
				this.form.carDetailsDatas.splice(index, 1)
			},
			//提交表单
			sumblie() {
				let code = 0
				checkInApi.insert(this.form).then(res => {
					console.log(res);
					code = res.code
					console.log(code);
					if(code == 200){
						uni.navigateBack({
							delta: 1 // 返回的页面数，这里设置为1表示返回上一页
						});
					}
				})
				
			},
			//点击确定后跳转页面
			que() {
				uni.navigateTo({
					url: 'record'
				});
			},

			
			showOldImg(){
				if(	this.form.cardImg != null){
					this.fileList = JSON.parse(this.form.cardImg);
				}
			},
			uploadSuccess(data, index, lists, name){
				console.log(this.fileList,"---------");
				
				let fileItems = {
					url:data.data
				}
				this.fileList.push(fileItems);
				this.form.imgList = this.fileList 
			},
			remove(data, index, lists, name){
				this.fileList.splice(index,1);
			},
			
			//开始时间回显
			sClickDate(e) {
				console.log(e)
				this.form.date = e.year + '-' + e.month + '-' + e.day;
				this.startTime = false;

			},
			//结束时间回显
			eClickDate(e) {
				this.form.createTime = e.year + '-' + e.month + '-' + e.day;
				this.endTime = false;
			},
			typeClick(e) {
				this.form.type = e[0].label
			},
			type2Click(e) {
				this.form.level = e[0].label
			},
			//开始时间回显

			//结束时间回显


			// Tab栏切换
			// change(index) {
			// 	this.current = index;
			// 	this.getDataList(index);
			// },
			// getDataList(index){
			// 	if(index == 0){
			// 		mettingApi.getNowMettingList().then(res=>{
			// 			this.dataList = res.rows;
			// 		})
			// 	}else{
			// 		mettingApi.getOldMettingList().then(res=>{
			// 			this.dataList = res.rows;
			// 		})
			// 	}
			// }

		},
		onLoad() {
			//下面这个是文件上传地址  和token  
			this.form.userId = util.getStorage("user").id;
			console.log(this.form.userId)
			this.header['Authorization'] = 	util.getStorage("token");
			this.header['userId'] = util.getStorage("userId");
			this.fileUploadUrl = config.httpUrl + "/common/upload";
		},

		onShow() {

		},
	}
</script>

<style lang="scss">
	text {
		font-size: 20rpx;
		color: #9c9fa4;
		padding: 0 20rpx;
	}

	.add {
		font-size: 30rpx;
		color: #35a3ef;
	}

	.flex {
		display: flex;
		justify-content: space-between;
		margin-top: 20rpx;
	}
</style>
