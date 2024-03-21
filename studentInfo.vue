<template>
	<view>
		<!-- <button bindtap="mySort" data-property="sum.no">根据学号排序</button> -->
		<!-- <button bindtap="mySort" data-property="sum.name">根据名字排序</button> -->
    <button @tap="mySort('no')">根据学号排序</button>
    <button @tap="mySort('name')">根据名字排序</button>
		
		<view class="box" v-for="(sum, index) in list" @click="goDetail(index)" >
			<view>
				<view style="margin-top: 22rpx;">
					第{{sum.sort}}
				</view>
			</view>
			
			<view>
				<view  style="margin-top: 22rpx;">
					姓名：{{sum.name}}
				</view>
				
				<view class="tet">
					学号：{{sum.no}}
				</view>
			</view>
			
			<view>
				<view class="tet" style="margin-top: 22rpx;">
					分数：{{sum.score}}
					
					级别：{{sum.performance}}
				</view>
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
				act: {
					id: '',
					status: '1',
					roleId:'',
				},
				jj: {
					id: '',
					status: '2',
					roleId:'',
				},
				fileList:[],
				emp:false,
				user:{
					
				},
			}
		},
		methods: {
			goDetail(index) {
				uni.setStorageSync('studentInfo', this.list[index])
				console.log(index,' index');
				uni.navigateTo({
					url: '/pages/Asking/studentDetail'
				})
			},
			
			//查询待处理时间事件详情
			getStudentInfo(storageId) {
				checkInApi.getStudent(storageId).then(res => {
					console.log(res, 'rrrrr');
				   this.list = res.data;
				   // this.list.forEach(v=>{
					  //  v.fileList = JSON.parse(v.img);  
				   // })
				})
			},
			mySort(property) {
        this.list.sort((a,b) => {
          if(typeof a[property] === 'string') {
            return a[property].localeCompare(b[property]);
          } else {
            return a[property] - b[property]
          }
        })
      }

		},
		onLoad() {
			let storageId = util.getStorage("user").storageId
			this.getStudentInfo(storageId);
		},
	}
</script>

<style>
	page {
		background-color: #f8f8f8;
	}

	.box {
		width: 80%;
		display: flex;
		margin: 25rpx auto;
		border-radius: 50rpx;
		background-color: #fefefe;
		padding: 0 50rpx;
		overflow: hidden;
		justify-content: space-between;
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
