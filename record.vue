<template>
	<view>
		<!-- <u-tabs :list="list" :is-scroll="false" :current="current" @change="change"></u-tabs> -->
		<view class="box" @click="xiangqing()">
			<view class="flex">
				<view class="userName">
					王国庆提交的用车申请
				</view>
				<view class="time">
					2022.02.02
				</view>
			</view>
			<view class="tet">
				用车时间：2022-03-08 16：08
			</view>
			<view class="tet">
				返回时间：2022-03-09 16：08
			</view>
			<view class="tet">
				往返行程：新闻大厦-消防
			</view>
			<view class="flex">
				<view class="approver">
					由王国庆提交
				</view>
				<view class="backout">
					审核通过
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				// list: [{
				// 	name: '发起提交'
				// }, {
				// 	name: '查看数据'
				// }],
				// current: 1,
			}
		},
		methods: {
			// change(index) {
			// 	if (index == 0) {
			// 		uni.reLaunch({
			// 			url: 'application'
			// 		});
			// 	}

			// },
		}
	}
</script>

<style>
	.box {
		background-color: #fefefe;
		padding: 35rpx 25rpx;
		border-bottom: 1px solid #ededed;
	}

	.userName {
		font-size: 32rpx;
		color: #272729;
		margin-bottom: 12rpx;
	}

	.time {
		font-size: 18rpx;
		color: #949494;
		line-height: 50rpx;
	}

	.tet {
		font-size: 22rpx;
		color: #949494;
		margin-bottom: 12rpx;
	}

	.approver {
		font-size: 25rpx;
		color: #292929;
	}

	.backout {
		font-size: 25rpx;
		color: #3186ac;
	}

	.flex {
		display: flex;
		justify-content: space-between;
	}

	.u-tabs-scorll-flex.data-v-3b2b1a80 {
		border-bottom: 5rpx solid #F6f6f6;
	}
</style>
