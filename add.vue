<template>
	<view class="content">
		<u-form :model="form" ref="uForm" :label-width="150">
			<view class="item">
				<u-form-item :required="true"   prop="startLeaveTime" label="开始时间 :">
					<u-input v-model="form.startTime" @click="beginTimeShow=true" type="select" />
					<u-picker  @confirm="startTimeSelect" :params="params" v-model="beginTimeShow" mode="time"></u-picker>
				</u-form-item>
			</view>
			<view class="item">
				<u-form-item :required="true"   prop="endLeaveTime" label="结束时间 :">
					<u-input v-model="form.endTime" @click="endTimeShow=true" type="select"/>
					<u-picker @confirm="endTimeSelect"  :params="params" v-model="endTimeShow" mode="time"></u-picker>
				</u-form-item>
			</view>
			<view class="item">
				<u-form-item :required="true"  prop="address" label="地址">
					<u-input v-model="form.address" />				
				</u-form-item>
			</view>
			<view class="item">
				<u-form-item :required="true"  prop="description" label="描述">
					<u-input v-model="form.description" />				
				</u-form-item>
			</view>
		</u-form>
		
		<u-button type="primary" class="submitBt" @click="submit">提交</u-button>
		
		<u-toast ref="uToast" />
	</view>
</template>

<script>
	import dict from"@/api/dict.js"
	import out from "@/api/out.js"
	const dictApi = new dict();
	const outApi = new out();
	export default {
		data() {
			return {
				params :{
					year: true,
					month: true,
					day: true,
					hour: true,
				},
				form:{
				},
				typeShow:false,
				beginTimeShow:false,
				endTimeShow:false,
				typeList:[
				],
				rules: {
					startTime: [
						{ 
							required: true, 
							message: '请输入开始时间'
							// 可以单个或者同时写两个触发验证方式 
						}
					],
					endTime: [
						{ 
							required: true, 
							message: '请输入结束时间'
							// 可以单个或者同时写两个触发验证方式 
						}
					],
					address: [
						{ 
							required: true, 
							message: '请输入地址'
						}
					],
					description: [
						{ 
							required: true, 
							message: '请输入事由'
						}
					],
				}
			}
		},
		onLoad() {
		},
		methods:{
			startTimeSelect(res){
				console.log(res);
				this.form.startTime = res.year +"-"+res.month+"-"+res.day+" "+res.hour+":00:00";
			},
			endTimeSelect(res){
				this.form.endTime= res.year +"-"+res.month+"-"+res.day+" "+res.hour+":00:00";
			},
			submit(){
				outApi.addOut(this.form).then(res=>{
					this.$refs.uToast.show({
						title: '发起成功',
						type: 'success',
						isTab:true,
						url: '/pages/message/index'
					})
				})
			}
		},
		onReady() {
			this.$refs.uForm.setRules(this.rules);
		}
	}
</script>

<style lang="scss">
	page{
		background-color: #f4f3f8;
	}
	.content{
		padding: 30rpx;
		font-size: 28rpx;
		letter-spacing: 3rpx;
	}
	.item{
		border-radius: 20rpx;
		background-color: #FFFFFF;
		padding-top: 10rpx;
		padding-bottom: 10rpx;
		padding-left: 40rpx;
		margin-top: 30rpx;
	}
	.submitBt{
		position: fixed;
		bottom: 100rpx;
		width: 700rpx;
	}
</style>
