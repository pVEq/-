<template>  
    <div>  
      <p>{{ message }}</p>  
    </div>  
</template>  
  
<script>  
  export default {  
	data() {  
		return {  
			message: '这是一段话。'  
		}  
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
