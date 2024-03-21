<template>
	<view>
		<view class="box" >
			<view class="tet" style="margin-top: 22rpx;">
				名称：{{sum.name}}
			</view>
			<view class="tet">
				账号(学号)：{{sum.no}}
			</view>
			<view class="tet">
				电话：{{sum.phone}}
			</view>
			<view class="tet">
				身份证号：{{sum.idCard}}
			</view>
			<view class="tet">
				年龄：{{sum.age}}
			</view>
			<view class="tet">
				分数：{{sum.score}}
			</view>
			<view class="tet">
				性别：{{sum.sex == 1 ? '男' : '女'}}
			</view>
			<view class="tet">
				班级名称：{{sum.storageName}}
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				sum: {}
			}
		},
		onLoad() {
			this.sum = uni.getStorageSync('studentInfo')
			console.log(this.sum);
		},
		methods: {
			
		}
	}
</script>

<style scoped>

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
