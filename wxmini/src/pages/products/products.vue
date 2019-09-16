<template>
  <div>

  <view class="section">
    <view class="product-list" style="flex-direction:row;">
      <view class="flex-item bc_green">

        <scroll-view  style="{'height': '100%'}" :scroll-y="true">
          <div :class="['type-item', {choosen:choosenType==type.id,chooseno:choosenType!=type.id}]" v-for="(type, index) in types" :key="index" @click="chooseItem(type.id)"> {{ type.name }} </div>
        </scroll-view>


      </view>
      <view class="flex-item bc_blue">3</view>
    </view>
  </view>

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
      types: [
        {
          name: '新品',
          id: 1,
        }, {
          name: '热销',
          id: 2,
        }, {
          name: '炒菜',
          id: 3,
        },{
          name: '炒菜',
          id: 3,
        },{
          name: '炒菜',
          id: 3,
        },{
          name: '炒菜',
          id: 3,
        },{
          name: '炒菜',
          id: 3,
        },{
          name: '炒菜',
          id: 3,
        },{
          name: '炒菜',
          id: 3,
        },
      ],
      choosenType: 2,
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


  },

  methods: {
    chooseItem(id) {
      this.choosenType = id
      console.log(this.choosenType)
    }
  }
}
</script>

<style>


.page-section{
  margin-bottom: 20rpx;
}
.product-list {
  display: flex;
  height: 100%;
}
.type_list {background: green;width:20%; height: 100px;}

.type-item {
  width: 100rpx;
  text-align: center;
}

.type-item.chooseno {
  color: white;
  background-color: #969696;
}

.type-item.choosen {
  color: black;
  background-color: white;
}

.type-item:after {
  width: 100rpx;
  text-align: center;
  color:black;
  background-color: white;
}


.bc_red {background: red;width:80%; height: 100px;}
.bc_blue {background: blue;width:80%; height: 100px;}
</style>
