<template>
	<view>
		<view class="box" v-for="(sum, index) in list" >
			<view class="tet" style="margin-top: 22rpx;">
				事由：{{sum.reason}}
			</view>
			
			<view class="tet">
				日期：{{sum.date}}
			</view>
			
			<view class="tet">
				类型：{{sum.type}}
			</view>
			
			<view class="tet">
				名称：{{sum.name}}
			</view>
			
			<view>
				<view class="tet">
					名称：{{sum.name}}
				</view>
				<!-- <u--image :showLoading="true" :src="src" width="80px" height="80px" @click="click"></u--image> -->
			</view>
			<view class="tet" style="display: flex;">
				原因：<u-input v-model="sum.message" type="text" border="none" />
			</view>
			 
			<view>
				<view class="tet">
					图片：
					<u-image v-for="item in sum.fileList"  width="100%" height="400" :src="item.url"></u-image>
				</view>
				<!-- <u--image :showLoading="true" :src="src" width="80px" height="80px" @click="click"></u--image> -->
			</view>
			<view class="flex">
				<u-button class="btn" type="primary" size="medium" plain=true @click="getCheck(sum.id, index)">拒绝</u-button>
				<u-button type="primary" size="medium" @click="DK(sum.id, index)">同意</u-button>
			</view>
		</view>
		<!-- 数据为空 -->
	<!-- 	<u-empty class="fixed" text="消息列表为空" mode="list" v-show="emp"></u-empty> -->
	</view>
</template>

<script>
	import checkin from "@/api/checkin.js";
	import util from "@/utils/util.js"
	const checkInApi = new checkin();
	export default {
		data() {
			return {
				src:"",
				list: [],
				message: '',
				act: {
					id: '',
					status: '1',
				},
				jj: {
					id: '',
					status: '2',
				},
				fileList:[],
				emp:false,
				user:{
					
				},
			}
		},
		methods: {
			
			//查询待处理时间事件详情
			getCheckIn(roleId) {
				checkInApi.examineList(roleId).then(res => {
				   this.list = res.data;
				   this.list.forEach(v=>{
					   v.fileList = JSON.parse(v.img);  
				   })
				})
			},
			//审批拒绝
			getCheck(id, index) {
				if (this.list[index].message == '') {
					return uni.showToast({
						title: '请填写原因',
						icon: 'error'
					})
				}
				this.jj.id = id;
				this.jj.message = this.list[index].message
				checkInApi.check(this.jj).then(res => {
					// this.$router.go(0)
					uni.redirectTo({
					    url: '/pages/Asking/approval' //写你的路径
					});
				})
			},
			//同意
			DK(id, index) {
				this.act.id = id;
				this.act.message = this.list[index].message
				checkInApi.check(this.act).then(res => {
					// this.$router.go(0)
					uni.redirectTo({
					    url: '/pages/Asking/approval' //写你的路径
					});
				})
			},
			

		},
		onLoad() {
			let roleId = util.getStorage("user").roleId
			// this.act.roleId = roleId
			this.getCheckIn(roleId);
		},
	}
</script>

<style>
	page {
		background-color: #f8f8f8;
	}

	.box {
		width: 80%;
		
		margin: 25rpx auto;
		border-radius: 50rpx;
		background-color: #fefefe;
		padding: 0 50rpx;
		overflow: hidden;
	}

	.userName {
		font-size: 32rpx;
		color: #272729;
		margin-top: 20rpx;
	}

	.tet {
		font-size: 22rpx;
		color: #949494;
		margin-bottom: 12rpx;
	}

	.approver {
		font-size: 25rpx;
		color: #d68f44;
	}

	.backout {
		font-size: 30rpx;
		color: #3186ac;
		padding-right: 50rpx;
	}

	.flex {
		display: flex;
		justify-content: space-around;
	}
	.fixed{
		position: fixed;
		left: 40%;
		top: 40%;
		 
	}
</style>
