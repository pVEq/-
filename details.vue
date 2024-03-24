<template>
	<view>
		<view class="boxOne">
			<view class="userName">
				王国庆提交的请假
			</view>
			<view class="approver">
				审批通过
			</view>
		</view>
		<view class="boxTwo">
			<view class="dp">
				<view class="depart">
					所在部门
				</view>
				<view class="dts">
					实施与研发部
				</view>
			</view>
			<view class="dp">
				<view class="depart">
					用车事由
				</view>
				<view class="dts">
					消防支队
				</view>
			</view>
			<view class="dp">
				<view class="depart">
					往返行程
				</view>
				<view class="dts">
					新闻大厦-消防支队
				</view>
			</view>
			<view class="dp">
				<view class="depart">
					用车日期
				</view>
				<view class="dts">
					2022-02-22 08:08
				</view>
			</view>
			<view class="dp">
				<view class="depart">
					返回日期
				</view>
				<view class="dts">
					2022-02-22 08:08
				</view>
			</view>
			<view class="car">
				<text >车辆明细</text>
			</view>
			<view style="margin: 0 50rpx; border-bottom: 5rpx solid #f6f6f6;">
				<view class="dp">
					<view class="departOne">
						车辆类型
					</view>
					<view class="dts">
						商务
					</view>
				</view>
				<view class="dp">
					<view class="depart">
						数量
					</view>
					<view class="dts">
						1
					</view>
				</view>
				<view class="dp">
					<view class="depart">
						车牌号
					</view>
					<view class="dts">
						陕K353P0
					</view>
				</view>
			</view>
			<view class="dp">
				<view class="depart">
					备注
				</view>
				<view class="dts">
					2022-02-22 08:08
				</view>
			</view>
		</view>
		<view class="boxThree">
			<view class="left">
				<view class="flow">
						流程
					</view>
					<view class= "">
						<view class="flex">
							<view class="blk">
								发起申请
							</view>
							<view class="size">
								12.23 08:22
							</view>
						</view>
						<view class="hui">
							我
						</view>
					</view>
					<view class="">
						<view class="flex">
							<view class="blk">
								审批人
							</view>
							<view class="size">
								12.23 08:22
							</view>
						</view>
						<view class="hui">
							张欢欢（审批中）
						</view>
					</view>
				</view>
			</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #f8f8f8;
	}
	.boxOne {
		width: 100%;
		height: 180rpx ;
		background-color: #ffffff;
		padding-left: 33rpx;
		overflow: hidden;
		.userName{
			font-size: 37rpx;
			color: #212121;
			margin: 25rpx 0;
		}
		.approver{
			font-size: 25rpx;
			color: #d68f44;
			
		}
	}
	.boxTwo{
		width: 95%;
		// height: 666rpx ;  
		background-color: #ffffff;
		margin: 25rpx auto;
		border-radius: 25rpx;
		overflow: hidden;
		.depart {
			font-size: 28rpx;
			color: #717171; 
			margin-top: 40rpx;
			margin-bottom: 20rpx;
			margin-left: 25rpx;
		}
		.dts{
			font-size: 32rpx;
			color:#1d1f21 ;
			margin-bottom: 25rpx;
			margin-left: 25rpx;
		}
		
		
		
		
	}
	.boxThree {
		width: 95%;
		// height: 450rpx;
		background-color:#ffffff;
		margin: 0 auto 100rpx auto;
		padding-bottom: 20rpx;
		border-radius: 25rpx;
		.left{
			margin-left: 25rpx;
			overflow: hidden;
			.flow {
				margin-top: 20rpx;
			}
		}
		.blk{
			font-size: 32rpx;
			color:#1d1f21 ;
			margin-top: 30rpx;
			margin-bottom: 20rpx;
		}
		.hui{
			font-size: 28rpx;;
			color: #7f8180;
		}
		
	}
	.car {
		font-size: 28rpx;
		color: #717171; 
		margin: 40rpx 25rpx 20rpx 25rpx;
		padding: 10rpx 20rpx;
		background-color: #f3f3f3;
	}
	.departOne{
		font-size: 28rpx;
		color: #717171; 
		margin-top: 20rpx;
		margin-bottom: 20rpx;
		margin-left: 25rpx;
	}
	.size{
		font-size:20rpx ;
		color: #b6b6b6;
		line-height: 100rpx;
		margin-right: 25rpx;
	}
	.flex {
		display: flex;
		justify-content: space-between;
	}
</style>
