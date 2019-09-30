<template>
  <div>

  <view class="section">
    <view class="product-list" style="flex-direction:row;">
      <view class="flex-item">
        <scroll-view style="height: 100vh;" :scroll-y="true">
          <div :class="['type-item', {choosen:choosenType==type.id,chooseno:choosenType!=type.id}]" v-for="(type, index) in types" :key="index" @click="chooseItem(type.id)"> {{ type.name }} </div>
          <!-- <div :class="['type-item']" v-for="item in goodMap" :key="item.id"> {{ item.name }} </div> -->
        </scroll-view>
      </view>
      <view class="flex-item">
        <scroll-view style="height: 100vh;" :scroll-y="true" :scroll-into-view="'id' + toGood">
          <div :id="'id' + good.id" :class="['good-item']" v-for="(good, key) in goods" :key="key">
             {{ good.name }} 
          </div>
        </scroll-view>
      </view>
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
        }
      ],
      goodMap: {},
      goods: [],
      choosenType: 0,
      toGood: '',

    }
  },

  created () {
    this.$http.get('good/list', {
      shopId: 1
    }).then(({data}) => {
      this.types=data.data.types
      this.goodMap = data.data.goods
      for (let item in this.goodMap) {
        this.goods = this.goods.concat(this.goodMap[item])
      }
      this.choosenType = this.types[0].id
    })

  },

  methods: {
    chooseItem(typeId) {
      this.choosenType = typeId
      for (let item in this.goodMap) {
        if (this.goodMap[item][0].typeId == typeId) {
          this.toGood = this.goodMap[item][0].id
        }
      }
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

.flex-item {
  height: 100%;
  overflow-y: auto;
}



.type_list {background: green;width:20%; height: 100px;}

.type-item {
  width: 150rpx;
  text-align: center;
}

.good-item {
  height: 80rpx;
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
