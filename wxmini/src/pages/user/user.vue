<template>
  <div>

    <open-data type="userAvatarUrl"></open-data>
    <open-data type="userNickName"></open-data>
    <!-- 需要使用 button 来授权登录 -->
    <button v-if="!canIUse" open-type="getUserInfo">获取权限</button>
  </div>
</template>

<script>
import mpButton from 'mpvue-weui/src/button'
import { formatTime } from '@/utils/index'
import card from '@/components/card'

export default {
  components: {
    card,
    mpButton
  },

  data () {
    return {
      logs: [],
      imgUrls: [
        'http://mss.sankuai.com/v1/mss_51a7233366a4427fa6132a6ce72dbe54/newsPicture/05558951-de60-49fb-b674-dd906c8897a6',
        'http://mss.sankuai.com/v1/mss_51a7233366a4427fa6132a6ce72dbe54/coursePicture/0fbcfdf7-0040-4692-8f84-78bb21f3395d',
        'http://mss.sankuai.com/v1/mss_51a7233366a4427fa6132a6ce72dbe54/management-school-picture/7683b32e-4e44-4b2f-9c03-c21f34320870'
      ],
      code: '',
      canIUse: wx.canIUse('button.open-type.getUserInfo')
    }
  },

  created () {

    console.log(this.canIUse)
    const self = this;
    wx.login({
      success (res) {
          if (res.code){
              // self.code = res.code;
              // self.wxGetUserInfo(res.code);
          }
      },
    })
  },

  methods: {

  }
}
</script>

<style>
.log-list {
  display: flex;
  flex-direction: column;
  padding: 40rpx;
}

.log-item {
  margin: 10rpx;
}
</style>
