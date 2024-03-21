<template>
	
	<view class="">
		<view class="box" v-for="sum in list" >
			<view class="tet" style="margin-top: 22rpx;">
				获奖事由：{{sum.reason}}
			</view>
			<view class="tet">
				获奖日期：{{sum.date}}
			</view>
			<view class="tet">
				获奖类型：{{sum.type}}
			</view>
			<view class="tet">
				获奖名称：{{sum.name}}
			</view>
			<view>
				<view class="tet">
					得分：{{sum.score}}
				</view>
				<view class="tet">
					拒绝原因：{{sum.message}}
				</view>
				<!-- <u--image :showLoading="true" :src="src" width="80px" height="80px" @click="click"></u--image> -->
			</view>
			<view>
				<view class="tet">
					图片：
					<u-image v-for="item in sum.fileList"  width="100%" height="400" :src="item.url"></u-image>
				</view>
				<!-- <u--image :showLoading="true" :src="src" width="80px" height="80px" @click="click"></u--image> -->
			</view>
		</view>
	</view>
</template>
  
<script>  
	import examine from '@/api/examine.js'
	const examineApi = new examine()
  export default {  
		data() {  
			return {
				list: '',
			}
		},
		onLoad() {
			let wxUserInfo = this.util.getStorage("userInfo");
			examineApi.getNotPass(wxUserInfo.id).then(res => {
				console.log(123, res);
				this.list = res.data
			 this.list.forEach(v=>{
				 v.fileList = JSON.parse(v.img);  
			 })
			})
		}
  }  
</script>

<style lang="scss">
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
