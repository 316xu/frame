<template>
<div>
  <scroll-view  :style="{'height': '300px'}" :scroll-y="true" @scrolltolower="scrolltolower" @scroll="scroll" >
  <div :style="{'height': '200px','background-color':'red'}">tyuiolpo</div>
  <div class=""  :style="{'height': '200px','background-color':'red'}">dsdsd
  </div><div class=""  :style="{'height': '200px','background-color':'red'}">grytyju</div>
</scroll-view>

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
      ]
    }
  },

  created () {
    console.log('1')
  },
  mounted() {
    console.log('1')

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
