import Vue from 'vue'
import App from './App'
import 'mpvue-weui/src/style/weui.css'

var Fly = require('flyio/dist/npm/wx')
var fly = new Fly()
fly.config.baseURL = 'http://localhost:8082'

Vue.prototype.$http = fly

Vue.config.productionTip = false
App.mpType = 'app'

const app = new Vue(App)
app.$mount()
