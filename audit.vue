<template>
	<view class="content">
		<view class="content_title">
			<view style="">{{typeName}}</view>
			<view style="margin-top: 30rpx; color: #606266; font-size: 30rpx;">{{getStatus(data.status)}}</view>
		</view>
		<view class="content_item">
			<view class="bodyItem" v-for="(v,k,index) in data.dataItem" :key="index">
				<view class="title">{{k}}</view>
				<view class="value">{{v}}</view>
			</view>
		</view>
		<view class="content_item">
			<view style="margin-bottom: 20rpx; font-size: 35rpx;">流程</view>
			<view v-for="(item,index) in data.actLogList">
				<view class="content_item_fg" v-if="index != 0"></view>
				<view class="lc_item">
					<view class="lc_item_img">
						{{item.nickName}}
					</view>
					<view class="lc_item_body">
						<view class="lc_item_body_a" v-if="item.flowName==null">创建</view>
						<view class="lc_item_body_a" v-if="item.flowName!=null">{{item.flowName}}</view>
						<view class="lc_item_body_b">{{item.nickName}} ({{getOperation(item.status)}})</view>
					</view>
					<view class="lc_time">
						{{item.createTime}}
					</view>
				</view>
			</view>
			<view style="height: 20rpx;"></view>
		</view>
		<view class="bt">
			<u-button style="width: 250rpx;"  type="primary" @click="agree">通过</u-button>
			<u-button style="width: 250rpx;" type="error" @click="agreedis">不通过</u-button>
		</view>
		<u-toast ref="uToast" />
	</view>
</template>

<script>
	import act from '@/api/act.js'
	const actApi = new act();
	export default{
		data(){
			return{
				data:{},
				typeName:""
			}
		},
		methods:{
			getStatus(status){
				return actApi.getStatus(status);
			},
			getOperation(status){
				return actApi.getOperation(status);
			},
			agree(){
				actApi.agreeAct(this.data.lid,this.type).then(res=>{
					this.$refs.uToast.show({
						title: '审核成功',
						type: 'success',
						isTab:true,
						url: '/pages/message/index'
					})
				});
			},
			agreedis(){
				actApi.agreedisAct(this.data.lid,this.type).then(res=>{
					this.$refs.uToast.show({
						title: '审核成功',
						type: 'success',
						isTab:true,
						url: '/pages/message/index'
					})
				});
			}
		},
		onLoad(option) {
			this.typeName = option.typeName;
			this.type = option.type;
			actApi.getActInfo(option.id,option.type).then(res=>{
				this.data = res.data;
			})
		}
	}
</script>

<style lang="scss">
	page{
		background-color: #f4f3f8;
	}
	.content_title{
		font-size: 35rpx;
		padding: 30rpx;
		background-color: #FFFFFF;
		width: 100%;
	}
	.content{
		font-size: 30rpx;
		letter-spacing: 1rpx;
		.content_item{
			background-color: #FFFFFF;
			width: 650rpx;
			border-radius: 25rpx;
			margin: 0 auto;
			margin-top: 20rpx;
			padding: 30rpx;

			.bodyItem{
				margin-top: 20rpx;
				.title{
					color: #767E81;
				}
				.value{
					
				}
			}
			
			.lc_item{
				display: flex;
				width: 100%;
				.lc_item_img{
					width: 80rpx;
					height: 80rpx;
					background-color: #007AFF;
					text-align: center;
					line-height: 80rpx;
					border-radius: 10rpx;
					color: #FFFFFF;	
				}
				
				.lc_item_body{
					margin-left: 25rpx;
					.lc_item_body_b{
						margin-top: 3rpx;
						color:#767E81;
						width: 250rpx;
					}
				}
				
				.lc_time{
					letter-spacing: 0rpx;
					text-align: right;
					width: 250rpx;
					color: #C0C0C0;
				}
			}
		}
	}
	.content_item_fg{
		width: 10rpx;
		height: 60rpx;
		background-color: #E4E6E5;
		margin-left: 35rpx;
		margin-top: 10rpx;
		margin-bottom: 10rpx;
	}
	.bt{
		display: flex;
		margin-top: 50rpx;
		justify-content: space-around;
		margin-bottom: 50rpx;
	}
</style>
