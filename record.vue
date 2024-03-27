<template>
  <view class="container">
    <div class="messages">
      <p v-for="(message, index) in messages" :key="index">{{ message }}</p>
    </div>
    <u-tabbar v-model="current" :list="list" :before-switch="beforeSwitch"></u-tabbar>
  </view>
</template>

<script>
import checkin from "@/api/checkin.js";
const checkInApi = new checkin();
export default {
  data() {
    return {
      messages: [
        '院级：',
        '思想引领 + 1',
        '1.学期内马原/习近平新时代思想/毛概/思修得分85以上',
        '专业技能 + 1',
        '文体活动 + 1',
        '社会工作和志愿服务 + 1',
        '创新能力 + 1',
        '日常表现综合评价 + 1',
        '劳动教育 + 1',
        '扣分项目 - 1',
        '校级：',
        '思想引领 + 2',
        '专业技能 + 2',
        '文体活动 + 2',
        '社会工作和志愿服务 + 2',
        '创新能力 + 2',
        '日常表现综合评价 + 2',
        '劳动教育 + 2',
        '扣分项目 - 2',
        '省级：',
        '思想引领 + 3',
        '专业技能 + 3',
        '文体活动 + 3',
        '社会工作和志愿服务 + 3',
        '创新能力 + 3',
        '日常表现综合评价 + 3',
        '劳动教育 + 3',
        '扣分项目 - 3',
        '国家级：',
        '思想引领 + 4',
        '专业技能 + 4',
        '文体活动 + 4',
        '社会工作和志愿服务 + 4',
        '创新能力 + 4',
        '日常表现综合评价 + 4',
        '劳动教育 + 4',
        '扣分项目 - 4'
      ],
      // tab
      list: [
        { iconPath: "home", selectedIconPath: "home-fill", text: '计算方式', customIcon: false },
        { iconPath: "photo", selectedIconPath: "photo-fill", text: '得分规则', customIcon: false }
      ],
      current: 1,
      recordList: [],
      emp: false
    };
  },
  methods: {
    beforeSwitch(index) {
      if (index === 0) {
        uni.reLaunch({ url: 'index' });
      }
    },
    async record() {
      try {
        const res = await checkInApi.workflow();
        if (res.code === 200) {
          this.recordList = res.data;
        } else if (res.data === []) {
          this.emp = true;
        } else {
          console.log('网络连接失败');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    }
  },
  onShow() {
    this.record();
  }
};
</script>

<style scoped>
.container {
  background-color: #f8f8f8;
}

.messages {
  background-color: #fefefe;
  padding: 35rpx 25rpx;
  border-bottom: 1px solid #ededed;
}

.messages p {
  font-size: 22rpx;
  color: #272729;
  margin-bottom: 12rpx;
}

.u-tabbar {
  position: fixed;
  bottom: 0;
  width: 100%;
}
</style>
