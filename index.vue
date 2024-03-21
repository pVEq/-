<template>  
    <view>
		<div>
		  <p>{{ message }}</p>  
		  <p>{{ message1 }}</p> 
		  <p>{{ message2 }}</p> 
		  <p>{{ message3}}</p> 
		  <p>{{ message4 }}</p> 
		  <p>{{ message5 }}</p> 
		  <p>{{ message6 }}</p> 
		  <p>{{ message7 }}</p> 
		  <p>{{ message8 }}</p>
		</div>  
		<u-tabbar v-model="current" :list="list" :before-switch="beforeSwitch"></u-tabbar>
	</view>
    
</template>  
  
<script>  
  export default {  
	data() {  
		return { 
			message: '总分=总体基础分：0' ,
			message1: '+ 思想引领 x100.00%' ,
			message2: '+ 专业技能 x100.00%' ,
			message3: '+ 文体活动 x100.00%' ,
			message4: '+ 社会工作和志愿服务 x100.00%' ,
			message5: '+ 创新能力 x100.00%' ,
			message6: '+ 日常表现综合评价 x100.00%' ,
			message7: '+ 劳动教育 x100.00%' ,
			message8: '+ 扣分项目(负) x100.00%' ,
			list: [{
					iconPath: "home",
					selectedIconPath: "home-fill",
					text: '计算方式',
					customIcon: false,
				},
				{
					iconPath: "photo",
					selectedIconPath: "photo-fill",
					text: '得分规则',
					customIcon: false,
				},
			],
			
		}
		  
	},
	methods: {
		beforeSwitch(index) {
			if (index == 1) {
				uni.reLaunch({
					url: 'record'
				});
			}
		},
		record() {
			checkInApi.workflow().then(res => {
				if(res.code == 200) {
					this.recordList = res.data;
					console.log(this.recordList)
				}else if(res.data = []) {
					this.emp = true;
				}else {
					console.log('网络连接失败')
				}
				
			})
		},
	},
	onShow(){
		this.record()
	}
	
  }  
</script>

<style lang="scss">
	page {
		background-color: #f8f8f8;
	}

	.bgc {
		background-color: #fff;
		padding: 0 20rpx;
		margin: 20rpx 0;
	}

	.content {
		width: 100%;
		margin: 0 auto;
	}

	.textStyle {
		font-size: 32rpx;
		margin: 20rpx 0;
	}

	textarea {
		cursor: auto;
		width: 700rpx;
		height: 280rpx;
		display: block;
		position: relative;
		overflow: hidden;
	}

	button {
		margin: 20rpx;
	}
</style>
